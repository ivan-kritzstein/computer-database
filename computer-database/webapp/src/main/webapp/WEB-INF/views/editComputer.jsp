<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
					key="lbl.edit.header" />
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
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${loadedComputer.getId()}</div>
					<h1>
						<fmt:message key="lbl.edit.editComputer" />
					</h1>

					<form:form action="editComputer" method="POST" modelAttribute="cmptDto">
						<input type="hidden" value="${loadedComputer.getId()}" id="id"
							name="id" />
						<!-- TODO: Change this value with the computer id -->
						<fieldset>
							<div class="form-group">
							<fmt:message key="lbl.edit.computerName" var="nameComputer"/>
								<label for="computerName">${nameComputer}</label> 
								<form:input type="text" class="form-control" id="name"  placeholder="${nameComputer}" value="${loadedComputer.getName()}" required="" path="name"/>
								<form:errors path="name" cssClass="error" />
							</div>
							<div class="form-group">
								<label for="introduced"><fmt:message
										key="lbl.edit.introducedDate" /></label> <form:input type="date"
									class="form-control" id="introduced" name="introduced"
									placeholder="Introduced date"
									onchange="minDiscontinued(this.value)"
									value="${loadedComputer.getIntroduced()}"
									path="introduced"/>
								<form:errors path="introduced" cssClass="error" />

							</div>
							<div class="form-group">
								<label for="discontinued"><fmt:message
										key="lbl.edit.discontinuedDate" /></label> <form:input type="date"
									class="form-control" id="discontinued" name="discontinued"
									placeholder="Discontinued date"
									onchange="maxIntroduced(this.value)"
									value="${loadedComputer.getDiscontinued()}"
									path="discontinued"/>
								<form:errors path="discontinued" cssClass="error" />
							</div>
							<div class="form-group">
								<label for="companyId"><fmt:message
										key="lbl.edit.company" /></label> <select class="form-control"
									id="companyId" name="companyId">

									<option value="${loadedComputer.getCompany().getId()}">${loadedComputer.getCompany().getName()}</option>
									<option value="0"></option>
									<c:forEach var="companyDto" items="${listCompany}">
										<option value="${companyDto.getId()}">${companyDto.getName()}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit"
								value="<fmt:message
							key="lbl.edit.edit" />"
								class="btn btn-primary"> or <a href="dashboard"
								class="btn btn-default"><fmt:message key="lbl.edit.cancel" /></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
</body>
<script src="/computer_database/js/addComputer.js"></script>
</html>