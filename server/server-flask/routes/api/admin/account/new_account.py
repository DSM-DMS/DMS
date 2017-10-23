from flask_restful_swagger_2 import Resource, request, swagger
from flask_jwt import current_identity, jwt_required

from db.models.account import AdminModel
from . import new_account_doc


class NewAccount(Resource):
    @swagger.doc(new_account_doc.NEW_ACCOUNT)
    @jwt_required()
    def post(self):
        if not AdminModel.objects(id=current_identity):
            # Forbidden
            return '', 403
        else:
            id = request.form.get('id')
            pw = request.form.get('pw')
            name = request.form.get('name')
            # New account data

            if AdminModel.objects(id=id):
                # ID already exists
                return'', 204
            else:
                AdminModel(id=id, pw=pw, name=name).save()

                return '', 201
