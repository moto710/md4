<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
</head>
<body>
<div class="container-fluid table-responsive mt-3">
    <table class="table">
        <thead>
        <tr class="bg-primary text-white">
            <th colspan="4">List Of Customers</th>
            <th colspan="3">
                <button type="button" class="btn btn-primary text-light"><i class="fa-solid fa-clock-rotate-left"></i>
                    Transfer money information
                </button>
            </th>
            <th colspan="2">
                <button type="button" class="btn btn-primary text-light"><i class="fa-solid fa-square-plus"></i> Add new
                    customer
                </button>
            </th>
        </tr>
        <tr class="bg-success text-white text-center">
            <th>ID</th>
            <th>Full Name</th>
            <th>Email</th>
            <th>Address</th>
            <th colspan="5">Action</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>1</td>
            <td>John Doe</td>
            <td>john@example.com</td>
            <td>US</td>
            <td>
                <button type="button" class="btn btn-outline-secondary"><i
                        class="fa-regular fa-pen-to-square"></i></button>
            </td>
            <td>
                <button type="button" class="btn btn-outline-success"><i class="fa-solid fa-plus"></i></button>
            </td>
            <td>
                <button type="button" class="btn btn-outline-warning"><i class="fa-solid fa-minus"></i></button>
            </td>
            <td>
                <button type="button" class="btn btn-outline-primary"><i
                        class="fa-solid fa-right-left"></i></button>
            </td>
            <td>
                <button type="button" class="btn btn-outline-danger"><i class="fa-solid fa-ban"></i></button>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>