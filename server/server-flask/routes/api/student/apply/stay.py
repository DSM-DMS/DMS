from flask import Response
from flask_restful import Resource, request
from flask_jwt import current_identity, jwt_required

from db.models.account import StudentModel
from db.models.apply import StayApplyModel


class Stay(Resource):
    @jwt_required()
    def get(self):
        """
        잔류신청 정보 조회
        """
        student = StudentModel.objects(id=current_identity).first()

        return {'value': student.stay_apply.value}, 200

    @jwt_required()
    def post(self):
        """
        잔류신청
        """
        value = request.form.get('value', type=int)

        student = StudentModel.objects(id=current_identity).first()
        student.update(stay_apply=StayApplyModel(value=value))

        return Response('', 201)
