from flask import send_from_directory
from flask_restful import Resource
from flask_jwt import current_identity, jwt_required

import openpyxl

from db.models.account import AdminModel, StudentModel


class CheckGoingout(Resource):
    @jwt_required()
    def get(self):
        if not AdminModel.objects(id=current_identity):
            return '', 403

        wb = openpyxl.load_workbook('명렬표.xlsx')
        ws = wb.active

        for row in map(str, range(3, 68)):
            for column1, column2, column3 in zip(['B', 'F', 'J', 'N'], ['D', 'H', 'L', 'P'], ['E', 'I', 'M', 'Q']):
                student = StudentModel.objects(number=ws[column1+row]).first()

                if not student:
                    continue

                sat = '토요 외출' if student.goingout_applies.on_saturday else ''
                sun = '일요 외출' if student.goingout_applies.on_sunday else ''
                ws[column2+row] = sat
                ws[column3+row] = sun

        wb.save('명렬표.xlsx')
        return send_from_directory('주소오오오', '명렬표.xlsx'), 200
