<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Profile - Online Book Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <jsp:include page="navbar.jsp" />

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="glass-card p-4">
                    <h2 class="fw-bold mb-4 text-center">My Profile</h2>
                    
                    <c:if test="${param.success != null}">
                        <div class="alert alert-success">Profile updated successfully!</div>
                    </c:if>

                    <form action="/profile/update" method="post">
                        <div class="mb-3">
                            <label class="form-label">Full Name</label>
                            <input type="text" name="fullName" value="${user.fullName}" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email Address</label>
                            <input type="email" name="email" value="${user.email}" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Phone Number</label>
                            <input type="text" name="phoneNumber" value="${user.phoneNumber}" class="form-control">
                        </div>
                        <div class="mb-4">
                            <label class="form-label">Shipping Address</label>
                            <textarea name="address" class="form-control" rows="3">${user.address}</textarea>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Save Changes</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>
