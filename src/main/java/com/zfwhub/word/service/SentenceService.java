package com.zfwhub.word.service;

import com.zfwhub.word.dto.SentenceAddDto;
import com.zfwhub.word.po.Sentence;

public interface SentenceService {
    
    Sentence add(SentenceAddDto sentenceAddDto);

}
