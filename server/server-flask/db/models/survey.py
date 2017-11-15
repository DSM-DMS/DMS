from datetime import date

from db.models.account import StudentModel
from db.mongo import db


class QuestionModel(db.Document):
    survey_id = db.StringField(required=True)
    title = db.StringField(required=True)
    is_objective = db.BooleanField(required=True)
    choice_paper = db.ListField()


class SurveyModel(db.Document):
    title = db.StringField(required=True)
    start_date = db.StringField(required=True)
    end_date = db.StringField(required=True)
    target = db.ListField(db.IntField())

    creation_date = db.StringField(required=True, default=str(date.today()))


class AnswerModel(db.Document):
    answer_student = db.ReferenceField(StudentModel)
    question = db.ReferenceField(QuestionModel)
    answer = db.StringField()
