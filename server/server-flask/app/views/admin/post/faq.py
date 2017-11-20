from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from app.models.account import AdminModel
from app.models.post import FAQModel
from app.docs.admin.post import faq


class FAQ(Resource):
    uri = '/faq'

    @swagger.doc(faq.FAQ_POST)
    @jwt_required
    def post(self):
        """
        FAQ 업로드
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        title = request.form.get('title')
        content = request.form.get('content')

        FAQModel(title=title, content=content, author=admin).save()

        return Response('', 201)

    @swagger.doc(faq.FAQ_PATCH)
    @jwt_required
    def patch(self):
        """
        FAQ 내용 수정
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        id = request.form.get('id')
        title = request.form.get('title')
        content = request.form.get('content')

        post = FAQModel.objects(id=id).first()
        post.update(title=title, content=content)

        return Response('', 200)

    @swagger.doc(faq.FAQ_DELETE)
    @jwt_required
    def delete(self):
        """
        FAQ 제거
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        id = request.form.get('id')

        FAQModel.objects(id=id).first().delete()

        return Response('', 200)
