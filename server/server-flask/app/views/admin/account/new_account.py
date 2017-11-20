from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from app.models.account import AdminModel
from app.docs.admin.account import new_account


class NewAccount(Resource):
    uri = '/new-account'

    @swagger.doc(new_account.NEW_ACCOUNT_POST)
    @jwt_required
    def post(self):
        """
        새로운 관리자 계정 추가
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        id = request.form.get('id')
        pw = request.form.get('pw')
        name = request.form.get('name')
        # New account data

        if AdminModel.objects(id=id):
            # ID already exists
            return Response('', 204)
        else:
            AdminModel(id=id, pw=pw, name=name).save()

            return Response('', 201)
