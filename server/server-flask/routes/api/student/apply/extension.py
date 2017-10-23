from flask_restful import Resource, request
from flask_jwt import current_identity, jwt_required

from db.models.account import StudentModel
from db.models.apply import ExtensionApplyModel


class Extension(Resource):
    @jwt_required()
    def get(self):
        student = StudentModel.objects(id=current_identity).first()

        return {
            'cls': student.extension_applies.cls,
            'seat': student.extension_applies.seat
        }, 200

    @jwt_required()
    def post(self):
        cls = request.form.get('cls', int)
        seat = request.form.get('seat', int)
        student = StudentModel.objects(id=current_identity).first()
        student.update(extension_applies=ExtensionApplyModel(cls=cls, seat=seat))

        return '', 201
