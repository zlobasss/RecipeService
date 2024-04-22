package com.example.recipeservice.controller;

import com.example.recipeservice.dto.Message;
import com.example.recipeservice.dto.ProductDto;
import com.example.recipeservice.model.Category;
import com.example.recipeservice.model.Product;
import com.example.recipeservice.repository.GroupRepo;
import com.example.recipeservice.view.ProductView;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
@AllArgsConstructor
public class ProductController {

    @Autowired
    private final ProductView view;

    @Autowired
    private final GroupRepo grRepo;

    @GetMapping(value = "/api/v1/product")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(view.readAll(), HttpStatusCode.valueOf(200));
    }

    @GetMapping(value = "/api/v1/product/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Product product = view.read(id);
        if (product == null) {
            return notFound();
        }
        return new ResponseEntity<>(view.read(id), HttpStatusCode.valueOf(200));
    }

    @PostMapping(value = "/api/v1/product")
    public ResponseEntity<?> add(@RequestBody ProductDto dto) {
        if (dto.group == null) {
            return new ResponseEntity<>(view.create(dto.name, null), HttpStatusCode.valueOf(201));
        }
        Optional<Category> optionalGroup = grRepo.findById(dto.group);
        if (optionalGroup.isEmpty()) {
            return notFound();
        }
        return new ResponseEntity<>(view.create(dto.name, optionalGroup.get()), HttpStatusCode.valueOf(201));
    }

    @PatchMapping(value = "api/v1/product/{id}")
    public ResponseEntity<?> patch(@PathVariable Long id, @RequestBody ProductDto dto) {
        Optional<Category> optionalGroup = grRepo.findById(dto.group);
        if (optionalGroup.isEmpty()) {
            return notFound();
        }
        return new ResponseEntity<>(view.update(id, dto.name, optionalGroup.get()).getId(), HttpStatusCode.valueOf(200));
    }

    private ResponseEntity<?> notFound() {
        Message result = new Message(
                "The product does not exist",
                "");
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(404));
    }
}
