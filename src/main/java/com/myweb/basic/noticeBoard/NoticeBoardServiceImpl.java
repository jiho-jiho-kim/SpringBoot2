package com.myweb.basic.noticeBoard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.basic.command.NoticeBoardVo;

@Service("noticeBoardService")
public class NoticeBoardServiceImpl implements NoticeBoardService {
	
	@Autowired
	NoticeBoardMapper noticeBoardMapper;

	@Override
	public boolean noticeRegist(NoticeBoardVo vo) {
		
		return noticeBoardMapper.noticeRegist(vo);
	}

	@Override
	public List<NoticeBoardVo> getListMe() {
		
		return noticeBoardMapper.getListMe();
	}

	@Override
	public List<NoticeBoardVo> getListAll() {
		
		return noticeBoardMapper.getListAll();
	}

	@Override
	public NoticeBoardVo getDetail(int nb_no) {
		
		return noticeBoardMapper.getDetail(nb_no);
	}
	
}
