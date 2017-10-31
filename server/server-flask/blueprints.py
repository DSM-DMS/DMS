import pkgutil

from flask import Blueprint
from flask_restful_swagger_2 import Api

from routes.api import admin, developer, student
import config as cf


def _modules(packages):
    modules = []

    def search(target):
        for loader, name, is_package in pkgutil.iter_modules(target.__path__):
            if is_package:
                search(loader.find_module(name).load_module(name))
            else:
                modules.append((loader, name))

    for package in packages:
        search(package)

    return modules

_global_resources = []


def _factory(packages, endpoint, url_prefix='', api_spec_url='/api/swagger', api_ver=cf.API_VER, api_title=cf.API_TITLE, api_desc=cf.API_DESC):
    """
    :param packages: Package for Auto Route
    :type packages: list

    :param url_prefix: URL Prefix of Blueprint
    :type url_prefix: str

    :param api_spec_url: Swagger API Spec URL. default ='/api/swagger'
    :type api_spec_url: str

    :rtype: Blueprint
    """
    bp = Blueprint(endpoint, __name__, url_prefix=url_prefix)
    api = Api(bp, api_spec_url=api_spec_url, api_version=api_ver, title=api_title, description=api_desc)

    resources = set()

    for loader, name in _modules(packages):
        module_ = loader.find_module(name).load_module(name)
        try:
            for resource in module_.Resource.__subclasses__():
                if resource not in _global_resources:
                    resources.add(resource)
                    _global_resources.append(resource)
        except AttributeError:
            pass

    for resource in resources:
        api.add_resource(resource, resource.uri)

    return bp

bp_admin = _factory([admin], 'Admin', api_spec_url='/api/admin', api_title='DMS Admin API')
bp_student = _factory([student], 'Student', api_spec_url='/api/student', api_title='DMS Student API')

all_blueprints = (bp_admin, bp_student)
