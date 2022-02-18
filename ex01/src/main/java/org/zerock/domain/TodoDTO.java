package org.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.InitBinder;

import lombok.Data;

@Data
public class TodoDTO {
	private String title;
	
	// @DateTimeFormat ����� ��� @InitBinder�� ��¥ ���� �޼��� �ʿ� ����
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date dueDate;
}
