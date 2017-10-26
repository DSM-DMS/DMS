import json
import requests

from db.models.account import AdminModel, StudentModel

from support import db_migrator


class Model:
    def __init__(self, id):
        self.id = id


def student_auth(id, pw):
    if id and pw and StudentModel.objects(id=id, pw=pw):
        return Model(id=id)
    else:
        # --- Migration code
        # Doesn't exist in MongoDB
        result = requests.post('http://dsm2015.cafe24.com/account/login/student', {'id': id, 'password': pw})
        if result.status_code == 201:
            # Login Success
            student_data = json.loads(requests.get('http://dsm2015.cafe24.com/info/student/id_based', {'id': id}))
            name, number, uuid = student_data['name'], student_data['number'], student_data['uuid']

            StudentModel(id=id, pw=pw, name=name, number=number).save()

            db_migrator.migrate_apply(id, uuid)

            return Model(id=id)


def admin_auth(id, pw):
    if id and pw and AdminModel.objects(id=id, pw=pw):
        return Model(id=id)


def identity(payload):
    return payload['identity']
