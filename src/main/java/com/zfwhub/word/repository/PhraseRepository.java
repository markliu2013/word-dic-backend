package com.zfwhub.word.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zfwhub.word.po.Phrase;

@Repository
public interface PhraseRepository extends JpaRepository<Phrase, Integer> {

}
