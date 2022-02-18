package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j 
public class SampleTests {	
	
	// log4j 롬복 미사용시 -> private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(SampleTests.class);
	// java Logging 사용시 -> private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(SampleTests.class.getName());
	
	@Setter(onMethod_ = { @Autowired })
	private Restaurant restaurant;
	
	@Test
	public void testExit() {
		assertNotNull(restaurant);
		
		log.info(restaurant);
		log.info("--------------------------");
		log.info(restaurant.getChef());
	}
	
	

}
