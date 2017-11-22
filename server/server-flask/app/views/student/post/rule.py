import json

from flask import Response
from flask_restful_swagger_2 import Resource, swagger

from app.docs.student.post.rule import *
from app.models.post import RuleModel
from support import post_inquire_helper


class RuleList(Resource):
    uri = '/rule'

    @swagger.doc(RULE_LIST_GET)
    def get(self):
        """
        기숙사규정 목록 조회
        """
        rule_list = post_inquire_helper.list(RuleModel)

        return Response(json.dumps(rule_list, ensure_ascii=False), 200, content_type='application/json; charset=utf8')


class Rule(Resource):
    uri = '/rule/<id>'

    @swagger.doc(RULE_GET)
    def get(self, id):
        """
        기숙사규정 내용 조회
        """
        rule = post_inquire_helper.inquire(RuleModel, id)

        return Response(json.dumps(rule, ensure_ascii=False), 200, content_type='application/json; charset=utf8') if rule else Response('', 204)
