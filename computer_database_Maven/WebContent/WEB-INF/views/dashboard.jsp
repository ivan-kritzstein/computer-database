<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="../../computer_database/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="../../computer_database/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="../../computer_database/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href=ComputerServlet> Application -
				Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${page.nbrComputer}</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" href=ComputerServlet?search="search"/>
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer"
						href="AddComputerServlet">Add Computer</a> <a
						class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>




			<form id="deleteForm" action="ComputerServlet" method="POST">
				<input type="hidden" name="selection" value="" id="selection">
			</form>


		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><a href="ComputerServlet?order=computer.name">
								Computer name </a></th>
						<th><a href="ComputerServlet?order=computer.introduced">Introduced
								date</a></th>
						<!-- Table header for Discontinued Date -->
						<th><a href="ComputerServlet?order=computer.discontinued">Discontinued
								date</a></th>
						<!-- Table header for Company -->
						<th><a href="ComputerServlet?order=company.name">Company</a></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">

					<c:forEach var="computerDto" items="${listComputerLimit10}">

						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computerDto.id}"></td>
							<td><a href="EditServlet?computerId=${computerDto.id}" onclick="">
									${computerDto.name}</a></td>
							<td>${computerDto.introduced}</td>
							<td>${computerDto.discontinued}</td>
							<td>${computerDto.companyName}</td>
						</tr>
					</c:forEach>



				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<li><a href="#" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<%-- <c:forEach var="page" items="${page}"> --%>
				<li><a href="ComputerServlet?offset=${index1}" >${index1}</a></li>
				<li><a href="ComputerServlet?offset=${index2}">${index2}</a></li>
				<li><a href="ComputerServlet?offset=${index3}">${index3}</a></li>
				<li><a href="ComputerServlet?offset=${index4}">${index4}</a></li>
				<li><a href="ComputerServlet?offset=${index5}">${index5}</a></li>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						<%-- </c:forEach> --%>
				</a></li>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<a href="ComputerServlet?limit=10" class="btn btn-default">10</a> <a
					href="ComputerServlet?limit=50" class="btn btn-default">50</a> <a
					href="ComputerServlet?limit=100" class="btn btn-default">100</a>
			</div>
		</div>
	</footer>
	<script src="../../computer_database/js/jquery.min.js"></script>
	<script src="../../computer_database/js/bootstrap.min.js"></script>
	<script src="../../computer_database/js/dashboard.js"></script>

</body>
</html>