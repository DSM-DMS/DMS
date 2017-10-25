from db.models.account import AdminModel, StudentModel


class Model:
    def __init__(self, id):
        self.id = id


def student_auth(id, pw):
    if id and pw and StudentModel.objects(id=id, pw=pw):
        return Model(id=id)


def admin_auth(id, pw):
    if id and pw and AdminModel.objects(id=id, pw=pw):
        return Model(id=id)


def identity(payload):
    return payload['identity']
