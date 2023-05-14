import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Book> arrLst = new ArrayList<Book>(); //Create ArrayList of books
        BookList bkLst = new BookList(); //Create BookList object

        // Read all records
        String[] fields = new String[6];
        //Open and read Books.txt
        Scanner sc = new Scanner(new FileInputStream("Books.txt"));

        // Create PrintWriter for YearErr.txt only if there are errors of year
        boolean hasYearErrors = false;
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            createBookFields(data, fields);
            int year = Integer.parseInt(fields[5]);
            if (year > 2023) {
                hasYearErrors = true;
                arrLst.add(new Book(fields[0], fields[1], Double.parseDouble(fields[2]), Long.parseLong(fields[3]),
                        fields[4], year));
            } else {
                bkLst.addAtEnd(new Book(fields[0], fields[1], Double.parseDouble(fields[2]), Long.parseLong(fields[3]),
                        fields[4], year));
            }
        }
        sc.close();

        if (hasYearErrors) {
            // Clear contents of YearErr.txt file
            PrintWriter clear = new PrintWriter("YearErr.txt");
            clear.close();

            PrintWriter pw = null;
            try {
                pw = new PrintWriter(new FileWriter("YearErr.txt", true));
            } catch (IOException ex) {
                System.out.println("Error creating PrintWriter: " + ex.getMessage());
            }
            // Write arraylist of incorrect year record to YearErr.txt file
            for (int i = 0; i < arrLst.size(); i++) {
                Book book = arrLst.get(i);
                pw.println(book.toString());
            }
            pw.close();
            System.out.println("YearErr file created");
        }

        //Displaying contents of current LinkedList
        System.out.println("Here are the contents of the list\n=================================");
        bkLst.displayContent();


        int select = 0;
        // Loop until exit case 8 is selected
        do {
            // Display functions of code
            System.out.println("Tell me what you want me to do? Let's Chat since this is trending now! Here are the options:\n" +
                    "   1) Give me a year # and I will extract all records of that year and store them in a file for that year;\n" +
                    "   2) Ask me to delete all consecutive repeated records;\n" +
                    "   3) Give me an author name and I will create a new list with the records of this author and display them;\n" +
                    "   4) Give me an ISBN number and a Book object, and I will insert Node with the book before the record with this ISBN;\n" +
                    "   5) Give me 2 ISBN numbers and a Book object, and I will insert Node between them, if I find them!\n" +
                    "   6) Give me 2 ISbn numbers and I will swap them in the list for rearrangement of records; of course if they exist!\n" +
                    "   7) Tell me to COMMIT! Your command is my wish. I will commit your list to a file called Updated_Books;\n" +
                    "   8) Tell me to STOP TALKING. Remember, if you do not commit, I will not!");
            System.out.print("Enter your Selection: ");

            Scanner key = new Scanner(System.in);
            select = key.nextInt(); //Read user input for chosen function to execute.

            Scanner key1 = new Scanner(System.in);

            // Switch statement or each function value.
            switch (select) {
                // Case 1 calling storeRecordsByYear method. Prompts user for a year.
                case 1: {
                    System.out.print("Enter a year: ");
                    int yr = key1.nextInt();
                    bkLst.storeRecordsByYear(yr);
                    break;
                }
                // Case 2 calling delConsecutiveRepeatedRecords method.
                case 2: {
                    bkLst.delConsecutiveRepeatedRecords();
                    System.out.println("Here are the contents of the list after removing consecutive duplicates\n" +
                            "=======================================================================");
                    bkLst.displayContent(); //Display updated content to console.
                    break;
                }

                // Case 3 calling extractAuthList method. Prompts user for author name.
                case 3: {
                    System.out.print("Enter the author's name: ");
                    String aut = key1.nextLine();
                    BookList authList = bkLst.extractAuthList(aut); // Creating new list to not avoid changing original one.
                    System.out.println("Here are the contents of the author list for " + aut);
                    System.out.println("============================================================");
                    authList.displayContent();
                    System.out.println("Here are the contents of the original list\n====================================");
                    bkLst.displayContent(); // Display updated content to console.
                    break;
                }

                // Case 4 calling insertBefore method. Prompts user for 1 ISBN and 1 Book record.
                case 4: {
                    System.out.print("ISBN number: ");
                    String ISBN = key1.nextLine();

                    System.out.print("Give me the title of the book: ");
                    String title = key1.nextLine();

                    System.out.print("The author of the book: ");
                    String author = key1.nextLine();

                    System.out.print("The price of the book (use format: 15.46): ");
                    String price = key1.nextLine();

                    System.out.print("The ISBN of the book: ");
                    String ISBN2 = key1.nextLine();

                    System.out.print("The genre of the book: ");
                    String genre = key1.nextLine();

                    System.out.print("The year of the book: ");
                    String year = key1.nextLine();

                    Book b = new Book(title, author, Double.parseDouble(price), Long.parseLong(ISBN2)
                            , genre, Integer.parseInt(year)); //Creating Book object with attributes entered by user.
                    bkLst.insertBefore(Long.parseLong(ISBN), b);
                    System.out.println("Here are the contents of the author list after inserting the new book: ");
                    System.out.println("======================================================================");
                    bkLst.displayContent(); // Display updated content to the console.
                    break;
                }
                // Case 5 calling insertBetween method. Prompts user for 2 ISBN values and 1 Book record.
                case 5: {
                    System.out.print("ISBN number 1: ");
                    String ISBN1 = key1.nextLine();

                    System.out.print("ISBN number 2: ");
                    String ISBN2 = key1.nextLine();

                    System.out.print("Give me the title of the book: ");
                    String title = key1.nextLine();

                    System.out.print("The author of the book: ");
                    String author = key1.nextLine();

                    System.out.print("The price of the book (use format: 15.46): ");
                    String price = key1.nextLine();

                    System.out.print("The ISBN of the book: ");
                    String ISBNb = key1.nextLine();

                    System.out.print("The genre of the book: ");
                    String genre = key1.nextLine();

                    System.out.print("The year of the book: ");
                    String year = key1.nextLine();

                    Book b = new Book(title, author, Double.parseDouble(price), Long.parseLong(ISBN2)
                            , genre, Integer.parseInt(year)); // Creating Book record objects with attributes entered by user.
                    bkLst.insertBetween(Long.parseLong(ISBN1), Long.parseLong(ISBN2), b);
                    System.out.println("Here are the contents of the author list after inserting the new book: ");
                    System.out.println("======================================================================");
                    bkLst.displayContent(); // Display updated content to console.
                    break;
                }
                //  Case 6 calling swap method. Prompts user for 2 ISBN values of books to be swapped.
                case 6 : {
                    System.out.print("ISBN number 1: ");
                    String ISBN1 = key1.nextLine();

                    System.out.print("ISBN number 2: ");
                    String ISBN2 = key1.nextLine();
                    bkLst.swap(Long.parseLong(ISBN1), Long.parseLong(ISBN2));
                    System.out.println("Here are the contents of the list after swapping the two indicated book objects: ");
                    System.out.println("================================================================================");
                    bkLst.displayContent(); // Display updated content to console .
                    break;
                }
                // Case 7 calling commit method.
                case 7 : {
                    bkLst.commit();
                 break;
                }
                // Case 8 to purposely terminate program.
                case 8 :{
                    System.exit(0);
                    break;
                }

            }
        } while (true);
    }



    /**
     * Static method to seperate book fields and store them in an array.
     * @param data data of the book
     * @param fields array where data of the book is stored
     * @return returns the array of book fields
     */
    public static String[] createBookFields(String data, String[] fields) {

        int i = 0;
        int index = 0;
        while (i < data.length() && index < 6) {
            String value = "";
            if (data.charAt(i) == '\"') {
                i++;
                value = "\"";
                while (i < data.length() && data.charAt(i) != '\"') {
                    value += data.charAt(i);
                    i++;
                }
                if (i < data.length() && data.charAt(i) == '\"') {
                    value += "\"";
                    i++; // skip the closing double quote
                }
            } else {
                while (i < data.length() && data.charAt(i) != ',' && data.charAt(i) != '\n') {
                    value += data.charAt(i);
                    i++;
                }
            }
            fields[index] = value;
            index++;
            if (i < data.length() && data.charAt(i) == ',') {
                i++; // skip the comma separator
            }
            if (i < data.length() && data.charAt(i) == '\n') {
                break; // end of record
            }
        }
        return fields;
    }
}