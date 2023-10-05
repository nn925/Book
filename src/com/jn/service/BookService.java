package com.jn.service;

import com.jn.bean.Book;
import com.jn.bean.Page;

import java.util.List;

public interface BookService {
    public void addBook(Book book);
    public void deleteBookById(Integer id);
    public void updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();
    public Page<Book> page(int pageNo,int pageSize);
    public Page<Book> pageByPrice(int pageNo,int pageSize,int min,int max);
}
