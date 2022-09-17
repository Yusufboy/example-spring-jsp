<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Products</title>
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
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>${product.price}</td>
                        <td>${product.quantity}</td>
                        <td><form action="add" method="post">
                        	<input type="number" name="quantity">
						    <input type="hidden" name="id" value="${product.id}" />
						    <input type="submit" value="Add to cart" />
						</form></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>