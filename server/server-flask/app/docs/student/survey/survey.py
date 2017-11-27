SURVEY_LIST_GET = {
    'tags': ['기숙사'],
    'description': '설문조사 리스트 불러오기(학생 학년에 따라 필터링됨)',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str',
            'required': True
        }
    ],
    'responses': {
        '200': {
            'description': '설문조사 리스트 불러오기 성공',
            'examples': {
                'application/json': [
                    {
                        'id': 's3qldmc13opeflds',
                        'title': '내일 저녁 치킨먹기 찬반설문',
                        'start_date': '2017-10-24',
                        'end_date': '2017-10-25'
                    },
                    {
                        'id': '1fnfdj3391idkflds',
                        'title': '등교 후 12시간 자습 찬반설문',
                        'start_date': '2017-10-24',
                        'end_date': '2017-10-30'
                    }
                ]
            }
        }
    }
}

SURVEY_GET = {
    'tags': ['기숙사'],
    'description': '설문조사 질문 리스트 불러오기',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str',
            'required': True
        },
        {
            'name': 'id',
            'description': '설문조사 ID',
            'in': 'path',
            'type': 'str',
            'required': True
        }
    ],
    'responses': {
        '200': {
            'description': '질문 리스트 불러오기 성공',
            'examples': {
                'application/json': [
                    {
                        'id': '13211265df16ads',
                        'title': '저녁에 치킨을 먹고 싶습니까?',
                        'is_objective': True,
                        'choice_paper': ['예', '아니오']
                    },
                    {
                        'id': '11265cd65432r9',
                        'title': '어디 치킨이 좋습니까?',
                        'is_objective': False
                    }
                ]
            }
        }
    }
}

SURVEY_POST = {
    'tags': ['기숙사'],
    'description': '답변 남기기',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str',
            'required': True
        },
        {
            'name': 'id',
            'description': '질문 ID',
            'in': 'path',
            'type': 'str',
            'required': True
        },
        {
            'name': 'answer',
            'description': '답변',
            'in': 'formData',
            'type': 'str',
            'required': True
        }
    ],
    'responses': {
        '201': {
            'description': '답변 남기기 성공'
        }
    }
}
