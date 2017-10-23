from datetime import date

from db.models.account import StudentModel
from db.mongo import *


class BugReportModel(Document):
    author = ReferenceField(StudentModel)
    title = StringField(required=True)
    content = StringField(required=True)

    report_date = StringField(required=True, default=str(date.today()))
