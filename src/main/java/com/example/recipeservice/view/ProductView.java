package com.example.recipeservice.view;

import com.example.recipeservice.model.Category;
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
    private final ProductRepo repo;

    @Autowired
    private final GroupRepo grRepo;

    // Добавление нового продукта в таблицу
    public Product create(String name, Category category) {
        return repo.save(Product.builder()
                .name(name)
                .category(category)
                .build());
    }

    // Обновление информации продукта
    public Product update(Long id, String name, Category category) {
        Optional<Product> optionalProduct = repo.findById(id);
        if (optionalProduct.isEmpty()) {
            return null;
        }
        Product product = optionalProduct.get();
        if (name != null) {
            product.setName(name);
        }
        if (category != null) {
            product.setCategory(category);
        }
        return repo.save(product);
    }

    // Получение информации конкретного продукта
    public Product read(Long id) {
        Optional<Product> optionalProduct = repo.findById(id);
        return optionalProduct.orElse(null);
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
