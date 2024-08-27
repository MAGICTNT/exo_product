package fr.maxime.repository;

import fr.maxime.entity.ProductHousing;
import jakarta.persistence.EntityManager;

public class ProductHousingRepository extends BaseRepository<ProductHousing> {
    private EntityManager em;

    public ProductHousingRepository(EntityManager entityManager) {
        super(entityManager, ProductHousing.class);
    }
}
