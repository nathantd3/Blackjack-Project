package com.project.blackjack.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.blackjack.models.Deck;
import com.project.blackjack.models.Hand;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(HttpSession session) {
		if(session.getAttribute("hand") == null) {
			Hand hand = new Hand();
			Hand handDealer = new Hand();
			Deck deck = new Deck();
			
			boolean playerActive = true;
			boolean dealerActive = false;
			boolean gameOver = false;
			
			hand.dealHand(deck);
			handDealer.dealHand(deck);
			session.setAttribute("hand", hand);
			session.setAttribute("handDealer", handDealer);
			session.setAttribute("deck", deck);
			session.setAttribute("playerActive", playerActive);
			session.setAttribute("dealerActive", dealerActive);
			session.setAttribute("gameOver", gameOver);
		
		}
		
		Hand hand = (Hand) session.getAttribute("hand");
		Hand handDealer = (Hand) session.getAttribute("handDealer");
		if(hand.getTotal() == 21 || handDealer.getTotal() == 21) {
			return "redirect:/gameover";
		}
		return "main.jsp";
	}
	
	@GetMapping("/hit")
	public String hit(HttpSession session) {
		Hand hand = (Hand) session.getAttribute("hand");
		Deck deck = (Deck) session.getAttribute("deck");
		hand.hit(deck);
		if(hand.isBust()) {
			return "redirect:/gameover";
		}
		if(hand.getTotal() == 21) {
			return "redirect:/dealer";
		}
		return "redirect:/";
	}
	
	@GetMapping("/stay")
	public String stay(HttpSession session) {
		session.setAttribute("playerActive", false);
		session.setAttribute("dealerActive", true);
		return "redirect:/dealer";
	}
	
	@GetMapping("/dealer")
	public String dealer(HttpSession session) {
		if((boolean) session.getAttribute("dealerActive")) {
			Hand dealer = (Hand) session.getAttribute("handDealer");
			Deck deck = (Deck) session.getAttribute("deck");
			// Hard 17
			if(dealer.getTotal() == 17 && dealer.getAceVal() == 0) {
				session.setAttribute("gameOver", true);
				System.out.println("Test2");
				return "redirect:/gameover";
			}
			// Soft 17 and lower
			while(dealer.getTotal() < 18) {
				dealer.hit(deck);
			}
			session.setAttribute("gameOver", true);
		}
		return "redirect:/gameover";
	}
	
	@GetMapping("/gameover")
	public String gameOver(HttpSession session, Model model) {
		Hand hand = (Hand) session.getAttribute("hand");
		Hand dealer = (Hand) session.getAttribute("handDealer");
		if((hand.getTotal() > dealer.getTotal()) && !hand.isBust() || dealer.isBust()) {
			model.addAttribute("result", "You Win!");
		}
		else if((hand.getTotal() == dealer.getTotal())) {
			model.addAttribute("result", "You Tie!");
		}
		else if((hand.getTotal() < dealer.getTotal()) && !dealer.isBust() || hand.isBust()) {
			model.addAttribute("result", "You Lose!");
		}
		return "main.jsp";
	}
	
	@GetMapping("/newgame")
	public String newGame(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
