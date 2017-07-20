<html   >
<head>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script src="resources/My_Design/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<script>
	var prod = ${mylist};
	angular.module('repeatSample', []).controller('repeatController', function($scope) 
			{
				$scope.products = prod;
				$scope.sort = function(keyname) 
				{
					$scope.sortKey = keyname; //set the sortKey to the param passed
					$scope.reverse = !$scope.reverse; //if true make it false and vice versa
				}
			});
</script>
</head>
<body class="container" ng-app="repeatSample" ng-controller="repeatController">
<c:if test="${!checkk}">


	
			<sf:form role="form" action="InsertCategory" commandName="category">
				<h2>
					Please See the all Categories...<small>It's free and always
						will be.</small>
				</h2>
				<hr class="colorgraph">
				<div class="row">
					<div class="row">
						<div class="col-xs-12">
							<div class="form-group col-xs-12">
								<sf:input path="cname" class="form-control input-lg"
									placeholder="categoryName" tabindex="1" pattern=".{3,15}"
									required="true" title="minimum length for categoryName is 5"></sf:input>
									
								<sf:input path="supid" class="form-control input-lg"
									placeholder="Supplier Id" tabindex="1" 
									required="true" ></sf:input>
								<sf:input path="supname" class="form-control input-lg"
									placeholder="Supplier Name" tabindex="1" 
									required="true" ></sf:input>		
							</div>
							</div>
							<div class="form-group col-xs-12">
								<input type="submit" value="Add category"
									class="btn btn-primary btn-block btn-lg" tabindex="7">
							</div>
						</div>
					</div>
			</sf:form>
		

</c:if>



 
					<h2 align="center">Search Category</h2>

					<input type="text" class="search-query form-control"
						placeholder="Search" ng-model="searchText" />

				
			<table class="table table-responsive">


				<thead>
					<tr>
						<th>cId</th>
						<th>cname</th>
						<th>supid</th>
						<th>supname</th>
						
						</tr>
				</thead>

		
				
				
				<tr ng-repeat="pro in products|filter:searchText">


					<td>{{pro.cId}}</td>
					<td>{{pro.cname}}</td>
					<td>{{pro.supid}}</td>
					<td>{{pro.supname}}</td>
					


				</tr>
						</table>
				
	</body>			
				
			
</html>		