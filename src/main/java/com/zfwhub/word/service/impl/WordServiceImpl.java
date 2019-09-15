package com.zfwhub.word.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zfwhub.word.dto.TagAddDto;
import com.zfwhub.word.dto.WordAddDto;
import com.zfwhub.word.exception.UniqueViolationException;
import com.zfwhub.word.exception.ValidationViolationException;
import com.zfwhub.word.po.Tag;
import com.zfwhub.word.po.Word;
import com.zfwhub.word.repository.TagRepository;
import com.zfwhub.word.repository.WordRepository;
import com.zfwhub.word.service.WordService;

@Service
@Transactional
public class WordServiceImpl implements WordService {
    
    @Autowired
    private WordRepository wordRepository;
    
    @Autowired
    private TagRepository tagRepository;

    @Override
    public boolean add(WordAddDto wordAddDto) {
        if (StringUtils.isEmpty(wordAddDto.getName())) {
            throw new ValidationViolationException("word name is empty");
        }
        if (wordRepository.findOneByName(wordAddDto.getName()) != null) {
            throw new UniqueViolationException("word already exists");
        }
        Word word = new Word();
        BeanUtils.copyProperties(wordAddDto, word);
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
        wordRepository.save(word);
        return true;
    }

}
