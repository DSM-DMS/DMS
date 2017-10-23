RULE_POST = {
    'tags': ['관리자 - 게시글'],
    'description': '기숙사규정 업로드',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'title',
            'description': '기숙사규정 제목',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'content',
            'description': '기숙사규정 내용',
            'in': 'formData',
            'type': 'str'
        }
    ],
    'responses': {
        '201': {
            'description': '기숙사규정 업로드 성공'
        }
    }
}

RULE_PATCH = {
    'tags': ['관리자 - 게시글'],
    'description': '기숙사규정 수정',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'id',
            'description': '수정할 기숙사규정 ID',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'title',
            'description': '기숙사규정 제목',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'content',
            'description': '기숙사규정 내용',
            'in': 'formData',
            'type': 'str'
        }
    ],
    'responses': {
        '200': {
            'description': '수정 성공'
        }
    }
}

RULE_DELETE = {
    'tags': ['관리자 - 게시글'],
    'description': '기숙사규정 삭제',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'id',
            'description': '삭제할 기숙사규정 ID',
            'in': 'formData',
            'type': 'str'
        }
    ],
    'responses': {
        '200': {
            'description': '삭제 성공'
        }
    }
}
