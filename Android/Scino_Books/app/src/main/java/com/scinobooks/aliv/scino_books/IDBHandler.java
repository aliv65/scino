package com.scinobooks.aliv.scino_books;

import java.util.List;

public interface IDBHandler {

        public void addBook(Book book);
        public void addCategory(Category category);

        public Book getBook(int id);
        public Book getBook(String name);

        public Category getCategory(int id);
        public Category getCategory(String name);

        public List<String> getBookByCategory(String title);
        public List<String> getAllBooks();
        public List<String> getAllCategories();

        public int getBooksCount();

        public int updateBook(Book book);
        public int updateCategory(Category category);

        public void deleteBook(Book book);
        public void deleteCategory(Category category);
        public void deleteAll();
}
