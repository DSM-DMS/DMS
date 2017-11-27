import json

from flask import Response
from flask_restful import Resource
from flasgger import swag_from

from app.docs.student.post.rule import *
from app.models.post import RuleModel
from support import post_inquire_helper


class RuleList(Resource):
    uri = '/rule'

    @swag_from(RULE_LIST_GET)
    def get(self):
        """
        기숙사규정 목록 조회
        """
        rule_list = post_inquire_helper.list(RuleModel)

        return Response(json.dumps(rule_list, ensure_ascii=False), 200, content_type='application/json; charset=utf8')


class Rule(Resource):
    uri = '/rule/<id>'

    @swag_from(RULE_GET)
    def get(self, id):
        """
        기숙사규정 내용 조회
        """
        rule = post_inquire_helper.inquire(RuleModel, id)

        return Response(json.dumps(rule, ensure_ascii=False), 200, content_type='application/json; charset=utf8') if rule else Response('', 204)
