package com.zfwhub.word.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zfwhub.word.dto.BaseResponse;
import com.zfwhub.word.dto.CategoryAddDto;
import com.zfwhub.word.service.CategoryService;

@RestController
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @RequestMapping(value="/categories", method=RequestMethod.POST)
    public BaseResponse add(@RequestBody CategoryAddDto categoryAddDto) {
        categoryService.add(categoryAddDto);
        return new BaseResponse(true);
    }

}
