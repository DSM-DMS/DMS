# DMS Flask Server
## Technical Stack
- Python & Flask
- Jinja2
- MongoDB & MongoEngine(for ODM)
- JWT
- Swagger
- Cafe24 Ubuntu

## 3-rd Party Libraries
- Flask
- Flask-restful-swagger-2
- Flask-JWT-Extended
- Flask-CORS
- MongoEngine
- OpenPyXl
- PyMySQL
- PyMongo
- requests
- schapi

## Utilities
- Pigar(For requirements.txt)
- Isort(For refactor import)

## Code Style
### 1. 기본적으로 PEP8 표준을 따릅니다.
- 클래스명은 Pascal Case, 변수와/함수/메소드명은 snake_case로 네이밍합니다.
- import 순서는 built-in - 3rd-party - user-generated 패키지 순으로 합니다.
- 알파벳 순으로 import를 정리합니다. 어렵다면 isort의 도움을 받도록 합니다.

### 2. PEP8에서 무시하는 스타일
- 효율적인 API 리소스 매핑을 위해, 모든 import를 모듈 최상단에서 하도록 하는 스타일을 무시합니다.

### 3. Flask 패턴
- config.py 모듈에서 모든 설정들을 관리합니다. Secrey Key나 데이터베이스 비밀번호와 같은 민감한 정보는 os 모듈을 이용해 환경 변수 형태로 관리합니다.
~~~
app.config.from_pyfile('config.py')
~~~
- app 객체의 데코레이터들을 활용해 request 전/후 과정에 모두 로그 코드를 작성합니다. before_first_request, before_request, after_request, teardown_appcontext 데코레이터를 사용하는 것을 지향합니다.
~~~
@app.before_first_request
def before_first_request():
    ...

@app.before_request
def before_request():
    ...
~~~
- Blueprint를 통해 Flask-restful로 api 객체를 얻고, API 배치 후 app 객체에 blueprint를 묶어 줍니다. API 엔드포인트가 겹치는 문제를 해결할 수 있습니다.
~~~
def deploy(app):
    blueprint = Blueprint('API', __name__)
    api = Api(blueprint, api_version='0.1')

    api.add_resource( ... )

    app.register_blueprint(blueprint)
~~~
- API 리소스의 메소드는 post-get-patch-delete 순으로 둡니다.

### 4. Swagger
- API-Description에 서버의 URL과 주요 Status Code들의 설명을 달아 줍니다.
- API 메소드에 한 줄짜리 docstring을 달아 API summary를 표현합니다.
- tag 기준으로 API들을 정리하며, description은 구체적으로 작성합니다.
- 파라미터들은 name, description, in, type, required 필드를 가져야 합니다.

### 5. 구조
- 최상단에 main.py, config.py, logger.py, resource.py를 두고 라우팅 모듈은 /routes/api와 /routes/templates 패키지 하위에 둡니다.
- 데이터베이스 스키마 모듈은 /db/models 패키지 하위에 둡니다.

## TODO
- [X] Package Structure
- [X] Server core
- [X] Schema
- [X] JWT Authentication
- [ ] API
- [ ] Swagger UI
- [ ] Template rendering
- [ ] MySQL Migrator
- [ ] Tests
- [ ] ELK Stack
