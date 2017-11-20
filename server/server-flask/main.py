from flask import Flask
from flask_cors import CORS
from flask_jwt_extended import JWTManager

import config as cf
from logger import Logger

# from support import db_migrator
# from support.api_interaction import meal

cors = CORS()
jwt = JWTManager()
logger = Logger()


def create_app(config_name):
    """
    Creates Flask instance & initialize

    :rtype: Flask
    """
    app = Flask(__name__)
    app.config.from_object(cf.config[config_name])
    app.config.from_pyfile('config.py')

    cors.init_app(app)
    jwt.init_app(app)
    logger.init_app(app)

    from blueprints import all_blueprints
    for bp in all_blueprints:
        app.register_blueprint(bp)

    return app

_app = create_app('dev')


if __name__ == '__main__':
    # db_migrator.migrate_posts()
    # meal.parse()

    _app.run(threaded=True)
