package bookstore.service;

import bookstore.entities.Book;
import bookstore.mapper.BookDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookService {

    @Resource
    BookDAO bookDAO;

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }

    public int add(Book entity) throws Exception {
        if (entity.getTitle() == null || entity.getTitle().equals("")) {
            throw new Exception("书名不能为空！");
        }
        return bookDAO.add(entity);
    }

    @Transactional
    public int add(Book entity1, Book entityBak){
        int rows = 0;
        rows = bookDAO.add(entity1);
        rows = bookDAO.add(entityBak);
        return rows;
    }

    public int delete(int id) {
        return bookDAO.delete(id);
    }

    /*
        多删除
     */
    public int delete(String[] ids) {
        int rows = 0;
        for (String idStr : ids) {
            int id = Integer.parseInt(idStr);
            rows += delete(id);
        }
        return rows;
    }

    public int update(Book entity) {
        return bookDAO.update(entity);
    }

}
