package com.myweb.basic.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class PageVo {
	
	//criteria 클래스를 받아서 화면에 그려질 페이지네이션을 계산
	private int start;	//페이지네이션 시작번호
	private int end;	//페이지네이션 끝번호
	private boolean prev;	//페이지네이션 이전버튼
	private boolean next;	//페이지네이션 다음버튼
	
	private int total;	//전체 게시글 수
	private int page;	//조회하는 페이지번호(cri)
	private int amount;	//조회하는 데이터개수(cri)
	private int realEnd; // 실제 끝번호
	private Criteria cri; //페이지 기준 클래스
	
	private List <Integer> pageList; //start~end값 저장
	
	//생성자는 반드시 Criteria클래스와 total을 받는다.
	public PageVo(Criteria cri, int total) {
		this.page = cri.getPage();
		this.amount = cri.getAmount();
		this.total = total;
		this.cri = cri;
		
		//페이지 계산
		
		//끝페이지 계산
		//page 번호를 1~10 클릭 -> 10, page번호를 11~20 -> 20
		//시작페이지 = (int)(Math.ceil(조회하는 페이지번호 / 보여질 페이지네이션)) * 보여질 페이지네이션;
		this.end = (int)(Math.ceil(this.page / 10.0)) * 10;
		
		//첫페이지 계산
		//끝페이지 - 보여질 페이지네이션  + 1
		this.start = this.end - 10 + 1; 
		
		//실제 끝번호 realEnd
		//총 게시글이 53개 -> 실제 끝번호는 6
		//총 게시글이 112개 -> 실제 끝번호는 12
		//실제끝번호 = (int)(Math.ceil( 전체게시글 수 / 화면에 뿌려지는 데이터 개수))
		this.realEnd = (int)(Math.ceil(this.total / (double)this.amount ));
		
		//끝페이지의 재계산
		//게시글이 412개 -> 마지막페이지에서: 끝페이지: 50, 실제끝번호는 42 -> 실제끝번호 따라가야
		//			  -> 20~30번 나머지페이지에서: 끝페이지: 30, 실제 끝번호는 42 -> 끝페이지 
		if(this.end > this.realEnd) {
			this.end = this.realEnd;
		}
		
		//이전페이지 여부
		//시작페이지는 항상 1, 11, 21, 31 ... 이기 때문에 11이상부터는 true 물론 지금은 10단위로 한거임
		this.prev = this.start > 1;
		
		//다음페이지 여부
		this.next = this.realEnd > this.end;
		
		//타임리프는 일반반복문을 제공해주지 않기 때문에 list형태로 start~end값을 저장할 것이다.
//		this.pageList = new ArrayList<>();
//		for(int i = this.start; i <= this.end; i++) {
//			pageList.add(i);
//		}
		
		//위처럼 해도 되고 이건 람다식인데 어렵다.
		this.pageList = IntStream.rangeClosed(this.start, this.end).boxed().collect(Collectors.toList());
		
		
		
	}
	
	
}
