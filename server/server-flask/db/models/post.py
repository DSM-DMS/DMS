from datetime import date

from db.models.account import AdminModel
from db.mongo import db


class PostBase(db.Document):
    title = db.StringField(required=True)
    content = db.StringField(required=True)
    author = db.ReferenceField(AdminModel, required=True)
    pinned = db.BooleanField(required=True, default=False)

    write_date = db.StringField(required=True, default=str(date.today()))
    meta = {'allow_inheritance': True}


class FAQModel(PostBase):
    pass


class NoticeModel(PostBase):
    pass


class RuleModel(PostBase):
    pass
