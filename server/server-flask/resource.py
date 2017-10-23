from flask_restful_swagger_2 import Api

from routes.api.student.account.after_signup import ChangeNumber, ChangePW
from routes.api.student.account.signup import Signup, UUIDVerification


def deploy(app):
    """
    :type app: Flask

    :rtype: None
    """
    api = Api(app, api_version=app.config['API_VER'], title=app.config['API_TITLE'], description=app.config['API_DESC'])

    api.add_resource(UUIDVerification, '/uuid-verify')
    api.add_resource(Signup, '/signup/student')

    api.add_resource(ChangePW, '/change/pw')
    api.add_resource(ChangeNumber, '/change/number')
