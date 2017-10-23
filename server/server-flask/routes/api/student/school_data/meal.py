from flask_restful_swagger_2 import Resource, request, swagger

from db.models.school_data import MealModel

from . import meal_doc


class Meal(Resource):
    @swagger.doc(meal_doc.MEAL)
    def get(self):
        date = request.args.get('date')

        meal = MealModel.objects(date=date).first()

        if not meal:
            return '', 204
        else:
            return {
                'breakfast': meal.breakfast,
                'lunch': meal.lunch,
                'dinner': meal.dinner
            }, 200
