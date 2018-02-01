<%--
  Created by IntelliJ IDEA.
  User: x110277
  Date: 11/09/2016
  Time: 02:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>${title}</title>
</head>
<body>
    <h1>${title}</h1>
    <p>${message}</p>

    <p><form method="POST" action="${pageContext.request.contextPath}/customermigrator" enctype="multipart/form-data">
        Upload a CSV file with Customer IDs where the Customer ID is the first column: <input type="file" name="csvFile">
        <input type="submit" value="Migrate Customers"/>
    </form></p>
</body>
</html>
