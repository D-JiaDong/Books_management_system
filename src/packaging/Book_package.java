package packaging;

public class Book_package extends Package{
    private int Book_id;
    private String Book_name;
    private String Book_author;
    private float Book_price;
    private int Book_num;
//    private int type_id;

    public Book_package() {
    }

    public int getBook_id() {
        return Book_id;
    }

    public void setBook_id(int book_id) {
        Book_id = book_id;
    }

    public String getBook_name() {
        return Book_name;
    }

    public void setBook_name(String book_name) {
        Book_name = book_name;
    }

    public String getBook_author() {
        return Book_author;
    }

    public void setBook_author(String book_author) {
        Book_author = book_author;
    }

    public float getBook_price() {
        return Book_price;
    }

    public void setBook_price(float book_price) {
        Book_price = book_price;
    }

    public int getBook_num() {
        return Book_num;
    }

    public void setBook_num(int book_num) {
        Book_num = book_num;
    }

//    public int getType_id() {
//        return type_id;
//    }
//
//    public void setType_id(int type_id) {
//        this.type_id = type_id;
//    }
}
