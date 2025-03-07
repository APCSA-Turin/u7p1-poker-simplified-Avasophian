package com.example.project;
import java.util.ArrayList;


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards)
    {
        int p1Rank = Utility.getHandRanking(p1Hand);
        int p2Rank = Utility.getHandRanking(p2Hand);
        int p1Total = 0;
        int p2Total = 0;
        ArrayList<Card> player1Cards = new ArrayList<Card>();
        ArrayList<Card> player2Cards =  new ArrayList<Card>();

        //creates a list of all the cards that player 1 and 2 have
        for (int i = 0; i < p1.getHand().size(); i++)
        {
            player1Cards.add(p1.getHand().get(i));
            player2Cards.add(p2.getHand().get(i));
        }
        for (int i = 0; i < communityCards.size(); i++)
        {
            player1Cards.add(communityCards.get(i));
            player2Cards.add(communityCards.get(i));
        }

        if (p1Rank > p2Rank){return "Player 1 wins!";}
        else if (p1Rank < p2Rank){return "Player 2 wins!";}
        else
        {
            // initializes a variable to the first rank value in the hand
            int maximumHandP1 = Utility.getRankValue(player1Cards.get(0).getRank());
            for (Card c: player1Cards)
             {
                // compares the current maximum to each rank subsequent value in the hand
                if (maximumHandP1 < Utility.getRankValue(c.getRank()))
                {
                    //sets new maximum
                    maximumHandP1 = Utility.getRankValue(c.getRank());
                }
            }
           
            // initializes a variable to the first rank value in the hand
            int maximumHandP2 = Utility.getRankValue(player2Cards.get(0).getRank());
            for (Card c: player2Cards)
             {
                // compares the current maximum to each subsequent rank value in the hand
                if (maximumHandP2 < Utility.getRankValue(c.getRank()))
                {
                    //sets new maximum
                    maximumHandP2 = Utility.getRankValue(c.getRank());
                }
            }

            // compares maximums to determine who wins
            if (maximumHandP1 > maximumHandP2){return "Player 1 wins!";}
            else if (maximumHandP2 > maximumHandP1){return "Player 2 wins!";}

            // this was my old solution, i implemented it but it didn't work for some test cases.
            // however some tests failed when i implemented my new solution.
            // so adding this as the 2nd tiebreaker made all the tests pass
            else
            {
                // this checks the total value of ALL their card ranks combined.
                for (Card c : player1Cards)
                {
                    p1Total += Utility.getRankValue(c.getRank());
                }
                for (Card c : player2Cards)
                {
                    p2Total += Utility.getRankValue(c.getRank());
                }
                
                //then compares the total value.
                if (p1Total > p2Total){return "Player 1 wins!";}
                else if (p2Total > p1Total){return "Player 2 wins!";}
                else {return "Tie!";}
            }
        }
    }

    public static void play(){ //simulate card playing
    
    }
        
        

}