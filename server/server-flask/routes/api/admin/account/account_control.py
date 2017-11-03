from uuid import uuid4

from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from db.models.account import AdminModel, SignupRequiredModel, StudentModel
from routes.api.admin.account import account_control_doc


class InitializeAccount(Resource):
    uri = '/admin/initialize-account'

    @swagger.doc(account_control_doc.INITIALIZE_ACCOUNT_POST)
    @jwt_required
    def post(self):
        """
        학생 계정 초기화
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        number = request.form.get('number', type=int)
        # Number to initialize

        student = StudentModel.objects(number=number).first()
        # Get model

        while True:
            new_uuid = str(uuid4())[:6]
            if not SignupRequiredModel.objects(uuid=new_uuid):
                # Escape Duplicate
                break

        SignupRequiredModel(uuid=new_uuid, name=student.name, number=student.number).save()
        # Move to 'signup required'
        student.delete()
        # Delete existing student account

        return {
            'uuid': new_uuid
        }, 201
