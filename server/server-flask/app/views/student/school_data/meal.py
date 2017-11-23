import json

from flask import Response
from flask_restful import Resource
from flasgger import swag_from

from app.docs.student.school_data.meal import *
from app.models.school_data import MealModel


class Meal(Resource):
    uri = '/meal/<date>'

    @swag_from(MEAL_GET)
    def get(self, date):
        """
        급식 정보 조회
        """
        meal = MealModel.objects(date=date).first()

        if not meal:
            return Response('', 204)
        else:
            return Response(json.dumps({
                'breakfast': meal.breakfast,
                'lunch': meal.lunch,
                'dinner': meal.dinner
            }, ensure_ascii=False), 200, content_type='application/json; charset=utf8')
