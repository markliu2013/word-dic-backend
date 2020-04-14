package com.zfwhub.word.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zfwhub.word.dto.CategoryAddDto;
import com.zfwhub.word.dto.CategorySentenceWrapper;
import com.zfwhub.word.dto.SentenceAddDto;
import com.zfwhub.word.dto.TagAddDto;
import com.zfwhub.word.dto.WordAddDto;
import com.zfwhub.word.dto.WordCategoryRelationAddDto;
import com.zfwhub.word.dto.WordDetailAddDto;
import com.zfwhub.word.exception.UniqueViolationException;
import com.zfwhub.word.exception.ValidationViolationException;
import com.zfwhub.word.po.Category;
import com.zfwhub.word.po.Tag;
import com.zfwhub.word.po.Word;
import com.zfwhub.word.po.WordCategoryRelation;
import com.zfwhub.word.repository.CategoryRepository;
import com.zfwhub.word.repository.TagRepository;
import com.zfwhub.word.repository.WordRepository;
import com.zfwhub.word.service.CategoryService;
import com.zfwhub.word.service.WordCategoryRelationService;
import com.zfwhub.word.service.WordService;

@Service
@Transactional
public class WordServiceImpl implements WordService {
    
    @Autowired
    private WordRepository wordRepository;
    
    @Autowired
    private TagRepository tagRepository;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private WordCategoryRelationService wordCategoryRelationService;
    
    @Autowired
    private SentenceServiceImpl sentenceService;

    @Override
    public Word add(WordAddDto wordAddDto) {
        if (StringUtils.isEmpty(wordAddDto.getName())) {
            throw new ValidationViolationException("word name is empty");
        }
        if (wordRepository.findOneByName(wordAddDto.getName()) != null) {
            throw new UniqueViolationException("word already exists");
        }
        Word word = new Word();
        BeanUtils.copyProperties(wordAddDto, word);
        if (wordAddDto.getTagAddDtos() != null) {
            List<Tag> tags = new ArrayList<>();
            for (TagAddDto tagAddDto : wordAddDto.getTagAddDtos()) {
                Tag tag = tagRepository.findOneByName(tagAddDto.getName());
                if (tag == null) {
                    tag = new Tag();
                    BeanUtils.copyProperties(tagAddDto, tag);
                    tagRepository.save(tag);
                }
                tags.add(tag);
            }
            word.setTags(tags);
        }
        wordRepository.save(word);
        return word;
    }

    @Override
    public boolean add(WordDetailAddDto wordDetailAddDto) {
        WordAddDto wordAddDto = wordDetailAddDto.getWordAddDto();
        Word word = wordRepository.findOneByName(wordAddDto.getName());
        if (word == null) {
            word = add(wordAddDto);
        }
        List<CategorySentenceWrapper> categorySentenceWrappers = wordDetailAddDto.getCategorySentenceWrappers();
        for (int i = 0; i < categorySentenceWrappers.size(); i++) {
            CategorySentenceWrapper categorySentenceWrapper = categorySentenceWrappers.get(i);
            Category category = categoryRepository.findOneByName(categorySentenceWrapper.getCategoryName());
            if (category == null) {
                category = categoryService.add(new CategoryAddDto(categorySentenceWrapper.getCategoryName()));
            }
            WordCategoryRelationAddDto wordCategoryRelationAddDto = new WordCategoryRelationAddDto();
            wordCategoryRelationAddDto.setWordId(word.getId());
            wordCategoryRelationAddDto.setCategoryId(category.getId());
            wordCategoryRelationAddDto.setDefinition(categorySentenceWrapper.getDefinition());
            wordCategoryRelationAddDto.setRank(i+1);
            WordCategoryRelation wordCategoryRelation = wordCategoryRelationService.add(wordCategoryRelationAddDto);
            for (String sentenceContent : categorySentenceWrapper.getSentenceContentList()) {
                SentenceAddDto sentenceAddDto = new SentenceAddDto(sentenceContent, wordCategoryRelation.getId());
                sentenceService.add(sentenceAddDto);
            }
        }
        return true;
    }

}
