<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Book - Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <jsp:include page="../navbar.jsp" />

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="glass-card p-4">
                    <h2 class="fw-bold mb-4">Edit Book Details</h2>
                    <form action="/admin/edit-book" method="post">
                        <!-- Hidden field for ID so the backend knows which book to update -->
                        <input type="hidden" name="id" value="${book.id}">
                        
                        <div class="mb-3">
                            <label class="form-label">Book Title</label>
                            <input type="text" name="title" value="${book.title}" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Author</label>
                            <input type="text" name="author" value="${book.author}" class="form-control" required>
                        </div>
                        <div class="row mb-3">
                            <div class="col">
                                <label class="form-label">Price ($)</label>
                                <input type="number" step="0.01" name="price" value="${book.price}" class="form-control" required>
                            </div>
                            <div class="col">
                                <label class="form-label">Category</label>
                                <select name="category" class="form-select">
                                    <option value="Classic" ${book.category == 'Classic' ? 'selected' : ''}>Classic</option>
                                    <option value="Fiction" ${book.category == 'Fiction' ? 'selected' : ''}>Fiction</option>
                                    <option value="Non-Fiction" ${book.category == 'Non-Fiction' ? 'selected' : ''}>Non-Fiction</option>
                                    <option value="Fantasy" ${book.category == 'Fantasy' ? 'selected' : ''}>Fantasy</option>
                                    <option value="Mystery" ${book.category == 'Mystery' ? 'selected' : ''}>Mystery</option>
                                </select>
                            </div>
                        </div>
                        <div class="mb-4">
                            <label class="form-label">Description</label>
                            <textarea name="description" class="form-control" rows="4" required>${book.description}</textarea>
                        </div>
                        <div class="d-flex gap-2">
                            <button type="submit" class="btn btn-primary flex-grow-1">Update Book Information</button>
                            <a href="/admin/manage-books" class="btn btn-outline-secondary">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="../footer.jsp" />
</body>
</html>
