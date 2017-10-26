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
    afterschool_apply = EmbeddedDocumentField(AfterSchoolApplyModel)
    extension_apply = EmbeddedDocumentField(ExtensionApplyModel)
    goingout_apply = EmbeddedDocumentField(GoingoutApplyModel, default=GoingoutApplyModel())
    # Default sat=False, sun=False
    stay_apply = EmbeddedDocumentField(StayApplyModel, default=StayApplyModel())
    # Default value=4


class AdminModel(AccountBase):
    pass
