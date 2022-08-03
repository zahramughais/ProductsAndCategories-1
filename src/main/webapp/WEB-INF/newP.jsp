<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<title>New Product</title>
</head>
<h1>New Product</h1>
<body>
    <form:form action="/products/new" method="post" modelAttribute="pro">
        <div>
            <form:label path="name">Name:</form:label>
            <form:errors path="name"/>
            <form:input path="name"/>
        </div>
        <div>
            <form:label path="description">Description:</form:label>
            <form:errors path="description"/>
            <form:input path="description"/>
        </div>
        <div>
            <form:label path="price">Price:</form:label>
            <form:errors path="price"/>
            <form:input type="number" path="price"/>
        </div>
        <input type="submit" value="Create"/>
    </form:form>     
</body>
</html>