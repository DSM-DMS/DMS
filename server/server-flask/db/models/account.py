from datetime import date

from db.mongo import *


class SignupRequiredModel(Document):
    uid = StringField(primary_key=True)
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


class AdminModel(AccountBase):
    pass
