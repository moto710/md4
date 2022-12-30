package com.moneyconversion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class MoneyController {
    @GetMapping("/")
    public String convert(Model model){
//        model.addAttribute("result", "");
        return "index";
    }
    @GetMapping("/convert")
    private String result(@RequestParam String usd, Model model){
        try{
            int vnd = Integer.parseInt(usd)*23500;
            model.addAttribute("result", vnd);
        }catch (Exception e){
            model.addAttribute("result","value must be number");
        }
        return "index";
    }
}
