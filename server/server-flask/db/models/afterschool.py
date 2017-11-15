from bson.objectid import ObjectId

from db.mongo import db


class AfterSchoolItemModel(db.EmbeddedDocument):
    id = db.ObjectIdField(primary_key=True, default=ObjectId())
    title = db.StringField(required=True)
    on_monday = db.BooleanField(required=True, default=False)
    on_tuesday = db.BooleanField(required=True, default=False)
    on_saturday = db.BooleanField(required=True, default=False)
    target = db.ListField(db.IntField())


class AfterSchoolModel(db.Document):
    start_date = db.StringField(required=True)
    end_date = db.StringField(required=True)
    content = db.StringField(required=True)
    items = db.ListField(db.EmbeddedDocumentField(AfterSchoolItemModel))
