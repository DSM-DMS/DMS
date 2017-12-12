from hashlib import sha256

from flask_jwt_extended import create_access_token
from flask_restful import Resource, request
from flasgger import swag_from

from app.docs.student.account.auth import *
from app.models.account import StudentModel


class Auth(Resource):
    uri = '/auth/student'

    @swag_from(AUTH_POST)
    def post(self):
        """
        로그인
        """
        id = request.form['id']
        pw = request.form['pw']

        m = sha256()
        m.update(pw.encode('utf-8'))
        encrypted_pw = m.hexdigest()

        if StudentModel.objects(id=id, pw=encrypted_pw):
            return {
                'access_token': create_access_token(identity=id)
            }, 201
        else:
            # print('Branched Migration way')
            # # --- Migration code
            # # Doesn't exist in MongoDB
            # result = requests.post('http://localhost:8080/account/login/student', {'id': id, 'password': pw, 'remember': True})
            # if result.status_code == 201:
            #     # Login Success
            #     student_data = json.loads(requests.get('http://localhost:8080/info/student/id_based', params={'id': id}).text)
            #     name, number, uuid = student_data['name'], student_data['number'], student_data['uuid']
            #
            #     StudentModel(id=id, pw=encrypted_pw, name=name, number=number).save()
            #
            #     db_migrator.migrate_apply(id, uuid)
            #
            #     return {
            #         'access_token': create_access_token(identity=id)
            #     }, 201
            # else:
            return {
                'msg': 'Authentication Failed.'
            }, 401
