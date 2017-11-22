FAQ_LIST_GET = {
    'tags': ['게시글'],
    'description': 'FAQ 리스트',
    'responses': {
        '200': {
            'description': 'FAQ 리스트 조회 성공',
            'examples': {
                'application/json': [
                    {
                        'id': 'fkdo13kroafdgmb',
                        'title': '치킨 먹는법',
                        'author': '교촌치킨',
                        'write_date': '2017-10-11'
                    },
                    {
                        'id': 'eoqk1kcmdkallw',
                        'title': 'KTX 싸게 예매하는법',
                        'author': '코레일',
                        'write_date': '2017-10-13'
                    },
                    {
                        'id': 'p1o9d0vmzjswek',
                        'title': '서울에서 먹고살기',
                        'author': '갓석진',
                        'write_date': '2017-10-16'
                    }
                ]
            }
        },
    }
}

FAQ_GET = {
    'tags': ['게시글'],
    'description': 'FAQ 내용 조회',
    'parameters': [
        {
            'name': 'id',
            'description': '조회할 FAQ 아이템의 ID',
            'in': 'path',
            'type': 'str',
            'required': True
        }
    ],
    'responses': {
        '200': {
            'description': 'FAQ 조회 성공',
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
            'description': 'FAQ 조회 실패(존재하지 않는 ID)'
        }
    }
}
