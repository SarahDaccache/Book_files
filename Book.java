//----------------------------------------------------------------
//Assignemnt 4
//Written by: Sarah Daccache 40246708
//----------------------------------------------------------------

/**
 * The book class allows to create book instances with the 6 following attributes: title, author, price,
 * ISBN, genre and year.
 */
public class Book {

    String title, author, genre;
    double price;
    long ISBN;
    int year;

    /**
     * Default constructor
     */
    public Book(){

    }

    /**
     * Parameterized constructor
     * @param title title of book
     * @param author author of book
     * @param price price of book
     * @param ISBN ISBN of book
     * @param genre genre of book
     * @param year year of publication of book
     */
   public Book(String title, String author, double price, long ISBN, String genre, int year){
        this.title = title;
        this.author = author;
        this.price = price;
        this.ISBN = ISBN;
        this.genre = genre;
        this.year = year;
   }

    /**
     * To string method to display attributes of a book object
     * @return List of book attributes
     */
   public String toString(){
       return(title + ", " + author + ", " + price  + ", "  + ISBN  + ", " + genre  + ", "  + year);
   }

    /**
     * Equals method to determine if two book objects are equal or not
     * @param o Passed Book object
     * @return True if objects are equals, false if they are not.
     */
   public boolean equals(Object o){
       if( o == this){
           return true;
       }

       if(!(o instanceof Book)){
           return false;
       }

       Book b = (Book) o;

       return (b.title.equals(title) && b.author.equals(author) && b.price == price && b.ISBN == ISBN
       && b.genre.equals(genre) && b.year == year);

   }

    //Accessor and mutator methods
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }




}
