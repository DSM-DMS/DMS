from flask import Flask
from flask_cors import CORS
from flask_jwt import JWT

import preprocessor


def create_app():
    """
    Creates Flask instance & initialize

    :rtype: Flask
    """
    app = Flask(__name__)
    app.config.from_pyfile('config.py')

    CORS(app)
    # JWT(app, authenticate, identity)

    preprocessor.decorate(app)
    preprocessor.add_resources(app)

    return app

_app = create_app()


if __name__ == '__main__':
    _app.run(debug=True)
