INITIALIZE_ACCOUNT = {
    'tags': ['관리자 - 계정'],
    'description': '학생 계정 초기화',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'number',
            'description': '초기화할 학생의 학번',
            'in': 'formData',
            'type': 'int'
        }
    ],
    'responses': {
        '201': {
            'description': '학생 계정 초기화 성공'
        }
    }
}
