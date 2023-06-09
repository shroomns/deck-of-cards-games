// Joy Angel Schwarting
// CS145
// Deck of Cards
//
// Creates deck of cards to be played with in Blackjack. Definitely more convenient to make
// a map instead of a list because the names of cards also come with values.


import java.util.*; // imports all that we need

public class deckOfCards {

      // creates an array of card names
      static private String[] cards = {
            "ace", "king", "queen", "jack", "ten", "nine", "eight",
            "seven", "six", "five", "four", "three", "two", "one"
      };
      
      // creates a hashmap to store values of cards in the deck
      private HashMap<String, Integer> deck; 
      
      private int cardCount;
      
      // creates a deck of 52 cards
      public deckOfCards() {
            deck = new HashMap<String, Integer>();
            // using put method to insert mapping into map
            // and replace with new value if their is an existing value
            deck.put("ace", 4);
            deck.put("king", 4);
            deck.put("queen", 4);
            deck.put("jack", 4);
            deck.put("ten", 4);
            deck.put("nine", 4);
            deck.put("eight", 4);
            deck.put("seven", 4);
            deck.put("six", 4);
            deck.put("five", 4);
            deck.put("four", 4);
            deck.put("three", 4);
            deck.put("two", 4);
            deck.put("one", 4);
            // 4 of each card
            cardCount = 52; 
      } // end of deckOfCards
      
      public void shuffle() {
            deck.replaceAll((card, count) -> 4); // linking parameters to body (4 of each card)
            cardCount = 52;
      }
      
      public int getCardCount() {
            return cardCount;
      }
      
      // method to draw cards
      public String draw() {
            ArrayList<String> filteredDeck = getFilteredDeck();
            
            Random random = new Random();
            int randomInt = random.nextInt(filteredDeck.size());
            
            String selectedCard = filteredDeck.get(randomInt);
            deck.put(selectedCard, deck.get(selectedCard) - 1);
            
            cardCount--;
            
            return selectedCard;
      } // end of draw method
      
      private ArrayList<String> getFilteredDeck() { // this
            ArrayList<String> filteredDeck = new ArrayList<String>();
            
            for (String card : cards) { // for each card in cards
                  if (deck.get(card) > 0) {
                        filteredDeck.add(card);
                  }
            }
            return filteredDeck;
      }
} // end of deckOfCards class