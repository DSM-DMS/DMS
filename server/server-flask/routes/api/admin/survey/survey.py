import json

from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from db.models.account import AdminModel
from db.models.survey import QuestionModel, SurveyModel
from routes.api.admin.survey import survey_doc


class Survey(Resource):
    uri = '/survey'

    @swagger.doc(survey_doc.SURVEY_POST)
    @jwt_required
    def post(self):
        """
        설문조사 set 등록
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

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

        return Response('', 201)


class Question(Resource):
    uri = '/survey/question'

    @swagger.doc(survey_doc.QUESTION_POST)
    @jwt_required
    def post(self):
        """
        설문조사에 질문 등록
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        id = request.form.get('id')
        title = request.form.get('title')
        is_objective = request.form.get('is_objective')

        if is_objective:
            choice_paper = json.loads(request.form.get('choice_paper'))
            QuestionModel(survey_id=id, title=title, is_objective=True, choice_paper=choice_paper).save()
        else:
            QuestionModel(survey_id=id, title=title, is_objective=False).save()

        return Response('', 201)
