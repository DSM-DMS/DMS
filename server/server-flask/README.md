# DMS Flask Server
## Technical Stack
- Python & Flask
- Jinja2
- MongoDB & MongoEngine(for ODM)
- JWT
- Swagger
- Cafe24 Ubuntu
## Changed Logics
1. 사용자 식별  
전 : Primary key가 가입 시 제공됐던 UID, Cookie와 Session 기반으로 식별  
현 : UID는 순전 가입용, Primary key는 가입 시 입력하는 ID, JWT 기반으로 식별
