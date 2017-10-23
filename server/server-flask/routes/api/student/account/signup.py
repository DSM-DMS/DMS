from flask_restful_swagger_2 import Resource, request, swagger

from db.models.account import SignupRequiredModel, StudentModel
from . import signup_doc


class UUIDVerification(Resource):
    @swagger.doc(signup_doc.UUID_VERIFICATION)
    def post(self):
        uuid = request.form.get('uuid')

        if SignupRequiredModel.objects(uuid=uuid):
            return '', 201
        else:
            return '', 204


class Signup(Resource):
    @swagger.doc(signup_doc.SIGNUP)
    def post(self):
        uuid = request.form.get('uuid')
        id = request.form.get('id')
        pw = request.form.get('pw')

        student = SignupRequiredModel.objects(uuid=uuid).first()
        if student:
            # Signup required UUID
            if StudentModel.objects(id=id):
                # ID Already exists
                return '', 204
            else:
                StudentModel(id=id, pw=pw, name=student.name, number=student.number).save()

                return '', 201
        else:
            return '', 400
