<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Users - Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <jsp:include page="../navbar.jsp" />

    <div class="container">
        <h2 class="fw-bold mb-5">Manage User Accounts</h2>

        <div class="glass-card overflow-hidden">
            <table class="table table-hover mb-0 align-middle">
                <thead class="bg-light">
                    <tr>
                        <th class="ps-4">Username</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Full Name</th>
                        <th class="text-end pe-4">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td class="ps-4 fw-bold">${user.username}</td>
                            <td>${user.email}</td>
                            <td><span class="badge ${user.role == 'ADMIN' ? 'bg-danger' : 'bg-primary'}">${user.role}</span></td>
                            <td>${user.fullName}</td>
                            <td class="text-end pe-4">
                                <c:if test="${user.role != 'ADMIN'}">
                                    <a href="/admin/delete-user/${user.id}" class="btn btn-outline-danger btn-sm" onclick="return confirm('Are you sure?')">Delete</a>
                                </c:if>
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
