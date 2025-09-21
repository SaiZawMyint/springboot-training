package com.example.demo.persistence.model.product;

import java.util.Date;
import java.util.List;
import com.example.demo.persistence.model.user.User;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_category") // use underscore for table name
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;

    @Column(name = "image_url")
    private String imageUrl; // store image path or URL

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products; // one category has many products

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = true)
    private User createdBy; // many categories created by one user

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
