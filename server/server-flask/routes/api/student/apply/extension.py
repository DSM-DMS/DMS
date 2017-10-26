from flask import Response
from flask_restful import Resource, request
from flask_jwt import current_identity, jwt_required

from db.models.account import StudentModel
from db.models.apply import ExtensionApplyModel


class Extension(Resource):
    @jwt_required()
    def get(self):
        """
        연장신청 정보 조회
        """
        student = StudentModel.objects(id=current_identity).first()

        return {
            'class': student.extension_apply.class_,
            'seat': student.extension_apply.seat
        }, 200

    @jwt_required()
    def post(self):
        """
        연장신청
        """
        class_ = request.form.get('class', type=int)
        seat = request.form.get('seat', type=int)

        student = StudentModel.objects(id=current_identity).first()
        student.update(extension_apply=ExtensionApplyModel(class_=class_, seat=seat))

        return Response('', 201)
