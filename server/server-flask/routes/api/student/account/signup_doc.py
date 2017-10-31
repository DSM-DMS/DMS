ID_VERIFICATION_POST = {
    'tags': ['회원가입'],
    'description': 'ID 중복체크',
    'parameters': [
        {
            'name': 'id',
            'description': '중복 여부를 체크할 ID',
            'in': 'formData',
            'type': 'str'
        }
    ],
    'responses': {
        '201': {
            'description': 'ID 중복되지 않음'
        },
        '204': {
            'description': 'ID 중복됨'
        }
    }
}

UUID_VERIFICATION_POST = {
    'tags': ['회원가입'],
    'description': 'UUID 유효성 검사',
    'parameters': [
        {
            'name': 'uuid',
            'description': '가입 가능 여부를 체크할 UUID',
            'in': 'formData',
            'type': 'str'
        }
    ],
    'responses': {
        '201': {
            'description': '가입 가능한 UUID'
        },
        '204': {
            'description': '가입 불가능한 UUID'
        }
    }
}

SIGNUP_POST = {
    'tags': ['회원가입'],
    'description': '회원가입',
    'parameters': [
        {
            'name': 'uuid',
            'description': 'UUID',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'id',
            'description': '사용자 ID',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'pw',
            'description': '사용자 PW',
            'in': 'formData',
            'type': 'str'
        }
    ],
    'responses': {
        '201': {
            'description': '가입 완료'
        },
        '400': {
            'description': '가입 불가능(유효하지 않은 UUID)'
        }
    }
}
