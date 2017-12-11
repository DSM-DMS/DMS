import json
from hashlib import sha256

from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful import Resource, request
from flasgger import swag_from

from app.models.account import StudentModel
from app.docs.student.account.after_signup import *


class ChangePW(Resource):
    uri = '/change/pw'

    @swag_from(CHANGE_PW_POST)
    @jwt_required
    def post(self):
        """
        비밀번호 변경
        """
        student = StudentModel.objects(id=get_jwt_identity()).first()
        if not student:
            return Response('', 403)

        current_pw = request.form['current_pw']

        m = sha256()
        m.update(current_pw.encode('utf-8'))
        current_pw = m.hexdigest()

        if not StudentModel.objects(id=get_jwt_identity(), pw=current_pw):
            return Response('', 403)

        new_pw = request.form.get('new_pw')

        m = sha256()
        m.update(new_pw.encode('utf-8'))
        new_pw = m.hexdigest()

        student.update(pw=new_pw)

        return Response('', 201)


class ChangeNumber(Resource):
    uri = '/change/number'

    @swag_from(CHANGE_NUMBER_POST)
    @jwt_required
    def post(self):
        """
        학번 변경
        """
        student = StudentModel.objects(id=get_jwt_identity()).first()
        if not student:
            return Response('', 403)

        new_number = int(request.form['new_number'])

        student.update(number=new_number)

        return Response('', 201)


class MyPage(Resource):
    uri = '/mypage'

    @swag_from(MYPAGE_GET)
    @jwt_required
    def get(self):
        """
        마이페이지
        """
        student = StudentModel.objects(id=get_jwt_identity()).first()

        if not student:
            return Response('', 403)

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
        }, ensure_ascii=False), 200, content_type='application/json; charset=utf8')
