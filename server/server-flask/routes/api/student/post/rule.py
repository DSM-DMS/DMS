import json

from flask import Response
from flask_restful_swagger_2 import Resource, swagger

from db.models.post import RuleModel
from routes.api.student.post import rule_doc
from support import post_inquire_helper


class RuleList(Resource):
    uri = '/rule'

    @swagger.doc(rule_doc.RULE_LIST_GET)
    def get(self):
        """
        기숙사규정 목록 조회
        """
        rule_list = post_inquire_helper.list(RuleModel)

        return Response(json.dumps(rule_list, ensure_ascii=False), 200)


class Rule(Resource):
    uri = '/rule/<id>'

    @swagger.doc(rule_doc.RULE_GET)
    def get(self, id):
        """
        기숙사규정 내용 조회
        """
        rule = post_inquire_helper.inquire(RuleModel, id)

        return Response(json.dumps(rule, ensure_ascii=False), 200) if rule else Response('', 204)