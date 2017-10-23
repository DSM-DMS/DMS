from mongoengine import *

from main import app

connect(app.config['DB_NAME'])
