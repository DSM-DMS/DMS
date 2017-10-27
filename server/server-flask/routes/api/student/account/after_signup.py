from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from db.models.account import StudentModel

from . import after_signup_doc


class ChangePW(Resource):
    @swagger.doc(after_signup_doc.CHANGE_PW_POST)
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
    @swagger.doc(after_signup_doc.CHANGE_NUMBER_POST)
    @jwt_required
    def post(self):
        """
        학번 변경
        """
        new_number = request.form.get('new_number', type=int)

        StudentModel.objects(id=get_jwt_identity()).first().update(number=new_number)

        return Response('', 201)
