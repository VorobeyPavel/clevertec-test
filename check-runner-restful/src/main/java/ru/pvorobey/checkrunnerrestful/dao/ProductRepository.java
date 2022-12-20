package ru.pvorobey.checkrunnerrestful.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.pvorobey.checkrunnerrestful.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
