from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from db.models.account import StudentModel
from db.models.survey import AnswerModel, QuestionModel, SurveyModel
from routes.api.student.survey import survey_doc


class SurveyList(Resource):
    uri = '/survey'

    @swagger.doc(survey_doc.SURVEY_LIST_GET)
    @jwt_required
    def get(self):
        """
        설문조사 리스트 조회
        """
        student_number = StudentModel.objects(id=get_jwt_identity()).first().number

        return [{
            'id': str(survey.id),
            'title': survey.title,
            'start_date': survey.start_date,
            'end_date': survey.end_date
        } for survey in SurveyModel.objects if str(student_number)[0] in survey.target], 200
        # Filter by student number


class Survey(Resource):
    uri = '/survey/<id>'

    @swagger.doc(survey_doc.SURVEY_GET)
    @jwt_required
    def get(self, id):
        """
        설문조사 내용 조회
        """
        questions = [{
            'id': str(question.id),
            'title': question.title,
            'is_objective': question.is_objective,
            'choice_paper': question.choice_paper
        } for question in QuestionModel.objects(survey_id=id)]
        # Question data

        answer_student = StudentModel.objects(id=get_jwt_identity()).first()

        for question in questions:
            answer = AnswerModel.objects(answer_student=answer_student, question=QuestionModel.objects(id=question['id']).first()).first()
            question['answer'] = answer.answer if answer else None

        return questions, 200

    @swagger.doc(survey_doc.SURVEY_POST)
    @jwt_required
    def post(self, id):
        """
        설문조사 답변 업로드
        """
        answer = request.form.get('answer')

        answer_student = StudentModel.objects(id=get_jwt_identity()).first()
        question = QuestionModel.objects(id=id).first()

        AnswerModel.objects(answer_student=answer_student, question=question).delete()
        AnswerModel(answer_student=answer_student, question=question, answer=answer).save()

        return Response('', 201)
