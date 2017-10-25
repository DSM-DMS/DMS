from flask import Flask
from flask_cors import CORS
from flask_jwt import JWT

import jwt
import logger
import resource


def create_app():
    """
    Creates Flask instance & initialize

    :rtype: Flask
    """
    app = Flask(__name__)
    app.config.from_pyfile('config.py')

    CORS(app)

    app.config['JWT_AUTH_URL_RULE'] = '/auth/student'
    JWT(app, jwt.student_auth, jwt.identity)

    app.config['JWT_AUTH_URL_RULE'] = '/auth/admin'
    JWT(app, jwt.admin_auth, jwt.identity)

    logger.decorate(app)
    resource.deploy(app)

    return app

_app = create_app()


if __name__ == '__main__':
    _app.run(threaded=True, debug=True)
