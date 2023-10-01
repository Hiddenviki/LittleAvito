package com.example.LittleAvito.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private String descriptiqn;
    private int price;
    private String city;
    private String author;


}
