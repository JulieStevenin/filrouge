package com.appfilrouge.projetfilrouge.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adType;
    private String adStyle;

    @OneToMany(mappedBy = "category" ,cascade=CascadeType.ALL)
    private List<Ad> ads = new ArrayList<>();
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", adType='" + adType + '\'' +
                ", adStyle='" + adStyle + '\'' +

                '}';
    }

}
