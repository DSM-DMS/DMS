from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful import Resource, request
from flasgger import swag_from

from app.docs.student.dormitory.report import *
from app.models.account import StudentModel
from app.models.dormitory import ReportModel


class Report(Resource):
    uri = '/report'
    
    @swag_from(REPORT_POST)
    @jwt_required
    def post(self):
        """
        시설고장신고
        """
        student = StudentModel.objects(id=get_jwt_identity()).first()
        if not student:
            return Response('', 403)

        title = request.form['title']
        room = int(request.form['room'])
        content = request.form['content']

        ReportModel(author=student, title=title, room=room, content=content).save()

        return Response('', 201)
