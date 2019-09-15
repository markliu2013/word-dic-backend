package com.zfwhub.word.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zfwhub.word.po.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
    Category findOneByName(String name);
    
}
