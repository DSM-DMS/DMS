import os
from datetime import timedelta

PORT = 3000

API_VER = '0.1'
API_TITLE = 'DMS'
API_DESC = '''
[ENDPOINT] http://dsm2015.cafe24.com:{0}

- Status Code 401 UNAUTHORIZED : JWT 토큰 만료됨, 또는 토큰이 정상적으로 전달되지 않음
- Status Code 403 Forbidden : 권한 없음
- Status Code 500 Internal Server Error : 서버 내부 오류
'''.format(PORT)

SECRET_KEY = os.getenv('SECRET_KEY')
JWT_SECRET_KEY = SECRET_KEY
JWT_ACCESS_TOKEN_EXPIRES = timedelta(days=365)
JWT_HEADER_TYPE = 'JWT'
# http://flask-jwt-extended.readthedocs.io/en/latest/options.html

MYSQL_PW = os.getenv('MYSQL_PW')
DB_NAME = 'dms'
