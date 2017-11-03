from flask import Flask
from flask_cors import CORS
from flask_jwt_extended import JWTManager

import logger

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
    JWTManager(app)

    logger.decorate(app)

    from blueprints import all_blueprints
    for bp in all_blueprints:
        app.register_blueprint(bp)

    return app

_app = create_app()


if __name__ == '__main__':
    # db_migrator.migrate_posts()
    # meal.parse()

    _app.run(port=_app.config['PORT'], threaded=True, debug=True)
