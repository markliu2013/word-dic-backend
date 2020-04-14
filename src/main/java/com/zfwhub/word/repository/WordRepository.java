package com.zfwhub.word.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zfwhub.word.po.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {
    
    Word findOneByName(String name);

}
