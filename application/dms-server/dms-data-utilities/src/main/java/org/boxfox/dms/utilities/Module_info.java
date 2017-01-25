package org.boxfox.dms.utilities;
/*
 * 해당 모듈은 DataBase 접근, 쿼리 편집, 급식/학사일정/게시글 파싱 등의 기능을 위한 유틸리티.
 * 코드에 대한 전체적인 주석은 프로젝트를 마친 뒤 리펙토링 단계에서 자세하게 기술할 예정.
 * 
 * 1. DataBase 클래스 사용법
 *  해당 클래스는 DataBase에 대한 접근권을 하나로 유지시키기 위해 사용되며 쓰레드 동기화 기술 및 SafeResultSet을 사용한다.
 *  - DataBase.getInstance().execute(쿼리);
 *  - ResultSet rs = DataBase.getInstance().executeQuery(쿼리);
 *  - int num = DataBase.getInstance().executeUpdate(쿼리);
 *  - DataBase.getInstance().execute(DataSaveAble);
 *  
 *   < com.boxfox.dms.utilities.database.SafeResultSet >
 *    - 하나의 connection을 이용하여 쿼리를 다루게 되면 사용중인 ResultSet의 변경이 일어나 충돌이 일어날 수 있음
 *    - 이를 방지하기 위해 ResultSet을 Object로 저장하여 다른 쿼리결과에 영향을 받지 않도록 함
 *    - 사용법은 기본 ResultSet의 인터페이스와 동일함
 * 
 * 2. QueryUtils 클래스 사용법
 * DataBase에 사용될 Query를 작성하는대에 도움을 주는 클래스
 *  - String query = QueryUtils.queryBuilder(Object ... args);
 *  - String query = QueryUtils.querySetter(쿼리 포맷, Object ... args); //쿼리 포맷에 맞춰 args를 삽입한 쿼리 생성
 *  - String formatedDate = QueryUtils.queryCreateDate(year,month,day); //yyyy-MM-dd
 *  - String formatedDate = QueryUtils.queryCreateDate(year, month, day, hour, minute, second) //yyyy-MM-dd HH:mm:ss
 *  - boolean isFormatedDate = QueryUtils.checkDate(dateStr) //해당 String이 날짜규격에 맞는지 판별
 *  
 *  3. PlanModel 클래스 사용법
 *  학사일정을 조회하는 클래스.
 *  -  DataSaveAble obj = PlanModel.getPlan(year, month) 
 *  
 *  4. MealModel 클래스 사용법
 *  급식을 조회하는 클래스
 *  - DataSaveAble obj = MealModel.getMealAtDate(year,month,day)
 *  
 *  5. PostChangeDetector 사용법
 *  학교 홈페이지의 게시글의 변화를 실시간으로 검사하고 업데이트 되거나 파싱되지 않은 게시글을 파싱
 *  PostChangeDetector.getInstance().start();
 *  
 *  6. PostModel 사용법
 *  - DataSaveAble obj = PostModel.get(no) //게시글의 고유번호를 통한 탐색
 *  - DataSaveAble obj = PostModel.get(category, number) //게시글의 카테고리와 홈페이지의 게시글 번호를 통한 탐색
 *  - DataSaveAble obj = PostModel.get(title) //게시글의 제목을 통한 탐색
 *  
 * 7. DataSaveAble 사용법 
 *  JSONObject obj = dataSaveAbleObj.toJSONObject() //전송을 위한 JSONObject화
 *  DataBase.getInstance().execute(dataSaveAbleObj) //Query로 바로 수행
 */
