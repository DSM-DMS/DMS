from flask import Response
from flask_jwt import current_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from db.models.account import StudentModel
from db.models.apply import ExtensionApplyModel

from . import extension_doc


class Extension(Resource):
    @swagger.doc(extension_doc.EXTENSION_GET)
    @jwt_required()
    def get(self):
        """
        연장신청 정보 조회
        """
        student = StudentModel.objects(id=current_identity).first()

        if not student.extension_apply:
            return Response('', 204)

        return {
            'class': student.extension_apply.class_,
            'seat': student.extension_apply.seat
        }, 200

    @swagger.doc(extension_doc.EXTENSION_POST)
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
