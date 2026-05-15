<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart - Online Book Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <jsp:include page="navbar.jsp" />

    <div class="container">
        <h2 class="mb-5 fw-bold">Your Shopping Cart</h2>

        <c:choose>
            <c:when test="${empty cartItems}">
                <div class="glass-card p-5 text-center">
                    <h4 class="text-muted">Your cart is empty</h4>
                    <a href="/books" class="btn btn-primary mt-3">Browse Books</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <div class="col-md-8">
                        <div class="glass-card overflow-hidden">
                            <table class="table mb-0 align-middle">
                                <thead class="bg-light">
                                    <tr>
                                        <th class="ps-4">Book</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Subtotal</th>
                                        <th class="text-end pe-4">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${cartItems}">
                                        <tr>
                                            <td class="ps-4">
                                                <div class="d-flex align-items-center py-2">
                                                    <div>
                                                        <div class="fw-bold">${item.book.title}</div>
                                                        <small class="text-muted">${item.book.author}</small>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>$${item.book.price}</td>
                                            <td>${item.quantity}</td>
                                            <td class="fw-bold">$${item.subtotal}</td>
                                            <td class="text-end pe-4">
                                                <a href="/cart/remove?bookId=${item.book.id}" class="btn btn-outline-danger btn-sm">Remove</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="glass-card p-4">
                            <h4 class="fw-bold mb-4">Order Summary</h4>
                            <div class="d-flex justify-content-between mb-3">
                                <span>Subtotal</span>
                                <span>$${total}</span>
                            </div>
                            <div class="d-flex justify-content-between mb-3">
                                <span>Shipping</span>
                                <span class="text-success">Free</span>
                            </div>
                            <hr>
                            <div class="d-flex justify-content-between mb-4">
                                <span class="fw-bold">Total</span>
                                <span class="fw-bold text-primary h4">$${total}</span>
                            </div>
                            <a href="/checkout" class="btn btn-primary w-100 py-3 fw-bold">Proceed to Checkout</a>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
