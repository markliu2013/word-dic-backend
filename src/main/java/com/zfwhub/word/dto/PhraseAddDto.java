package com.zfwhub.word.dto;

public class PhraseAddDto implements DtoEntity {

    private String content;
    private Integer wordCategoryRelationId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getWordCategoryRelationId() {
        return wordCategoryRelationId;
    }

    public void setWordCategoryRelationId(Integer wordCategoryRelationId) {
        this.wordCategoryRelationId = wordCategoryRelationId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((wordCategoryRelationId == null) ? 0 : wordCategoryRelationId.hashCode());
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
        PhraseAddDto other = (PhraseAddDto) obj;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (wordCategoryRelationId == null) {
            if (other.wordCategoryRelationId != null)
                return false;
        } else if (!wordCategoryRelationId.equals(other.wordCategoryRelationId))
            return false;
        return true;
    }

}
