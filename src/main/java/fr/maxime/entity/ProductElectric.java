package fr.maxime.entity;

import fr.maxime.abstracts.Product;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@Entity
public class ProductElectric extends Product {
    private int duration;
}
