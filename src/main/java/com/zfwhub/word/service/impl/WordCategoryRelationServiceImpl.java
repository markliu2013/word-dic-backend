package com.zfwhub.word.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zfwhub.word.dto.WordCategoryRelationAddDto;
import com.zfwhub.word.exception.UniqueViolationException;
import com.zfwhub.word.exception.ValidationViolationException;
import com.zfwhub.word.po.Category;
import com.zfwhub.word.po.Word;
import com.zfwhub.word.po.WordCategoryRelation;
import com.zfwhub.word.repository.CategoryRepository;
import com.zfwhub.word.repository.WordCategoryRelationRepository;
import com.zfwhub.word.repository.WordRepository;
import com.zfwhub.word.service.WordCategoryRelationService;

@Transactional
@Service
public class WordCategoryRelationServiceImpl implements WordCategoryRelationService {
    
    @Autowired
    private WordRepository wordRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private WordCategoryRelationRepository wordCategoryRelationRepository;

    @Override
    public WordCategoryRelation add(WordCategoryRelationAddDto wordCategoryRelationAddDto) {
        Optional<Word> wordOption = wordRepository.findById(wordCategoryRelationAddDto.getWordId());
        if (!wordOption.isPresent()) {
            throw new ValidationViolationException("word does not exists");
        }
        Optional<Category> categoryOption = categoryRepository.findById(wordCategoryRelationAddDto.getCategoryId());
        if (!categoryOption.isPresent()) {
            throw new ValidationViolationException("category does not exists");
        }
        WordCategoryRelation wordCategoryRelationExample = new WordCategoryRelation();
        wordCategoryRelationExample.setWord(wordOption.get());
        wordCategoryRelationExample.setCategory(categoryOption.get());
        wordCategoryRelationExample.setDefinition(wordCategoryRelationAddDto.getDefinition());
        Example<WordCategoryRelation> example = Example.of(wordCategoryRelationExample);
        List<WordCategoryRelation> wordCategoryRelationList = wordCategoryRelationRepository.findAll(example);
        if (wordCategoryRelationList.size() > 0) {
            throw new UniqueViolationException("wordCategoryRelation already exists");
        }
        WordCategoryRelation wordCategoryRelation = new WordCategoryRelation(wordOption.get(), categoryOption.get(), wordCategoryRelationAddDto.getDefinition(), wordCategoryRelationAddDto.getRank());
        wordCategoryRelationRepository.save(wordCategoryRelation);
        return wordCategoryRelation;
    }

}
