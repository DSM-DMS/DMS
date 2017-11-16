from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from db.models.account import AdminModel
from db.models.post import RuleModel
from routes.api.admin.post import rule_doc


class Rule(Resource):
    uri = '/rule'

    @swagger.doc(rule_doc.RULE_POST)
    @jwt_required
    def post(self):
        """
        기숙사규정 업로드
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        title = request.form.get('title')
        content = request.form.get('content')

        RuleModel(title=title, content=content, author=admin).save()

        return Response('', 201)

    @swagger.doc(rule_doc.RULE_PATCH)
    @jwt_required
    def patch(self):
        """
        기숙사규정 내용 수정
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        id = request.form.get('id')
        title = request.form.get('title')
        content = request.form.get('content')

        post = RuleModel.objects(id=id).first()
        post.update(title=title, content=content)

        return Response('', 200)

    @swagger.doc(rule_doc.RULE_DELETE)
    @jwt_required
    def delete(self):
        """
        기숙사규정 삭제
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        id = request.form.get('id')

        RuleModel.objects(id=id).first().delete()

        return Response('', 200)
