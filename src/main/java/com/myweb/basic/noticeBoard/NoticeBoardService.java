package com.myweb.basic.noticeBoard;

import java.util.List;

import com.myweb.basic.command.NoticeBoardVo;

public interface NoticeBoardService {

	public boolean noticeRegist(NoticeBoardVo vo);
	
	public List<NoticeBoardVo> getListMe();
	
	public List<NoticeBoardVo> getListAll();
	
	public NoticeBoardVo getDetail(int nb_no);
}
