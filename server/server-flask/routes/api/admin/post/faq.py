from flask_restful_swagger_2 import Resource, request, swagger
from flask_jwt import current_identity, jwt_required

from db.models.account import AdminModel
from db.models.post import FAQModel

from . import faq_doc
from . import helper


class FAQ(Resource):
    @swagger.doc(faq_doc.FAQ_POST)
    @jwt_required()
    def post(self):
        admin = AdminModel.objects(id=current_identity).first()

        if not admin:
            # Forbidden
            return '', 403
        else:
            title = request.form.get('title')
            content = request.form.get('content')

            helper.post(FAQModel, title, content, admin)

            return '', 201

    @swagger.doc(faq_doc.FAQ_PATCH)
    @jwt_required()
    def patch(self):
        if not AdminModel.objects(id=current_identity):
            # Forbidden
            return '', 403
        else:
            id = request.form.get('id')
            title = request.form.get('title')
            content = request.form.get('content')

            helper.patch(FAQModel, id, title, content)

            return '', 200

    @swagger.doc(faq_doc.FAQ_DELETE)
    @jwt_required()
    def delete(self):
        if not AdminModel.objects(id=current_identity):
            # Forbidden
            return '', 403
        else:
            id = request.form.get('id')

            helper.delete(FAQModel, id)

            return '', 200
