package com.zfwhub.word.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zfwhub.word.po.WordCategoryRelation;

@Repository
public interface WordCategoryRelationRepository extends JpaRepository<WordCategoryRelation, Integer> {

}
