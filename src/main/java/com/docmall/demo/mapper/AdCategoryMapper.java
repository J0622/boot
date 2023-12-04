package com.docmall.demo.mapper;

import java.util.List;

//import org.apache.ibatis.annotations.Mapper;

import com.docmall.demo.domain.CategoryVO;

//@Mapper 어노테이션이 생략되어 있음
public interface AdCategoryMapper {

	List<CategoryVO> getFirstCategoryList();
	
	List<CategoryVO> getSecondCategoryList(Integer cg_parent_code);
	
	CategoryVO get(Integer cg_code);
}
