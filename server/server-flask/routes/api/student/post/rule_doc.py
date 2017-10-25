RULE_LIST_GET = {
    'tags': ['게시글'],
    'description': '기숙사규정 리스트',
    'responses': {
        '200': {
            'description': '기숙사규정 리스트 조회 성공',
            'examples': {
                'application/json': [
                    {
                        'id': 'fkdo13kroafdgmb',
                        'title': '치킨 먹으면 상점주는 규정',
                        'author': '사감부',
                        'write_date': '2017-10-11'
                    },
                    {
                        'id': 'eoqk1kcmdkallw',
                        'title': '아침운동 안나오면 인증제 점수 가산점 규정',
                        'author': '사감부',
                        'write_date': '2017-10-13'
                    },
                    {
                        'id': 'p1o9d0vmzjswek',
                        'title': '오버워치 장려 규정',
                        'author': '사감부',
                        'write_date': '2017-10-16'
                    }
                ]
            }
        },
    }
}

RULE_GET = {
    'tags': ['게시글'],
    'description': '기숙사규정 내용 조회',
    'parameters': [
        {
            'name': 'id',
            'description': '조회할 기숙사규정 아이템의 ID',
            'in': 'path',
            'type': 'str'
        }
    ],
    'responses': {
        '200': {
            'description': '기숙사규정 조회 성공',
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
            'description': '기숙사규정 조회 실패(존재하지 않는 ID)'
        }
    }
}
