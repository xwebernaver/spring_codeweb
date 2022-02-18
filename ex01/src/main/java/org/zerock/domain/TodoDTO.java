package org.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.InitBinder;

import lombok.Data;

@Data
public class TodoDTO {
	private String title;
	
	// @DateTimeFormat 사용할 경우 @InitBinder의 날짜 포멧 메서드 필요 없음
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date dueDate;
}
