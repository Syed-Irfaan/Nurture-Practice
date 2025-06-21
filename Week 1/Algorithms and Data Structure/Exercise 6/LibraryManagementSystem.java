import java.util.*;

class Book {
    int bookId;
    String title;
    String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public void display() {
        System.out.println("Book ID: " + bookId + ", Title: " + title + ", Author: " + author);
    }
}

public class LibraryManagementSystem {

    public static Book linearSearch(Book[] books, String target) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(target)) {
                return book;
            }
        }
        return null;
    }

    public static Book binarySearch(Book[] books, String target) {
        int left = 0;
        int right = books.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int compare = books[mid].title.compareToIgnoreCase(target);
            if (compare == 0) {
                return books[mid];
            } else if (compare < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of books: ");
        int n = sc.nextInt();
        sc.nextLine();

        Book[] books = new Book[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for Book " + (i + 1) + ":");
            System.out.print("Book ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Title: ");
            String title = sc.nextLine();
            System.out.print("Author: ");
            String author = sc.nextLine();
            books[i] = new Book(id, title, author);
        }

        System.out.print("\nEnter book title to search: ");
        String searchTitle = sc.nextLine();

        Book foundLinear = linearSearch(books, searchTitle);
        if (foundLinear != null) {
            System.out.println("\n[Linear Search] Book Found:");
            foundLinear.display();
        } else {
            System.out.println("\n[Linear Search] Book not found.");
        }

        Arrays.sort(books, Comparator.comparing(b -> b.title.toLowerCase()));

        Book foundBinary = binarySearch(books, searchTitle);
        if (foundBinary != null) {
            System.out.println("[Binary Search] Book Found:");
            foundBinary.display();
        } else {
            System.out.println("[Binary Search] Book not found.");
        }

        

        sc.close();
    }
}
