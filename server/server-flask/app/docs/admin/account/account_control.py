INITIALIZE_ACCOUNT_POST = {
    'tags': ['관리자 계정'],
    'description': '학생 계정 초기화',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str',
            'required': True
        },
        {
            'name': 'number',
            'description': '초기화할 학생의 학번',
            'in': 'formData',
            'type': 'int',
            'required': True
        }
    ],
    'responses': {
        '201': {
            'description': '학생 계정 초기화 성공. 새로운 UUID 발급',
            'examples': {
                'application/json': {
                    'uuid': 'd27a3b'
                }
            }
        },
        '403': {
            'description': '권한 없음'
        }
    }
}
