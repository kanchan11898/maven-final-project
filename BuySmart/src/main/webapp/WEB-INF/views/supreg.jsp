<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <div align="center">
        <sf:form action="InsertSupplier" method="post" commandName="sup">
            <table border="0">
                <tr>
                    <td colspan="2" align="center">
                    <h2>Supplier Register</h2></td>
               
               
                <tr>
                    <td>Supplier Name</td>
                    <td><sf:input path="name" required="true"  /></td>
                </tr>
                
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Add Supplier" /></td>
                </tr>
            </table>
        </sf:form>
    </div>
</body>
</html>