from flask import send_from_directory
from flask_restful import Resource
from flask_jwt import current_identity, jwt_required

import openpyxl

from db.models.account import AdminModel, StudentModel


class CheckStay(Resource):
    @jwt_required()
    def get(self):
        if not AdminModel.objects(id=current_identity):
            return '', 403

        wb = openpyxl.load_workbook('명렬표.xlsx')
        ws = wb.active
        status = ''

        for row in map(str, range(3, 68)):
            for column1, column2 in zip(['B', 'F', 'J', 'N'], ['D', 'H', 'L', 'P']):
                student = StudentModel.objects(number=ws[column1+row]).first()

                if not student:
                    continue

                if student.stay_apply.value == '1':
                    status = '금요 귀가'
                elif student.stay_apply.value == '2':
                    status = '토요 귀가'
                elif student.stay_apply.value == '3':
                    status = '토요 귀사'
                elif student.stay_apply.value == '4':
                    status = '잔류'
                ws[column2+row] = status

        wb.save('명렬표.xlsx')
        return send_from_directory('주소오오오', '명렬표.xlsx'), 200
