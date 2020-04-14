package com.zfwhub.word.service;

import com.zfwhub.word.dto.WordCategoryRelationAddDto;
import com.zfwhub.word.po.WordCategoryRelation;

public interface WordCategoryRelationService {
    
    WordCategoryRelation add(WordCategoryRelationAddDto wordCategoryRelationAddDto);

}
