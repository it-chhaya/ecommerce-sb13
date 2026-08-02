package co.istad.chanchhaya.ecommerce.repository;

import co.istad.chanchhaya.ecommerce.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Integer> {

    Optional<Media> findByName(String name);

}
