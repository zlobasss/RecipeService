package com.example.recipeservice.view;

import com.example.recipeservice.dto.ProductDto;
import com.example.recipeservice.model.Group;
import com.example.recipeservice.model.Product;
import com.example.recipeservice.repository.GroupRepo;
import com.example.recipeservice.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductView {

    @Autowired
    private static ProductRepo repo;

    @Autowired
    private static GroupRepo grRepo;

    // Добавление нового продукта в таблицу
    public Product create(String name, Group group) {
        return repo.save(Product.builder()
                .name(name)
                .group(group)
                .build());
    }

    // Обновление информации продукта
    public Product update(Long id, String name, Group group) {
        Optional<Product> optionalProduct = repo.findById(id);
        if (optionalProduct.isEmpty()) {
            return null;
        }
        Product product = optionalProduct.get();
        if (!name.equals(null)) {
            product.setName(name);
        }
        if (!group.equals(null)) {
            product.setGroup(group);
        }
        return repo.save(product);
    }

    // Получение информации конкретного продукта
    public Product read(Long id) {
        Optional<Product> optionalProduct = repo.findById(id);
        if (optionalProduct.isEmpty()) {
            return null;
        }
        return optionalProduct.get();
    }

    // Получение информации всех продуктов
    public List<Product> readAll() {
        return (List<Product>) repo.findAll();
    }

    // Удаление конкретного продукта
    public boolean delete(Long id) {
        Optional<Product> optionalProduct = repo.findById(id);
        if (optionalProduct.isEmpty()) {
            return false;
        }
        repo.delete(optionalProduct.get());
        return true;
    }

}
