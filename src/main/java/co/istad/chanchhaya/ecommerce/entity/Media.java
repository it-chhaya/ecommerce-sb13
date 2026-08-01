package co.istad.chanchhaya.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "medias")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, length = 10)
    private String extension;
    @Column(nullable = false)
    private Float size;
    @Column(nullable = false, length = 32)
    private String mediaType;
    @Column(nullable = false)
    private Boolean isDraft;
}
