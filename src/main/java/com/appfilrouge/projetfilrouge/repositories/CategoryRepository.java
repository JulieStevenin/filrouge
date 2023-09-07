package com.appfilrouge.projetfilrouge.repositories;

import com.appfilrouge.projetfilrouge.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
