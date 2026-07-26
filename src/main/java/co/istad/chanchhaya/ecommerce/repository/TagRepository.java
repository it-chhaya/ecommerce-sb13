package co.istad.chanchhaya.ecommerce.repository;

import co.istad.chanchhaya.ecommerce.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository
    extends JpaRepository<Tag, Integer> {
}
