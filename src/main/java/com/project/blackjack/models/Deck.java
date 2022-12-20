package com.project.blackjack.models;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	private ArrayList<Card> cards;
	private int currentCards = 52;
	
    public Deck() {
        this.cards = new ArrayList<Card>();

        // Populate the cards list -- loop to 52
        for (String name : new String[] {"Hearts", "Clubs", "Diamonds", "Spades"}) {
            for (Integer rank = 1; rank <= 13; rank++) {
                this.cards.add(new Card(name, rank));
            }
        }
    }
    
    public Card getRandomCard() {
    	Random rand = new Random();
		int index = rand.nextInt(this.getCurrentCards());
		Card card = this.cards.get(index);
		this.cards.remove(index);
		this.setCurrentCards(this.getCurrentCards() - 1);
		return card;
    }

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public int getCurrentCards() {
		return currentCards;
	}

	public void setCurrentCards(int currentCards) {
		this.currentCards = currentCards;
	}   
}