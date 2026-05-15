<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${book.title} - Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <jsp:include page="navbar.jsp" />

    <div class="container">
        <div class="row mb-5 justify-content-center">
            <div class="col-md-10">
                <div class="glass-card p-5">
                    <h1 class="fw-bold mb-2">${book.title}</h1>
                    <p class="text-muted mb-4">by <span class="text-primary fw-bold">${book.author}</span> | Category: ${book.category}</p>
                    <h3 class="text-primary fw-bold mb-4">$${book.price}</h3>
                    <p class="lead mb-5 text-secondary">${book.description}</p>
                    
                    <c:if test="${empty sessionScope.user || sessionScope.user.role != 'ADMIN'}">
                        <form action="/cart/add" method="post" class="row g-3 align-items-center mb-0">
                            <input type="hidden" name="bookId" value="${book.id}">
                            <div class="col-auto">
                                <input type="number" name="quantity" value="1" min="1" class="form-control" style="width: 80px;">
                            </div>
                            <div class="col-auto">
                                <button type="submit" class="btn btn-primary px-5">Add to Cart</button>
                            </div>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="glass-card p-4">
            <h4 class="fw-bold mb-4">Customer Reviews</h4>
            
            <c:if test="${empty reviews}">
                <p class="text-muted">No reviews yet. Be the first to share your thoughts!</p>
            </c:if>
            
            <c:forEach var="review" items="${reviews}">
                <div class="border-bottom mb-3 pb-3">
                    <div class="d-flex justify-content-between mb-2">
                        <span class="fw-bold">${review.username}</span>
                        <span class="text-warning">
                            <c:forEach begin="1" end="${review.rating}">★</c:forEach>
                            <c:forEach begin="${review.rating + 1}" end="5">☆</c:forEach>
                        </span>
                    </div>
                    <p class="mb-1">${review.comment}</p>
                    <small class="text-muted">${review.date}</small>
                </div>
            </c:forEach>

            <c:if test="${not empty sessionScope.user && sessionScope.user.role != 'ADMIN'}">
                <div class="mt-5 p-4 bg-light rounded-4">
                    <h5 class="fw-bold mb-3">Write a Review</h5>
                    <form action="/reviews/add" method="post">
                        <input type="hidden" name="bookId" value="${book.id}">
                        <div class="mb-3">
                            <label class="form-label">Rating</label>
                            <select name="rating" class="form-select w-auto">
                                <option value="5">5 Stars</option>
                                <option value="4">4 Stars</option>
                                <option value="3">3 Stars</option>
                                <option value="2">2 Stars</option>
                                <option value="1">1 Star</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Your Thoughts</label>
                            <textarea name="comment" class="form-control" rows="3" required placeholder="What did you like or dislike?"></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit Review</button>
                    </form>
                </div>
            </c:if>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
