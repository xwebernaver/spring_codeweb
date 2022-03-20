package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyService service;
	
	
	@Test
	public void testRegister(){
		log.info("�׽�ƮS Reply Start!");
		
		ReplyVO reply = new ReplyVO();
		reply.setBno(3932647L);
		reply.setReply("�׽�Ʈ�ڵ� ���005");
		reply.setReplyer("user405");	
		
		int insertCount = service.register(reply);
		
		log.info("�׽�Ʈ Reply Count : " + insertCount);
		
		
	}
	
	
	
	
}
