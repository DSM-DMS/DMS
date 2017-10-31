EXTENSION_GET = {
    'tags': ['신청'],
    'description': '연장신청 정보 조회',
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
            'description': '연장신청 정보 조회 성공',
            'examples': {
                'application/json': {
                    'class': 1,
                    'seat': 16
                }
            }
        },
        '204': {
            'description': '연장신청 정보 없음'
        }
    }
}

EXTENSION_POST = {
    'tags': ['신청'],
    'description': '연장신청',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'class',
            'description': '''
            연장 학습실 번호
            1: 가온실
            2: 나온실
            3: 다온실
            4: 라온실
            5: 3층 독서실
            6: 4층 독서실
            7: 5층 열린교실
            ''',
            'in': 'formData',
            'type': 'int'
        },
        {
            'name': 'seat',
            'description': '연장 학습실 자리 번호',
            'in': 'formData',
            'type': 'int'
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
