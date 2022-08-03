<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<title>Product Page</title>
</head>
<body>
    <h1><c:out value="${pro.name}"/></h1>
    <div>
        <div>
            <h2>Categories :</h2>
            <ul>
                <c:forEach var="c" items="${cats}">
                    <li><c:out value="${c.name}"/></li>        
                </c:forEach>
            </ul>
        </div>
        <form:form action="/add/cat/${pro.id}" method="post" modelAttribute="pro">
            <input type="hidden" name="_method" value="put">
            <form:label path="categories">Categories :</form:label>
            <form:select path="categories">
                <c:forEach var="c" items="${allcats}">
                    <form:option value="${c.id}" path="categories">
                        <c:out value="${c.name}"/>
                    </form:option>
                </c:forEach>
            </form:select>
            <input type="submit" value="Add">
        </form:form> 
    </div>
</body>
</html>