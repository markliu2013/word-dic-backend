package com.zfwhub.word.service;

import com.zfwhub.word.dto.CategoryAddDto;
import com.zfwhub.word.po.Category;

public interface CategoryService {
    
    Category add(CategoryAddDto categoryAddDto);

}
