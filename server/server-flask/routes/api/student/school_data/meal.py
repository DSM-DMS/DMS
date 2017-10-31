from flask import Response
from flask_restful_swagger_2 import Resource, swagger

from db.models.school_data import MealModel
from routes.api.student.school_data import meal_doc


class Meal(Resource):
    uri = '/meal/<date>'

    @swagger.doc(meal_doc.MEAL_GET)
    def get(self, date):
        """
        급식 정보 조회
        """
        meal = MealModel.objects(date=date).first()

        if not meal:
            return Response('', 204)
        else:
            return {
                'breakfast': meal.breakfast,
                'lunch': meal.lunch,
                'dinner': meal.dinner
            }, 200
