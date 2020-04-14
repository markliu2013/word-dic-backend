package com.zfwhub.word.dto;

import java.util.List;

public class CategorySentenceWrapper {

    private String categoryName;
    private String definition;
    private List<String> sentenceContentList;
    private Integer rank;

    public CategorySentenceWrapper() { }

    public CategorySentenceWrapper(String categoryName, String definition, List<String> sentenceContentList, Integer rank) {
        this.categoryName = categoryName;
        this.definition = definition;
        this.sentenceContentList = sentenceContentList;
        this.rank = rank;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public List<String> getSentenceContentList() {
        return sentenceContentList;
    }

    public void setSentenceContentList(List<String> sentenceContentList) {
        this.sentenceContentList = sentenceContentList;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

}
