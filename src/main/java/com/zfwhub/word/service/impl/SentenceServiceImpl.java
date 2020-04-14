package com.zfwhub.word.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zfwhub.word.dto.SentenceAddDto;
import com.zfwhub.word.exception.UniqueViolationException;
import com.zfwhub.word.exception.ValidationViolationException;
import com.zfwhub.word.po.Sentence;
import com.zfwhub.word.po.WordCategoryRelation;
import com.zfwhub.word.repository.SentenceRepository;
import com.zfwhub.word.repository.WordCategoryRelationRepository;
import com.zfwhub.word.service.SentenceService;

@Service
@Transactional
public class SentenceServiceImpl implements SentenceService {
    
    @Autowired
    private WordCategoryRelationRepository wordCategoryRelationRepository;
    
    @Autowired
    private SentenceRepository sentenceRepository;

    @Override
    public Sentence add(SentenceAddDto sentenceAddDto) {
        if (StringUtils.isEmpty(sentenceAddDto.getContent())) {
            throw new ValidationViolationException("sentence content is empty");
        }
        Optional<WordCategoryRelation> wordCategoryRelationOption = wordCategoryRelationRepository.findById(sentenceAddDto.getWordCategoryRelationId());
        if (!wordCategoryRelationOption.isPresent()) {
            throw new ValidationViolationException("word category relation dose not exists");
        }
        Sentence sentenceExample = new Sentence(sentenceAddDto.getContent(), wordCategoryRelationOption.get());
        Example<Sentence> example = Example.of(sentenceExample);
        List<Sentence> sentenceList = sentenceRepository.findAll(example);
        if (sentenceList.size() > 0) {
            throw new UniqueViolationException("sentence already exists");
        }
        return sentenceRepository.save(new Sentence(sentenceAddDto.getContent(), wordCategoryRelationOption.get()));
    }

}
