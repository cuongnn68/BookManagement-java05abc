package abc.java05.model;

public class Book {
    private String id;
    private String title;
    private String author;
    private String brief;
    private String publisher;
    private String content;
    private String category;


    public Book(){}
    public Book(String title, String author, String brief, String publisher, String category, String content) {
        this.title = title;
        this.author = author;
        this.brief = brief;
        this.publisher = publisher;
        this.content = content;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

  public void display(){
      System.out.printf("\n%-8s%-10s%-14s%-11s%-13s%-9s%-15s%-9s%",this.id ,this.title,this.author,this.category,this.brief,this.content,this.publisher);
  }
}
