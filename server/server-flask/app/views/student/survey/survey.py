import json

from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful import Resource, request
from flasgger import swag_from

from app.docs.student.survey.survey import *
from app.models.account import StudentModel
from app.models.survey import AnswerModel, QuestionModel, SurveyModel


class SurveyList(Resource):
    uri = '/survey'

    @swag_from(SURVEY_LIST_GET)
    @jwt_required
    def get(self):
        """
        설문조사 리스트 조회
        """
        student = StudentModel.objects(id=get_jwt_identity()).first()
        if not student:
            return Response('', 403)

        student_number = student.number

        return Response(json.dumps([{
            'id': str(survey.id),
            'title': survey.title,
            'start_date': survey.start_date,
            'end_date': survey.end_date
        } for survey in SurveyModel.objects if str(student_number)[0] in survey.target], ensure_ascii=False), 200, content_type='application/json; charset=utf8')
        # Filter by student number


class Survey(Resource):
    uri = '/survey/<id>'

    @swag_from(SURVEY_GET)
    @jwt_required
    def get(self, id):
        """
        설문조사 내용 조회
        """
        student = StudentModel.objects(id=get_jwt_identity()).first()
        if not student:
            return Response('', 403)

        questions = [{
            'id': str(question.id),
            'title': question.title,
            'is_objective': question.is_objective,
            'choice_paper': question.choice_paper
        } for question in QuestionModel.objects(survey_id=id)]
        # Question data

        for question in questions:
            answer = AnswerModel.objects(answer_student=student, question=QuestionModel.objects(id=question['id']).first()).first()
            question['answer'] = answer.answer if answer else None

        return Response(json.dumps(questions, ensure_ascii=False), 200, content_type='application/json; charset=utf8')

    @swag_from(SURVEY_POST)
    @jwt_required
    def post(self, id):
        """
        설문조사 답변 업로드
        """
        student = StudentModel.objects(id=get_jwt_identity()).first()
        if not student:
            return Response('', 403)

        answer = request.form['answer']

        question = QuestionModel.objects(id=id).first()

        AnswerModel.objects(answer_student=student, question=question).delete()
        AnswerModel(answer_student=student, question=question, answer=answer).save()

        return Response('', 201)
