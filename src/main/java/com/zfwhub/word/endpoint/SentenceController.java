package com.zfwhub.word.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zfwhub.word.dto.BaseResponse;
import com.zfwhub.word.dto.SentenceAddDto;
import com.zfwhub.word.service.SentenceService;

@RestController
public class SentenceController {
    
    @Autowired
    private SentenceService sentenceService;
    
    @RequestMapping(value="/sentences", method=RequestMethod.POST)
    public BaseResponse add(@RequestBody SentenceAddDto sentenceAddDto) {
        sentenceService.add(sentenceAddDto);
        return new BaseResponse(true);
    }
    
}
