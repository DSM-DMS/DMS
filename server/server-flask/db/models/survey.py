from datetime import date

from db.models.account import StudentModel
from db.mongo import *


class QuestionModel(Document):
    survey_id = StringField(required=True)
    title = StringField(required=True)
    is_objective = BooleanField(required=True)
    choice_paper = ListField()


class SurveyModel(Document):
    title = StringField(required=True)
    start_date = StringField(required=True)
    end_date = StringField(required=True)
    target = ListField(IntField())

    creation_date = StringField(required=True, default=str(date.today()))


class AnswerModel(Document):
    answer_student = ReferenceField(StudentModel)
    question = ReferenceField(QuestionModel)
    answer = StringField()
