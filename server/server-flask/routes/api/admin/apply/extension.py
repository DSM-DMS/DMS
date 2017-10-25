from flask import send_from_directory
from flask_restful import Resource
from flask_jwt import current_identity, jwt_required

import openpyxl

from db.models.account import AdminModel, StudentModel


class CheckExtension(Resource):
    @jwt_required()
    def get(self):
        if not AdminModel.objects(id=current_identity):
            return '', 403

        wb = openpyxl.load_workbook('명렬표.xlsx')
        ws = wb.active

        for row in map(str, range(3, 68)):
            for column1, column2 in zip(['B', 'F', 'J', 'N'], ['D', 'H', 'L', 'P']):
                student = StudentModel.objects(number=ws[column1+row]).first()
                status = ''
                cls = student.extension_apply.cls

                if not student:
                    continue

                if cls == '1':
                    status = '가온실'
                elif cls == '2':
                    status = '나온실'
                elif cls == '3':
                    status = '다온실'
                elif cls == '4':
                    status = '라온실'
                elif cls == '5':
                    status = '3층 독서실'
                elif cls == '6':
                    status = '4층 독서실'
                elif cls == '7':
                    status = '5층 독서실'

                ws[column2+row] = status

        wb.save('명렬표.xlsx')
        return send_from_directory('주소오오오', '명렬표.xlsx'), 200
