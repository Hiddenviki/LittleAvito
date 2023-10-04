package com.example.LittleAvito.services;

import com.example.LittleAvito.Models.Image;
import com.example.LittleAvito.Models.Product;
import com.example.LittleAvito.Models.User;
import com.example.LittleAvito.repositories.ProductRepository;
import com.example.LittleAvito.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.Console;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Service
//@Slf4j // это от lombok
//@RequiredArgsConstructor
//public class ProductService {
//    private final ProductRepository productRepository; //оп оп берем этот интерфейс
//    private final UserRepository userRepository;
//
//    public List<Product> list(String title) {
//        if (title != null) return productRepository.findByTitle(title); //если название сущевствет
//        return productRepository.findAll();
//    }
//
//    public void saveProduct(Principal principal, Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3 ) throws IOException {
//
//        product.setUser(getUserByPrincipal(principal));
//        Image image1 = null;
//        Image image2 =null;
//        Image image3=null;
//        if (file1.getSize() != 0) {
//            image1 = toImageEntity(file1);
//            image1.setPreviewImage(true);
//            product.addImageToProduct(image1);
//        }
//        if (file2.getSize () != 0) {
//            image2 = toImageEntity(file2);
//            product.addImageToProduct(image2);
//        }
//        if (file3.getSize() != 0) {
//            image3 = toImageEntity(file3);
//            product.addImageToProduct(image3);
//        }
//
//        log.info("Сохраняю новый продукт.\n Название {};\n email fвтора {}",product.getTitle(),product.getUser().getEmail()); //оно сохранит стороковое представление
//
////        //обновляем товар с превьюшной фотографией ее айди
////        Product productFromDB = productRepository.save(product);
////        productFromDB.setPreviewImageId(productFromDB.getImages().get(0).getId());
////        productRepository.save(product);
//        Product productFromDB = productRepository.save(product); // Сохраняем объект Product и получаем его из базы данных
//        // Устанавливаем previewImageId после сохранения в базе
//        if (!productFromDB.getImages().isEmpty()) {
//            productFromDB.setPreviewImageId(productFromDB.getImages().get(0).getId());
//            productRepository.save(productFromDB); // Сохраняем обновленный объект Product с установленным previewImageId
//        }
//
//    }
//
//    public User getUserByPrincipal(Principal principal) {
//        log.info("productService: ");
//        if(principal==null){
//            log.info("чета ноль ");
//            return new User();}
//        log.info("Не ноль: "+userRepository.findByEmail(principal.getName()));
//        return userRepository.findByEmail(principal.getName());
//    }
//
//
//    private Image toImageEntity(MultipartFile file) throws IOException {
//        Image image = new Image ();
//        image.setName(file.getName()) ;
////        image.setName(file.getOriginalFilename());
//
//        image.setOriginalFileName(file.getOriginalFilename());
//        image.setContentType(file.getContentType());
//        image.setSize(file.getSize());
//        image.setBytes(file.getBytes());
//        return image;
//    }
//
//
//    public void deleteProduct(Long id){
//        productRepository.deleteById(id);
//    }
//
//    public Product getProductById(Long id) {
//        return productRepository.findById(id).orElseThrow(); //еще проверяем если нет такого то выбрасываем ошибку
//
//    }
//}
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Product> listProducts(String title) {
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    public void saveProduct(Principal principal, Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        product.setUser(getUserByPrincipal(principal));
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }
        log.info("Saving new Product. Title: {}; Author email: {}", product.getTitle(), product.getUser().getEmail());
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}