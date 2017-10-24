from flask_restful_swagger_2 import Resource, request, swagger
from flask_jwt import current_identity, jwt_required

from db.models.account import StudentModel
from db.models.survey import QuestionModel, SurveyModel, AnswerModel

from . import survey_doc


class SurveyList(Resource):
    @swagger.doc(survey_doc.SURVEY_LIST_GET)
    @jwt_required()
    def get(self):
        student_number = StudentModel.objects(id=current_identity).first().number

        return [{
            'id': str(survey.id),
            'title': survey.title,
            'start_date': survey.start_date,
            'end_date': survey.end_date
        } for survey in SurveyModel.objects if str(student_number)[0] in survey.target], 200
        # Filter by student number


class Survey(Resource):
    @swagger.doc(survey_doc.SURVEY_GET)
    @jwt_required()
    def get(self):
        id = request.args.get('id')

        questions = [{
            'id': str(question.id),
            'title': question.title,
            'is_objective': question.is_objective,
            'choice_paper': question.choice_paper
        } for question in SurveyModel.objects(id=id).first().questions]
        # Question data

        for question in questions:
            answer = AnswerModel.objects(answer_student=StudentModel(id=current_identity), id=question['id']).first()
            question['answer'] = answer.answer if answer else None

        return questions, 200

    @swagger.doc(survey_doc.SURVEY_POST)
    @jwt_required()
    def post(self):
        id = request.form.get('id')
        answer = request.form.get('answer')

        AnswerModel.objects(answer_student=StudentModel(id=current_identity), question=QuestionModel(id=id)).delete()
        AnswerModel(answer_student=StudentModel(id=current_identity), question=QuestionModel(id=id), answer=answer).save()

        return '', 201
