<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Checkout - Online Book Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <jsp:include page="navbar.jsp" />

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-7">
                <div class="glass-card p-4">
                    <h2 class="fw-bold mb-4">Complete Your Purchase</h2>
                    <form action="/checkout" method="post">
                        <h5 class="fw-bold mb-3">1. Shipping Address</h5>
                        <div class="mb-4">
                            <textarea name="shippingAddress" class="form-control" rows="3" required>${user.address}</textarea>
                        </div>

                        <h5 class="fw-bold mb-3">2. Payment Method</h5>
                        <div class="mb-4">
                            <div class="form-check mb-2">
                                <input class="form-check-input" type="radio" name="paymentMethod" value="CREDIT_CARD" id="payNow" checked>
                                <label class="form-check-label" for="payNow">Credit Card / Pay Now</label>
                            </div>
                            <c:choose>
                                <c:when test="${hasEBook}">
                                    <div class="alert alert-info py-2 small">
                                        <i class="bi bi-info-circle me-2"></i>
                                        E-Books require online payment. Cash on Delivery is disabled.
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="paymentMethod" value="CASH_ON_DELIVERY" id="cod">
                                        <label class="form-check-label" for="cod">Cash on Delivery</label>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>


                        <button type="submit" class="btn btn-primary btn-lg w-100 py-3 mt-3">Place Order</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
