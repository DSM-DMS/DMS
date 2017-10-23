ADD_SURVEY = {
    'tags': ['관리자 - 설문조사'],
    'description': '설문조사 등록',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'title',
            'description': '설문조사 제목',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'start_date',
            'description': '시작 날짜(YYYY-MM-DD)',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'end_date',
            'description': '종료 날짜(YYYY-MM-DD)',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'target_1st',
            'description': '1학년 대상 여부',
            'in': 'formData',
            'type': 'bool'
        },
        {
            'name': 'target_2st',
            'description': '2학년 대상 여부',
            'in': 'formData',
            'type': 'bool'
        },
        {
            'name': 'target_3st',
            'description': '3학년 대상 여부',
            'in': 'formData',
            'type': 'bool'
        }
    ],
    'responses': {
        '201': {
            'description': '설문조사 등록 성공'
        }
    }
}

ADD_QUESTION ={
    'tags': ['관리자 - 설문조사'],
    'description': '설문조사에 질문 등록',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        },
        {
            'name': 'id',
            'description': '질문을 추가할 설문조사 ID',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'title',
            'description': '질문 제목',
            'in': 'formData',
            'type': 'str'
        },
        {
            'name': 'is_objective',
            'description': '객관식 여부',
            'in': 'formData',
            'type': 'bool'
        },
        {
            'name': 'choice_paper',
            'description': '객관식 선택지',
            'in': 'formData',
            'type': 'list'
        }
    ],
    'responses': {
        '201': {
            'description': '질문 추가 성공'
        }
    }
}
