package com.dictionary.model;

import java.util.HashMap;

public class Dictionary {
    private HashMap<String, String> dictionaryList;

    public Dictionary() {
        dictionaryList = new HashMap<>();
        dictionaryList.put("hello", "xin chao");
        dictionaryList.put("dictionary", "tu dien");
        dictionaryList.put("phone", "dien thoai");
    }

    public HashMap<String, String> getDictionaryList() {
        return dictionaryList;
    }

    public void setDictionaryList(HashMap<String, String> dictionaryList) {
        this.dictionaryList = dictionaryList;
    }
}
