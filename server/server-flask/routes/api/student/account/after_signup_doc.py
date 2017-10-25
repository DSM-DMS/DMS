CHANGE_PW_POST = {
    'tags': ['계정 정보 변경'],
    'description': '비밀번호 변경',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'current_pw',
            'description': '현재 비밀번호',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'new_pw',
            'description': '바꿀 비밀번호',
            'in': 'formData',
            'type': 'str'
        }
    ],
    'responses': {
        '201': {
            'description': '비밀번호 변경 성공'
        },
        '403': {
            'description': '비밀번호 변경 실패(틀린 비밀번호)'
        }
    }
}

CHANGE_NUMBER_POST = {
    'tags': ['계정 정보 변경'],
    'description': '학번 변경',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'new_number',
            'description': '바꿀 학번',
            'in': 'formData',
            'type': 'str'
        }
    ],
    'responses': {
        '201': {
            'description': '학번 변경 성공'
        }
    }
}
