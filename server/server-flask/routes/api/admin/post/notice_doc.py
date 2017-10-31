NOTICE_POST = {
    'tags': ['게시글'],
    'description': '공지사항 업로드',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'title',
            'description': '공지사항 제목',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'content',
            'description': '공지사항 내용',
            'in': 'formData',
            'type': 'str'
        }
    ],
    'responses': {
        '201': {
            'description': '공지사항 업로드 성공'
        }
    }
}

NOTICE_PATCH = {
    'tags': ['게시글'],
    'description': '공지사항 수정',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'id',
            'description': '수정할 공지사항 ID',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'title',
            'description': '공지사항 제목',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'content',
            'description': '공지사항 내용',
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

NOTICE_DELETE = {
    'tags': ['게시글'],
    'description': '공지사항 삭제',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'id',
            'description': '삭제할 공지사항 ID',
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
