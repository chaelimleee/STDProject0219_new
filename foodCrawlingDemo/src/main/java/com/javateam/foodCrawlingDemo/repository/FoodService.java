package com.javateam.foodCrawlingDemo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.javateam.foodCrawlingDemo.domain.FoodVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FoodService {
	
	@Autowired
	private FoodRepository foodRepository;
	
	public void saveCrawData(FoodVO foodvo) throws Exception{
		try {
	        log.info("비포 데이터");
	        log.info(" 값 잘 넘어 왔는지 확인. ==> "+ foodvo);
	        foodRepository.save(foodvo);
	        System.out.println(foodvo.getId());
	        log.info("애프터 데이터");
	     // 트랜잭션이 커밋된 경우에만 로그 출력
            if (TransactionSynchronizationManager.isActualTransactionActive()) {
                boolean isCommitted = TransactionSynchronizationManager.isActualTransactionActive();
                log.info("트랜잭션이 커밋되었습니다. : " + isCommitted);
            } else {
                log.info("트랜잭션이 활성화되지 않았습니다.");
            }
	    } catch (Exception e) {
	    	log.error("에러 발생! 롤백을 시작합니다.", e);
	            throw e; // 여기에 추가해서 예외를 다시 던지도록 수정
	    }
	}
	
	

}
