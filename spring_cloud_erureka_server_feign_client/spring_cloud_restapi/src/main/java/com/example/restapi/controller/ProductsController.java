package com.example.restapi.controller;

import com.example.restapi.model.Product;
import com.example.restapi.repository.ProductRepository;
import com.example.restapi.service.ProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/products/")
public class ProductsController {

    private ProductsService productsService;

    private Logger LOG = LoggerFactory.getLogger(ProductsController.class);

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable(name = "id") String id) {
        return productsService.getProduct(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product saveProduct(@RequestBody Product productToSave) {
        return productsService.saveProduct(productToSave);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@RequestBody Product productToUpdate, @PathVariable(name = "id") String id) {
        return productsService.updateProduct(productToUpdate, id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable(name = "id") String id) {
        productsService.deleteProduct(id);
    }
}
