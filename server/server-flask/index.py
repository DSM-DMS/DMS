from flask import render_template
from flask_restful_swagger_2 import Resource

class Index(Resource):
    uri = '/'

    def get(self):
        return render_template('new_index.html')