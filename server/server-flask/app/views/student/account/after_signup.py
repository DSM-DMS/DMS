import json

from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from app.models.account import StudentModel
from app.docs.student.account.after_signup import *


class ChangePW(Resource):
    uri = '/change/pw'

    @swagger.doc(CHANGE_PW_POST)
    @jwt_required
    def post(self):
        """
        비밀번호 변경
        """
        current_pw = request.form.get('current_pw')
        student = StudentModel.objects(id=get_jwt_identity(), pw=current_pw).first()
        if not student:
            # Forbidden
            return Response('', 403)

        new_pw = request.form.get('new_pw')

        student.update(pw=new_pw)

        return Response('', 201)


class ChangeNumber(Resource):
    uri = '/change/number'

    @swagger.doc(CHANGE_NUMBER_POST)
    @jwt_required
    def post(self):
        """
        학번 변경
        """
        new_number = request.form.get('new_number', type=int)

        StudentModel.objects(id=get_jwt_identity()).first().update(number=new_number)

        return Response('', 201)


class MyPage(Resource):
    uri = '/mypage'

    @swagger.doc(MYPAGE_GET)
    @jwt_required
    def get(self):
        """
        마이페이지
        """
        student = StudentModel.objects(id=get_jwt_identity()).first()

        if not student:
            return Response('', 204)

        return Response(json.dumps({
            'name': student.name,
            'signup_date': student.signup_date,
            'number': student.number,
            'extension_11_class': student.extension_apply_11.class_ if student.extension_apply_11 else None,
            'extension_11_seat': student.extension_apply_11.seat if student.extension_apply_11 else None,
            'extension_12_class': student.extension_apply_12.class_ if student.extension_apply_12 else None,
            'extension_12_seat': student.extension_apply_12.seat if student.extension_apply_12 else None,
            'goingout_sat': student.goingout_apply.on_saturday if student.goingout_apply else None,
            'goingout_sun': student.goingout_apply.on_sunday if student.goingout_apply else None,
            'stay_value': student.stay_apply.value if student.stay_apply else None
        }, ensure_ascii=False), 200)
