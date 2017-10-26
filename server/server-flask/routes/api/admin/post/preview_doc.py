FAQ_PREVIEW_GET = {
    'tags': ['관리자 - 게시글 프리뷰'],
    'description': 'FAQ 프리뷰 설정',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'id',
            'description': 'FAQ ID',
            'in': 'formData',
            'type': 'str'
        }
    ],
    'responses': {
        '201': {
            'description': '프리뷰 설정 성공'
        }
    }
}

NOTICE_PREVIEW_GET = {
    'tags': ['관리자 - 게시글 프리뷰'],
    'description': '공지사항 프리뷰 설정',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'id',
            'description': '공지사항 ID',
            'in': 'formData',
            'type': 'str'
        }
    ],
    'responses': {
        '201': {
            'description': '프리뷰 설정 성공'
        }
    }
}

RULE_PREVIEW_GET = {
    'tags': ['관리자 - 게시글 프리뷰'],
    'description': '기숙사규정 프리뷰 설정',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'id',
            'description': '기숙사규정 ID',
            'in': 'formData',
            'type': 'str'
        }
    ],
    'responses': {
        '201': {
            'description': '프리뷰 설정 성공'
        }
    }
}
