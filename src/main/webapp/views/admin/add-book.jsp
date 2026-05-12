<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Book - Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <jsp:include page="../navbar.jsp" />

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="glass-card p-4">
                    <h2 class="fw-bold mb-4">Add New Book</h2>
                    <form action="/admin/add-book" method="post">
                        <div class="mb-3">
                            <label class="form-label">Book Title</label>
                            <input type="text" name="title" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Author</label>
                            <input type="text" name="author" class="form-control" required>
                        </div>
                        <div class="row mb-3">
                            <div class="col">
                                <label class="form-label">Price ($)</label>
                                <input type="number" step="0.01" name="price" class="form-control" required>
                            </div>
                            <div class="col">
                                <label class="form-label">Category</label>
                                <select name="category" class="form-select">
                                    <option value="Classic">Classic</option>
                                    <option value="Fiction">Fiction</option>
                                    <option value="Non-Fiction">Non-Fiction</option>
                                    <option value="Fantasy">Fantasy</option>
                                    <option value="Mystery">Mystery</option>
                                </select>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col">
                                <label class="form-label">Book Type</label>
                                <select name="bookType" id="bookType" class="form-select" onchange="toggleFields()">
                                    <option value="PRINTED">Printed Book</option>
                                    <option value="EBOOK">E-Book</option>
                                </select>
                            </div>
                        </div>
                        
                        <div id="ebookFields" style="display:none;" class="mb-3">
                            <label class="form-label">Book Link (Download URL)</label>
                            <input type="text" name="bookLink" class="form-control">
                        </div>

                        
                        <div id="printedFields" class="mb-3">
                            <label class="form-label">Weight (kg)</label>
                            <input type="number" step="0.1" name="weight" class="form-control">
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Description</label>
                            <textarea name="description" class="form-control" rows="4" required></textarea>
                        </div>
                        
                        <script>
                            function toggleFields() {
                                var type = document.getElementById("bookType").value;
                                if (type === "EBOOK") {
                                    document.getElementById("ebookFields").style.display = "block";
                                    document.getElementById("printedFields").style.display = "none";
                                } else {
                                    document.getElementById("ebookFields").style.display = "none";
                                    document.getElementById("printedFields").style.display = "block";
                                }
                            }
                        </script>

                        <button type="submit" class="btn btn-primary w-100">Add Book to Catalog</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="../footer.jsp" />
</body>
</html>
