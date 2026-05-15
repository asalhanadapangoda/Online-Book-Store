<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Moderate Reviews - Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <jsp:include page="../navbar.jsp" />

    <div class="container">
        <h2 class="fw-bold mb-5">Review Moderation Queue</h2>

        <div class="glass-card overflow-hidden">
            <table class="table table-hover mb-0 align-middle">
                <thead class="bg-light">
                    <tr>
                        <th class="ps-4">User</th>
                        <th>Book ID</th>
                        <th>Rating</th>
                        <th>Comment</th>
                        <th class="text-end pe-4">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="review" items="${reviews}">
                        <tr>
                            <td class="ps-4 fw-bold">${review.username}</td>
                            <td>${review.bookId}</td>
                            <td><span class="text-warning">★ ${review.rating}</span></td>
                            <td><small>${review.comment}</small></td>
                            <td class="text-end pe-4">
                                <button class="btn btn-outline-danger btn-sm">Delete</button>
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
