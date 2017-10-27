AFTERSCHOOL_POST = {
    'tags': ['방과후 신청'],
    'description': '방과후 신청 set 추가',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'start_date',
            'description': '방과후 신청 시작일(YYYY-MM-DD)',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'end_date',
            'description': '방과후 신청 종료일(YYYY-MM-DD)',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'content',
            'description': '방과후 신청 개요',
            'in': 'formData',
            'type': 'str'
        }
    ],
    'responses': {
        '201': {
            'description': '방과후 신청 set 추가 성공'
        }
    }
}

AFTERSCHOOL_DELETE = {
    'tags': ['방과후 신청'],
    'description': '방과후 신청 set 제거',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'id',
            'description': '방과후 신청 set의 ID',
            'in': 'formData',
            'type': 'str'
        }
    ],
    'responses': {
        '200': {
            'description': '방과후 신청 set 제거 성공'
        }
    }
}

AFTERSCHOOL_ITEM_POST = {
    'tags': ['방과후 신청'],
    'description': '방과후 아이템 추가',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'id',
            'description': '방과후 신청 set의 ID',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'title',
            'description': '방과후 아이템의 제목',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'on_monday',
            'description': '월요일에 진행',
            'in': 'formData',
            'type': 'bool'
        },
        {
            'name': 'on_tuesday',
            'description': '화요일에 진행',
            'in': 'formData',
            'type': 'bool'
        },
        {
            'name': 'on_saturday',
            'description': '토요일에 진행',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'target',
            'description': '대상 학년',
            'in': 'formData',
            'type': 'list'
        }
    ],
    'responses': {
        '201': {
            'description': '아이템 추가 성공'
        },
        '204': {
            'description': '아이템 추가 실패(존재하지 않는 방과후 set ID)'
        }
    }
}

AFTERSCHOOL_ITEM_DELETE = {
    'tags': ['방과후 신청'],
    'description': '방과후 아이템 제거',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'id',
            'description': '방과후 아이템 ID',
            'in': 'formData',
            'type': 'str'
        }
    ],
    'responses': {
        '200': {
            'description': '아이템 제거 성공'
        }
    }
}
