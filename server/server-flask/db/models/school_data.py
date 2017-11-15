from db.mongo import db


class MealModel(db.Document):
    date = db.StringField(primary_key=True)
    breakfast = db.ListField(required=True)
    lunch = db.ListField(required=True)
    dinner = db.ListField(required=True)


class ScheduleModel(db.Document):
    date = db.StringField(primary_key=True)
    schedules = db.ListField(required=True)
