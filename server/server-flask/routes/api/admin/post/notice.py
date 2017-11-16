from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from db.models.account import AdminModel
from db.models.post import NoticeModel
from routes.api.admin.post import notice_doc


class Notice(Resource):
    uri = '/notice'

    @swagger.doc(notice_doc.NOTICE_POST)
    @jwt_required
    def post(self):
        """
        공지사항 업로드
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        title = request.form.get('title')
        content = request.form.get('content')

        NoticeModel(title = title, content = content, author = admin).save()

        return Response('', 201)

    @swagger.doc(notice_doc.NOTICE_PATCH)
    @jwt_required
    def patch(self):
        """
        공지사항 내용 수정
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        id = request.form.get('id')
        title = request.form.get('title')
        content = request.form.get('content')

        post = NoticeModel.objects(id=id).first()
        post.update(title=title, content=content)

        return Response('', 200)

    @swagger.doc(notice_doc.NOTICE_DELETE)
    @jwt_required
    def delete(self):
        """
        공지사항 제거
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        id = request.form.get('id')

        NoticeModel.objects(id=id).first().delete()

        return Response('', 200)
