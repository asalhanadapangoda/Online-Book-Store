<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Books - Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <jsp:include page="../navbar.jsp" />

    <div class="container">
        <div class="d-flex justify-content-between align-items-center mb-5">
            <h2 class="fw-bold mb-0">Manage Book Catalog</h2>
            <a href="/admin/add-book" class="btn btn-primary">Add New Book</a>
        </div>

        <div class="glass-card overflow-hidden">
            <table class="table table-hover mb-0 align-middle">
                <thead class="bg-light">
                    <tr>
                        <th class="ps-4">Title</th>
                        <th>Author</th>
                        <th>Type</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th class="text-end pe-4">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${books}">
                        <tr>
                            <td class="ps-4 fw-bold">${book.title}</td>
                            <td>${book.author}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${book.bookType == 'EBOOK'}">
                                        <span class="badge bg-info text-dark">E-BOOK</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-warning text-dark">PRINTED</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td><span class="badge bg-secondary">${book.category}</span></td>
                            <td class="text-primary fw-bold">$${book.price}</td>
                            <td class="text-end pe-4">
                                <a href="/admin/edit-book/${book.id}" class="btn btn-outline-primary btn-sm me-2">Edit</a>
                                <a href="/admin/delete-book/${book.id}" class="btn btn-outline-danger btn-sm" onclick="return confirm('Delete this book permanently?')">Delete</a>
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
