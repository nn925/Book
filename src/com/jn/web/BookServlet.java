package com.jn.web;

import com.jn.bean.Book;
import com.jn.bean.Page;
import com.jn.service.BookService;
import com.jn.service.impl.BookServiceImpl;
import com.jn.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数，封装成Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //2、调用 BookService.addBook()保存图书
        bookService.addBook(book);
        //3、跳到图书列表页面--重定向
        //使用重定向而不是请求转发的原因：当用户提交完请求，浏览器会记录下最后一次请求的全部信息。当用户按下功能键 F5，就会发起浏览器记录的最后一次请求。
        //即如果使用请求转发，按一次F5，就会调用一次add
        //重定向中，参照路径为web服务器的根路径http://ip:port/
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page");
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数 id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 2、调用 bookService.deleteBookById();删除图书
        bookService.deleteBookById(id);
        // 3、重定向回图书列表管理页面
        // /book/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page");
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数，封装成Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        // 2、调用 bookService.updateBook(book);修改图书
        bookService.updateBook(book);
        // 3、重定向回图书列表管理页面
        // /book/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page");
    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 通过 BookService 查询全部图书
        List<Book> books = bookService.queryBooks();
        //2 把全部图书保存到 Request 域中
        req.setAttribute("books", books);
        //3、请求转发到/pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    /**
     * 获取指定图书信息返回到修改页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取图书的编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //调用bookService.queryBookById
        Book book = bookService.queryBookById(id);
        //将数据保存到request域中
        req.setAttribute("book",book);
        //将请求转发到/pages/manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 获取请求的参数 当前页码 和 每页显示数量
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 调用BookService.page()
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");
        // 保存page对象到Request域中
        req.setAttribute("page",page);
        // 请求转发到 pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
