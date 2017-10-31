from flask import Response
from flask_jwt_extended import get_jwt_identity, jwt_required
from flask_restful_swagger_2 import Resource, request, swagger

from db.models.account import AdminModel
from db.models.afterschool import AfterSchoolItemModel, AfterSchoolModel
from routes.api.admin.apply import afterschool_doc


class AfterSchool(Resource):
    @swagger.doc(afterschool_doc.AFTERSCHOOL_POST)
    @jwt_required
    def post(self):
        """
        방과후 신청 set 추가
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        start_date = request.form.get('start_date')
        end_date = request.form.get('end_date')
        content = request.form.get('content')

        AfterSchoolModel(start_date=start_date, end_date=end_date, content=content).save()

        return Response('', 201)

    @swagger.doc(afterschool_doc.AFTERSCHOOL_DELETE)
    @jwt_required
    def delete(self):
        """
        방과후 신청 set 제거
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        id = request.form.get('id')

        AfterSchoolModel.objects(id=id).delete()

        return Response('', 200)


class AfterSchoolItem(Resource):
    @swagger.doc(afterschool_doc.AFTERSCHOOL_ITEM_POST)
    @jwt_required
    def post(self):
        """
        방과후 아이템 추가
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
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

    @swagger.doc(afterschool_doc.AFTERSCHOOL_ITEM_DELETE)
    @jwt_required
    def delete(self):
        """
        방과후 아이템 제거
        """
        admin = AdminModel.objects(id=get_jwt_identity()).first()
        if not admin:
            # Forbidden
            return Response('', 403)

        id = request.form.get('id')

        afterschool = AfterSchoolModel.objects().first()

        afterschool_items = afterschool.items
        afterschool_items.remove(AfterSchoolItemModel(id=id))

        afterschool.update(items=afterschool_items)

        return Response('', 200)
