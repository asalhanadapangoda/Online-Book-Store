<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard - Online Book Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <jsp:include page="../navbar.jsp" />

    <div class="container">
        <h2 class="mb-5 fw-bold">Admin Control Panel</h2>

        <div class="row g-4 mb-5">
            <div class="col-md-4">
                <div class="glass-card p-4 text-center">
                    <h1 class="display-4 fw-bold text-primary">${totalBooks}</h1>
                    <p class="text-muted mb-0">Total Books</p>
                    <a href="/admin/manage-books" class="btn btn-sm btn-outline-primary mt-3">Manage</a>
                </div>
            </div>
            <div class="col-md-4">
                <div class="glass-card p-4 text-center">
                    <h1 class="display-4 fw-bold text-success">${totalOrders}</h1>
                    <p class="text-muted mb-0">Total Orders</p>
                    <a href="/admin/manage-orders" class="btn btn-sm btn-outline-success mt-3">View Orders</a>
                </div>
            </div>
            <div class="col-md-4">
                <div class="glass-card p-4 text-center">
                    <h1 class="display-4 fw-bold text-info">${totalUsers}</h1>
                    <p class="text-muted mb-0">Registered Users</p>
                    <a href="/admin/manage-users" class="btn btn-sm btn-outline-info mt-3">Manage Users</a>
                </div>
            </div>
            <div class="col-md-4">
                <div class="glass-card p-4 text-center">
                    <h1 class="display-4 fw-bold text-warning">${totalReviews}</h1>
                    <p class="text-muted mb-0">Total Reviews</p>
                    <a href="/admin/manage-reviews" class="btn btn-sm btn-outline-warning mt-3">Manage Reviews</a>
                </div>
            </div>
            <div class="col-md-4">
                <div class="glass-card p-4 text-center">
                    <h1 class="display-4 fw-bold text-danger">${totalAdmins}</h1>
                    <p class="text-muted mb-0">Total Admins</p>
                    <a href="/admin/manage-admins" class="btn btn-sm btn-outline-danger mt-3">Manage Admins</a>
                </div>
            </div>
        </div>

    </div>

    <jsp:include page="../footer.jsp" />
</body>
</html>
