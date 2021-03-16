package com.liquoreview.model.service.member;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.liquoreview.exception.DeleteFailException;
import com.liquoreview.exception.EditFailException;
import com.liquoreview.exception.RegistFailException;
import com.liquoreview.model.domain.admin.Auth;
import com.liquoreview.model.repository.member.AuthDAO;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

	@Autowired
	@Qualifier("mybatisAuthDAO")
	AuthDAO authDAO;

	Logger logger = Logger.getLogger(this.getClass().getName());

	public List<Auth> selectAll() {
		List<Auth> authList = authDAO.selectAll();
		logger.info("authList 중 timestamp 리턴을 확인한다. 리스트 중 첫 객체의 regdate확인 : " + authList.get(0).getRegdate());
		return authList;
	}

	public Auth select(int auth_id) {
		return authDAO.select(auth_id);
	}

	public int insert(Auth auth) {
		logger.info("auth 확인 : "+auth);
		logger.info(auth.isAdm_assign());
		logger.info(auth.isMem_adm());
		logger.info(auth.isCate_adm());
		logger.info(auth.isAlc_adm());
		logger.info(auth.isRev_adm());
		logger.info(auth.isRev_comm_adm());
		logger.info(auth.isBoard_adm());
		logger.info(auth.isBoard_comm_adm());
		//Field[] fieldList = auth.getClass().getDeclaredFields();
		//logger.info("fieldList 확인 : "+fieldList);
				
		//Field[] nullCheckField;
		//List<Field> boolTypeField = new ArrayList<Field>();
		/*
		for(Field obj : fieldList) {
			if(obj.getType().getName().equals("boolean")) {
				boolTypeField.add(obj);
			}
		}
		*/
		/*
		for(Field field : boolTypeField) {
			Object value = null;
			field.setAccessible(true);
			try {
				value = field.get(auth);
				
				if(ObjectUtils.isEmpty(value)) {
					logger.info("the field value of auth's bool type memer has a null value."); }
				
				logger.info(value);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		*/
		int result=authDAO.insert(auth);
		return result;
	}

	public JSONObject update(Auth auth) throws EditFailException{
		logger.info("authServiceImpl update method에서 받은 auth 확인 : "+auth);
		int result = 0;
		JSONObject resultObj = new JSONObject();
		if(auth.getAuth_id() > 1) {//권한 목록 중 기본적으로 1개는 수정 불가 처리
			result= authDAO.update(auth);
			logger.info("result 확인 : "+result);
		}
		if(result==0) {
			logger.info("hoxy update 실패면 여기");
			resultObj.put("resultCode", "0");
			resultObj.put("msg", "권한수정 실패");
			throw new EditFailException("권한 수정 실패");
		} else {
			logger.info("hoxy update 성공이면 여기");
			resultObj.put("resultCode", "1");
			resultObj.put("msg", "권한수정 성공");
		}
		return resultObj;
	}

	public void delete(int auth_id) throws DeleteFailException{
		int result = 0;
		if(auth_id > 2) {//권한 목록 중 기본적으로 2개는 수정 불가 처리
			result= authDAO.delete(auth_id);
		}
		if(result==0) {
			throw new DeleteFailException("권한 삭제 실패");
		}
	}
	
	public void delete(List<Integer> deleteList) throws DeleteFailException {
		logger.info(deleteList);
		int result = 0;
		//deleteList로부터 auth_id추출
		for(Integer num : deleteList) {
			logger.info(num);
			int auth_id = num;
			logger.info(auth_id);
			result = authDAO.delete(auth_id);
			if (result==0) {
				throw new DeleteFailException("권한 삭제 실패");
			}
		}
	}

	@Override
	public void update(List<Auth> updateList) {
		// TODO Auto-generated method stub
		
	}
}
