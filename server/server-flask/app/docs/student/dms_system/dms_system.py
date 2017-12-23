BUG_REPORT_POST = {
    'tags': ['DMS 시스템'],
    'description': '버그 신고',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str',
            'required': True
        },
        {
            'name': 'title',
            'description': '버그 신고 제목',
            'in': 'formData',
            'type': 'str',
            'required': True
        },
        {
            'name': 'content',
            'description': '버그 신고 내용',
            'in': 'formData',
            'type': 'str',
            'required': True
        }
    ],
    'responses': {
        '201': {
            'description': '버그 신고 성공'
        },
        '403': {
            'description': '권한 없음'
        }
    }
}
