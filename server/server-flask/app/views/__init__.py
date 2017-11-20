import pkgutil

from flask import Blueprint
from flask_restful_swagger_2 import Api

from app.views import admin, student


class Swagger(object):
    def __init__(self, app=None):
        self._global_resources = set()

        if app is not None:
            self.init_app(app)

    def _modules(self, packages):
        modules = set()

        def search(target):
            for loader, name, is_package in pkgutil.iter_modules(target.__path__):
                if is_package:
                    search(loader.find_module(name).load_module(name))
                else:
                    modules.add((loader, name))

        for pkg in packages:
            search(pkg)

        return modules

    def _factory(self, app, packages, bp_endpoint, url_prefix='', api_spec_url='/api/swagger', api_ver=None, api_title=None, api_desc=None):
        api_ver = api_ver or app.config['API_VER']
        api_title = api_title or app.config['API_TITLE']
        api_desc = api_desc or app.config['API_DESC']

        bp = Blueprint(bp_endpoint, __name__, url_prefix=url_prefix)
        api = Api(bp, api_spec_url=api_spec_url, api_version=api_ver, title=api_title, description=api_desc)

        resources = set()

        for loader, name in self._modules(packages):
            module_ = loader.find_module(name).load_module(name)
            try:
                for res in module_.Resource.__subclasses__():
                    if res not in self._global_resources:
                        resources.add(res)
                        self._global_resources.add(res)
            except AttributeError:
                pass

        for res in resources:
            api.add_resource(res, res.uri)

        return bp

    def init_app(self, app):
        bp_admin = self._factory(app, [admin], 'Admin', url_prefix='/admin', api_spec_url='/api/admin', api_title='DMS Admin API')
        bp_student = self._factory(app, [student], 'Student', api_spec_url='/api/student', api_title='DMS Student API')

        all_blueprints = (bp_admin, bp_student)

        for bp in all_blueprints:
            app.register_blueprint(bp)
