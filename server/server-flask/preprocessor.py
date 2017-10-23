from logging import INFO, Formatter
from logging.handlers import RotatingFileHandler

from flask import Flask, current_app
from flask_restful_swagger_2 import Api

from routes.api.student.account.signup import UUIDVerification, Signup
from routes.api.student.account.after_signup import ChangePW, ChangeNumber


def decorate(app):
    """
    :type app: Flask
    :rtype: None
    """
    @app.before_first_request
    def before_first_request():
        def make_logger():
            handler = RotatingFileHandler('server_log.log', maxBytes=100000, backupCount=5)
            handler.setFormatter(Formatter("[%(asctime)s] %(levelname)s - %(message)s"))

            current_app.logger.addHandler(handler)
            current_app.logger.setLevel(INFO)

        make_logger()
        current_app.logger.info('------ Logger Initialized ------')

    @app.before_request
    def before_request():
        current_app.logger.info('Requested from {0} [ {1} {2} ]'.format(request.host, request.method, request.url))
        current_app.logger.info('Request values : {0}'.format(request.values))

    @app.after_request
    def after_request(response):
        current_app.logger.info('Respond : {0}'.format(response.status))

        return response

    @app.teardown_request
    def teardown_request(exception):
        if not exception:
            current_app.logger.info('Teardown request successfully.')

    @app.teardown_appcontext
    def teardown_appcontext(exception):
        if not exception:
            current_app.logger.info('Teardown appcontext successfully.')


def add_resources(app):
    """
    :type app: Flask

    :rtype: None
    """
    api = Api(app, api_version=app.config['API_VER'], title=app.config['API_TITLE'], description=app.config['API_DESC'])

    api.add_resource(UUIDVerification, '/uuid-verify')
    api.add_resource(Signup, '/signup/student')

    api.add_resource(ChangePW, '/change/pw')
    api.add_resource(ChangeNumber, '/change/number')
