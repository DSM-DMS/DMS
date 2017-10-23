from db.mongo import *


class AfterSchoolItemModel(EmbeddedDocument):
    title = StringField(required=True)
    on_monday = BooleanField(required=True, default=False)
    on_tuesday = BooleanField(required=True, default=False)
    on_saturday = BooleanField(required=True, default=False)
    target = IntField(required=True)


class AfterSchoolModel(Document):
    start_date = StringField(required=True)
    end_date = StringField(required=True)
    content = StringField(required=True)
    items = ListField(EmbeddedDocumentField(AfterSchoolItemModel))
