class Student:
    def __init__(self, id):
        self.id = id


class Admin:
    def __init__(self, id):
        self.id = id


def student_auth(id, pw):
    pass


def student_id(payload):
    return payload['identity']


def admin_auth(id, pw):
    pass


def admin_id(payload):
    return payload['identity']
