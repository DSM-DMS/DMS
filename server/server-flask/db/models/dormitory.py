from db.models.account import StudentModel
from db.mongo import *


class ReportModel(Document):
    author = ReferenceField(StudentModel, required=True)
    title = StringField(required=True)
    room = IntField(required=True)
    content = StringField(required=True)
