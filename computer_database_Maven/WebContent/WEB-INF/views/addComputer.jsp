<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
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
			<a class="navbar-brand" href="dashboard"> <fmt:message
							key="lbl.add.header" /> </a>
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
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1><fmt:message
							key="lbl.add.addComputer" /></h1>
					<form action="addComputer" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName"><fmt:message
							key="lbl.add.computerName" /></label> <input
									type="text" class="form-control" id="computerName"
									name="computerName" placeholder="<fmt:message
							key="lbl.add.computerName" />" required>
							</div>
							<div class="form-group">
								<label for="introduced"><fmt:message
							key="lbl.add.introducedDate" /></label> <input
									type="date" class="form-control" id="introduced"
									name="introduced" placeholder="Introduced date" onchange= minDiscontinued(this.value) value ="">
							</div>
							<div class="form-group">
								<label for="discontinued"><fmt:message
							key="lbl.add.discontinuedDate" /></label> <input
									type="date" class="form-control" id="discontinued"
									name="discontinued" placeholder="Discontinued date" onchange=maxIntroduced(this.value) value= "">
							</div>
							<div class="form-group">
								<label for="companyId"><fmt:message
							key="lbl.add.company" /></label> <select
									class="form-control" id="companyId" name="companyId" >
									<option value="0"></option>
									<c:forEach var="companyDto" items="${listCompany}">
										<option value="${companyDto.getId()}">${companyDto.getName()}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="<fmt:message
							key="lbl.add.add" />" class="btn btn-primary"> or <a
								href="addComputer" class="btn btn-default"><fmt:message
							key="lbl.add.cancel" /></a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
 <script src="/computer_database/js/addComputer.js"></script>

</html>