import pkgutil

from flask import Blueprint
from flask_restful_swagger_2 import Api

import config

from routes.api import admin, developer, student, index

cf = config.Config()


def _modules(packages):
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

_global_resources = set()


def _factory(packages, bp_endpoint, url_prefix='', api_spec_url='/api/swagger', api_ver=cf.API_VER, api_title=cf.API_TITLE, api_desc=cf.API_DESC):
    bp = Blueprint(bp_endpoint, __name__, url_prefix=url_prefix)
    api = Api(bp, api_spec_url=api_spec_url, api_version=api_ver, title=api_title, description=api_desc)

    resources = set()

    for loader, name in _modules(packages):
        module_ = loader.find_module(name).load_module(name)
        try:
            for res in module_.Resource.__subclasses__():
                if res not in _global_resources:
                    resources.add(res)
                    _global_resources.add(res)
        except AttributeError:
            pass

    for res in resources:
        api.add_resource(res, res.uri)

    return bp

bp_admin = _factory([admin], 'Admin', url_prefix='/admin', api_spec_url='/api/admin', api_title='DMS Admin API')
bp_student = _factory([student], 'Student', api_spec_url='/api/student', api_title='DMS Student API')
bp_index = _factory([index], 'Index')

all_blueprints = (bp_admin, bp_student, bp_index)
