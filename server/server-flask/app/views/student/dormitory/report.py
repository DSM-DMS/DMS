from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from app.docs.student.dormitory.report import *
from app.models.account import StudentModel
from app.models.dormitory import ReportModel


class Report(Resource):
    uri = '/report'
    
    @swagger.doc(REPORT_POST)
    @jwt_required
    def post(self):
        """
        시설고장신고
        """
        author = StudentModel.objects(id=get_jwt_identity()).first()
        title = request.form.get('title')
        room = request.form.get('room', type=int)
        content = request.form.get('content')

        ReportModel(author=author, title=title, room=room, content=content).save()

        return Response('', 201)
