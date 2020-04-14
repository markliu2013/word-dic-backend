package com.zfwhub.word.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zfwhub.word.po.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
    Category findOneByName(String name);
    
}
