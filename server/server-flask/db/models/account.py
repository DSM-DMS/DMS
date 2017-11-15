from datetime import date

from db.models.apply import AfterSchoolApplyModel, ExtensionApplyModel, GoingoutApplyModel, StayApplyModel
from db.mongo import db


class SignupRequiredModel(db.Document):
    uuid = db.StringField(primary_key=True)
    name = db.StringField(required=True)
    number = db.IntField(required=True)


class AccountBase(db.Document):
    id = db.StringField(primary_key=True)
    pw = db.StringField(required=True)
    name = db.StringField(required=True)
    signup_date = db.StringField(required=True, default=str(date.today()))

    meta = {'allow_inheritance': True}


class StudentModel(AccountBase):
    number = db.IntField(required=True)
    afterschool_apply = db.EmbeddedDocumentField(AfterSchoolApplyModel)
    extension_apply_11 = db.EmbeddedDocumentField(ExtensionApplyModel)
    extension_apply_12 = db.EmbeddedDocumentField(ExtensionApplyModel)
    goingout_apply = db.EmbeddedDocumentField(GoingoutApplyModel, default=GoingoutApplyModel())
    # Default sat=False, sun=False
    stay_apply = db.EmbeddedDocumentField(StayApplyModel, default=StayApplyModel())
    # Default value=4


class AdminModel(AccountBase):
    pass
