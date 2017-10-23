from datetime import date

from db.mongo import *


class PostBase(Document):
    title = StringField(required=True)
    content = StringField(required=True)
    writer = StringField(required=True)
    pinned = BooleanField(required=True, default=False)

    write_date = StringField(required=True, default=str(date.today()))
    meta = {'allow_inheritance': True}


class FAQModel(PostBase):
    pass


class NoticeModel(PostBase):
    pass


class RuleModel(PostBase):
    pass
