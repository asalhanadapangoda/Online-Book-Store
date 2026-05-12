<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome to Online Book Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
    <style>
        .hero-section {
            padding: 100px 0;
            background: linear-gradient(rgba(255,255,255,0.8), rgba(255,255,255,0.8)), url('https://images.unsplash.com/photo-1507842217343-583bb7270b66?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80');
            background-size: cover;
            border-radius: 2rem;
            margin-bottom: 3rem;
        }
    </style>
</head>
<body>
    <jsp:include page="navbar.jsp" />

    <div class="container">
        <div class="hero-section text-center glass-card">
            <h1 class="display-3 fw-bold mb-4">Discover Your Next <span class="text-primary">Great Adventure</span></h1>
            <p class="lead mb-5">Explore our curated collection of books from all genres. Physical and E-books available.</p>
            <a href="/books" class="btn btn-primary btn-lg px-5">Shop Now</a>
        </div>

        <h2 class="text-center mb-5 fw-bold">Featured Collections</h2>
        <div class="row g-4">
            <c:forEach var="book" items="${featuredBooks}">
                <div class="col-md-4">
                    <div class="card glass-card h-100 border-0 overflow-hidden">
                        <div class="card-body text-center">
                            <h5 class="card-title fw-bold">${book.title}</h5>
                            <p class="text-muted">${book.author}</p>
                            <p class="text-primary fw-bold">$${book.price}</p>
                            <a href="/books/${book.id}" class="btn btn-outline-primary w-100">View Details</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
