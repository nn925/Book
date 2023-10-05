package com.jn.dao;

import com.jn.bean.Book;
import com.sun.javafx.tk.TKPulseListener;

import java.util.List;

/**
 * @author jiangna27602
 */
public interface BookDao {
    /**
     * 新增
     * @param book
     * @return 如果返回-1,说明执行失败<br/>返回其他表示影响的行数
     */
    public int addBook(Book book);

    /**
     * 根据id删除
     * @param id
     * @return 如果返回-1,说明执行失败<br/>返回其他表示影响的行数
     */
    public int deleteBookById(Integer id);

    /**
     * 修改
     * @param book
     * @return 如果返回-1,说明执行失败<br/>返回其他表示影响的行数
     */
    public int updateBook(Book book);

    /**
     * 根据id查询
     * @param id
     * @return 返回一个Book对象
     */
    public Book queryBookById(Integer id);

    /**
     * 查询所有Book
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 查询Book的总数量
     * @return
     */
    public Integer queryForPageTotalCount();

    /**
     * 查询指定页的book
     * @return
     */
    public List<Book> queryForPageItems(int begin,int pageSize);

    /**
     * 查询价格区间内的记录总数
     * @param min
     * @param max
     * @return
     */
    public Integer queryForPageTotalCountByPrice(int min,int max);

    /**
     *按价格区间分页查询
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    public List<Book> queryForPageItemsByPrice(int begin,int pageSize,int min,int max);

}
