package com.zfwhub.word.dto;

import java.util.List;

public class WordDetailAddDto {

    private WordAddDto wordAddDto;
    private List<CategorySentenceWrapper> categorySentenceWrappers;
    
    public WordDetailAddDto() { }

    public WordDetailAddDto(WordAddDto wordAddDto, List<CategorySentenceWrapper> categorySentenceWrappers) {
        this.wordAddDto = wordAddDto;
        this.categorySentenceWrappers = categorySentenceWrappers;
    }

    public WordAddDto getWordAddDto() {
        return wordAddDto;
    }

    public void setWordAddDto(WordAddDto wordAddDto) {
        this.wordAddDto = wordAddDto;
    }

    public List<CategorySentenceWrapper> getCategorySentenceWrappers() {
        return categorySentenceWrappers;
    }

    public void setCategorySentenceWrappers(List<CategorySentenceWrapper> categorySentenceWrappers) {
        this.categorySentenceWrappers = categorySentenceWrappers;
    }

}
