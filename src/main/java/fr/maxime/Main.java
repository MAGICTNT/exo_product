package fr.maxime;


import fr.maxime.entity.ProductElectric;
import fr.maxime.entity.ProductFood;
import fr.maxime.entity.ProductHousing;
import fr.maxime.repository.ProductElectricRepository;
import fr.maxime.repository.ProductFoodRepository;
import fr.maxime.repository.ProductHousingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ProductElectricRepository productElectricRepository;
    private static ProductFoodRepository productFoodRepository;
    private static ProductHousingRepository productHousingRepository;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_exo_product");
        EntityManager em = emf.createEntityManager();
        productElectricRepository = new ProductElectricRepository(em);
        productFoodRepository = new ProductFoodRepository(em);
        productHousingRepository = new ProductHousingRepository(em);

        boolean flag = true;
        while (flag) {
            System.out.println("1. voir tout les produit");
            System.out.println("2. gestion produit nouriture");
            System.out.println("3. gestion produit energie");
            System.out.println("4. gestion produit de maison");
            System.out.println("5. fini");
            System.out.print("votre choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1 -> listAllProduct();
                case 2 -> menuProductFood();
                case 3 -> menuProductElect();
                case 4 -> menuProductHous();
            }
        }
    }

    private static void menuProductFood() {
        boolean flag = true;
        while (flag) {
            System.out.println("1. voir tout les produit nourriture");
            System.out.println("2. Creer produit nouriture");
            System.out.println("3. update produit energie");
            System.out.println("4. supprimer produit energie");
            System.out.println("5. retour");
            int choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1 -> listAllFoodProduct();
                case 2 -> creatFoodProduct();
                case 3 -> updateFoodProduct();
                case 4 -> deleteFoodProduct();
                case 5 -> flag = !flag;
                default -> System.out.println("invalid choix");
            }

        }
    }

    private static void creatFoodProduct() {
        System.out.println("quelle et le nom du produite:");
        String nom = scanner.nextLine();
        System.out.println("quelle et le prix du produite:");
        float prix = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("quelle et sa date de peremption (en int )");
        int date = scanner.nextInt();
        scanner.nextLine();
        ProductFood newProductFood = ProductFood.builder()
                .name(nom)
                .price(prix)
                .date(date)
                .build();
        productFoodRepository.create(newProductFood);
        System.out.println("nouriture " + nom + " créé");
    }

    private static void updateFoodProduct() {
        listAllFoodProduct();
        System.out.println("quelle et l'id du produite a mettre a jour:");
        long id = scanner.nextLong();
        scanner.nextLine();
        ProductFood updateProductFood = productFoodRepository.findById(id);
        if (updateProductFood == null) {
            System.out.println("produite non trouver");
        }else {
            System.out.println("quelle et son nouveau nom:");
            updateProductFood.setName(scanner.nextLine());
            System.out.println("quelle et son nouveau prix:");
            updateProductFood.setPrice(scanner.nextFloat());
            scanner.nextLine();
            System.out.println("quelle et sa date de peremption (en int )");
            updateProductFood.setDate(scanner.nextInt());
            scanner.nextLine();
            productFoodRepository.update(updateProductFood);
            System.out.println("mise a jour réussi");
        }
    }

    private static void deleteFoodProduct() {
        listAllFoodProduct();
        System.out.println("quelle et l'id du produite a supprimer:");
        ProductFood deleteProductFood = productFoodRepository.findById(scanner.nextLong());
        if (deleteProductFood == null) {
            System.out.println("produite non trouver");
        }else {
            productFoodRepository.delete(deleteProductFood);
            System.out.println("produit supprimer");
        }
    }


    private static void menuProductElect() {
        boolean flag = true;
        while (flag) {
            System.out.println("1. voir tout les produit eletrique");
            System.out.println("2. Creer produit eletrique");
            System.out.println("3. update produit eletrique");
            System.out.println("4. supprimer produit eletrique");
            System.out.println("5. retour");
            int choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1 -> listAllEnergicProduct();
                case 2 -> creatEnergicProduct();
                case 3 -> updateEnergicProduct();
                case 4 -> deleteEnergicProduct();
                case 5 -> flag = !flag;
                default -> System.out.println("invalid choix");
            }

        }
    }

    private static void creatEnergicProduct() {
        System.out.println("quelle et le nom du produit:");
        String nom = scanner.nextLine();
        System.out.println("quelle et le prix du produit:");
        float prix = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("quelle et sa puissance");
        int puissance = scanner.nextInt();
        scanner.nextLine();
        ProductElectric newProductEnergic = ProductElectric.builder()
                .name(nom)
                .price(prix)
                .duration(puissance)
                .build();
        productElectricRepository.create(newProductEnergic);
        System.out.println("produit créé");
    }

    private static void updateEnergicProduct() {
        listAllEnergicProduct();
        System.out.println("quelle et l'id du produit:");
        ProductElectric updateProductEnergic = productElectricRepository.findById(scanner.nextLong());
        scanner.nextLine();
        if (updateProductEnergic == null) {
            System.out.println("produite non trouver");
        }else {
            System.out.println("quelle et son nouveau nom:");
            updateProductEnergic.setName(scanner.nextLine());
            System.out.println("quelle et son nouveau prix:");
            updateProductEnergic.setPrice(scanner.nextFloat());
            scanner.nextLine();
            System.out.println("quelle et sa puissance:");
            updateProductEnergic.setDuration(scanner.nextInt());
            scanner.nextLine();
            productElectricRepository.update(updateProductEnergic);
            System.out.println("produit mise a jour");
        }
    }

    private static void deleteEnergicProduct() {
        listAllEnergicProduct();
        System.out.println("quelle et l'id du produit:");
        ProductElectric deleteProductEnergic = productElectricRepository.findById(scanner.nextLong());
        if (deleteProductEnergic == null) {
            System.out.println("produite non trouver");
        }else {
            productElectricRepository.delete(deleteProductEnergic);
            System.out.println("produit supprimer");
        }
    }



    private static void menuProductHous() {
        boolean flag = true;
        while (flag) {
            System.out.println("1. voir tout les produit de maison");
            System.out.println("2. Creer un produit de maison");
            System.out.println("3. update un produit de maison");
            System.out.println("4. supprimer un produit de maison");
            System.out.println("5. retour");
            int choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1 -> listAllHousProduct();
                case 2 -> creatHousProduct();
                case 3 -> updateHousProduct();
                case 4 -> deleteHousProduct();
                case 5 -> flag = !flag;
                default -> System.out.println("invalid choix");
            }

        }
    }

    private static void creatHousProduct() {
        ProductHousing newHous = new ProductHousing();
        System.out.println("quelle et le nom de la maison:");
        newHous.setName(scanner.nextLine());
        System.out.println("quelle et le prix du produit:");
        newHous.setPrice(scanner.nextFloat());
        scanner.nextLine();
        System.out.println("quelle et sa hauteur ");
        newHous.setHeight(scanner.nextInt());
        scanner.nextLine();
        System.out.println("quelle et sa largeur ");
        newHous.setWidth(scanner.nextInt());
        scanner.nextLine();
        productHousingRepository.create(newHous);
        System.out.println("maison creation");

    }

    private static void updateHousProduct() {
        listAllHousProduct();
        System.out.println("quelle et l'id de la maison a mettre a jour");
        long id = scanner.nextLong();
        scanner.nextLine();
        ProductHousing updateProductHousing = productHousingRepository.findById(id);
        if (updateProductHousing == null) {
            System.out.println("produite non trouver");
        }else {
            System.out.println("quelle et son nouveau nom:");
            updateProductHousing.setName(scanner.nextLine());
            System.out.println("quelle et son nouveau prix:");
            updateProductHousing.setPrice(scanner.nextFloat());
            scanner.nextLine();
            System.out.println("quelle est sa nouvelle hauteur");
            updateProductHousing.setHeight(scanner.nextInt());
            scanner.nextLine();
            System.out.println("quelle est sa nouvelle largeur");
            updateProductHousing.setWidth(scanner.nextInt());
            scanner.nextLine();
            productHousingRepository.update(updateProductHousing);
            System.out.println("maison mise a jour");
        }
    }

    private static void deleteHousProduct() {
        listAllHousProduct();
        System.out.println("quelle et l'id de la maison a supprimer");
        long id = scanner.nextLong();
        scanner.nextLine();
        ProductHousing deleteProductHousing = productHousingRepository.findById(id);
        if (deleteProductHousing == null) {
            System.out.println("produite non trouver");
        }else {
            productHousingRepository.delete(deleteProductHousing);
            System.out.println("produite supprimer la maison");
        }
    }

    private static void listAllProduct() {
        List<ProductElectric> productElectricsList = productElectricRepository.findAll();
        List<ProductFood> productFoodsList = productFoodRepository.findAll();
        List<ProductHousing> productHousingsList = productHousingRepository.findAll();
        if (productElectricsList.isEmpty()) {
            System.out.println("pas de produit eletric trouver");
        }else {
            listAllEnergicProduct();
        }
        if (productFoodsList.isEmpty()) {
            System.out.println("pas de produit nourriture trouver");
        }else{
            listAllFoodProduct();
        }
        if (productHousingsList.isEmpty()) {
            System.out.println("pas de produit housing trouver");
        }else {
            listAllHousProduct();
        }
    }
    private static void listAllFoodProduct() {
        for(ProductFood productFood : productFoodRepository.findAll()){
            System.out.println("----------");
            System.out.println("id  : " + productFood.getId());
            System.out.println("name: " + productFood.getName());
            System.out.println("prix: " + productFood.getPrice());
            System.out.println("date: " + productFood.getDate());
            System.out.println("----------");
        }

    }
    private static void listAllEnergicProduct() {
        for(ProductElectric productElectric : productElectricRepository.findAll()){
            System.out.println("----------");
            System.out.println("id   : "+productElectric.getId());
            System.out.println("nom  : "+productElectric.getName());
            System.out.println("prix : "+productElectric.getPrice());
            System.out.println("durée: "+productElectric.getDuration());
            System.out.println("-----");
        }

    }
    private static void listAllHousProduct() {
        for(ProductHousing productFood : productHousingRepository.findAll()){
            System.out.println("----------");
            System.out.println("id     : "+productFood.getId());
            System.out.println("nom    : "+productFood.getName());
            System.out.println("prix   : "+productFood.getPrice());
            System.out.println("hauteur: "+productFood.getHeight());
            System.out.println("largeur: "+productFood.getWidth());
            System.out.println("----------");
        }

    }
}