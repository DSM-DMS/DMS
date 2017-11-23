import socket

from config.base import *

HOST = socket.gethostbyname(socket.gethostname())
SWAGGER.update({'host': '{0}:{1}'.format(HOST, PORT)})

DEBUG = False

MONGODB_SETTINGS = {
    'db': 'tellin.production',
    'host': 'localhost',
}
