package com.lec.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.lec.domain.Category;
import com.lec.domain.Goods;
import com.lec.domain.Member;
import com.lec.domain.PageInfo;
import com.lec.service.CategoryService;
import com.lec.service.GoodsService;
import com.lec.service.MemberService;

@Controller
@SessionAttributes({"loginss", "pageInfo"})
public class AdminController {
	
	@Autowired
	private SessionCheckAspect sessionCheckAspect;
	@Autowired
	private MemberService memberService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private GoodsService goodsService;
	
	@Value("${path.upload}")
	public String uploadFolder;
	

	
	@GetMapping("/adminmain")
	public String adminMainCheck(SessionStatus status, HttpSession session) {
        return "redirect:admin_main";
    }

	@GetMapping("/admin_main")
	public String adminMain() {
		return "admin/admin_main";
	}

	
	@GetMapping("/admin_join")
	public String insertAdminView() {
		return "admin/admin_join";
	}
	
	@PostMapping("/insert_admin")
	public String insertAdmin(Member member) {
		memberService.insertAdmin(member);
		return "redirect:admin_list";
	}
	
	
	
	@RequestMapping("/member_list")
	public String memberList(Model model,
						@RequestParam(defaultValue = "0") int page,
						@RequestParam(defaultValue = "10") int pageSize,
						@RequestParam(defaultValue="id") String searchType,
						@RequestParam(defaultValue="") String searchWord) {
		
		Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").descending());
		Page<Member> pageResult = memberService.getMemberList(pageable, searchType, searchWord);
		
		
		List<Member> memberList = pageResult.getContent();
		
		for (Member member : memberList) {
		    System.out.println(member.toString());
		}
		
		int resultTotalCnt = pageResult.getNumberOfElements();
		
		System.out.println(resultTotalCnt);
		
		PageInfo pageInfo = new PageInfo((int)pageResult.getTotalElements(), page, pageSize, searchType, searchWord);
		
		model.addAttribute("pageable", pageable);		// 쿼리문 실행 전 페이징 정보
		model.addAttribute("pageInfo", pageInfo);		// 쿼리문 실행 후 페이징 정보
		model.addAttribute("pageResult", pageResult);	// 페이징 처리된 데이터
		
		model.addAttribute("pg", page);
		model.addAttribute("bp", pageInfo.getBeginPage());
		model.addAttribute("ep", pageInfo.getEndPage());
		model.addAttribute("ns", pageInfo.getNaviSize());
		model.addAttribute("ps", pageSize);
		model.addAttribute("tp", pageInfo.getTotalPage());
		model.addAttribute("st", searchType);
		model.addAttribute("sw", searchWord);	
		
		return "admin/member_list";
	}
	
	
	@RequestMapping("/admin_list")
	public String adminList(Model model,
						@RequestParam(defaultValue = "0") int page,
						@RequestParam(defaultValue = "10") int pageSize,
						@RequestParam(defaultValue="id") String searchType,
						@RequestParam(defaultValue="") String searchWord) {
		
		Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
		Page<Member> pageResult = memberService.getAdminList(pageable, searchType, searchWord);
		
		
		List<Member> memberList = pageResult.getContent();
		
		for (Member member : memberList) {
		    System.out.println(member.toString());
		}
		
		int resultTotalCnt = pageResult.getNumberOfElements();
		
		System.out.println(resultTotalCnt);
		
		PageInfo pageInfo = new PageInfo((int)pageResult.getTotalElements(), page, pageSize, searchType, searchWord);
		
		model.addAttribute("pageable", pageable);		// 쿼리문 실행 전 페이징 정보
		model.addAttribute("pageInfo", pageInfo);		// 쿼리문 실행 후 페이징 정보
		model.addAttribute("pageResult", pageResult);	// 페이징 처리된 데이터
		
		model.addAttribute("pg", page);
		model.addAttribute("bp", pageInfo.getBeginPage());
		model.addAttribute("ep", pageInfo.getEndPage());
		model.addAttribute("ns", pageInfo.getNaviSize());
		model.addAttribute("ps", pageSize);
		model.addAttribute("tp", pageInfo.getTotalPage());
		model.addAttribute("st", searchType);
		model.addAttribute("sw", searchWord);	
		
		return "admin/admin_list";
	}
	
	
	
	@RequestMapping("/category_list")
	public String categoryList(Model model,
	                           @RequestParam(defaultValue = "0") int page,
	                           @RequestParam(defaultValue = "10") int pageSize,
	                           @RequestParam(defaultValue = "cateCode") String searchType,
	                           @RequestParam(defaultValue = "") String searchWord) {

	    Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Order.asc(searchType)));
	    Page<Category> pageResult = categoryService.getCategoryList(pageable, searchType, searchWord);

	    List<Category> categoryList = pageResult.getContent();

	    for (Category category : categoryList) {
	        System.out.println(category.toString());
	    }

	    int resultTotalCnt = pageResult.getNumberOfElements();

	    System.out.println(resultTotalCnt);

	    PageInfo pageInfo = new PageInfo((int) pageResult.getTotalElements(), page, pageSize, searchType, searchWord);

	    model.addAttribute("pageable", pageable);        // 쿼리문 실행 전 페이징 정보
	    model.addAttribute("pageInfo", pageInfo);        // 쿼리문 실행 후 페이징 정보
	    model.addAttribute("pageResult", pageResult);    // 페이징 처리된 데이터

	    model.addAttribute("pg", page);
	    model.addAttribute("bp", pageInfo.getBeginPage());
	    model.addAttribute("ep", pageInfo.getEndPage());
	    model.addAttribute("ns", pageInfo.getNaviSize());
	    model.addAttribute("ps", pageSize);
	    model.addAttribute("tp", pageInfo.getTotalPage());
	    model.addAttribute("st", searchType);
	    model.addAttribute("sw", searchWord);

	    return "admin/category_list";
	}

	
	@RequestMapping("/goods_list")
	public String goodsList(Model model,
			            @RequestParam(defaultValue = "0") int page,
			            @RequestParam(defaultValue = "10") int pageSize,
			            @RequestParam(defaultValue = "gdsNum") String searchType,
			            @RequestParam(defaultValue = "") String searchWord) {
		
		Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Order.asc(searchType)));
	    Page<Goods> pageResult = goodsService.getGoodsList(pageable, searchType, searchWord);
		
	    List<Goods> goodsList = pageResult.getContent();
	    
	    for (Goods goods : goodsList) {
	        System.out.println(goods.toString());
	    }

	    int resultTotalCnt = pageResult.getNumberOfElements();

	    System.out.println(resultTotalCnt);
		
	    PageInfo pageInfo = new PageInfo((int) pageResult.getTotalElements(), page, pageSize, searchType, searchWord);

	    model.addAttribute("pageable", pageable);        // 쿼리문 실행 전 페이징 정보
	    model.addAttribute("pageInfo", pageInfo);        // 쿼리문 실행 후 페이징 정보
	    model.addAttribute("pageResult", pageResult);    // 페이징 처리된 데이터

	    model.addAttribute("pg", page);
	    model.addAttribute("bp", pageInfo.getBeginPage());
	    model.addAttribute("ep", pageInfo.getEndPage());
	    model.addAttribute("ns", pageInfo.getNaviSize());
	    model.addAttribute("ps", pageSize);
	    model.addAttribute("tp", pageInfo.getTotalPage());
	    model.addAttribute("st", searchType);
	    model.addAttribute("sw", searchWord);
		
		return "admin/goods_list";
	}
	
	@GetMapping("/admin_logout")
	public String adminLogout(SessionStatus status) {
		status.setComplete();
		return "redirect:login_admin";
	}
	
	@GetMapping("/category_add")
	public String categoryAddView() {
		return "admin/category_add";
	}
	
	@PostMapping("/category_add")
	public String categoryAdd(Category category) {
		categoryService.insertCategory(category);
		return "redirect:category_list";
	}
	
	@GetMapping("/goods_add")
	public String goodsAddView(Model model) {
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		
		return "admin/goods_add";
	}
	
	@PostMapping("/goods_add")
	public String goodsAdd(Goods goods, Category cateCode) throws IOException {
		//파일업로드
		MultipartFile uploadFile = goods.getUploadFile();
		if(!uploadFile.isEmpty()) {	//파일이 있다면
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File(uploadFolder + fileName));
			goods.setGdsImg(fileName);
		}
		
		goods.setCategory(cateCode);
		goodsService.insertGoods(goods);
		
		return "redirect:goods_list";
	}
	
	@GetMapping("/admin_delete")
	public String adminDelete(Member member) {
		memberService.deleteMember(member);
		return "redirect:admin_list";
	}
	
	@GetMapping("/member_delete")
	public String memberDelete(Member member) {
		memberService.deleteMember(member);
		return "redirect:admin_list";
	}
	
	@GetMapping("/category_delete")
	public String categoryDelete(Category category) {
		categoryService.deleteCategory(category);
		return "redirect:category_list";
	}
	
	@GetMapping("/goods_delete")
	public String goodsDelete(Goods goods) {
		goodsService.deleteGoods(goods);
		return "redirect:goods_list";
	}
	
	@GetMapping("/admin_update")
	public String adminUpdateView(Member member, Model model) {
		model.addAttribute("member", memberService.getMember(member));
		return "admin/admin_update";
	}
	
	@PostMapping("/admin_update")
	public String adminUpdate(Member member) {
		memberService.updateMember(member);
		return "redirect:admin_list";
	}
	
	@GetMapping("/member_update")
	public String memberUpdateView(Member member, Model model) {
		model.addAttribute("member", memberService.getMember(member));
		return "admin/member_update";
	}
	
	@PostMapping("/member_update")
	public String memberUpdate(Member member) {
		memberService.updateMember(member);
		return "redirect:member_list";
	}
	
	@GetMapping("/category_update")
	public String categoryUpdateView(Category category, Model model) {
		model.addAttribute("category", categoryService.getCategory(category));
		return "admin/category_update";
	}
	
	@PostMapping("/category_update")
	public String categoryUpdate(Category category) {
		categoryService.updateCategory(category);
		return "redirect:category_list";
	}
	
	@GetMapping("/goods_update")
	public String goodsUpdateView(Goods goods, Model model, Category category) {
		model.addAttribute("goods", goodsService.getGoods(goods));
		System.out.println(goodsService.getGoods(goods));
		model.addAttribute("categories", categoryService.getAllCategories());
		return "admin/goods_update";
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	
	@PostMapping("/goods_update")
	public String goodsUpdate(Goods goods, Model model, @RequestParam("gdsDate") String gdsDate) throws Exception, IOException {
		MultipartFile uploadFile = goods.getUploadFile();
	    if (!uploadFile.isEmpty()) { // 새로운 파일이 업로드된 경우
	        // 파일 업로드 로직 추가
	        String fileName = uploadFile.getOriginalFilename();
	        uploadFile.transferTo(new File(uploadFolder + fileName));
	        goods.setGdsImg(fileName);
	    }

		goodsService.updateGoods(goods);
		
		return "redirect:goods_list";
	}
	
	
//	@GetMapping("/profile/photo/{imageName}")
//	   @ResponseBody
//	   public ResponseEntity<?> getProfieleImage(@PathVariable("imageName") String imageName) throws Exception {
//	      File imageFile = new File(uploadFolder + imageName);
//	      
//	      if(imageFile.exists() != true) {
//	         throw new NoSuchFileException(imageName + "is not found");
//	      } else if(imageFile.isFile() != true) {
//	         throw new NoSuchFileException(imageName + "is not File");
//	      }
//	      
//	      InputStream fis = new FileInputStream(imageFile);
//	      Resource imageResource = new InputStreamResource(fis);
//	      
//	      return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageResource);
//	   }

	
}
