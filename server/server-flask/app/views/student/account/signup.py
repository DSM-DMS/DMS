from hashlib import sha256

from flask import Response
from flask_restful import Resource, request
from flasgger import swag_from

from app.docs.student.account.signup import *
from app.models.account import SignupRequiredModel, StudentModel


class IDVerification(Resource):
    uri = '/verify/id'

    @swag_from(ID_VERIFICATION_POST)
    def post(self):
        """
        ID 중복체크
        """
        id = request.form['id']

        if StudentModel.objects(id=id):
            # ID already exists
            return Response('', 204)
        else:
            return Response('', 201)


class UUIDVerification(Resource):
    uri = '/verify/uuid'

    @swag_from(UUID_VERIFICATION_POST)
    def post(self):
        """
        UUID 검사
        """
        uuid = request.form['uuid']

        if SignupRequiredModel.objects(uuid=uuid):
            return Response('', 201)
        else:
            return Response('', 204)


class Signup(Resource):
    uri = '/signup'

    @swag_from(SIGNUP_POST)
    def post(self):
        """
        회원가입
        """
        uuid = request.form['uuid']
        id = request.form['id']
        pw = request.form['pw']

        m = sha256()
        m.update(pw.encode('utf-8'))
        pw = m.hexdigest()

        student = SignupRequiredModel.objects(uuid=uuid).first()
        if student:
            # Valid UUID
            StudentModel(id=id, pw=pw, name=student.name, number=student.number).save()
            student.delete()
            # Delete existing 'signup required' data

            return Response('', 201)
        else:
            return Response('', 204)
