package bookstore.action;

import bookstore.entities.Book;
import bookstore.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/BookController.do")
public class BookController extends BaseController {
    private static final long serialVersionUID = 1L;

    BookService bookService;

    @Override
    public void init() throws ServletException {
        bookService = CtxUtil.getBean(BookService.class);
    }

    //  图书列表action
    public String ListBook(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("books", bookService.getAllBooks());
        return "ListBook.jsp";
    }

    //  删除图书action
    public String Delete(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("message", bookService.delete(id) > 0 ? "删除成功！" : "删除失败！");
        request.setAttribute("books", bookService.getAllBooks());
        return "ListBook.jsp";
    }

    //  多删除图书action
    public String Deletes(HttpServletRequest request, HttpServletResponse response){
        String[] ids = request.getParameterValues("ids");
        if(ids != null && ids.length > 0){
            request.setAttribute("message", bookService.delete(ids) > 0 ? "删除成功！" : "删除失败！");
        }
        else {
            request.setAttribute("message", "请选择删除页！");
        }
        request.setAttribute("books", bookService.getAllBooks());
        return "ListBook.jsp";
    }

    //  添加图书action
    public String AddBook(HttpServletRequest request, HttpServletResponse response){
        return "AddBook.jsp";
    }

    //  保存添加图书
    public String AddBookPost(HttpServletRequest request, HttpServletResponse response){
        try {
            String title = request.getParameter("title");
            double price = Double.parseDouble(request.getParameter("price"));

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
            Date publishDate = simpleDateFormat.parse(request.getParameter("publishDate"));

            Book entity = new Book(0, title, price, publishDate);
            if(bookService.add(entity) > 0){
                request.setAttribute("model", new Book());
                request.setAttribute("message", "添加成功！");
            }
            else {
                request.setAttribute("model", entity);
                request.setAttribute("message", "添加失败！");
            }
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            e.printStackTrace();
        }
        return "AddBook.jsp";
    }

    //  编辑图书Action
    public String EditBookPost(HttpServletRequest request, HttpServletResponse response){
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            String title = request.getParameter("title");
            double price = Double.parseDouble(request.getParameter("price"));

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date publishDate = simpleDateFormat.parse(request.getParameter("publishDate"));

            Book entity = new Book(id, title, price, publishDate);
            request.setAttribute("message", bookService.update(entity) > 0 ? "更新成功！" : "更新失败！");
            request.setAttribute("model", entity);
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            e.printStackTrace();
        }
        return "EditBook.jsp";
    }

    // test
    public String myTest(HttpServletRequest request, HttpServletResponse rsResponse){
        int id = 2;
        Book book = bookService.getBookById(id);
        request.setAttribute("book", book);
        System.out.println(book);

        return "test.jsp";
    }

}