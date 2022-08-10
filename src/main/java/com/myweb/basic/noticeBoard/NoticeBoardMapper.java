package com.myweb.basic.noticeBoard;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myweb.basic.command.NoticeBoardVo;

@Mapper
public interface NoticeBoardMapper {
	
	public boolean noticeRegist(NoticeBoardVo vo);
	
	public List<NoticeBoardVo> getListMe();
	
	public List<NoticeBoardVo> getListAll();

}
