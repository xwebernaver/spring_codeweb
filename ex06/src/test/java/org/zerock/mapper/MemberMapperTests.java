package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class MemberMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
	@Test
	public void testRead() {
		log.info("==스타또 testRead() ==================================");
		try {
			log.info("== try ==================================");
			MemberVO vo = mapper.read("admin90");
			log.info("vo[로그] : " + vo);
		
		vo.getAuthList().forEach(authVO -> log.info("authVO[로그] : " + authVO));
		} catch (Exception e) {
			log.info("[어러] " + e.getMessage());
		}
		
		
		
	}
	

}
