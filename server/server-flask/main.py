from flask import Flask
from flask_cors import CORS
from flask_jwt import JWT

import auth
import logger
import resource

from support import db_migrator
from support.api_interaction import meal


def create_app():
    """
    Creates Flask instance & initialize

    :rtype: Flask
    """
    app = Flask(__name__)
    app.config.from_pyfile('config.py')

    CORS(app)

    app.config['JWT_AUTH_URL_RULE'] = '/auth/student'
    JWT(app, auth.student_auth, auth.identity)

    app.config['JWT_AUTH_URL_RULE'] = '/auth/admin'
    JWT(app, auth.admin_auth, auth.identity)

    logger.decorate(app)
    resource.deploy(app)

    return app

_app = create_app()


if __name__ == '__main__':
    db_migrator.migrate_posts()
    meal.parse()
    _app.run(port=_app.config['PORT'], threaded=True, debug=True)
