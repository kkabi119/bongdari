<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp=request.getContextPath();
%>
<div class="container">
<table class="table table-bordered table-striped table-hover">
  <tr>
      <th>Product ID</th>
      <th>Product Name</th>
      <th>Product Quality</th>
      <th>Product Quantity</th>
  </tr>
  <tr>
      <td>1</td>
      <td>Wheat</td>
      <td>Good</td>
      <td>200 Bags</td>
  </tr>
  <tr>
      <td>2</td>
      <td>Rice</td>
      <td>Good</td>
      <td>250 Bags</td>
  <tr>
      <td>3</td>
      <td>Sugar</td>
      <td>Good</td>
      <td>200 Bags</td>
  </tr>  
</table>
</div><br /><br />