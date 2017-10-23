from datetime import date

from db.models.apply import AfterSchoolApplyModel, ExtensionApplyModel, GoingoutApplyModel, StayApplyModel
from db.mongo import *


class SignupRequiredModel(Document):
    uuid = StringField(primary_key=True)
    name = StringField(required=True)
    number = IntField(required=True)


class AccountBase(Document):
    id = StringField(primary_key=True)
    pw = StringField(required=True)
    name = StringField(required=True)

    signup_date = StringField(required=True, default=str(date.today()))
    meta = {'allow_inheritance': True}


class StudentModel(AccountBase):
    number = IntField(required=True)
    afterschool_applies = EmbeddedDocumentField(AfterSchoolApplyModel)
    extension_applies = EmbeddedDocumentField(ExtensionApplyModel)
    goingout_applies = EmbeddedDocumentField(GoingoutApplyModel)
    stay_applies = EmbeddedDocumentField(StayApplyModel)


class AdminModel(AccountBase):
    pass
