CHANGE_PW_POST = {
    'tags': ['계정 정보 변경'],
    'description': '비밀번호 변경',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str',
            'required': True
        },
        {
            'name': 'current_pw',
            'description': '현재 비밀번호',
            'in': 'formData',
            'type': 'str',
            'required': True
        },
        {
            'name': 'new_pw',
            'description': '바꿀 비밀번호',
            'in': 'formData',
            'type': 'str',
            'required': True
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
            'type': 'str',
            'required': True
        },
        {
            'name': 'new_number',
            'description': '바꿀 학번',
            'in': 'formData',
            'type': 'int',
            'required': True
        }
    ],
    'responses': {
        '201': {
            'description': '학번 변경 성공'
        }
    }
}

MYPAGE_GET = {
    {
        'tags': ['계정 정보'],
        'description': '마이페이지 정보 조회',
        'parameters': [
            {
                'name': 'Authorization',
                'description': 'JWT Token',
                'in': 'header',
                'type': 'str',
                'required': True
            }
        ],
        'responses': {
            '200': {
                'description': '마이페이지 조회 성공',
                'examples': {
                    'application/json': {
                        'name': '조민규',
                        'signup_date': '2017-10-10',
                        'number': 20120,
                        'extension_class': None,
                        'extension_seat': None,
                        'goingout_sat': True,
                        'goingout_sun': False,
                        'stay_value': 4
                    }
                }
            },
            '204': {
                'description': '해당 학생 정보 없음(재로그인 필요)'
            }
        }
    }
}
