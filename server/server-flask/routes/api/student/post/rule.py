from flask import Response
from flask_restful_swagger_2 import Resource, swagger

from db.models.post import RuleModel

from . import helper, rule_doc


class RuleList(Resource):
    @swagger.doc(rule_doc.RULE_LIST_GET)
    def get(self):
        """
        기숙사규정 목록 조회
        """
        return helper.list(RuleModel), 200


class Rule(Resource):
    @swagger.doc(rule_doc.RULE_GET)
    def get(self, id):
        """
        기숙사규정 내용 조회
        """
        rule = helper.inquire(RuleModel, id)

        return (rule, 200) if rule else Response('', 204)
