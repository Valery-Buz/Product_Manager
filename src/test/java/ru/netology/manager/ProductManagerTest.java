package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    ProductRepository productRepository = new ProductRepository();
    ProductManager productManager = new ProductManager(productRepository);

    Book book1 = new Book(1, "book1", 100, "author1");
    Book book2 = new Book(2, "book2", 250, "author2");
    Book book3 = new Book(6, "book3", 300, "author3");

    Smartphone smartphone1 = new Smartphone(15, "smartphone1", 13_000, "manufacturer1");
    Smartphone smartphone2 = new Smartphone(9, "smartphone2", 25_999, "manufacturer2");
    Smartphone smartphone3 = new Smartphone(11, "smartphone3", 99_999, "manufacturer3");

    @BeforeEach
    void add() {
        productManager.add(book1);
        productManager.add(book2);
        productManager.add(book3);
        productManager.add(smartphone1);
        productManager.add(smartphone2);
        productManager.add(smartphone3);
    }

    @Test
    void shouldShowByBookName() {
        Product[] actual = productManager.searchBy("book");
        Product[] expected = new Product[]{book1, book2, book3};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldShowBySmartphoneName() {
        Product[] actual = productManager.searchBy("smartphone");
        Product[] expected = new Product[]{smartphone1, smartphone2, smartphone3};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByNoBookName() {
        Product[] actual = productManager.searchBy("book4");
        Product[] expected = new Product[0];
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByNoSmartphoneName() {
        Product[] actual = productManager.searchBy("smartphone4");
        Product[] expected = new Product[0];
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowByManufacturer() {

        Product[] expected = {smartphone1};
        Product[] actual = productManager.searchBy("manufacturer1");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowByAuthorName() {

        Product[] expected = {book1};
        Product[] actual = productManager.searchBy("author1");

        assertArrayEquals(expected, actual);
    }
}