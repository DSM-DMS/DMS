from flask import Flask
from flask_restful_swagger_2 import Api

import config


def decorate(app):
    """
    :type app: Flask
    :rtype: None
    """
    @app.before_first_request
    def before_first_request():
        pass

    @app.before_request
    def before_request():
        pass

    @app.after_request
    def after_request(response):
        return response

    @app.teardown_request
    def teardown_request(exception):
        pass

    @app.teardown_appcontext
    def teardown_appcontext(exception):
        pass


def add_resources(app):
    """
    :type app: Flask

    :rtype: None
    """
    api = Api(app, api_version=app.config['API_VER'], title=app.config['API_TITLE'], description=app.config['API_DESC'])
