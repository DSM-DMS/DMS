from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful import Resource, request
from flasgger import swag_from

from app.docs.admin.apply.afterschool import *
from app.models.account import AdminModel
from app.models.afterschool import AfterSchoolItemModel, AfterSchoolModel


class AdminAfterSchool(Resource):
    uri = '/admin/afterschool'

    @swag_from(AFTERSCHOOL_POST)
    @jwt_required
    def post(self):
        """
        방과후 신청 set 추가
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            return Response('', 403)

        start_date = request.form.get('start_date')
        end_date = request.form.get('end_date')
        content = request.form.get('content')

        AfterSchoolModel(start_date=start_date, end_date=end_date, content=content).save()

        return Response('', 201)

    @swag_from(AFTERSCHOOL_DELETE)
    @jwt_required
    def delete(self):
        """
        방과후 신청 set 제거
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            return Response('', 403)

        id = request.form.get('id')

        AfterSchoolModel.objects(id=id).delete()

        return Response('', 200)


class AdminAfterSchoolItem(Resource):
    uri = '/admin/afterschool/item'

    @swag_from(AFTERSCHOOL_ITEM_POST)
    @jwt_required
    def post(self):
        """
        방과후 아이템 추가
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            return Response('', 403)

        id = request.form.get('id')
        title = request.form.get('title')
        on_monday = request.form.get('on_monday', type=bool)
        on_tuesday = request.form.get('on_tuesday', type=bool)
        on_saturday = request.form.get('on_saturday', type=bool)
        target = request.form.get('target', type=list)

        afterschool = AfterSchoolModel.objects(id=id).first()

        if not afterschool:
            return Response('', 204)

        afterschool_items = afterschool.items
        afterschool_items.append(AfterSchoolItemModel(title=title, on_monday=on_monday, on_tuesday=on_tuesday, on_saturday=on_saturday, target=target))

        afterschool.update(items=afterschool_items)

        return Response('', 201)

    @swag_from(AFTERSCHOOL_ITEM_DELETE)
    @jwt_required
    def delete(self):
        """
        방과후 아이템 제거
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            return Response('', 403)

        id = request.form.get('id')

        afterschool = AfterSchoolModel.objects().first()

        afterschool_items = afterschool.items
        afterschool_items.remove(AfterSchoolItemModel(id=id))

        afterschool.update(items=afterschool_items)

        return Response('', 200)
