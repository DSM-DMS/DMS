from flask import Response
from flask_restful_swagger_2 import Resource, request, swagger

from db.models.account import SignupRequiredModel, StudentModel

from . import signup_doc


class UUIDVerification(Resource):
    @swagger.doc(signup_doc.UUID_VERIFICATION_POST)
    def post(self):
        """
        UUID 검사
        """
        uuid = request.form.get('uuid')

        if SignupRequiredModel.objects(uuid=uuid):
            return Response('', 201)
        else:
            return Response('', 204)


class Signup(Resource):
    @swagger.doc(signup_doc.SIGNUP_POST)
    def post(self):
        """
        회원가입
        """
        uuid = request.form.get('uuid')
        id = request.form.get('id')
        pw = request.form.get('pw')

        student = SignupRequiredModel.objects(uuid=uuid).first()
        if student:
            # Valid UUID
            if StudentModel.objects(id=id):
                # ID already exists
                return Response('', 204)
            else:
                StudentModel(id=id, pw=pw, name=student.name, number=student.number).save()
                student.delete()
                # Delete existing 'signup required' data

                return Response('', 201)
        else:
            return Response('', 400)
