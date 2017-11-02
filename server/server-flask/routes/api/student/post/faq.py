import json

from flask import Response
from flask_restful_swagger_2 import Resource, swagger

from db.models.post import FAQModel
from routes.api.student.post import faq_doc
from support import post_inquire_helper


class FAQList(Resource):
    uri = '/faq'

    @swagger.doc(faq_doc.FAQ_LIST_GET)
    def get(self):
        """
        FAQ 목록 조회
        """
        faq_list = post_inquire_helper.list(FAQModel)

        return Response(json.dumps(faq_list, ensure_ascii=False), 200)


class FAQ(Resource):
    uri = '/faq/<id>'

    @swagger.doc(faq_doc.FAQ_GET)
    def get(self, id):
        """
        FAQ 내용 조회
        """
        faq = post_inquire_helper.inquire(FAQModel, id)

        return Response(json.dumps(faq, ensure_ascii=False), 200) if faq else Response('', 204)
