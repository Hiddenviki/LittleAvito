package com.example.LittleAvito.controllers;

import com.example.LittleAvito.Models.Product;
import com.example.LittleAvito.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String products(Model model) {
        model.addAttribute("products", productService.list());
        return "products";
    }

    @GetMapping ("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
//        model.addAttribute("product", productService.getProductById(id));
//        return "product-info";
        try {
            Product product = productService.getProductById(id);
            if (product == null) {
                // Можно обработать случай, когда продукт не найден
                return "product-not-found"; // Создайте соответствующее представление
            }
            model.addAttribute("product", product);
            return "product-info";
        } catch (Exception e) {
            // Обработка исключения, если оно произошло
            e.printStackTrace();
            return "ОШИБКА";
        }
    }

    @PostMapping("/product/create")
    public String createProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
