from flask import Response
from flask_restful_swagger_2 import Resource, swagger

from db.models.post import FAQModel

from . import faq_doc, helper


class FAQList(Resource):
    @swagger.doc(faq_doc.FAQ_LIST_GET)
    def get(self):
        """
        FAQ 목록 조회
        """
        return helper.list(FAQModel), 200


class FAQ(Resource):
    @swagger.doc(faq_doc.FAQ_GET)
    def get(self, id):
        """
        FAQ 내용 조회
        """
        faq = helper.inquire(FAQModel, id)

        return (faq, 200) if faq else Response('', 204)
