package com.zfwhub.word.service;

import com.zfwhub.word.dto.WordAddDto;
import com.zfwhub.word.dto.WordDetailAddDto;
import com.zfwhub.word.po.Word;

public interface WordService {
    
    Word add(WordAddDto wordAddDto);
    
    boolean add(WordDetailAddDto wordDetailAddDto);

}
