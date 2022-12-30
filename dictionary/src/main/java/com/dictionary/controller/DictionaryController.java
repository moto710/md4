package com.dictionary.controller;

import com.dictionary.model.Dictionary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
public class DictionaryController {
    @GetMapping("/")
    private String home() {
        return "index";
    }
    @GetMapping("/search")
    private String search(@RequestParam String keyword, Model model){
        Dictionary dictionary = new Dictionary();
        HashMap<String, String> dictionaryList = dictionary.getDictionaryList();
        for (String str : dictionaryList.keySet()) {
            if (str.equals(keyword)) {
                model.addAttribute("result", dictionaryList.get(str));
                break;
            }
        }
        return "index";
    }
}
