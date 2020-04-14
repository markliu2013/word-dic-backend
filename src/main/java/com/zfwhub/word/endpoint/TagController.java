package com.zfwhub.word.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zfwhub.word.dto.BaseResponse;
import com.zfwhub.word.dto.TagAddDto;
import com.zfwhub.word.service.TagService;

@RestController
public class TagController {
    
    @Autowired
    private TagService tagService;
    
    @RequestMapping(value="/tags", method=RequestMethod.POST)
    public BaseResponse add(@RequestBody TagAddDto tagAddDto) {
        tagService.add(tagAddDto);
        return new BaseResponse(true);
    }

}
