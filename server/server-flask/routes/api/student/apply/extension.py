from flask_restful import Resource, request
from flask_jwt import current_identity, jwt_required

from db.models.account import StudentModel
from db.models.apply import ExtensionApplyModel


class Extension(Resource):
    @jwt_required()
    def get(self):
        student = StudentModel.objects(id=current_identity).first()

        return {
            'class': student.extension_apply.class_,
            'seat': student.extension_apply.seat
        }, 200

    @jwt_required()
    def post(self):
        class_ = request.form.get('class', type=int)
        seat = request.form.get('seat', type=int)

        student = StudentModel.objects(id=current_identity).first()
        student.update(extension_applies=ExtensionApplyModel(class_=class_, seat=seat))

        return '', 201
