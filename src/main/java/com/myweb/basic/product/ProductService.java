package com.myweb.basic.product;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.myweb.basic.command.CategoryVO;
import com.myweb.basic.command.ProductVo;
import com.myweb.basic.command.UploadVO;
import com.myweb.basic.util.Criteria;

public interface ProductService {

	public boolean productRegist(ProductVo vo, List<MultipartFile> files);
	
	//public List<ProductVo> getList(); //조회
	public List<ProductVo> getList(Criteria cri); //페이징 조회
	public int getTotal(Criteria cri); 	//전체게시글 수 
	
	public ProductVo getDetail(int prod_id); //상세조회
	public List<UploadVO> getDetailImg(int prod_id); //상세이미지조회
	
	public boolean productUpdate(ProductVo vo); //수정
	
	public boolean productDelete(int prod_id); //삭제
	
	//카테고리
	public List<CategoryVO> getCategory(); //1단
	public List<CategoryVO> getCategoryChild(CategoryVO vo); // 2단, 3단
}
