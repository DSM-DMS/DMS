import socket

from config.base import *

HOST = socket.gethostbyname(socket.gethostname())
SWAGGER.update({'host': '{0}:{1}'.format('dsm2015.cafe24.com', PORT)})

DEBUG = False

MONGODB_SETTINGS = {
    'db': '{0}-production'.format(SERVICE_NAME),
    'host': 'localhost',
}
