package com.zfwhub.word.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zfwhub.word.po.WordCategoryRelation;

public interface WordCategoryRelationRepository extends JpaRepository<WordCategoryRelation, Integer> {

}
