package com.zfwhub.word.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zfwhub.word.dto.TagAddDto;
import com.zfwhub.word.exception.UniqueViolationException;
import com.zfwhub.word.exception.ValidationViolationException;
import com.zfwhub.word.po.Tag;
import com.zfwhub.word.repository.TagRepository;
import com.zfwhub.word.service.TagService;


@Service
@Transactional
public class TagServiceImpl implements TagService {
    
    @Autowired
    private TagRepository tagRepository;

    @Override
    public boolean add(TagAddDto tagAddDto) {
        if (StringUtils.isEmpty(tagAddDto.getName())) {
            throw new ValidationViolationException("tag name is empty");
        }
        if (tagRepository.findOneByName(tagAddDto.getName()) != null) {
            throw new UniqueViolationException("tag already exists");
        }
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagAddDto, tag);
        tagRepository.save(tag);
        return true;
    }

}
