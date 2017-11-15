from datetime import date

from db.mongo import db


class ApplyBase(db.EmbeddedDocument):
    apply_date = db.StringField(required=True, default=str(date.today()))
    meta = {'allow_inheritance': True}


class AfterSchoolApplyModel(ApplyBase):
    applied = db.ListField(db.StringField())
    # ReferenceField에서 EmbeddedDocument인 AfterSchoolItemModel 참조 불가


class ExtensionApplyModel(ApplyBase):
    class_ = db.IntField(required=True)
    seat = db.IntField(required=True)


class GoingoutApplyModel(ApplyBase):
    on_saturday = db.BooleanField(required=True, default=False)
    on_sunday = db.BooleanField(required=True, default=False)


class StayApplyModel(ApplyBase):
    value = db.IntField(required=True, default=4)
