<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Conversion</title>
</head>
<body>
<div>
    <form action="/convert">
        USD: <input type="text" name="usd" placeholder="USD">
        <button type="submit">Convert</button>
        <p>VND: <span>${requestScope.result}</span></p>
    </form>

</div>
</body>
</html>