<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Orders - Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
    <style>
        .select-pending { background-color: #ffc107 !important; color: #000 !important; font-weight: bold; }
        .select-completed { background-color: #198754 !important; color: #fff !important; font-weight: bold; }
        .select-cancelled { background-color: #dc3545 !important; color: #fff !important; font-weight: bold; }
        .form-select-sm { padding-top: 0.25rem; padding-bottom: 0.25rem; }
    </style>
</head>
<body>
    <jsp:include page="../navbar.jsp" />

    <div class="container">
        <h2 class="fw-bold mb-5">Track Sales & Orders</h2>

        <div class="glass-card overflow-hidden">
            <table class="table table-hover mb-0 align-middle">
                <thead class="table-primary text-white">
                    <tr>
                        <th class="ps-4">Order ID</th>
                        <th>User ID</th>
                        <th>Details</th>
                        <th>Total Amount</th>
                        <th class="text-center">Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td class="ps-4 fw-bold">#${order.id}</td>
                            <td>${order.userId}</td>
                            <td><small>${order.bookDetails}</small></td>
                            <td class="text-primary fw-bold">$${order.totalPrice}</td>
                             <td class="text-center">
                                 <form action="/admin/update-order-status" method="post" class="d-inline-block">
                                     <input type="hidden" name="orderId" value="${order.id}">
                                     <select name="status" class="form-select form-select-sm fw-bold ${order.status == 'COMPLETED' ? 'select-completed' : (order.status == 'PENDING' ? 'select-pending' : 'select-cancelled')}" style="width: auto; border: none; border-radius: 20px;" onchange="this.form.submit()">
                                         <option value="PENDING" ${order.status == 'PENDING' ? 'selected' : ''}>PENDING</option>
                                         <option value="COMPLETED" ${order.status == 'COMPLETED' ? 'selected' : ''}>COMPLETED</option>
                                         <option value="CANCELLED" ${order.status == 'CANCELLED' ? 'selected' : ''}>CANCELLED</option>
                                     </select>
                                 </form>
                             </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <jsp:include page="../footer.jsp" />
</body>
</html>
