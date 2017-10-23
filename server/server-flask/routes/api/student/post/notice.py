from flask_restful_swagger_2 import Resource, request, swagger

from db.models.post import NoticeModel

from . import notice_doc
from . import helper


class NoticeList(Resource):
    @swagger.doc(notice_doc.NOTICE_LIST)
    def get(self):
        return helper.list(NoticeModel), 200


class Notice(Resource):
    @swagger.doc(notice_doc.NOTICE)
    def get(self):
        id = request.args.get('id')

        notice = helper.inquire(NoticeModel, id)

        return (notice, 200) if notice else ('', 204)
