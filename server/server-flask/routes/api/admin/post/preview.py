from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from db.models.account import AdminModel
from db.models.post import FAQModel, NoticeModel, RuleModel
from routes.swagger_docs.admin.post import preview_doc


class FAQPreview(Resource):
    @swagger.doc(preview_doc.FAQ_PREVIEW_GET)
    @jwt_required
    def post(self):
        """
        FAQ 프리뷰 설정
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        id = request.form.get('id')

        if FAQModel.objects(id=id).first().update(pinned=True):
            FAQModel.objects(pinned=True).first().update(pinned=False)

        return Response('', 201)


class NoticePreview(Resource):
    @swagger.doc(preview_doc.NOTICE_PREVIEW_GET)
    @jwt_required
    def post(self):
        """
        공지사항 프리뷰 설정
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        id = request.form.get('id')

        if NoticeModel.objects(id=id).first().update(pinned=True):
            NoticeModel.objects(pinned=True).first().update(pinned=False)

        return Response('', 201)


class RulePreview(Resource):
    @swagger.doc(preview_doc.RULE_PREVIEW_GET)
    @jwt_required
    def post(self):
        """
        기숙사규정 프리뷰 설정
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        id = request.form.get('id')

        RuleModel.objects(pinned=True).first().update(pinned=False)
        RuleModel.objects(id=id).first().update(pinned=True)

        return Response('', 201)
