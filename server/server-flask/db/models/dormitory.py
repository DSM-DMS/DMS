from db.models.account import StudentModel
from db.mongo import db


class ReportModel(db.Document):
    author = db.ReferenceField(StudentModel, required=True)
    title = db.StringField(required=True)
    room = db.IntField(required=True)
    content = db.StringField(required=True)
