<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<title>Category Page</title>
</head>
<body>
<h1><c:out value="${cat.name}"/></h1>
<div class="container">
    <div>
        <h2>Products :</h2>
        <ul>
        <c:forEach var="p" items="${pros}">
            <li><c:out value="${p.name}"/></li>        
        </c:forEach>
        </ul>
    </div>
    <div>
	<form:form action="/add/pro/${cat.id}" method="post" modelAttribute="cat">
        <input type="hidden" name="_method" value="put">
        <form:label path="products">Products :</form:label>
        <form:select path="products">
            <c:forEach var="p" items="${allPros}">
                <form:option value="${p.id}" path="products">
                    <c:out value="${p.name}"/>
                </form:option>
            </c:forEach>
        </form:select>
        <input type="submit" value="Add">
    </form:form> 
    </div>
</div>
</body>
</html>