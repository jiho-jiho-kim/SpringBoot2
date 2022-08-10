package com.myweb.basic.noticeBoard;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myweb.basic.command.NoticeBoardVo;

@Mapper
public interface NoticeBoardMapper {
	
	public boolean noticeRegist(NoticeBoardVo vo);
	
	public List<NoticeBoardVo> getListMe();	//내글 조회
	
	public List<NoticeBoardVo> getListAll(); //전체조회
	
	public NoticeBoardVo getDetail(int nb_no); //상세보기

}
