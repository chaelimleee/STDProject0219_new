<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>  

<html>
<head>
<%-- <link href="<c:url value="/resources/css/bootstrap.min.css"/>"  
    rel="stylesheet"> --%>
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" /> -->

<link href="./css/bootstrap.min.css" rel="stylesheet" />    

<title>도서 목록</title>
</head>
<body>
    <nav class="navbar navbar-expand  navbar-dark bg-dark"> 
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="./home">Home</a>
            </div>
        </div>
    </nav>
    <div class="jumbotron">
        <div class="container">
            <h1 class="display-3">도서 목록</h1>
        </div>
    </div>
    <div class="container">
        <div class="row" align="center">
            <c:forEach items="${bookList}" var="book"> 
                <div class="col-md-4">
                    <h3>${book.name}</h3>
                    <p>${book.author}
                        <br> ${book.publisher} | ${book.releaseDate}
                    <p align=left>${fn:substring(book.description, 0, 100)}...
                    <p>${book.unitPrice}원
                </div>
            </c:forEach> 
        </div>
        <hr>
        <footer>
            <p>&copy; BookMarket</p>
        </footer>
    </div>
</body>
</html>