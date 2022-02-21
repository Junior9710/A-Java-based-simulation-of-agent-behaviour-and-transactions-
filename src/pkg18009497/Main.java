/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg18009497;
import java.util.*;

/**
 *
 * @author 18009497
 */

public class Main {

    static int Total_Buyers = 15;
    static int Total_Sellers = 5;
    static int numberOfBooks = 30;
    static int BooksPerSeller = 15;
    
    //Array list for book buyer
    static ArrayList<BookBuyer> bookBuyers = new ArrayList<>();
    //Array list for book seller
    static ArrayList<BookSeller> bookSellers = new ArrayList<>();
    public static void main(String[] args) {


        //Creates and adds buyers to list
        for (int i = 1; i <= Total_Buyers; i++){
            BookBuyer bookBuyer = new BookBuyer(i);
            bookBuyers.add(bookBuyer);
        }

        //Creates and adds buyers to list
        for (int i = 1; i <= Total_Sellers; i++){
            BookSeller bookSeller = new BookSeller(i);
            bookSellers.add(bookSeller);
        }

        //Prints book buyers that have been created
        for(BookBuyer b: bookBuyers){
            System.out.println("Book buyer active: "+ b.ID);
        }

        //Shuffles buyer order
        Collections.shuffle(bookBuyers);
        //Space between book buyers and book sellers for readabilty
        System.out.println("\n");
        //Prints book sellers that have been created
        for(BookSeller s: bookSellers) {
            System.out.println("Book seller active: "+ s.ID);
            //System.out.println("First book to sell: "+ s.localBooks.get(0).name +" For: £"+ s.localBooks.get(0).price);
        }


        System.out.println("\n");
        Random r = new Random();
        //Agents buy books 2 times
        for (int i = 1; i <=2 ; i++) {

            for (BookBuyer b : bookBuyers) {
                int[] bestOffer = b.requestBook();
                //if book exists, buy book
                if (bestOffer[0] == 0) {
                    System.out.println("Out of stock: " + b.currentBookRequest);
                } else {
                    b.purchaseBook(bestOffer);
                }




                System.out.println("\n");
                //Random timer between 1 and 5 seconds for next request
                try {
                    Thread.sleep(r.nextInt(5000) + 1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }



            }
        }

        //Sorts book buyer by money spent highest to lowest
        bookBuyers.sort(Comparator.comparing(bookBuyer -> bookBuyer.moneySpent));
        Collections.reverse(bookBuyers);
        //Displays total money spent for each book agent
        for (BookBuyer b: bookBuyers){
            System.out.println("Book Buyer "+b.ID +" spent £" +b.moneySpent);
        }




    }
}


