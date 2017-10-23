from datetime import date

from db.mongo import *


class QuestionModel(EmbeddedDocument):
    title = StringField(required=True)
    is_objective = BooleanField(required=True)
    question = StringField()
    choice_paper = ListField()


class SurveyModel(Document):
    title = StringField(required=True)
    start_date = StringField(required=True)
    end_date = StringField(required=True)
    questions = ListField(EmbeddedDocumentField(QuestionModel))

    creation_date = StringField(required=True, default=str(date.today()))
