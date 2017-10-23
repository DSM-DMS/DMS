from flask_restful_swagger_2 import Resource, request, swagger

from db.models.post import FAQModel

from . import faq_doc
from . import helper


class FAQList(Resource):
    @swagger.doc(faq_doc.FAQ_LIST)
    def get(self):
        return helper.list(FAQModel), 200


class FAQ(Resource):
    @swagger.doc(faq_doc.FAQ)
    def get(self):
        id = request.args.get('id')

        faq = helper.inquire(FAQModel, id)

        return (faq, 200) if faq else ('', 204)
