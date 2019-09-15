package com.zfwhub.word.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zfwhub.word.po.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    
    Tag findOneByName(String name);

}
