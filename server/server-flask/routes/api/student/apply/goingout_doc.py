GOINGOUT_GET = {
    'tags': ['신청'],
    'description': '외출신청 정보 조회',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        }
    ],
    'responses': {
        '200': {
            'description': '외출신청 정보 조회 성공',
            'examples': {
                'application/json': {
                    'sat': True,
                    'sun': False
                }
            }
        },
        '204': {
            'description': '외출신청 정보 없음'
        }
    }
}

GOINGOUT_POST = {
    'tags': ['신청'],
    'description': '외출신청',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'sat',
            'description': '토요일 외출 여부',
            'in': 'formData',
            'type': 'bool'
        },
        {
            'name': 'sun',
            'description': '일요일 외출 여부',
            'in': 'formData',
            'type': 'bool'
        }
    ],
    'responses': {
        '201': {
            'description': '연장신청 성공'
        },
        '204': {
            'description': '연장신청 실패(신청 가능 시간 아님)'
        }
    }
}
