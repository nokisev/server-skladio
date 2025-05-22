package ru.nokisev.skladio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nokisev.skladio.models.Product;
import ru.nokisev.skladio.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**

    GET /api/products/search – поиск товара по названию, артикулу, штрихкоду

    * */

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Long id) {
        if (!productRepository.existsById(id)) {
            return Optional.empty();
        }

        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id,Product product) throws RuntimeException {

        Product oldProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setId(id);

        if (product.getArticle() != null) {
            oldProduct.setArticle(product.getArticle());
        }
        if (product.getName() != null) {
            oldProduct.setName(product.getName());
        }
        if (product.getDescription() != null) {
            oldProduct.setDescription(product.getDescription());
        }
        if (product.getProduct_unit() != null) {
            oldProduct.setProduct_unit(product.getProduct_unit());
        }
        if (product.getQuantity() != 0) {
            oldProduct.setQuantity(product.getQuantity());
        }
        if (product.getPrice() != 0) {
            oldProduct.setPrice(product.getPrice());
        }
        if (product.getPicture() != null) {
            oldProduct.setPicture(product.getPicture());
        }

        return productRepository.save(oldProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    /*  search  */
    public List<Product> searchProductsByArticle(String article) {
        productRepository.findAllByArticle(article);
        return productRepository.findAllByArticle(article);
    }

    public List<Product> searchProductsByName(String name) {
        return productRepository.findAllByName(name);
    }

    public List<Product> searchProducts(String searchText) {
        List<Product> products = new ArrayList<>();

        products.addAll(productRepository.findAllByName(searchText));
        products.addAll(productRepository.findAllByArticle(searchText));

        return products;
    }
}
