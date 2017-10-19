from flask import Flask
from flask_cors import CORS
from flask_jwt import JWT

import preprocessor
import config


def create_app():
    """
    Creates Flask instance & initialize

    :rtype: Flask
    """
    app = Flask(__name__)
    app.config.update(
        SECRET_KEY=config.SECRET_KEY,
        JWT_AUTH_URL_RULE='/signin',
        JWT_AUTH_USERNAME_KEY='id',
        JWT_AUTH_PASSWORD_KEY='pw'
    )

    CORS(app)
    # JWT(app, authenticate, identity)

    preprocessor.decorate(app)
    preprocessor.add_resources(app)

    return app

_app = create_app()


if __name__ == '__main__':
    _app.run(debug=True)
