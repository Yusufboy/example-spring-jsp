<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Cart</title>
    </head>
    <body>
    	<div class="navbar-inner">
	        <div class="container">
	            <div class="nav-collapse collapse">
	                        <a href="/e-commerce/products">Products</a>
	                        <a href="/e-commerce/orders">Cart</a>
	                        <form action="previous_carts" method="post">
			                	<input type="hidden" name="id" value="${id}" />
								<input type="submit" value="Previous Carts" />    
							</form>
	            </div>
	        </div>
	    </div>
	    <c:forEach items="${ids}" var="tempId">
           	<form action="previous_cart" method="post">
              	<input type="hidden" name="id" value="${tempId}" />
              	<%
				String id=request.getParameter("id");
				%>
				<input type="submit" value="Cart ${tempId}" />    
			</form>
        </c:forEach>
    </body>
</html>