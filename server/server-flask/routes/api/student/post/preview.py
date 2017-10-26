from flask import Response
from flask_jwt import current_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from db.models.post import FAQModel, NoticeModel, RuleModel

from . import helper, preview_doc


class FAQPreview(Resource):
    @swagger.doc(preview_doc.FAQ_PREVIEW_GET)
    def get(self):
        """
        FAQ 프리뷰 조회
        """
        faq = FAQModel.objects(pinned=True).first()
        if not faq:
            faq = FAQModel.objects().first()
            if not faq:
                return Response('', 204)

        return helper.inquire(FAQModel, faq.id), 200


class NoticePreview(Resource):
    @swagger.doc(preview_doc.NOTICE_PREVIEW_GET)
    def get(self):
        """
        공지사항 프리뷰 조회
        """
        notice = FAQModel.objects(pinned=True).first()
        if not notice:
            notice = FAQModel.objects().first()
            if not notice:
                return Response('', 204)

        return helper.inquire(NoticeModel, notice.id), 200


class RulePreview(Resource):
    @swagger.doc(preview_doc.RULE_PREVIEW_GET)
    def get(self):
        """
        기숙사규정 프리뷰 조회
        """
        rule = RuleModel.objects(pinned=True).first()
        if not rule:
            rule = FAQModel.objects().first()
            if not rule:
                return Response('', 204)

        return helper.inquire(RuleModel, rule.id), 200
