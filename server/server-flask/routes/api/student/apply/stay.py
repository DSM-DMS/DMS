from flask_restful import Resource, request
from flask_jwt import current_identity, jwt_required

from db.models.account import StudentModel
from db.models.apply import StayApplyModel


class Stay(Resource):
    @jwt_required()
    def get(self):
        student = StudentModel.objects(id=current_identity).first()

        return {'value': student.stay_applies.value}, 200

    @jwt_required()
    def post(self):
        value = request.form.get('value')
        student = StudentModel.objects(id=current_identity).first()
        student.update(stay_applies=StayApplyModel(value=value))

        return '', 201
