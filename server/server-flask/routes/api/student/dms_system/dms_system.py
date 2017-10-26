from flask import Response
from flask_jwt import current_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from db.models.account import StudentModel
from db.models.dms_system import BugReportModel

from . import dms_system_doc


class BugReport(Resource):
    @swagger.doc(dms_system_doc.BUG_REPORT_POST)
    @jwt_required()
    def post(self):
        """
        DMS 버그 신고
        """
        author = StudentModel.objects(id=current_identity).first()
        title = request.form.get('title')
        content = request.form.get('content')

        BugReportModel(author=author, title=title, content=content).save()

        return Response('', 201)
