from datetime import date

from db.mongo import *


class ApplyBase(EmbeddedDocument):
    apply_date = StringField(required=True, default=str(date.today()))
    meta = {'allow_inheritance': True}


class AfterSchoolApplyModel(ApplyBase):
    applied_ids = ListField(StringField(required=True))


class ExtensionApplyModel(ApplyBase):
    cls = IntField(required=True)
    seat = IntField(required=True)


class GoingoutApplyModel(ApplyBase):
    on_saturday = BooleanField(required=True, default=False)
    on_sunday = BooleanField(required=True, default=False)


class StayApplyModel(ApplyBase):
    value = IntField(required=True)
