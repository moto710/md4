package com.productmanager.controller;

import com.productmanager.model.Product;
import com.productmanager.service.IService;
import com.productmanager.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping({"/", "/product"})
public class ProductController {
    private final IService<Product> productIService = new ProductService();
    private List<Product> productList;

    @GetMapping("/")
    private ModelAndView index(Model model) {
        productList = productIService.findAll();
        model.addAttribute("productList", productList);
        return new ModelAndView("/index");
    }

    @GetMapping("/create")
    private ModelAndView create(Model model) {
        model.addAttribute("product", new Product());
        return new ModelAndView("/create");
    }

    @PostMapping("/save")
    private ModelAndView save(Product product) {
        product.setId(productIService.findMaxId() + 1);
        productIService.save(product);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/{id}/edit")
    private ModelAndView edit(Model model, @PathVariable int id) {
        model.addAttribute("product", productIService.findById(id));
        return new ModelAndView("/edit");
    }

    @PostMapping("/update")
    private ModelAndView update(Product product) {
        productIService.update(product.getId(), product);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/{id}/remove")
    private ModelAndView remove(@PathVariable int id, Model model) {
        model.addAttribute("product", productIService.findById(id));
        return new ModelAndView("/remove");
    }

    @PostMapping("/remove")
    private ModelAndView remove(Product product, RedirectAttributes redirect) {
        productIService.remove(product.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/search/${keyword}")
    private ModelAndView search(@PathVariable String keyword, RedirectAttributes redirectAttributes) {
        productList = productIService.findByName(keyword);
        redirectAttributes.addFlashAttribute("productList", productList);
        return new ModelAndView("/search");
    }
}
