package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c)
    {
        hand.add(c);
    }

    private void setAllCards(ArrayList<Card> cards)
    {
        allCards = cards;
    }

    private int rankValue(Card c) {return Utility.getRankValue(c.getRank());}
    
    private boolean isRoyalFlush ()
    {
        // essentially, what these variables do is make sure that the program only counts an ace, king, queen, jack and ten ONCE.
        // to make sure it doesn't track, say, 10, 10, Q, J, A as a royal flush
        boolean hasAce = false;
        boolean hasKing = false;
        boolean hasQueen = false;
        boolean hasJack = false;
        boolean hasTen = false;
        // this is for counting the amount of times a rank that causes a royal flush appears
        int rankCount = 0;
        
        // this is for counting the amount of times the same suit appears.
        // i coded this before flush. had i not, i would've just used isFlush() instead.
        int suitCount = 1;

        // iterates through allCards to see if it fits all the requirements of a royal flush in terms of rank.
        for (Card c : allCards)
        {
            if (rankValue(c) == 10 && hasTen == false)
            {
                hasTen = true; rankCount ++;
            }
            else if (rankValue(c) == 11 && hasJack == false)
            {
                hasJack = true; rankCount ++;
            }
            else if (rankValue(c) == 12 && hasQueen == false)
            {
                hasQueen = true; rankCount ++;
            }
            else if (rankValue(c) == 13 && hasKing == false)
            {
                hasKing = true; rankCount ++;
            }
            else if (rankValue(c) == 14 && hasAce == false)
            {
                hasAce = true; rankCount ++;
            }
        }

        // because all suits have to be the same, this just checks the first suit.
        String suitToMatch = allCards.get(0).getSuit();

        // then checks that the rest of them match that first suit in the list.
        // starts at 1 since 0 was already checked
        for (int i = 1; i < allCards.size(); i++)
        {
            if (allCards.get(i).getSuit().equals(suitToMatch))
            {
                // adds to suit count whenever an equal suit to the initial one is counted
                suitCount++;
            }
        }
        if (rankCount == 5 && suitCount == 5){return true;}
        return false;
    }

    private boolean isStraightFlush(){return (isStraight() && isFlush());}

    private boolean isFourOfAKind()
    {
        // i didn't know about the frequency methods until i was already done with all of these.
        // ... oh well.
        // this just does what that one would do. 
        int[] appearances = new int[15];

        // iterates through all cards
        for (Card c : allCards)
        {
            appearances[rankValue(c)] ++;
        }
        
        // checks to see if any have an index above 4, returns true if one does
        for (int i = 0; i < appearances.length; i++)
        {
            if (appearances[i] >= 4) {return true;}
        }
        return false;
    }

    private boolean isFullHouse()
    {
        // variables which will store if there is 1 pair and 1 trio
        int pairs = 0;
        int trios = 0;

        //as mentioned beforehand i didn't know about the frequency methods until i was already done with all of these
        int[] appearances = new int[15];
        for (Card c : allCards)
        {
            appearances[rankValue(c)] ++;
        }

        // if there is one index with a number above 2, adds to pairs
        for (int i = 0; i < appearances.length; i++)
        {
            if (appearances[i] == 2) {pairs++;}
        }
        // if there is one index with a number above 3, adds to trios
        for (int i = 0; i < appearances.length; i++)
        {
            if (appearances[i] == 3) {trios++;}
        }

        //if there's a pair and a trio, it's a FULL HOUSE !!
        if (pairs == 1 && trios == 1){return true;}
        return false;
    }

    private boolean isFlush()
    {
        int suitCount = 1;
        // because all suits have to be the same, this just checks the first suit.
        String suitToMatch = allCards.get(0).getSuit();

         // then checks that the rest of them match that first suit in the list.
        // starts at 1 since 0 was already checked
        for (int i = 1; i < allCards.size(); i++)
        {
            if (allCards.get(i).getSuit().equals(suitToMatch))
            {
                // adds to suit count whenever an equal suit to the initial one is counted
                suitCount++;
            }
        }
        if (suitCount == 5){return true;}
        return false;
    }

    //PRECONDITION : allCards() needs to be sorted.
    private boolean isStraight()
    {
        // Because the for loop will only be iterating 4 times in the first place, not 4.
        int sequenceCount = 1;
        
        // Checks the next value in the list to see if it's equal to the current, if it is, increases sequencecount
        for (int i = 0; i < allCards.size() - 1; i++)
        {
            if (rankValue(allCards.get(i)) + 1 == rankValue(allCards.get(i + 1))) 
            {
                sequenceCount ++;
            }
        }
        System.out.println(sequenceCount);

        // Returns true if all the values in the list are 1 less than the one directly after
        if (sequenceCount >= 5){return true;}
        return false;
    }

    private boolean isThreeOfAKind()
    {
        // same as four of a kind but it checks..
        // guess what...
        // THREE!!!!! 😵‍💫🤯
        // instead of 4
        int[] appearances = new int[15];
        for (Card c : allCards)
        {
            appearances[rankValue(c)] ++;
        }
        for (int i = 0; i < appearances.length; i++)
        {
            if (appearances[i] >= 3) {return true;}
        }
        return false;
    }

    private boolean isTwoPair()
    {
        //similar to fourofakind and threeofakind but slightly different.
        int[] appearances = new int[15];

        //checks to count how many pairs there are.
        int pairs = 0;
        for (Card c : allCards)
        {
            appearances[rankValue(c)] ++;
        }

        //iterates through the list twice.
        //appearances[i] is set to zero because if it isn't the second loop will count the appearance twice.
        for (int i = 0; i < appearances.length; i++)
        {
            if (appearances[i] >= 2) {appearances[i] = 0; pairs++; break;}
        }
        for (int i = 0; i < appearances.length; i++)
        {
            if (appearances[i] >= 2) {pairs++; break;}
        }
        //returns true if pairs == 2
        if (pairs == 2){return true;}
        return false;
    }

    private boolean isOnePair()
    {
        //similar to isTwoPair(), but searches for one pair instead of two.
        int[] appearances = new int[15];
        for (Card c : allCards)
        {
            appearances[rankValue(c)] ++;
        }

        //searches for a pair once.
        for (int i = 0; i < appearances.length; i++)
        {
            if (appearances[i] >= 2) {return true;}
        }
        return false;
    }

    private boolean isHighCard(ArrayList<Card> communityCards)
    {
        //sets maximumCommunity to the value at index 0. 
        int maximumCommunity = rankValue(communityCards.get(0));

        //searches through communityCards to find the highest value in communityCards.
        for (Card c: communityCards)
        {
            if (maximumCommunity < rankValue(c))
            {
                maximumCommunity = rankValue(c);
            }
        }
        //sets maximumCommunity to the value at index 0. 
        int maximumHand = rankValue(hand.get(0));

        //searches through hand to find the highest value in hand.
        for (Card c: hand)
        {
            if (maximumHand < rankValue(c))
            {
                maximumHand = rankValue(c);
            }
        }
    
        //returns true if the highest card in the hand is higher than the highest in the community
        if (maximumHand > maximumCommunity){return true;}
        else {return false;}
    }

    public String playHand(ArrayList<Card> communityCards)
    {      
        allCards = new ArrayList<Card>();

        //Sets allCards to the sum of hand and communityCards.
        for (Card c : hand)
        {
            allCards.add(c);
        }
        for (Card c : communityCards)
        {
            allCards.add(c);
        }

        //isStraight() needs a sorted deck, so this sorts the deck so isStraight() is correct.
        sortAllCards();

        //uses all the above methods to check for which string to return.
        if (isRoyalFlush()){return "Royal Flush";}
        else if (isStraightFlush()){return "Straight Flush";}
        else if (isFourOfAKind()){return "Four of a Kind";}
        else if (isFullHouse()){return "Full House";}
        else if (isFlush()){return "Flush";}
        else if (isStraight()){return "Straight";}
        else if (isThreeOfAKind()){return "Three of a Kind";}
        else if (isTwoPair()){return "Two Pair";}
        else if (isOnePair()){return "A Pair";}
        else if (isHighCard(communityCards)){return "High Card";}
        //returns nothing is nothing else is applicable
        else {return "Nothing";}
    }

    public void sortAllCards()
    {
        //uses a selection sort techniaue to sort the cards by rank.
        for (int i = 0; i < allCards.size(); i++)
        {
            int minimum = rankValue(allCards.get(i));
            int minimumIndex = i;
            for (int j = i; j < allCards.size(); j++)
            {
                if (minimum > rankValue(allCards.get(j)))
                {
                    minimumIndex = j;
                    minimum = rankValue(allCards.get(j));
                }
            }
            Card temp = allCards.get(minimumIndex);
            allCards.set(minimumIndex, allCards.get(i));
            allCards.set(i, temp);
        } 
    } 
    public static void main(String[] args) 
    {
        Card c1 = new Card("6","♠");
        Card c2 = new Card("3","♠");
        Card c3 = new Card("5","♠");
        Card c4 = new Card("4","♠");
        Card c5 = new Card("7","♠");
        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(c1);
        cardList.add(c2);
        cardList.add(c3);
        cardList.add(c4);
        cardList.add(c5);
        Player p = new Player();
        p.setAllCards(cardList);
        p.sortAllCards();
        System.out.println(cardList);

    }

    // I did not realize either of these methods existed until my playHand code was already done...
    // ...oopsies...
    public ArrayList<Integer> findRankingFrequency()
    {
        return new ArrayList<>(); 
    }

    public ArrayList<Integer> findSuitFrequency(){
        return new ArrayList<>(); 
    }
 
    @Override
    public String toString(){
        return hand.toString();
    }
}
