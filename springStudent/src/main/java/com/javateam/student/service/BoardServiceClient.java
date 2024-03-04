package com.javateam.student.service;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.javateam.student.domain.BoardVO;


public class BoardServiceClient {

	public static void main(String[] args) {
		
		//AbstractApplicationContext : 컨테이너 종료(close)와 같은 기능을 제공해 주는 객체.
		//GenericXmlApplicationContext 객체를 생성할 때 파라미터 값으로 xml의 경로를 전달하여 설정 파일로 사용함.
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		 
		BoardService boardService = (BoardService) container.getBean("boardService");
		
		String[]  str1={"김","이","박","최","나","석","윤"};
		String[]  str2={"둘리","하니","똘이","영심","만수","지효","하늘이"};
		
		String[]  title1={"초급","중급","고급","실무"};
		String[]  title2={"ASP","JSP","PHP","Spring","Spring Boot","파이썬"};
		
		for(int i = 1 ; i <= 50 ; i++) {
			int k1 = (int) (Math.random()*7) ;
			int k2 = (int) (Math.random()*7) ;
			
			int t1 = (int) (Math.random()*4) ;
			int t2 = (int) (Math.random()*6) ;
			
			BoardVO vo = new BoardVO();
			vo.setTitle(title1[t1] + " " + title1[t1] + " 수강하기");
			vo.setWriter(str1[k1]+str2[k2]);
			vo.setContent(str1[k1] + str2[k2] + " " + title1[t1] + " " + title1[t1] + " 수강하기");
			boardService.insert(vo);
		}
		
		BoardVO vo2 = new BoardVO();
		List<BoardVO> li = boardService.getBoardList(vo2);
		int i = 0 ;
		for(BoardVO m : li) {
			System.out.println("==>" + m.toString());
			i = i + 1;
		}
		
		System.out.println("전체레코드 수 : " + i);
		container.close();
		
		
		

	}

}
