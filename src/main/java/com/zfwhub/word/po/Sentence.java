package com.zfwhub.word.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sentence {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(length = 512, nullable = false)
    private String content;
    
    @ManyToOne(optional=false)
    private WordCategoryRelation wordCategoryRelation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public WordCategoryRelation getWordCategoryRelation() {
        return wordCategoryRelation;
    }

    public void setWordCategoryRelation(WordCategoryRelation wordCategoryRelation) {
        this.wordCategoryRelation = wordCategoryRelation;
    }
    
}
