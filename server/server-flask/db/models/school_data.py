from db.mongo import *


class Meal(Document):
    date = StringField(primary_key=True)
    breakfast = DictField(required=True)
    lunch = DictField(required=True)
    dinner = DictField(required=True)


class Schedule(Document):
    date = StringField(primary_key=True)
    schedules = ListField(required=True)
