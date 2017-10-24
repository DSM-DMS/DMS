import json

from flask_restful_swagger_2 import Resource, request, swagger
from flask_jwt import current_identity, jwt_required

from db.models.account import AdminModel
from db.models.survey import QuestionModel, SurveyModel

from . import survey_doc


class Survey(Resource):
    @swagger.doc(survey_doc.ADD_SURVEY)
    @jwt_required()
    def post(self):
        if not AdminModel.objects(id=current_identity):
            # Forbidden
            return '', 403
        else:
            title = request.form.get('title')
            start_date = request.form.get('start_date')
            end_date = request.form.get('end_date')
            target = request.form.get('target', type=list)

            SurveyModel(
                title=title,
                start_date=start_date,
                end_date=end_date,
                target=target
            ).save()

            return '', 201


class AddQuestion(Resource):
    @swagger.doc(survey_doc.ADD_QUESTION)
    @jwt_required()
    def post(self):
        if not AdminModel.objects(id=current_identity):
            # Forbidden
            return '', 403
        else:
            id = request.form.get('id')
            title = request.form.get('title')
            is_objective = request.form.get('is_objective')

            survey = SurveyModel.objects(id=id).first()
            question = None

            if is_objective:
                global question

                choice_paper = json.loads(request.form.get('choice_paper'))
                question = QuestionModel(title=title, is_objective=True, choice_paper=choice_paper)
            else:
                global question

                question = QuestionModel(title=title, is_objective=False)

            survey.questions.append(question)
            survey.update(questions=survey.questions)

            return '', 201
