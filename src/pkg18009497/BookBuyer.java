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


public class BookBuyer {

    //Agent attributes
    int ID;
    String currentBookRequest;
    int moneySpent=0;
    //method for creating book buyers
    BookBuyer(int ID){
        this.ID = ID;
    }

    Random r = new Random();
    int []requestBook(){

        String name = "Book: " + (r.nextInt(Main.numberOfBooks));
        System.out.println("Buyer "+ID +" requesting " + name);
        currentBookRequest = name;
        //array list for trade offers, holds seller ID and price
        ArrayList<int[]> tradeOffers = new ArrayList<>();
        for(BookSeller s: Main.bookSellers) {
            int[] offer = s.shareInventory(name);
            if (offer[0] != 0){
                tradeOffers.add(offer);
            }
        }

        //best offer varible, starts with high price to find lowest offered price
        int [] bestOffer = {0,100000};
        for(int[] offer: tradeOffers){
            if (offer[1] < bestOffer[1] || offer[0]==0) {
                bestOffer[0] = offer[0];
                bestOffer[1] = offer[1];
            }
        }

        return bestOffer;
    }

    void purchaseBook(int[] bestOffer){

        for (BookSeller s: Main.bookSellers) {
            if (bestOffer[0]==s.ID) {
                s.confirmPurchase(currentBookRequest,bestOffer[1]);
                System.out.println("Buyer "+ ID +" purchased " +currentBookRequest+" (£"+bestOffer[1]+") "+ " from seller "+ s.ID);
                //Adds cost of book to total money spent
                moneySpent = moneySpent + bestOffer[1];
                currentBookRequest = "";
                break;
            }
        }
    }



}