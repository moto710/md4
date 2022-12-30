<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dictionary</title>
</head>
<body>
<div>
    <form action="/search">
        Keyword: <input type="text" placeholder="Enter keyword" name="keyword">
        <button type="submit">Search</button>
        Result: <span>${requestScope.result}</span>
    </form>
</div>
</body>
</html>