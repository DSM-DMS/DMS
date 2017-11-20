import json

from flask import Response
from flask_restful_swagger_2 import Resource, swagger

from app.models.post import NoticeModel
from app.docs.student.post import notice
from support import post_inquire_helper


class NoticeList(Resource):
    uri = '/notice'

    @swagger.doc(notice.NOTICE_LIST_GET)
    def get(self):
        """
        공지사항 목록 조회
        """
        notice_list = post_inquire_helper.list(NoticeModel)

        return Response(json.dumps(notice_list, ensure_ascii=False), 200)


class Notice(Resource):
    uri = '/notice/<id>'

    @swagger.doc(notice.NOTICE_GET)
    def get(self, id):
        """
        공지사항 내용 조회
        """
        notice = post_inquire_helper.inquire(NoticeModel, id)

        return Response(json.dumps(notice, ensure_ascii=False), 200) if notice else Response('', 204)
