package com.zfwhub.word.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zfwhub.word.dto.BaseResponse;
import com.zfwhub.word.dto.WordAddDto;
import com.zfwhub.word.dto.WordDetailAddDto;
import com.zfwhub.word.service.WordService;

@RestController
public class WordController {
    
    @Autowired
    private WordService wordService;
    
    @RequestMapping(value="/words", method=RequestMethod.POST)
    public BaseResponse add(@RequestBody WordAddDto wordAddDto) {
        wordService.add(wordAddDto);
        return new BaseResponse(true);
    }
    
    @RequestMapping(value="/wordDetail", method=RequestMethod.POST)
    public BaseResponse add(@RequestBody WordDetailAddDto wordDetailAddDto) {
        wordService.add(wordDetailAddDto);
        return new BaseResponse(true);
    }
    

}
