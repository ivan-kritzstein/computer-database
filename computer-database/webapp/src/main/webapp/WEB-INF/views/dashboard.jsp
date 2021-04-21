<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href=dashboard> <fmt:message
					key="lbl.dashboard.header" />
			</a>
			<ul>
				<li><a href="?lang=fr"><fmt:message
							key="lbl.dashboard.lang.fr" /></a></li>
				<li><a href="?lang=en"><fmt:message
							key="lbl.dashboard.lang.en" /></a></li>
			</ul>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${page.nbrComputer}
				<fmt:message key="lbl.dashboard.found" />
			</h1>

			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit"
							value="<fmt:message key="lbl.dashboard.filter" />"
							class="btn btn-primary" href=dashboard?search= "search"/>
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer"><fmt:message
							key="lbl.dashboard.add" /></a> <a class="btn btn-default"
						id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><fmt:message
							key="lbl.dashboard.edit" /></a>
				</div>
			</div>
		</div>




		<form id="deleteForm" action="dashboard" method="POST">
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
						<th><a href="dashboard?order=computer.name"> <fmt:message
							key="lbl.dashboard.computerName" /> </a></th>
						<th><a href="dashboard?order=computer.introduced"><fmt:message
							key="lbl.dashboard.introducedDate" /></a></th>
						<!-- Table header for Discontinued Date -->
						<th><a href="dashboard?order=computer.discontinued"><fmt:message
							key="lbl.dashboard.discontinuedDate" /></a></th>
						<!-- Table header for Company -->
						<th><a href="dashboard?order=computer.companyHQLDto.name"><fmt:message
							key="lbl.dashboard.company" /></a></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">

					<c:forEach var="computerDto" items="${listComputerLimit10}">

						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computerDto.id}"></td>
							<td><a href="editComputer?computerId=${computerDto.id}"
								onclick=""> ${computerDto.name}</a></td>
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
				<li><a href="dashboard?offset=${index1}">${index1}</a></li>
				<li><a href="dashboard?offset=${index2}">${index2}</a></li>
				<li><a href="dashboard?offset=${index3}">${index3}</a></li>
				<li><a href="dashboard?offset=${index4}">${index4}</a></li>
				<li><a href="dashboard?offset=${index5}">${index5}</a></li>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						<%-- </c:forEach> --%>
				</a></li>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<a href="dashboard?limit=10" class="btn btn-default">10</a> <a
					href="dashboard?limit=50" class="btn btn-default">50</a> <a
					href="dashboard?limit=100" class="btn btn-default">100</a>
			</div>
		</div>
	</footer>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>