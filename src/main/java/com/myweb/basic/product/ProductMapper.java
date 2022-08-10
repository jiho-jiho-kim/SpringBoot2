package com.myweb.basic.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myweb.basic.command.CategoryVO;
import com.myweb.basic.command.ProductVo;
import com.myweb.basic.command.UploadVO;
import com.myweb.basic.util.Criteria;

@Mapper
public interface ProductMapper {

	public boolean productRegist(ProductVo vo); //등록
	public boolean productRegistFile(UploadVO vo); //파일등록
	
	
	// public List<ProductVo> getList(); //조회
	
	public List<ProductVo> getList(Criteria cri); //페이징 조회
	
	public int getTotal(Criteria cri); 	//전체게시글 수 
	
	public ProductVo getDetail(int prod_id); //상세
	public List<UploadVO> getDetailImg(int prod_id); //상세이미지조회
	
	public boolean productUpdate(ProductVo vo); //수정
	
	public boolean productDelete(int prod_id); //삭제
	
	//카테고리
	public List<CategoryVO> getCategory(); //1단
	public List<CategoryVO> getCategoryChild(CategoryVO vo); // 2단, 3단
}
