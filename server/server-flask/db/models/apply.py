from datetime import date

from db.mongo import *


class ApplyBase(EmbeddedDocument):
    apply_date = StringField(required=True, default=str(date.today()))
    meta = {'allow_inheritance': True}


class AfterSchoolModel(ApplyBase):
    no = IntField(required=True)


class ExtensionModel(ApplyBase):
    cls = IntField(required=True)
    seat = IntField(required=True)


class StayModel(ApplyBase):
    value = IntField(required=True)
