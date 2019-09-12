package bookstore.entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Book {
    private int id;
    private String title;
    private double price;
    @DateTimeFormat(pattern = "yy-MM-dd")
    private Date publishDate;

    public Book() {
    }

    public Book(int id, String title, double price, Date publishDate) {
        this.id = id;   // 编号
        this.title = title; // 书名
        this.price = price; // 价格
        this.publishDate = publishDate;   // 出版日期
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPublicDate() {
        return publishDate;
    }

    public void setPublicDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        String book = "id: " + this.id + " title: " + this.title
                + " price: " + this.price + " publishDate: " + this.publishDate;
        return book;
    }
}
