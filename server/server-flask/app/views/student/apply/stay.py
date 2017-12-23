from datetime import datetime, time

from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful import Resource, request
from flasgger import swag_from

from app.docs.student.apply.stay import *
from app.models.account import StudentModel
from app.models.apply import StayApplyModel


class Stay(Resource):
    uri = '/stay'

    @swag_from(STAY_GET)
    @jwt_required
    def get(self):
        """
        잔류신청 정보 조회
        """
        student = StudentModel.objects(id=get_jwt_identity()).first()
        if not student:
            return Response('', 403)

        if not student.stay_apply:
            return Response('', 204)

        return {'value': student.stay_apply.value}, 200

    @swag_from(STAY_POST)
    @jwt_required
    def post(self):
        """
        잔류신청
        """
        student = StudentModel.objects(id=get_jwt_identity()).first()
        if not student:
            return Response('', 403)

        today = datetime.today()

        if (today.weekday() == 3 and today.time() > time(22, 00))\
                or 6 > today.weekday() > 3\
                or (today.weekday() == 6 and today.time() < time(20, 30)):
            # 목요일 10시 이후, 또는 금요일과 토요일 사이, 또는 일요일 8시 30분 이전
            return Response('', 204)

        value = int(request.form['value'])

        student.update(stay_apply=StayApplyModel(value=value))

        return Response('', 201)
