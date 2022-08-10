package com.myweb.basic.util;

import com.myweb.basic.command.UserVO;

import lombok.Data;

@Data // getter, setter, toString
public class Criteria extends UserVO {  //criteria에서 userVO를 사용하기 위해
	private int page; //페이지번호
	private int amount; //데이터 개수
	
	//검색키워드 추가
	private String searchName; //상품명
	private String searchContent; //상품내용
	private String searchPrice; //상품가격
	private String startDate; //시작일
	private String endDate; //종료일
	
	
	//ctrl + space + enter
	public Criteria() {
		this(1,10);
	}
	
	//alt + shift + s -> construct using field
	public Criteria(int page, int amount) {
		super();
		this.page = page;
		this.amount = amount;
	}
	
	//limit (페이지 - 1) * 데이터개수, 데이터개수
	//limit 함수의 앞에 전달될 값
	public int getPageStart() {
		return (page - 1) * amount;
	}
}
