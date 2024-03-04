<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach items="${bookList}" var="book" varStatus="st">
		${st.count}, ${book.name}, ${book.author}, 
		${book.publisher}, ${book.releaseDate},
		${fn:substring(book.description, 0, 100)}... ${book.unitPrice}원 <br> 
</c:forEach>
</body>
</html>

