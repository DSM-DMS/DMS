from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful import Resource, request
from flasgger import swag_from

from app.docs.student.dms_system.dms_system import *
from app.models.account import StudentModel
from app.models.dms_system import BugReportModel


class BugReport(Resource):
    uri = '/bug-report'

    @swag_from(BUG_REPORT_POST)
    @jwt_required
    def post(self):
        """
        DMS 버그 신고
        """
        student = StudentModel.objects(id=get_jwt_identity()).first()
        if not student:
            return Response('', 403)

        title = request.form['title']
        content = request.form['content']

        BugReportModel(author=student, title=title, content=content).save()

        return Response('', 201)
