FAQ_PREVIEW_GET = {
    'tags': ['프리뷰'],
    'description': 'FAQ 프리뷰 조회',
    'responses': {
        '200': {
            'description': '프리뷰 조회 성공',
            'examples': {
                'application/json': {
                    'id': 'fkdo13kroafdgmb',
                    'title': '치킨 먹는법',
                    'author': '교촌치킨',
                    'write_date': '2017-10-11',
                    'content': '교촌허니콤보를 웨지감자와 함께'
                }
            }
        },
        '204': {
            'description': '게시글이 하나도 없음'
        }
    }
}

NOTICE_PREVIEW_GET = {
    'tags': ['프리뷰'],
    'description': '공지사항 프리뷰 조회',
    'responses': {
        '200': {
            'description': '프리뷰 조회 성공',
            'examples': {
                'application/json': {
                    'id': 'fkdo13kroafdgmb',
                    'title': '내일 아침 11시 기상 안내',
                    'author': '사감부',
                    'write_date': '2017-10-16',
                    'content': '11시 기상 후 9시간동안 자습 줍니다. 그리고 하교 개꿀 동의? 어 보감~'
                }
            }
        },
        '204': {
            'description': '게시글이 하나도 없음'
        }
    }
}

RULE_PREVIEW_GET = {
    'tags': ['프리뷰'],
    'description': '기숙사규정 프리뷰 조회',
    'responses': {
        '200': {
            'description': '프리뷰 조회 성공',
            'examples': {
                'application/json': {
                    'id': 'fkdo13kroafdgmb',
                    'title': '오버워치 장려 규정',
                    'author': '사감부',
                    'write_date': '2017-10-16',
                    'content': '기숙사 내에서 오버워치 하면 꿀잼입니다!'
                }
            }
        },
        '204': {
            'description': '게시글이 하나도 없음'
        }
    }
}
