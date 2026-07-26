package co.istad.chanchhaya.ecommerce.repository;

import co.istad.chanchhaya.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends
        JpaRepository<Product, Integer> {
}
