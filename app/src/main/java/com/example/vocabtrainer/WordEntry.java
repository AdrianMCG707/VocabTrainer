package com.example.vocabtrainer;

public class WordEntry {


    private String word;
    private String definition;
    private String phonetic; //Phonetic Respellling
    private String example;
    private String partOfSpeech;


    //constructor
    public WordEntry(String word, String definition, String partOfSpeech ,String phonetic, String example) {
        this.word = word;
        this.definition = definition;
        this.partOfSpeech = partOfSpeech;
        this.phonetic = phonetic;
        this.example = example;
    }


    public String getWord() {
        return word;
    }


    public void setWord(String word) {
        this.word = word;
    }


    public String getDefinition() {
        return definition;
    }


    public void setDefinition(String definition) {
        this.definition = definition;
    }


    public String getPhonetic() {
        return phonetic;
    }


    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }


    public String getExample() {
        return example;
    }


    public void setExample(String example) {
        this.example = example;
    }


    public String getPartOfSpeech() {
        return partOfSpeech;
    }


    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        WordEntry other = (WordEntry) obj;
        return word != null && word.equals(other.word);
    }

    @Override
    public int hashCode() {
        return word != null ? word.hashCode() : 0;
        }

    }



