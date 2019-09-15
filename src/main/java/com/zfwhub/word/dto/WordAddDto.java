package com.zfwhub.word.dto;

import java.util.List;

public class WordAddDto implements DtoEntity {
    
    private String name;
    private Integer frequency;
    private String phoneticSymbol;
    private List<TagAddDto> tagAddDtos;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getPhoneticSymbol() {
        return phoneticSymbol;
    }

    public void setPhoneticSymbol(String phoneticSymbol) {
        this.phoneticSymbol = phoneticSymbol;
    }

    public List<TagAddDto> getTagAddDtos() {
        return tagAddDtos;
    }

    public void setTagAddDtos(List<TagAddDto> tagAddDtos) {
        this.tagAddDtos = tagAddDtos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        WordAddDto other = (WordAddDto) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    
}
