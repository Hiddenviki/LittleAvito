package com.example.LittleAvito.services;

import com.example.LittleAvito.Models.Product;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();
    private long id=1;

    {
        products.add(new Product(id++,"Название1","описание1", 301, "Город1", "Автор1"));
        products.add(new Product(id++,"Название2","описание2", 302, "Город2", "Автор2"));
    }

    public List<Product> list() { return products; }

    public void saveProduct (Product product) {
        product.setId(id++);
        products.add(product);
    }

    public void deleteProduct(Long id){
        products.removeIf(product -> product.getId().equals(id));
    }

    public Product getProductById(Long id) {
//        System.out.println("Из сервиса "+id);
        for (Product product_ : products) {
            if (product_.getId().equals(id)) return product_;
        }
        return null;
    }
}
