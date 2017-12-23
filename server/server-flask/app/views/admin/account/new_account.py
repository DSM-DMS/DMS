from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful import Resource, request
from flasgger import swag_from

from app.docs.admin.account.new_account import *
from app.models.account import AdminModel


class AdminNewAccount(Resource):
    uri = '/admin/new-account'

    @swag_from(NEW_ACCOUNT_POST)
    @jwt_required
    def post(self):
        """
        새로운 관리자 계정 추가
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
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
