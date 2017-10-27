import json
import requests

from flask_jwt_extended import create_access_token
from flask_restful_swagger_2 import Resource, request, swagger

from db.models.account import AdminModel, StudentModel

from support import db_migrator

from . import authentication_doc


class AdminAuth(Resource):
    @swagger.doc(authentication_doc.AUTH_POST)
    def post(self):
        id = request.form.get('id')
        pw = request.form.get('pw')

        if id and pw and AdminModel.objects(id=id, pw=pw):
            return {
                'access_token': create_access_token(identity=id)
            }, 201
        else:
            return {
                'msg': 'Authentication Failed.'
            }, 401


class StudentAuth(Resource):
    @swagger.doc(authentication_doc.AUTH_POST)
    def post(self):
        id = request.form.get('id')
        pw = request.form.get('pw')

        if id and pw and StudentModel.objects(id=id, pw=pw):
            return {
                'access_token': create_access_token(identity=id)
            }, 201
        else:
            # --- Migration code
            # Doesn't exist in MongoDB
            result = requests.post('http://dsm2015.cafe24.com:8080/account/login/student', {'id': id, 'password': pw})
            if result.status_code == 201:
                # Login Success
                student_data = json.loads(requests.get('http://dsm2015.cafe24.com:8080/info/student/id_based', params={'id': id}).text)
                name, number, uuid = student_data['name'], student_data['number'], student_data['uuid']

                StudentModel(id=id, pw=pw, name=name, number=number).save()

                db_migrator.migrate_apply(id, uuid)

                return {
                    'access_token': create_access_token(identity=id)
                }
            else:
                return {
                    'msg': 'Authentication Failed.'
                }, 401
