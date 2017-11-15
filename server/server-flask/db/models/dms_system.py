from datetime import date

from db.models.account import StudentModel
from db.mongo import db


class BugReportModel(db.Document):
    author = db.ReferenceField(StudentModel)
    title = db.StringField(required=True)
    content = db.StringField(required=True)

    report_date = db.StringField(required=True, default=str(date.today()))
