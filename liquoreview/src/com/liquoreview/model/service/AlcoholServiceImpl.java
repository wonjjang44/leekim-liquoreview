package com.liquoreview.model.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liquoreview.common.Criteria;
import com.liquoreview.common.NewPager;
import com.liquoreview.common.StringUtil;
import com.liquoreview.model.domain.Alcohol;
import com.liquoreview.model.repository.AlcoholDAO;
import com.liquoreview.model.repository.SequenceDAO;

@Service("AlcoholService")
public class AlcoholServiceImpl implements AlcoholService{
	@Resource(name = "AlcoholDAO")
	private AlcoholDAO alcoholDAO;
	
	@Resource(name = "SequenceDAO")
	private SequenceDAO sequenceDAO;
	
	Logger logger = Logger.getLogger(this.getClass().getName());

	//@Autowired
	//private TransactionTemplate transactionTemplate;
	
	/**
	 * 술 정보 전체조회 + 페이징
	 * @author 이양원
	 * @date 2021. 03. 11  최초생성
	 * 			  개정이력  2021. 04. 18  
	 * @param 
	 * */
	@Override
	public List<Alcohol> alcoholLst(Criteria cri) {

		return alcoholDAO.alcoholLst(cri);
	}

	/**
	 * 상위 카테고리 전체조회
	 * @author 이양원
	 * @date 2021. 03. 17  최초생성
	 * @param 
	 * */
	@Override
	public List<Alcohol> topLst() {

		return alcoholDAO.topLst();
	}

	/**
	 * 하위 카테고리 전체조회
	 * @author 이양원
	 * @date 2021. 03. 17  최초생성
	 * @param param 
	 * */
	@Override
	public List<Alcohol> subLst(Map<String, Object> param) {

		return alcoholDAO.subLst(param);
	}

	/**
	 * 상위 카테고리 등록
	 * @author 이양원
	 * @date 2021. 03. 22  최초생성
	 * @param
	 * */
	@Override
	public int topCateReg(Alcohol vo) {

		return topCateReg(vo);
	}

	/**
	 * 하위 카테고리 등록
	 * @author 이양원
	 * @date 2021. 03. 22  최초생성
	 * @param
	 * */
	@Override
	public int subCateReg(Alcohol vo) {
		
		return subCateReg(vo);
	}

	/**
	 * 주류 등록
	 * Transactional 어노테이션 사용으로 인한 트랜잭션 처리(이미지 등록 여부에 상관없이 주류 및 주류 이미지 테이블에 동시에 insert)
	 * @author 이양원
	 * @date 2021. 03. 22  최초생성
	 * 개정이력  2021. 06. 05  주류 등록 시 주류 이미지 등록도 동시에 진행(트랜잭션 추가) 
	 *                                 조건 추가 => 파일명이 존재할 경우에만 주류 이미지테이블에 insert
	 *                                 개선 사항 => 주류정보를 등록할 때 다중이미지가 들어온다면 주류 이미지테이블에 
	 *                                                  레코드가 다중으로 insert 돼야 하는지? 아니면 하나의 레코드에 여러개의 이미지명이 들어가야하는지?
	 *            
	 *            2021. 06. 12   로직 변경 => 이미지 파일이 존재하지 않는다면 주류 테이블에만 insert되는 것으로 변경 
	 * @param param
	 * */
	@Transactional
	@Override
	public int alcoholReg(Map<String, Object> param) {
		//AOP 관련 개념이 들어가므로 주석처리. AOP를 공부하고 적용할 것.(pointCut)
		
		/*logger.debug(">>>>>>>>>>>>>>>>>> 트랜잭션 처리 Start >>>>>>>>>>>>>>>>>>>>");
		transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		
		int result = transactionTemplate.execute(new TransactionCallback<Integer>() {
			public Integer doInTransaction(TransactionStatus status) {
				int regCnt = 0;
				
				try {
					regCnt+= alcoholDAO.alcoholReg(param);
					regCnt+= alcoholDAO.alcImgReg(param);
					
					logger.debug("%%%%%%%%%%% regCnt : "+regCnt);
				}catch(Exception e) {
					e.printStackTrace();
					status.setRollbackOnly();
				}
				
				return regCnt;
			}
		});
		
		logger.debug(">>>>>>>>>>>>>>>>>> 트랜잭션 처리 End >>>>>>>>>>>>>>>>>>>>");
		*/
		
		int regCnt = 0;
		
		//기존 파일명 절대경로 수정
		String FILENAME = StringUtil.null2void(param.get("FILENAME").toString().replace("C:\\fakepath\\", ""));
		param.put("FILENAME", FILENAME);
		
		//파일명 존재여부
		boolean fileExsist = StringUtil.null2void(param.get("FILENAME").toString()) != "" ? true : false;
		
		try {
			logger.debug("파일명(FILENAME): >>>>> "+param.get("FILENAME")); 
			logger.debug("파일명 존재여부 확인 : >>>>> "+ fileExsist);
			
			if(fileExsist == true) {//true일 때 파일명 존재, false라면 파일명 존재x
				int alcImgSequence = sequenceDAO.selectAlcImgSequence();
				param.put("ALC_IMG_ID", alcImgSequence);
				
				regCnt+= alcoholDAO.alcoholReg(param);
				regCnt+= alcoholDAO.alcImgReg(param);
			}else {//false라면 == 파일명이 존재하지 않을 때
				param.put("ALC_IMG_ID", null);
				
				regCnt+= alcoholDAO.alcoholReg(param);
			}
			
			logger.debug("%%%%%%%%%%% regCnt : "+regCnt);
		}catch(Exception e) {
			e.printStackTrace();
		}

		return regCnt;
	}

	/**
	 * 게시물 총 갯수
	 * @date 2021. 04. 18
	 *            개정이력 2021. 06. 12 함수명 변경
	 * */
	@Override
	public int alcLstAllCnt() {
		
		return alcoholDAO.alcLstAllCnt();
	}

	/**
	 * 주류 정보 상세보기
	 * @author 이양원
	 * @date 2021. 05. 31  최초생성
	 * @param alcoholId
	 * */
	@Override
	public Alcohol alcoholLstIqrDtl(int alcoholId) {
		
		return alcoholDAO.alcoholLstIqrDtl(alcoholId);
	}
	
	/**
	 * 주류 이미지 등록
	 * @author 이양원
	 * @date 2021. 06. 04  최초생성
	 * @param param
	 * */
	@Override
	public int alcImgReg(Map<String, Object> param) {
		
		return alcoholDAO.alcImgReg(param);
	}

	/**
	 * 주류 정보 삭제
	 * @author 이양원
	 * @date 2021. 06. 04  최초생성
	 *            개정이력 2021. 06. 05 
	 *                       주류 정보 삭제시 이미지 파일이 존재한다면 주류 이미지 파일도 동시에 삭제
	 *                       트랜잭션 적용
	 * @param alcohol_id
	 * */
	@Transactional
	@Override
	public int alcoholDel(int alcohol_id) {
		int delCnt = 0;
		
		try {
			delCnt += alcoholDAO.alcoholDel(alcohol_id);
			delCnt += alcoholDAO.alcoholImgDel(alcohol_id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		logger.debug("%%%%%%%%%%% regCnt : "+delCnt);
		return delCnt;
	}

	/**
	 * 주류 이미지 정보 삭제
	 * @author 이양원
	 * @date 2021. 06. 05  최초생성
	 * @param alcohol_id
	 * */
	@Override
	public int alcoholImgDel(int alcohol_id) {
		
		return alcoholDAO.alcoholImgDel(alcohol_id);
	}
	
	

}
