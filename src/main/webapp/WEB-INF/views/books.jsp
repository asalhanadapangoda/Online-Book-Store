<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Browse Books - Online Book Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <jsp:include page="navbar.jsp" />

    <div class="container">
        <h2 class="mb-5 fw-bold text-center">Digital Bookshelf</h2>
        
        <div class="row g-4">
            <c:forEach var="book" items="${books}">
                <div class="col-md-3">
                    <div class="card glass-card h-100 border-0 overflow-hidden text-center">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title fw-bold">${book.title}</h5>
                            <p class="text-muted small mb-1">${book.author}</p>
                            <div class="mb-3">
                                <span class="badge ${book.bookType == 'EBOOK' ? 'bg-info' : 'bg-secondary'}">
                                    ${book.bookType == 'EBOOK' ? 'E-Book' : 'Printed Book'}
                                </span>
                            </div>

                            <div class="mt-auto">
                                <p class="text-primary fw-bold mb-3">$${book.price}</p>
                                <div class="d-grid gap-2">
                                    <a href="/books/${book.id}" class="btn btn-outline-primary btn-sm">View Details</a>
                                    <c:if test="${empty sessionScope.user || sessionScope.user.role != 'ADMIN'}">
                                        <form action="/cart/add" method="post">
                                            <input type="hidden" name="bookId" value="${book.id}">
                                            <input type="hidden" name="quantity" value="1">
                                            <button type="submit" class="btn btn-primary btn-sm w-100">Add to Cart</button>
                                        </form>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
