import socket

from config import *

HOST = 'dsm2015.cafe24.com'
ENDPOINT = '{0}:{1}'.format(HOST, PORT)
SWAGGER.update({'host': ENDPOINT})

DEBUG = False

MONGODB_SETTINGS = {
    'db': '{0}-production'.format(SERVICE_NAME)
}
