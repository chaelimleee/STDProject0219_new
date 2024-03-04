-- 페이징 적용
-- 페이지당 10개씩의 레코드 조회
-- rownum : 번호 출력
-- 인라인 뷰
SELECT *  
FROM (SELECT m.*,  
             FLOOR((ROWNUM - 1) / 10 + 1) page  
      FROM (
             SELECT *
			 FROM users
             ORDER BY userid ASC
           ) m  
      )  
WHERE page = 1;

