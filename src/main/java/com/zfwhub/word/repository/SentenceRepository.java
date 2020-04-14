package com.zfwhub.word.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zfwhub.word.po.Sentence;

@Repository
public interface SentenceRepository extends JpaRepository<Sentence, Integer> {

}
