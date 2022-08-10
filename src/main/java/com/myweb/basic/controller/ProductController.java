package com.myweb.basic.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.basic.command.ProductVo;
import com.myweb.basic.command.UploadVO;
import com.myweb.basic.product.ProductService;
import com.myweb.basic.util.Criteria;
import com.myweb.basic.util.PageVo;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	@Qualifier("productService")
	ProductService productService;
	
	
	@GetMapping("/productReg")
	public String productReg() {
		return "product/productReg";
	}
	
	@GetMapping("/productList")
	public String productList(Model model, Criteria cri, HttpSession session) {
		
		//System.out.println(session.getAttribute("userId"));
		String userId = (String)session.getAttribute("userId");
		cri.setUserId(userId);
		
		
		//1st - admin 기반으로 조회(나중에 세션값으로 변경)
		//List<ProductVo> list = productService.getList();
		//model.addAttribute("list", list);
		
		//2nd - 페이지 (cri로 page, amount를 받는다)
//		List<ProductVo> list = productService.getList(cri);  //데이터
//		int total = productService.getTotal();	//전체 게시글 수
//		PageVo pageVo = new PageVo(cri, total);	//페이지네이션
//		
//		model.addAttribute("list", list);
//		model.addAttribute("pageVo", pageVo);
		
		//3nd
		List<ProductVo> list = productService.getList(cri); 
		int total = productService.getTotal(cri);	//전체 게시글 수
		PageVo pageVo = new PageVo(cri, total);	//페이지네이션
		
		model.addAttribute("list", list);
		model.addAttribute("pageVo", pageVo);
		
		
		return "product/productList";
	}

	@GetMapping("/productDetail")
	public String productDetail(@RequestParam("prod_id") int prod_id, Model model) {
		
		/*
		 * 1.화면에서 넘어오는 prod_id 값을 받습니다.
		 * 2.getDetail(prod_id) 형식으로 select한 결과를 vo에 저장합니다
		 * 3.model에 vo를 저장합니다.
		 * 4.화면에는 vo의 결과값을 출력해주세요.
		 */
		
		ProductVo vo = productService.getDetail(prod_id);
		model.addAttribute("vo", vo);
		
		//상품 이미지를 조회
		List<UploadVO> list = productService.getDetailImg(prod_id);
		model.addAttribute("uploadList", list);
		
		
		
		
		return "product/productDetail";
	}
	
//	@PostMapping("/productForm")
//	public String productForm(@Valid ProductVo vo, Errors errors, Model model) {
//		
//		if(errors.hasErrors()) {
//			List <FieldError> list = errors.getFieldErrors();
//			for(FieldError err : list) {
//				if(err.isBindingFailure()) {
//					model.addAttribute("valid" + err.getField(), "형식을 일치시켜주세요");
//				}else {
//					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage() );
//				}
//			}
//			
//			//다시 등록화면으로
//			model.addAttribute("vo", vo);
//			return "product/productReg";
//		}
//		
//		
//		//상품등록
//		boolean result = productService.productRegist(vo);
//				
//		return "redirect:/product/productList";
//		
//		
//	}
	
	@PostMapping("/productForm")
	public String productForm(@Valid ProductVo vo, Errors errors, Model model, 
							  @RequestParam("file") List<MultipartFile> files) {
		
		if(errors.hasErrors()) {
			List <FieldError> list = errors.getFieldErrors();
			for(FieldError err : list) {
				if(err.isBindingFailure()) {
					model.addAttribute("valid" + err.getField(), "형식을 일치시켜주세요");
				}else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage() );
				}
			}
			
			//다시 등록화면으로
			model.addAttribute("vo", vo);
			return "product/productReg";
		}
		
		//파일데이터(첫번째 공백데이터 제거, 두번째 img만 받을 수 있도록 처리) 지금은 어떻게 나오는지 확인만한거임
//		for(MultipartFile f : files) {
//			System.out.println(f.getContentType());
//			System.out.println(f.isEmpty());
//		}
		
		//공백데이터 제거
		files = files.stream().filter( (f) -> !f.isEmpty()).collect(Collectors.toList());
		//이미지 파일검증
		for(MultipartFile f : files) {
			if(f.getContentType().contains("image") == false) { //이미지가 아닌경우
				//다시 등록화면으로
				model.addAttribute("vo", vo);
				model.addAttribute("valid_files", "이미지형식만 등록가능합니다");
				return "product/productReg";
			}
		}
		
		
		//상품등록
		boolean result = productService.productRegist(vo, files); //상품데이터, 이미지데이터
				
		return "redirect:/product/productList";
		
		
	}
	
	//수정기능 
	@PostMapping("/productUpdate")
	public String productUpdate(ProductVo vo, RedirectAttributes RA) {
			
		boolean result = productService.productUpdate(vo);
		//메시지처리(리다이렉트 시 1회성 메시지를 보내는 방법)
		if(result) {
			RA.addFlashAttribute("msg", "정상 수정 되었습니다");
		} else {
			RA.addFlashAttribute("msg", "정보 수정에 실패했습니다");
		}
			
		return "redirect:/product/productList";
	}
	
	//삭제
	@PostMapping("/productDelete")
	public String productDelete(@RequestParam("prod_id") int prod_id, RedirectAttributes RA) {
		
		/*
		 * 1. productDelete(int) 메서드를 이용해서 삭제를 진행한다.
		 * 2. 삭제 키값은 prod_id
		 * 3. 삭제 성공실패 여부에 따라서 msg변수에 메시지를 담아보내주세요.
		 */
		
		boolean result = productService.productDelete(prod_id);
		
		if(result) {
			RA.addFlashAttribute("msg", "삭제 성공하였습니다");
		} else {
			RA.addFlashAttribute("msg", "삭제 실패하였습니다");
		}
		
		return "redirect:/product/productList";
	}
	
	
}
