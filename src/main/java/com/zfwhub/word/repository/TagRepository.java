package com.zfwhub.word.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zfwhub.word.po.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    
    Tag findOneByName(String name);

}
