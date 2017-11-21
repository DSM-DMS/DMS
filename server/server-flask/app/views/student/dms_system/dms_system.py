from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from app.docs.student.dms_system.dms_system import *
from app.models.account import StudentModel
from app.models.dms_system import BugReportModel


class BugReport(Resource):
    uri = '/bug-report'

    @swagger.doc(BUG_REPORT_POST)
    @jwt_required
    def post(self):
        """
        DMS 버그 신고
        """
        author = StudentModel.objects(id=get_jwt_identity()).first()
        title = request.form.get('title')
        content = request.form.get('content')

        BugReportModel(author=author, title=title, content=content).save()

        return Response('', 201)
