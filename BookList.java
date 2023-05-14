import java.io.*;
import java.util.Scanner;

//----------------------------------------------------------------
//Assignemnt 4
//Written by: Sarah Daccache 40246708
//----------------------------------------------------------------

/**
 * The BookList class has a Node class as a private inner class. It has one attribute classed head, which
 * points to the first node of a BookList object. This class allows to create a circular linked list
 * containing all valid book objects that we want to work with. It has multiple methods that help execute
 * different actions to the list of books.
 */
public class BookList {
    private Node head;

    /**
     * Node class as a private inner class
     */
    private class Node {

        private Book b;
        private Node next;

        /**
         * Default constructor, initializes head to null
         */
        public Node() {
            head = null;
        }

        /**
         * Parameterized constructor
         *
         * @param b    current book in list
         * @param next next book in list
         */
        public Node(Book b, Node next) {
            this.b = b;
            this.next = next;
        }

        // Accessor and mutator methods
        public Book getB() {
            return b;
        }

        public void setB(Book b) {
            this.b = b;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }


    }


    /**
     * Constructor that initializes head to null
     */
    public BookList() {
        head = null;
    }

    /**
     * This method adds a node to the start of the list
     *
     * @param b Passed book object
     */
    public void addToStart(Book b) {

        head = new Node(b, head);
    }

    /**
     * This method adds a node to the end of the list. It is used to create a bookList while
     * keeping the same order that the books are in. If we used addToStart, everything would be reversed.
     *
     * @param b book to add to the list
     */
    public void addAtEnd(Book b) {
        Node t = new Node(b, null);

        //If list is empty at first
        if (head == null) {
            head = t;
            head.next = head; // point the head node to itself
            return;
        }

        Node lastNode = head;
        while (lastNode.next != head) {
            lastNode = lastNode.next;
        }

        lastNode.next = t;
        t.next = head; // Ensure that the last node points to the head node
    }


    /**
     * This method finds all the book records based on a given year, and stores them in a file called yr.txt
     *
     * @param yr Given year
     */
    public void storeRecordsByYear(int yr) {
        boolean found = false; // Initialize a boolean variable to keep track if books are found
        Node t = head;
       //Loop through linked list until end or head is reached.
        while (t != null) {
            Book b = t.getB();
            if (b.getYear() == yr) {
                found = true;
                break;
            }
            t = t.next;
            if (t == head) break;
        }
        if (found) {
            try {

                FileWriter fw = new FileWriter(yr + ".txt");
                t = head;
                do {
                    Book b = t.getB();
                    //If the year of publication of the current book matches the specified year,
                    // write its record to file
                    if (b.getYear() == yr) {
                        fw.write(b.getTitle() + "," + b.getAuthor() + "," + b.getPrice() + "," + b.getISBN() + "," + b.getYear() + "\n");
                    }
                    // Move to the next Node in the linked list
                    t = t.next;
                } while (t != head);
                System.out.println("Successfully written records to file.");
                fw.close();
            } catch (IOException e) {
                System.out.println("Error writing to file.");
            }
        }
    }

    /**
     * This method searches the list for the first occurrence of a Node holding a Book object
     * that has an ISBN equals to the passed one. If it finds one, it inserts the new book before
     * it. If it doesn't find it, return false.
     *
     * @param ISBN ISBN in search
     * @param b    book to add
     * @return tru or false if book was found or not
     */
    public boolean insertBefore(long ISBN, Book b) {
        if (head == null) { // list is empty
            System.out.println("List empty");
            return false;
        }

        Node current = head;
        Node prev = null;

        do {
            //Check for book corresponding to isbn
            if (current.getB().getISBN() == ISBN) {
                System.out.println("Found book with ISBN " + ISBN);
                Node t = new Node(b, current);

                if (prev == null) { // inserting at head
                    System.out.println("Inserting at head");
                    if (head.next == head) {
                        head.next = t;
                        t.next = head;
                    } else {
                        t.next = head;
                        Node lastNode = head;
                        while (lastNode.next != head) {
                            lastNode = lastNode.next;
                        }
                        lastNode.next = t;
                        head = t;
                    }


                }
                //Inserting anywhere after head
                else {
                    System.out.println("Inserting after previous node");
                    prev.next = t;
                    t.next = current;
                }

                return true;
            }

            prev = current;
            current = current.next;
        } while (current != head); // loop until we reach the starting point

        System.out.println("Book not found"); // book not found
        return false;
    }


    /**
     * This method searches the list for the first occurrence of the first two consecutive nodes holding Book objects
     * with ISBN values equal to isbn1 and isbn2. If found, insert new book in between. If not found, return false.
     *
     * @param ISBN1 first ISBN
     * @param ISBN2 second ISBN
     * @param b     book to add in between
     * @return returns true or false if book was found.
     */
    public boolean insertBetween(long ISBN1, Long ISBN2, Book b) {
        if (head == null) {
            return false;
        }

        Node t = head;

        do {
            //Check for consecutive books corresponding to isbn1 and isbn2
            if (t.getB().getISBN() == ISBN1 && t.next.getB().getISBN() == ISBN2) {
                Node newNode = new Node(b, t.next);
                t.next = newNode;
                return true;
            }
            // If the ISBN of the current Node matches ISBN2 and the next Node is the head,
            // insert the new Node as the last element in the linked list
            else if (t.getB().getISBN() == ISBN2 && t.next == head) {
                Node newNode = new Node(b, head);
                t.next = newNode;
                return true;
            }
            t = t.next;
        } while (t != head); // loop until we reach the starting point

        return false;
    }

    /**
     * This method displays the contents of the circular linked list for the user to read from.
     */
    public void displayContent() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node t = head;
        do {
            System.out.println(t.getB().getTitle() + ", " + t.getB().getAuthor() + ", " +
                    t.getB().getPrice() + ", " + t.getB().getISBN() + ", " + t.getB().getGenre() + ", " +
                    t.getB().getYear() + " ==>");
            t = t.getNext();
        } while (t != head && t.next != null);

        System.out.println("===> head");

    }

    /**
     * This method will find all consecutive repeated nodes, each having the same Book record and will delete them, if any.
     * It deletes only consecutive records.
     * @return True if consecutive records were found and deleted, false if not.
     */
    public boolean delConsecutiveRepeatedRecords() {
        if (head == null) { // list is empty
            System.out.println("List is empty");
            return false;
        }
        boolean deleted = false;
        Node t = head;
        do {
            //Check for two consecutive records
            if (t.getB().equals(t.next.getB())) {
                t.next = t.next.next;
                deleted = true;

            }
            //Check if head is consecutive with last node
            if (t.next == head && t.getB().equals(head.getB())) {
                head = head.next;
                deleted = true;
            } else {
                t = t.next; // move to next node

            }

        } while (t.next != head); //loop until we reach starting point
        if (deleted) {
            System.out.println("Successfully deleted consecutive records.");
        } else {
            System.out.println("No consecutive records found. Nothing was deleted.");
        }

        return deleted;

    }

    /**
     * This method will search all records in the calling list for Book objects that have the author given to the
     * method, and if found the method will create a new singular circular list that includes only records of that author.
     * It however does not change the official BookList, but rather creates a new one only for display purposes.
     * @param aut Author's name
     * @return List of all books written by the passed author
     */
    public BookList extractAuthList(String aut) {
        boolean found = false;
        Node t = head;
        // Traverse the linked list until either a book by the author is found or the end of the list is reached.
        while (t != null) {
            Book b = t.getB();
            if (b.getAuthor().equals(aut)) {
                found = true;
                break;
            }
            t = t.next;
            if (t == head) break;
        }
        // If a book by the author was found, create a new BookList object to store the books.
        if (found) {
            BookList authList = new BookList();
            Node s = new Node(t.getB(), null);
            authList.head = s;
            // Traverse the linked list, starting from the next node after the one containing
            // the first book by the author.
            t = t.next;
            while (t != head) {
                Book b = t.getB();
                // If a book by the author is found, create a new node u to hold it and add it to the
                // end of the new BookList.
                if (b.getAuthor().equals(aut)) {
                    Node u = new Node(b, null);
                    s.next = u;
                    s = u;
                }
                t = t.next;
            }
            s.next = authList.head;
            return authList;
        }
        return null;
    }

    /**
     * This method will rearrange some of the records in the list. Specifically, the method will swap
     * the first pair of nodes of which the first stores a Book record with isbn1 and the second stores a
     * Book record with isbn2, then returns true.
     * @param isbn1 First Book's ISBN
     * @param isbn2 Second Book's ISBN
     * @return True if the two Book records were found and the swap was completed, false otherwise.
     */
    public boolean swap(long isbn1, long isbn2) {
        boolean found1 = false;
        boolean found2 = false;
        Node node1 = null;
        Node node2 = null;
        Node curr = head;

        while (curr != null && (!found1 || !found2)) {
            // If the current node contains the first book to be swapped, set found1 to true
            // and store the node in node1.
            if (curr.getB().getISBN() == isbn1) {
                found1 = true;
                node1 = curr;
            }
            // If the current node contains the second book to be swapped, set found2 to true
            // and store the node in node2.
            else if (curr.getB().getISBN() == isbn2) {
                found2 = true;
                node2 = curr;
            }
            // Move to next node.
            curr = curr.getNext();
        }
        if (!found1 || !found2) {
            return false;
        }
        Book temp = node1.getB();
        node1.setB(node2.getB());
        node2.setB(temp);
        return true;
    }

    /**
     * This method finally commits the content of the list at the time of its call by storing the contents of
     * the list in a file called Update_Books.txt.
     */
    public void commit() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Update_Books.txt"));

            Node current = head;
            //Writing each element of the circular linked list to the file.
            do {
                writer.write(current.getB().toString() + "\n");
                current = current.next;
            } while (current != head);
            System.out.println("Update_Books.txt successfully written to.");

            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

}
























