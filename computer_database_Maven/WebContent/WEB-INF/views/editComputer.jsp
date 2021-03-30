<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="ComputerServlet"> Application -
				Computer Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right" >id: ${loadedComputer.getId()}</div>
					<h1>Edit Computer</h1>

					<form action="EditServlet" method="POST">
						<input type="hidden" value="${loadedComputer.getId()}" id="id" name="id" />
						<!-- TODO: Change this value with the computer id -->
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="computerName" name="computerName"
									placeholder="Computer name" value="${loadedComputer.getName()}" required>
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" id="introduced" name="introduced"
									placeholder="Introduced date" onchange= minDiscontinued(this.value) value ="${loadedComputer.getIntroduced()}">
									
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" id="discontinued" name="discontinued"
									placeholder="Discontinued date" onchange=maxIntroduced(this.value) value= "${loadedComputer.getDiscontinued()}">
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									
									<option value="${loadedComputer.getCompany().getId()}">${loadedComputer.getCompany().getName()}</option>
									<option value="0"></option>
									<c:forEach var="companyDto" items="${listCompany}">
										<option value="${companyDto.getId()}">${companyDto.getName()}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary"  >
							or <a href="ComputerServlet" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
 <script src="/computer_database/js/addComputer.js"></script>
</html>