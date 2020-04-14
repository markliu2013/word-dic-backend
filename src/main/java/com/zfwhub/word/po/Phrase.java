package com.zfwhub.word.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"content", "word_category_relation_id"})
})
public class Phrase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(length = 64, nullable = false, name="content")
    private String content;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="word_category_relation_id")
    private WordCategoryRelation wordCategoryRelation;
    
    public Phrase() { }

    public Phrase(String content, WordCategoryRelation wordCategoryRelation) {
        this.content = content;
        this.wordCategoryRelation = wordCategoryRelation;
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((wordCategoryRelation == null) ? 0 : wordCategoryRelation.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Phrase other = (Phrase) obj;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (wordCategoryRelation == null) {
            if (other.wordCategoryRelation != null)
                return false;
        } else if (!wordCategoryRelation.equals(other.wordCategoryRelation))
            return false;
        return true;
    }
    
}
