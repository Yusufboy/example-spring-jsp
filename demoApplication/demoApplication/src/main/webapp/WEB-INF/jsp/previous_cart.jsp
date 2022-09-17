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
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${orders}" var="orderItem">
                    <tr>
                        <td>${orderItem.getProduct().getName()}</td>
                        <td>${orderItem.getProduct().getPrice()}</td>
                        <td>${orderItem.getQuantity()}</td>
                        <td>${orderItem.getQuantity() * orderItem.getProduct().getPrice()}</td>
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        Total Price: ${totalPrice}  
    </body>
</html>