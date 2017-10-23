from datetime import date

from db.mongo import *


class BugReportModel(Document):
    title = StringField(required=True)
    content = StringField(required=True)

    report_date = StringField(required=True, default=str(date.today()))
