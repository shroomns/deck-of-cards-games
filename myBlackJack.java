// Joy Angel Schwarting
// CS145
// Deck of Cards
//
// The purpose of this program plays the game Blackjack (utilizing the player and deckOfCards
// class) between the user and the computer. It also introduces the game and asks if player 
// wants to play again.

import java.util.*; // import from library

public class myBlackJack {

      static deckOfCards deckOfCards; //deckofcards class
      static Player dealer; //player class method
      static Player player; //player class method 
      static Scanner scanner;
      
      
      public static void main(String[] args) { // main method
            theWholeGame();
      } 
      
      
      // combines all the other methods into one method for main method
      public static void theWholeGame() {
            deckOfCards = new deckOfCards(); // gets new "deck" ready for game
            dealer = new Player(); // new player hand for 'player'
            player = new Player(); // new dealer hand for 'dealer'
            
            scanner = new Scanner(System.in); // create new scanner for future user input
            
            gameIntro(); // game intro method
            
            System.out.println("Are you ready to play? Enter Yes[1] or No[2].");
            int reply = scanner.nextInt(); // user input asking if ready to play
            if (reply == 2){
                  System.out.println("Bye.");
                  return;
            } else {
                  while (true) {
                        System.out.println("Let's play!");
                        setUpGame();
                        
                        if (player.getTotal() == 21) {
                              scoreBlackjack();
                        } else {
                              hitOrStay();
                              finalHandsScore();
                        } if (!playAgain()) {
                              break;
                        }
                  }
                  scanner.close();
           }
      }// end of for main
      
      
      // welcomes user and gives link for game rules
      public static void gameIntro() {
            System.out.println("                       BLACKJACK");
            System.out.println("      Hi! Let's play Blackjack, also known as 21.");
            System.out.println("   If you want to read the rules of Blackjack, please");
            System.out.println("     copy & paste the link below into your preferred");
            System.out.println("                      web browser:");
            System.out.println();
            System.out.println("https://www.blackjackapprenticeship.com/how-to-play-blackjack/");
            System.out.println();
      }
      
      
      public static void setUpGame() {
            dealer.reset(); // clears values (resets player and dealer hand)
            player.reset();
            
            shuffleDeck();
            
            dealer.addToHand(deckOfCards.draw());
            dealer.addToHand(deckOfCards.draw());
            
            player.addToHand(deckOfCards.draw());
            player.addToHand(deckOfCards.draw());
            
            System.out.println();
      } 
       
       
      public static void shuffleDeck() {
            if (deckOfCards.getCardCount() < 26) {
                 deckOfCards.shuffle(); // shuffle method reorders specified list
                 // in this case that is the deck created in deckOfCards class
            }
      } 
 
      
      public static void scoreBlackjack() {
            printGameState(false);
            if (dealer.getTotal() == 21) {
                  displayMessage("It's a draw! You and the Dealer both got Blackjack.");
            } else {
                  displayMessage("You got blackjack! You win! Congrats!");
            }
      } // end of scoreBlackJack
      
      
      public static void hitOrStay() {
            printGameState(true);
            while (player.getTotal() < 22) {
                  String question = "Would you like to [h]it or [s]tay?";
                  List<String> validEntries = Arrays.asList("hit","h","stay","s");
                  String move = askUntilValidInput(question, validEntries);
                  if (move.equals("h") || move.equals("hit")) {
                        player.addToHand(deckOfCards.draw());
                        System.out.println("\nI'll hit!");
                  } else if (move.equals("s") || move.equals("stay")) {
                        System.out.println("\nI'll stay!");
                        break;
                  }
                  printGameState(true);
            }
      } // end of hitorstay
      
      
      // game results to print at end of game
      public static void finalHandsScore() {
            if (player.getTotal() > 21) {
                  printGameState(false);
                  displayMessage("You bust!\nYou lose.");
            } else {
                  dealerHits();
                  System.out.println("\nRevealing final hands...");
                  printGameState(false);
                  
                  if (dealer.getTotal() > 21) {
                        displayMessage("Dealer bust!\nYou win!");
                  } else if (dealer.getTotal() > player.getTotal()) {  
                        displayMessage("Dealer's hand is higher...\nYou lose!");
                  } else if (dealer.getTotal() < player.getTotal()) {
                        displayMessage("Your hand is higher!\nYou win!");
                  } else {
                        displayMessage("It's a tie!");
                  }
            }
     } // end of finalhandscore
  
     
     public static void dealerHits() {
         while (dealer.getTotal() <= 16) {
               dealer.addToHand(deckOfCards.draw());
         }
     }
     
     
     public static boolean playAgain() {
         String playQuestion = "Would you like to play again? Please enter (y)es or (n)o.";
         List<String> validEntries = Arrays.asList("y","yes","n","no");
         String playDecision = askUntilValidInput(playQuestion, validEntries);
         
         if (playDecision.equals("n") || playDecision.equals("no")) {
               displayMessage("Thanks for playing! See you next time.");
               return false;
         } else {
               return true;
         }
     } // end of playAgain method
     
     
     public static String askUntilValidInput(String playQuestion, List<String>validEntries) {
         boolean validInput = false;
         String playDecision = "";
         while (!validInput) {
               System.out.println(playQuestion);
               playDecision = scanner.nextLine().toLowerCase();
               if (validEntries.contains(playDecision)) {
                     validInput = true;
               } // end while loop to determine play again or not
         }
         return playDecision;
     } // end of askuntilvalidinput
     
     
     // boolean hideDealerHand true or false depending on point in game
     public static void printGameState(boolean hideDealerHand) {
         System.out.println("\n----------------------------------\n");
         System.out.println("Dealer's Hand:");
         if (hideDealerHand) {
               System.out.printf("['%s','----']\n\n",dealer.getHand().get(0));
         } else {
               System.out.println(dealer.getHand() + "\n");
         }
         System.out.println("Dealer's Total:");
         if (hideDealerHand) {
               System.out.println("?-?-?\n"); // hide dealer hand
         } else {
               System.out.println(dealer.getTotal() + "\n"); // display dealer's total
         }
         System.out.println("Your Hand:");
         System.out.println(player.getHand() + "\n");
         
         System.out.println("Your Total:");
         System.out.println(player.getTotal());
         System.out.println("\n----------------------------------\n");
     } // end of printGameState
     
     
     // use displayMessage method to display different strings, is game results in finalHandScore
     public static void displayMessage(String body) {
         System.out.println(body);
         System.out.println("------------------------------------\n");
     } //end of displayMessage
} // end of myBlackJack class