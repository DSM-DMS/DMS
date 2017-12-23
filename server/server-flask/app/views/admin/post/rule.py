from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful import Resource, request
from flasgger import swag_from

from app.docs.admin.post.rule import *
from app.models.account import AdminModel
from app.models.post import RuleModel


class AdminRule(Resource):
    uri = '/admin/rule'

    @swag_from(RULE_POST)
    @jwt_required
    def post(self):
        """
        기숙사규정 업로드
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            return Response('', 403)

        title = request.form.get('title')
        content = request.form.get('content')

        RuleModel(title=title, content=content, author=admin).save()

        return Response('', 201)

    @swag_from(RULE_PATCH)
    @jwt_required
    def patch(self):
        """
        기숙사규정 내용 수정
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            return Response('', 403)

        id = request.form.get('id')
        title = request.form.get('title')
        content = request.form.get('content')

        post = RuleModel.objects(id=id).first()
        post.update(title=title, content=content)

        return Response('', 200)

    @swag_from(RULE_DELETE)
    @jwt_required
    def delete(self):
        """
        기숙사규정 삭제
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            return Response('', 403)

        id = request.form.get('id')

        RuleModel.objects(id=id).first().delete()

        return Response('', 200)
