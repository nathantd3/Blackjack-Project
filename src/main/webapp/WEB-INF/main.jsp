<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/main.css"/>
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Blackjack</title>
</head>
<body class="bg-success">
	<h1 class="text-center mt-3 text-warning">Blackjack</h1>
	<main class="container d-flex justify-content-around">
		<div class="mt-5">
			<div class="d-flex flex-column align-items-center">
				<h3 class="text-center text-warning">Dealer</h3>
					<div>
						<c:if test="${result == null}">
							<c:set var="first" value="true"/>
							<c:forEach var="card" items="${handDealer.cards}">
								<c:choose>
									<c:when test="${first == true}">
										<c:set var="first" value="false"/>
										<img alt="back" src="/cards/back.png" width=125px>
									</c:when>
									<c:otherwise>
										<img alt="${card.rank}${card.suit}" src="/cards/${card.rank}${card.suit}.png" width=125px>	
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<p class="text-warning text-center">Total: ?</p>
						</c:if>
						<c:if test="${result != null}">
							<c:forEach var="card" items="${handDealer.cards}">
								<img alt="${card.rank}${card.suit}" src="/cards/${card.rank}${card.suit}.png" width=125px>
							</c:forEach>
							<p class="text-warning text-center">Total: <c:out value="${handDealer.total}"></c:out></p>
						</c:if>
					</div>
			</div>
			<div class="d-flex flex-column align-items-center">
				<h3 class="text-center text-warning">Player</h3>
					<div>
						<c:forEach var="card" items="${hand.cards}">
							<img alt="${card.rank}${card.suit}" src="/cards/${card.rank}${card.suit}.png" width=125px>
						</c:forEach>
					</div>
				<p class="text-warning">Total: <c:out value="${hand.total}"></c:out></p>
			</div>
		</div>
		<div class="d-flex flex-column align-items-center mt-5 justify-content-center">
			<c:if test="${result == null}">
				<a class="btn btn-primary w-100 mt-4" href="/hit">Hit</a>
				<a class="btn btn-danger w-100 mt-4" href="/stay">Stay</a>
			</c:if>
			<c:if test="${result != null}">
				<a class="btn btn-warning w-100 mt-4" href="/newgame">New Game</a>
				<h3 class="text-warning mt-4"><c:out value="${result}"></c:out></h3>
			</c:if>
		</div>
		<div class="d-flex flex-column align-items-center mt-5 justify-content-center">
			<img alt="probability" src="/cards/probability.png" width=300px>
		</div>
	</main>
</body>
</html>