import bookstore.entities.Book;
import bookstore.service.BookService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class TestBookService {

    static BookService bookService;

    @BeforeClass
    public static void before(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        bookService = ctx.getBean(BookService.class);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = bookService.getAllBooks();
        for (Book book : books){
            System.out.println(book);
        }
        Assert.assertNotNull(books);
    }

    @Test
    public void testAdd(){
        Book entity=new Book(0, "Hibernate 第七版", 78.1, new Date());
        try {
            Assert.assertEquals(1, bookService.add(entity));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteInt(){
        Assert.assertEquals(1, bookService.delete(16));
    }

    @Test
    public void testDeleteStringArray(){
        String[] ids = {"7", "11", "12"};
        Assert.assertEquals(3, bookService.delete(ids));
    }

    @Test
    public void testUpdate(){
        Book entity = new Book(2, "Hibernate 第二版", 79.1, new Date());
        Assert.assertEquals(1, bookService.update(entity));
    }

    @Test
    public void testGetBookById(){
        Assert.assertNotNull(bookService.getBookById(1));
    }

    @Test
    public void testAddDouble(){
        // 因为书名相同，添加第二本会失败，用于测试事务
        Book entity1 = new Book(0, "Hibernate 第八版", 78.1, new Date());
        Book entity2 = new Book(0, "Hibernate 第八版", 78.1, new Date());
        Assert.assertEquals(2, bookService.add(entity1, entity2));
    }

    /*@Test
    public void test(){
        System.out.println(Math.ceil(1));
        System.out.println(Math.floor(2));
    }*/

}
