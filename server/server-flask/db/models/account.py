from db.mongo import *


class SignupRequiredModel(Document):
    uid = StringField(primary_key=True)
    name = StringField(required=True)
    number = IntField(required=True)


class AccountBase(Document):
    id = StringField(primary_key=True)
    pw = StringField(required=True)
    name = StringField(required=True)
    
    meta = {'allow_inheritance': True}


class StudentModel(AccountBase):
    number = IntField(required=True)


class AdminModel(AccountBase):
    pass
