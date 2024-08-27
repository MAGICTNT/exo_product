package fr.maxime.repository;

import fr.maxime.entity.ProductFood;
import jakarta.persistence.EntityManager;

public class ProductFoodRepository extends BaseRepository<ProductFood> {
    private EntityManager em;
    public ProductFoodRepository(EntityManager entityManager) {
        super(entityManager, ProductFood.class);
    }
}
