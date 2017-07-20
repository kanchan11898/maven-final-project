
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="resources/My_Design/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>

<script>
    
	var prod1 = ${mylist1};
	var prod2 = ${mylist2};
	var prod3 = ${mylist3};
	angular.module('repeatSample', []).controller('repeatController',
			function($scope) {
		
		        
				$scope.list1 = prod1;
				$scope.list2 = prod2;
				$scope.list3 = prod3;

				$scope.sort = function(keyname) {
					$scope.sortKey = keyname; //set the sortKey to the param passed
					$scope.reverse = !$scope.reverse; //if true make it false and vice versa
				}

			});
</script>
<script>
    
	var pro=${poc};

	angular.module('repeatS', []).controller('repeatC',
			function($scope) {
		
		        
				$scope.poc = pro;
				
			});
</script>


</head>
<body class="container">
 
  <c:if test="${checkk==3}">
  
  <div ng-app="repeatS" ng-controller="repeatC"  >
  
     <center>
    
     <div ng-repeat="product in poc" >
     
    <img src="./resources/My_Design/{{product.productid}}.jpg" height="400px" width="500px">
     </div>
   
     <table class="table table-responsive"  ng-app="repeatS"  ng-controller="repeatC"  ng-repeat="product in poc">
    
             <tr>
                <td><b>product id</b></td>
                 <td></td>
                <td>{{product.productid}}</td>
             </tr>
              <tr>
                <td><b>product name</b></td>
                 <td></td>
                <td>{{product.productname}}</td>
              </tr>
              <tr>
                 <td><b> price</b></td>
                  <td></td>
                 <td>{{product.price}}</td>
              </tr>
              <tr>
                 <td><b> Stock</b></td>
                <td></td>
                 <td>{{product.stock}}</td>
              </tr>
                 <tr>
                   <td><b>Description</b></td>
                   <td></td>
                   <td>{{product.description}}</td>
                </tr>


            
                
       </table>
  </center>
    

<br>
<center>
<div  ng-repeat="product in poc">
<a   href="Carddetails?pid={{product.productid}}"><button type="button" >Add To Cart</button></a> 
<a   href="plzUpdatedproduct?pid={{product.productid}}"><button type="button" >Buy Now</button></a> 
</div>
</center>



</c:if>
 

 <div ng-app="repeatSample" ng-controller="repeatController">
    <c:if test="${checkk==1}">




		
			<sf:form role="form" action="InsertProduct" commandName="pro" method="post" modelAttribute="pro"  enctype="multipart/form-data">
				<h2>
					Please See the all Products<small>It's All and always will
						be for Admin</small>
				</h2>
				
						    <sf:input type="file" path="pimage" class="form-control input-lg" 
                                 placeholder="file" tabindex="1" 
									title="minimum length for ProductDiscription is 1"/>
						   
							<sf:input path="productname"
									class="form-control input-lg" placeholder="productname"
									tabindex="1" pattern=".{1,15}" required="true"
									title="minimum length for productname is 5"></sf:input>
						
						
						<sf:input path="price"
									class="form-control input-lg" placeholder="price"
									tabindex="1" pattern=".{1,15}" required="true"
									title="minimum length for prize is 1"></sf:input>

					
			
					<sf:input path="stock"
									class="form-control input-lg" placeholder="stock"
									tabindex="1" pattern=".{1,15}" required="true"
									title="minimum length for stock is 1"></sf:input>
		
		
				<sf:input path="description"
									class="form-control input-lg" placeholder="description"
									tabindex="1" pattern=".{1,15}" required="true"
									title="minimum length for ProductDescription is 1"></sf:input>
									
			
			  
			  <sf:label path="categoryid">category id</sf:label>
			  <sf:select path="categoryid" class="form-control input-sm" ng-controller="repeatController">
			  <option ng-repeat="product in list1" value="{{product.cId}}">{{product.cId}}</option>
			  </sf:select>
			  
		
			  <sf:label path="supplierid">supplier id</sf:label>
			  <sf:select path="supplierid" class="form-control input-sm" ng-controller="repeatController">
			  <option ng-repeat="product in list2" value="{{product.sId}}">{{product.sId}}</option>
			  </sf:select>
			  
			   
			   
		
				<input type="submit" value="Get-Products"
				class="btn btn-primary btn-block btn-lg" tabindex="7">
			
			   	   
									
			</sf:form>
		
	

   </c:if>


   
  <c:if test="${checkk==2}">



	
		
			<sf:form role="form" action="plzNowUpdatedproducts" commandName="pro" method="post" >
				<h2>
					Please Update Products<small>It's All and always will
						be for Admin</small>
				</h2>
			
					
						
						
						   <sf:input type="file" path="pimage" class="form-control input-lg" 
                                 placeholder="file" tabindex="1" 
									title="minimum length for ProductDiscription is 1"/>
									
						
				
						<sf:input path="productid"
									class="form-control input-lg" placeholder="productid"
									tabindex="1" pattern=".{1,15}" required="true"
									title="minimum length for productid is 5"></sf:input>
						
						
							<sf:input path="productname"
									class="form-control input-lg" placeholder="productname"
									tabindex="1" pattern=".{1,15}" required="true"
									title="minimum length for productname is 5"></sf:input>
						
						
						<sf:input path="price"
									class="form-control input-lg" placeholder="price"
									tabindex="1" pattern=".{1,15}" required="true"
									title="minimum length for prize is 1"></sf:input>

					
					
					<sf:input path="stock"
									class="form-control input-lg" placeholder="stock"
									tabindex="1" pattern=".{1,15}" required="true"
									title="minimum length for stock is 1"></sf:input>
				
				
				<sf:input path="description"
									class="form-control input-lg" placeholder="description"
									tabindex="1" pattern=".{1,15}" required="true"
									title="minimum length for ProductDiscription is 1"></sf:input>
									
			
			   
			  <sf:label path="categoryid">category id</sf:label>
			  <sf:select path="categoryid" class="form-control input-sm" ng-controller="repeatController">
			  <option ng-repeat="product in list1" value="{{product.cId}}">{{product.cId}}</option>
			  </sf:select>
			   
			   
			      
			  <sf:label path="supplierid">supplier id</sf:label>
			  <sf:select path="supplierid" class="form-control input-sm" ng-controller="repeatController">
			  <option ng-repeat="product in list2" value="{{product.sId}}">{{product.sId}}</option>
			  </sf:select>
			   
			   
			
									
			   
				<input type="submit" value="Get-Update-Products"
				class="btn btn-primary btn-block btn-lg" tabindex="7">
				
			 	   
									
			</sf:form>
		
	


</c:if>


 

<c:if test="${checkk==2 || checkk==1}">

<div>
<h2 align="center">Search Products</h2>
<input type="text" class="search-query form-control" placeholder="Search" ng-model="searchText" />



      <table class="table table-responsive" >
                 <thead>
					<tr>
						<th>product id</th>

						<th>product name</th>
						<th>price</th>
						<th>stock</th>
						<th>category id</th>
						<th>supplier id</th>
						<th>description</th>
						<th>Image</th>
					</tr>
				</thead>
				<tr ng-repeat="product in list3|filter:searchText">
				    <td><a href="ProductDescription?pid={{product.productid}}">{{product.productid}}</a></td>
					<td><a href="ProductDescription?pid={{product.productid}}">{{product.productname}}</a></td>
					<td><a href="ProductDescription?pid={{product.productid}}">{{product.price}}</a></td>
					<td><a href="ProductDescription?pid={{product.productid}}">{{product.stock}}</a></td>
					<td><a href="ProductDescription?pid={{product.productid}}">{{product.categoryid}}</a></td>
					<td><a href="ProductDescription?pid={{product.productid}}">{{product.supplierid}}</a></td>
					<td><a href="ProductDescription?pid={{product.productid}}">{{product.description}}</a></td>
					<td><a href="plzUpdatedproduct?pid={{product.productid}}"><img src="./resources/My_Design/{{product.productid}}.jpg" height="50px" 
                     width="50px">Update</a></td>

                    <td><a href="Deleteproduct?pid={{product.productid}}">Delete</a></td>

                </tr>
        </table>
       </div>
      </c:if>
</div>



</body>
</html>