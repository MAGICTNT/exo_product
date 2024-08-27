package fr.maxime.repository;

import fr.maxime.entity.ProductElectric;
import jakarta.persistence.EntityManager;

public class ProductElectricRepository extends BaseRepository<ProductElectric> {
    private EntityManager em;

    public ProductElectricRepository(EntityManager entityManager) {
        super(entityManager, ProductElectric.class);
    }
}
