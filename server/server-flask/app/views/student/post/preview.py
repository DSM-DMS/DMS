import json

from flask import Response
from flask_restful import Resource
from flasgger import swag_from

from app.docs.student.post.preview import *
from app.models.post import FAQModel, NoticeModel, RuleModel
from support import post_inquire_helper


class FAQPreview(Resource):
    uri = '/preview/faq'

    @swag_from(FAQ_PREVIEW_GET)
    def get(self):
        """
        FAQ 프리뷰 조회
        """
        faq = FAQModel.objects(pinned=True).first()
        if not faq:
            faq = FAQModel.objects().first()
            if not faq:
                return Response('', 204)

        faq = post_inquire_helper.inquire(FAQModel, faq.id)

        return Response(json.dumps(faq, ensure_ascii=False), 200, content_type='application/json; charset=utf8')


class NoticePreview(Resource):
    uri = '/preview/notice'

    @swag_from(NOTICE_PREVIEW_GET)
    def get(self):
        """
        공지사항 프리뷰 조회
        """
        notice = NoticeModel.objects(pinned=True).first()
        if not notice:
            notice = NoticeModel.objects().first()
            if not notice:
                return Response('', 204)

        notice = post_inquire_helper.inquire(NoticeModel, notice.id)

        return Response(json.dumps(notice, ensure_ascii=False), 200, content_type='application/json; charset=utf8')


class RulePreview(Resource):
    uri = '/preview/rule'

    @swag_from(RULE_PREVIEW_GET)
    def get(self):
        """
        기숙사규정 프리뷰 조회
        """
        rule = RuleModel.objects(pinned=True).first()
        if not rule:
            rule = RuleModel.objects().first()
            if not rule:
                return Response('', 204)

        rule = post_inquire_helper.inquire(RuleModel, rule.id)

        return Response(json.dumps(rule, ensure_ascii=False), 200, content_type='application/json; charset=utf8')
