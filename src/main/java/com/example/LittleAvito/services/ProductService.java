package com.example.LittleAvito.services;

import com.example.LittleAvito.Models.Product;
import com.example.LittleAvito.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j // это от lombok
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository; //оп оп берем этот интерфейс

    public List<Product> list(String title) {
        if (title != null) return productRepository.findByTitle(title); //если название сущевствет
        return productRepository.findAll();
    }

    public void saveProduct (Product product) {
        log.info("Сохраняю {}",product); //оно сохранит стороковое представление
        productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(); //еще проверяем если нет такого то выбрасываем ошибку

    }
}
