// Blackjack web service that deals cards and evaluates hands
package com.blackjack;

import com.sun.xml.ws.developer.servlet.HttpSessionScope;
import java.util.ArrayList;
import java.util.Random;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@HttpSessionScope // enable web service to maintain session state
@WebService( name = "Blackjack", serviceName = "BlackjackService" )
public class Blackjack
{
   private ArrayList<String> deck; // deck of cards for one user session
   private static final Random randomObject = new Random();
   
   // deal one card
   @WebMethod( operationName = "dealCard" )
   public String dealCard()
   {
      String card = "";
      card = deck.get( 0 ); // get top card of deck
      deck.remove( 0 ); // remove top card of deck
      return card;
   }

   // shuffle the deck
   @WebMethod( operationName = "shuffle" )
   public void shuffle()
   {
      // populate deck of cards
      deck = new ArrayList<>();

      for ( int face = 1; face <= 13; face++ ) // loop through faces
         for ( int suit = 0; suit <= 3; suit++ ) // loop through suits
            deck.add( face + " " + suit ); // add each card to deck

      String tempCard; // holds card temporarily durring swapping
      int index; // index of randomly selected card

      for ( int i = 0; i < deck.size() ; i++ ) // shuffle
      {
         index = randomObject.nextInt( deck.size() - 1 );

         // swap card at position i with randomly selected card
         tempCard = deck.get( i );
         deck.set( i, deck.get( index ) );
         deck.set( index, tempCard );
      }
   }

   // determine a hand's value
   @WebMethod( operationName = "getHandValue" )
   public int getHandValue( @WebParam( name = "hand" ) String hand )
   {
      // split hand into cards
      String[] cards = hand.split( "\t" );
      int total = 0; // total value of cards in hand
      int face; // face of current card
      int aceCount = 0; // number of aces in hand

      for ( int i = 0; i < cards.length; i++ )
      {
         // parse string and get first int in String
         face = Integer.parseInt(
            cards[ i ].substring( 0, cards[ i ].indexOf( " " ) ) );

         switch ( face )
         {
            case 1: // in ace, increment aceCount
               ++aceCount;
               break;
            case 11: // jack
            case 12: // queen
            case 13: // king
               total += 10;
               break;
            default: // otherwise, add face
               total += face;
               break;
         }
      }

      // calculate optimal use of aces
      if ( aceCount > 0 )
      {
         // if possible, count one ace as 11
         if ( total + 11 + aceCount - 1 <= 21 )
            total += 11 + aceCount - 1;
         else // otherwise, count all aces as 1
            total += aceCount;
       }

      return total;
   }
}
