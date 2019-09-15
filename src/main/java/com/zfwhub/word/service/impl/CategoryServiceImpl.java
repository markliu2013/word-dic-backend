package com.zfwhub.word.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.beans.BeanUtils;

import com.zfwhub.word.dto.CategoryAddDto;
import com.zfwhub.word.exception.UniqueViolationException;
import com.zfwhub.word.exception.ValidationViolationException;
import com.zfwhub.word.po.Category;
import com.zfwhub.word.repository.CategoryRepository;
import com.zfwhub.word.service.CategoryService;


@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean add(CategoryAddDto categoryAddDto) {
        if (StringUtils.isEmpty(categoryAddDto.getName())) {
            throw new ValidationViolationException("category name is empty");
        }
        if (categoryRepository.findOneByName(categoryAddDto.getName()) != null) {
            throw new UniqueViolationException("category already exists");
        }
        Category category = new Category();
        BeanUtils.copyProperties(categoryAddDto, category);
        categoryRepository.save(category);
        return true;
    }

}
