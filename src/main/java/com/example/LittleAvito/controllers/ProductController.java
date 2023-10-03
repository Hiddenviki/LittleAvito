package com.example.LittleAvito.controllers;

import com.example.LittleAvito.Models.Product;
import com.example.LittleAvito.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("products", productService.list(title));
        return "products";
    }

    @GetMapping ("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
////        model.addAttribute("product", productService.getProductById(id));
////        return "product-info";
//        try {
//            Product product = productService.getProductById(id);
//            if (product == null) {
//                // Можно обработать случай, когда продукт не найден
//                return "product-not-found"; // Создайте соответствующее представление
//            }
//            model.addAttribute("product", product);
//            return "product-info";
//        } catch (Exception e) {
//            // Обработка исключения, если оно произошло
//            e.printStackTrace();
//            return "ОШИБКА";
//        }
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        return "product-info";
    }

    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,
                                Product product) throws IOException
    {
        productService.saveProduct(product,file1,file2,file3);
        return "redirect:/";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
