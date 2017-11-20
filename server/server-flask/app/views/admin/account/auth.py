from flask_jwt_extended import create_access_token
from flask_restful_swagger_2 import Resource, request, swagger

from app.models.account import AdminModel
from app.docs.admin.account import auth


class Auth(Resource):
    uri = '/auth/admin'

    @swagger.doc(auth.AUTH_POST)
    def post(self):
        """
        로그인
        """
        id = request.form.get('id')
        pw = request.form.get('pw')

        if id and pw and AdminModel.objects(id=id, pw=pw):
            return {
                'access_token': create_access_token(identity=id)
            }, 201
        else:
            return {
                'msg': 'Authentication Failed.'
            }, 401
