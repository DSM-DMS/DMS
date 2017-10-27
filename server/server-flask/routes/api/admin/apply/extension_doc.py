EXTENSION_GET = {
    'tags': ['신청 정보'],
    'description': '연장신청 정보 다운로드',
    'parameters': [
        {
            'name': 'Authorization',
            'description': 'JWT Token',
            'in': 'header',
            'type': 'str'
        }
    ],
    'responses': {
        '200': {
            'description': '연장신청 정보 추출 성공. 엑셀 파일 응답'
        }
    }
}
