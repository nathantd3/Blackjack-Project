package com.project.blackjack.models;

import java.util.ArrayList;

public class Hand {
	private int total;
	private ArrayList<Card> cards;
	private int aceVal = 0;
	
	
	public Hand() {
		this.cards = new ArrayList<Card>();
	}
	
	public void dealHand(Deck myDeck) {
		int value = 0;
		for(int i = 0; i < 2; i++) {
			Card card = myDeck.getRandomCard();
			this.cards.add(card);
			if(card.getRank() >= 10) {
				value = 10;
			}
			else if(card.getRank() == 1) {
				value = 11;
				this.setAceVal(this.getAceVal() + 1);
				
			}
			else {
				value = card.getRank();
			}
			this.setTotal(this.getTotal() + value);
		}
	}
	
	public boolean isBust() {
		if(this.total > 21) {
			if(this.getAceVal() > 0) {
				this.setTotal(this.getTotal() - 10);
				this.setAceVal(this.getAceVal() - 1);
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	public void hit(Deck myDeck) {
		Card card = myDeck.getRandomCard();
		int value = 0;
		this.cards.add(card);
		if(card.getRank() >= 10) {
			value = 10;
		}
		else if(card.getRank() == 1) {
			value = 11;
			this.setAceVal(this.getAceVal() + 1);
			
		}
		else {
			value = card.getRank();
		}
		
		this.setTotal(this.getTotal() + value);
		this.isBust();
		this.showHand();
	}
	
	public void showHand() {
		for(Card card : this.getCards() ) {
			System.out.println(card.getName() + " " + card.getSuit());
		}
		System.out.println("Total: " + this.getTotal());
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public int getAceVal() {
		return aceVal;
	}

	public void setAceVal(int aceVal) {
		this.aceVal = aceVal;
	}
}
