from flask import Response
from flask_jwt import current_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from db.models.account import StudentModel
from db.models.apply import StayApplyModel

from . import stay_doc


class Stay(Resource):
    @swagger.doc(stay_doc.STAY_GET)
    @jwt_required()
    def get(self):
        """
        잔류신청 정보 조회
        """
        student = StudentModel.objects(id=current_identity).first()

        if not student.stay_apply:
            return Response('', 204)

        return {'value': student.stay_apply.value}, 200

    @swagger.doc(stay_doc.STAY_POST)
    @jwt_required()
    def post(self):
        """
        잔류신청
        """
        value = request.form.get('value', type=int)

        student = StudentModel.objects(id=current_identity).first()
        student.update(stay_apply=StayApplyModel(value=value))

        return Response('', 201)
