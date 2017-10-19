import os

API_VER = '0.1'
API_TITLE = 'DMS'
API_DESC = '[ENDPOINT] http://dsm2015.cafe24.com'

SECRET_KEY = os.getenv('SECRET_KEY')
JWT_AUTH_URL_RULE = 'signin'
JWT_AUTH_USERNAME_KEY = 'id'
JWT_AUTH_PASSWORD_KEY = 'pw'
