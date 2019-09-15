package com.zfwhub.word.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zfwhub.word.dto.BaseResponse;
import com.zfwhub.word.dto.ResponseSuccess;
import com.zfwhub.word.dto.TagDto;
import com.zfwhub.word.dto.WordCategoryRelationAddDto;
import com.zfwhub.word.service.WordCategoryRelationService;

@RestController
public class WordCategoryRelationController {
    
    @Autowired
    private WordCategoryRelationService wordCategoryRelationService;
    
    @RequestMapping(value="/wordCategoryRelations", method=RequestMethod.POST)
    public BaseResponse add(@RequestBody WordCategoryRelationAddDto wordCategoryRelationAddDto) {
        wordCategoryRelationService.add(wordCategoryRelationAddDto);
        ResponseSuccess<TagDto> response = new ResponseSuccess<>();
        return response;
    }
    
}
