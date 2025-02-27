package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{
    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public  void initializeDeck()
    { //hint.. use the utility class
        String currentRank = "";
        String currentSuit = "";

        for (int i = 0; i < Utility.getSuits().length; i++)
        {
            for (int j = 0; j < Utility.getRanks().length; i++)
            {
                currentSuit = Utility.getSuits()[i];
                currentRank = Utility.getSuits()[j];
                cards.add(new Card(currentSuit, currentRank));
            }
        }
    }

    public  void shuffleDeck(){ //You can use the Collections library or another method. You do not have to create your own shuffle algorithm
        
    }

    public  Card drawCard(){
       return new Card("","");
    }

    public  boolean isEmpty(){
        return cards.isEmpty();
    }

    public static void main(String[] args) 
    {
        Deck d = new Deck();
        d.initializeDeck();
        for (Card c : d.getCards())
        {
            System.out.println(c.getRank() + " " + c.getSuit());
        }
    }


}