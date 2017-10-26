from flask import Response
from flask_restful_swagger_2 import Resource, request, swagger
from flask_jwt import current_identity, jwt_required

from db.models.account import AdminModel, StudentModel, SignupRequiredModel
from . import account_control_doc


class InitializeAccount(Resource):
    @swagger.doc(account_control_doc.INITIALIZE_ACCOUNT_POST)
    @jwt_required()
    def post(self):
        """
        학생 계정 초기화
        """
        admin = AdminModel.objects(id=current_identity).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        number = request.form.get('number', type=int)
        # Number to initialize

        student = StudentModel.objects(number=number).first()
        # Get model
        SignupRequiredModel(uuid=student.uuid, name=student.name, number=student.number).save()
        # Move to 'signup required'
        student.delete()
        # Delete existing student account

        return Response('', 201)
