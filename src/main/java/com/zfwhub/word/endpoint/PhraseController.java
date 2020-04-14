package com.zfwhub.word.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zfwhub.word.dto.BaseResponse;
import com.zfwhub.word.dto.PhraseAddDto;
import com.zfwhub.word.service.PhraseService;

@RestController
public class PhraseController {
    
    @Autowired
    private PhraseService phraseService;
    
    @RequestMapping(value="/phrases", method=RequestMethod.POST)
    public BaseResponse add(@RequestBody PhraseAddDto phraseAddDto) {
        phraseService.add(phraseAddDto);
        return new BaseResponse(true);
    }
    
}
