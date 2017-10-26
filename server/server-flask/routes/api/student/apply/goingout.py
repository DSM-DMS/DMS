from flask import Response
from flask_jwt import current_identity, jwt_required
from flask_restful import Resource, request

from db.models.account import StudentModel
from db.models.apply import GoingoutApplyModel


class Goingout(Resource):
    @jwt_required()
    def get(self):
        """
        외출신청 정보 조회
        """
        student = StudentModel.objects(id=current_identity).first()

        return {
            'sat': student.goingout_apply.on_saturday,
            'sun': student.goingout_apply.on_sunday
        }, 200

    @jwt_required()
    def post(self):
        """
        외출신청
        """
        sat = request.form.get('sat', type=bool)
        sun = request.form.get('sun', type=bool)

        student = StudentModel.objects(id=current_identity).first()
        student.update(goingout_apply=GoingoutApplyModel(on_saturday=sat, on_sunday=sun))

        return Response('', 201)
