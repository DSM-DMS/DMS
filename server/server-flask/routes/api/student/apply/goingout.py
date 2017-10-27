from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from db.models.account import StudentModel
from db.models.apply import GoingoutApplyModel

from . import goingout_doc


class Goingout(Resource):
    @swagger.doc(goingout_doc.GOINGOUT_GET)
    @jwt_required
    def get(self):
        """
        외출신청 정보 조회
        """
        student = StudentModel.objects(id=get_jwt_identity()).first()

        if not student.goingout_apply:
            return {
                'sat': False,
                'sun': False
            }

        return {
            'sat': student.goingout_apply.on_saturday,
            'sun': student.goingout_apply.on_sunday
        }, 200

    @swagger.doc(goingout_doc.GOINGOUT_POST)
    @jwt_required
    def post(self):
        """
        외출신청
        """
        sat = request.form.get('sat').upper() == 'TRUE'
        sun = request.form.get('sun').upper() == 'TRUE'

        student = StudentModel.objects(id=get_jwt_identity()).first()
        student.update(goingout_apply=GoingoutApplyModel(on_saturday=sat, on_sunday=sun))

        return Response('', 201)
