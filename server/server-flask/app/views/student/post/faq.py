import json

from flask import Response
from flask_restful import Resource
from flasgger import swag_from

from app.docs.student.post.faq import *
from app.models.post import FAQModel
from support import post_inquire_helper


class FAQList(Resource):
    uri = '/faq'

    @swag_from(FAQ_LIST_GET)
    def get(self):
        """
        FAQ 목록 조회
        """
        faq_list = post_inquire_helper.list(FAQModel)

        return Response(json.dumps(faq_list, ensure_ascii=False), 200, content_type='application/json; charset=utf8')


class FAQ(Resource):
    uri = '/faq/<id>'

    @swag_from(FAQ_GET)
    def get(self, id):
        """
        FAQ 내용 조회
        """
        faq = post_inquire_helper.inquire(FAQModel, id)

        return Response(json.dumps(faq, ensure_ascii=False), 200, content_type='application/json; charset=utf8') if faq else Response('', 204)
