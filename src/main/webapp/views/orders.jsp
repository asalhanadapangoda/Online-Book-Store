<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Orders - Online Book Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
    <style>
        .badge-pending { background-color: #ffc107 !important; color: #000 !important; }
        .badge-completed { background-color: #198754 !important; color: #fff !important; }
        .badge-cancelled { background-color: #dc3545 !important; color: #fff !important; }
    </style>
</head>
<body>
    <jsp:include page="navbar.jsp" />

    <div class="container">
        <h2 class="mb-5 fw-bold">Order History</h2>

        <c:choose>
            <c:when test="${empty orders}">
                <div class="glass-card p-5 text-center">
                    <p class="text-muted mb-0">You haven't placed any orders yet.</p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="glass-card overflow-hidden">
                    <table class="table table-hover mb-0 align-middle">
                        <thead class="bg-light">
                            <tr>
                                <th class="ps-4">Order ID</th>
                                <th>Date</th>
                                <th>Details</th>
                                <th>Total</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="order" items="${orders}">
                                <tr>
                                    <td class="ps-4 fw-bold">#${order.id}</td>
                                    <td>${order.orderDate}</td>
                                    <td><small>${order.bookDetails}</small></td>
                                    <td class="text-primary fw-bold">$${order.totalPrice}</td>
                                    <td>
                                        <span class="badge ${order.status == 'COMPLETED' ? 'badge-completed' : (order.status == 'PENDING' ? 'badge-pending' : 'badge-cancelled')}">
                                            ${order.status}
                                        </span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
