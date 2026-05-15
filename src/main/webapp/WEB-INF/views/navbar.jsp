<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<link rel="stylesheet" href="/css/navbar.css">
<nav class="navbar navbar-expand-lg sticky-top mb-4">
    <div class="container">
        <a class="navbar-brand" href="/">📚 BOOKSTORE</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <c:choose>
                    <c:when test="${sessionScope.user.role == 'ADMIN'}">
                        <!-- Admin Specific Navigation Order -->
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/'/>">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/admin/dashboard'/>">Dashboard</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/books'/>">Browse Books</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/admin/manage-books'/>">Manage Books</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/admin/manage-users'/>">Manage Users</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/admin/manage-orders'/>">Manage Orders</a></li>
                        <li class="nav-item"><a class="btn btn-danger text-white ms-lg-3" href="<c:url value='/logout'/>">Logout</a></li>
                    </c:when>
                    <c:when test="${sessionScope.user.role == 'CUSTOMER'}">
                        <!-- Customer Navigation -->
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/'/>">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/books'/>">Browse Books</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/cart'/>">My Cart</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/my-orders'/>">My Orders</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/profile'/>">Profile</a></li>
                        <li class="nav-item"><a class="nav-link text-danger" href="<c:url value='/logout'/>">Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <!-- Guest Navigation -->
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/'/>">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/books'/>">Browse Books</a></li>
                        <li class="nav-item"><a class="nav-link" href="<c:url value='/login'/>">Login</a></li>
                        <li class="nav-item"><a class="btn btn-primary ms-lg-2" href="<c:url value='/register'/>">Register</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
