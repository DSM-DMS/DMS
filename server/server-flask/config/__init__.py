import os
import socket
from datetime import timedelta

HOST, PORT = socket.gethostbyname(socket.gethostname()), 3000

SECRET_KEY = os.getenv('SECRET_KEY', '85c145a16bd6f6e1f3e104ca78c6a102')
# Secret key for any 3-rd party libraries

JWT_ACCESS_TOKEN_EXPIRES = timedelta(days=3)
JWT_REFRESH_TOKEN_EXPIRES = timedelta(days=60)
JWT_HEADER_TYPE = 'JWT'
# http://flask-jwt-extended.readthedocs.io/en/latest/options.html

API_VER = '1.0'
API_TITLE = 'DMS'
API_DESC = '''
### [BASE URL] http://{0}:{1}

JWT Access Token의 유효기간은 {2}일, Refresh Token의 유효기간은 {3}일입니다.

- Status Code 1xx : Informational
- Status Code 2xx : Success
- Status Code 3xx : Redirection
- Status Code 4xx : Client Error
- Status Code 5xx : Server Error

##### <a href="https://httpstatuses.com/">[All of HTTP status code]</a>
##### <a href="http://meetup.toast.com/posts/92">[About REST API]</a>
##### <a href="http://jinja.pocoo.org/docs/2.10/">[About Jinja2]</a>
##### <a href="https://velopert.com/2389">[About JWT]</a>
'''.format(HOST, PORT, JWT_ACCESS_TOKEN_EXPIRES.days, JWT_REFRESH_TOKEN_EXPIRES.days)
# For flask-restful-swagger-2
