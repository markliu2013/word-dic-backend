package com.zfwhub.word.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zfwhub.word.dto.PhraseAddDto;
import com.zfwhub.word.exception.UniqueViolationException;
import com.zfwhub.word.exception.ValidationViolationException;
import com.zfwhub.word.po.Phrase;
import com.zfwhub.word.po.WordCategoryRelation;
import com.zfwhub.word.repository.PhraseRepository;
import com.zfwhub.word.repository.WordCategoryRelationRepository;
import com.zfwhub.word.service.PhraseService;

@Service
@Transactional
public class PhraseServiceImpl implements PhraseService {
    
    @Autowired
    private WordCategoryRelationRepository wordCategoryRelationRepository;
    
    @Autowired
    private PhraseRepository phraseRepository;

    @Override
    public boolean add(PhraseAddDto phraseAddDto) {
        if (StringUtils.isEmpty(phraseAddDto.getContent())) {
            throw new ValidationViolationException("phrase content is empty");
        }
        Optional<WordCategoryRelation> wordCategoryRelationOption = wordCategoryRelationRepository.findById(phraseAddDto.getWordCategoryRelationId());
        if (!wordCategoryRelationOption.isPresent()) {
            throw new ValidationViolationException("word category relation dose not exists");
        }
        Phrase phraseExample = new Phrase(phraseAddDto.getContent(), wordCategoryRelationOption.get());
        Example<Phrase> example = Example.of(phraseExample);
        List<Phrase> phraseList = phraseRepository.findAll(example);
        if (phraseList.size() > 0) {
            throw new UniqueViolationException("phrase already exists");
        }
        phraseRepository.save(new Phrase(phraseAddDto.getContent(), wordCategoryRelationOption.get()));
        return true;
    }

}
