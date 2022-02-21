/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg18009497;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author 18009497
 */
public class BookSeller {

    //Seller attributes
    int ID;
    ArrayList<Book> localBooks = new ArrayList<>();
    //method for creating book sellers
    BookSeller(int ID){
        this.ID = ID;
        for(int i = 1; i <= Main.BooksPerSeller; i++){
            newBook();
        }
    }

    Random r = new Random();
    void newBook() {
        String name = "Book: " + (r.nextInt(Main.numberOfBooks));
        int price = r.nextInt(60)+5;
        Book book = new Book(name, price);
        localBooks.add(book);
    }

    int [] shareInventory(String name){
        int[] offer = {0, 0};
        for(Book b: localBooks){
            if (b.name.equals(name)){
                offer[0] = ID;
                offer[1] = b.price;
                //Prints book seller offer
                System.out.println("Seller: "+ID +" can offer "+ name+" for £"+ b.price);
                break;
            }
        }

        return offer;
    }

    public void confirmPurchase(String currentBookRequest, int price) {
        for (Book b: localBooks){
            if (b.name.equals(currentBookRequest) && b.price == price) {
                localBooks.remove(b);
                break;
            }
        }

    }
}