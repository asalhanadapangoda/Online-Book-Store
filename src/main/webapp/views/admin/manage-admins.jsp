<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Admins - Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <jsp:include page="../navbar.jsp" />

    <div class="container">
        <h2 class="fw-bold mb-5">Manage Administrator Accounts</h2>

        <!-- Add Admin Form -->
        <div class="glass-card p-4 mb-5">
            <h4 class="fw-bold mb-4">Add New Admin</h4>
            <form action="/admin/add-admin" method="post" class="row g-3">
                <div class="col-md-3">
                    <input type="text" name="username" class="form-control" placeholder="Username" required>
                </div>
                <div class="col-md-3">
                    <input type="email" name="email" class="form-control" placeholder="Email" required>
                </div>
                <div class="col-md-3">
                    <input type="password" name="password" class="form-control" placeholder="Password" required>
                </div>
                <div class="col-md-3">
                    <button type="submit" class="btn btn-primary w-100">Create Admin</button>
                </div>
            </form>
        </div>

        <!-- Admin List -->
        <div class="glass-card overflow-hidden">
            <table class="table table-hover mb-0 align-middle">
                <thead class="bg-light">
                    <tr>
                        <th class="ps-4">Username</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th class="text-end pe-4">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="admin" items="${admins}">
                        <tr>
                            <td class="ps-4 fw-bold">${admin.username}</td>
                            <td>${admin.email}</td>
                            <td><span class="badge bg-danger">${admin.role}</span></td>
                            <td class="text-end pe-4">
                                <a href="/admin/delete-admin/${admin.id}" class="btn btn-outline-danger btn-sm" onclick="return confirm('Are you sure?')">Delete</a>
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
