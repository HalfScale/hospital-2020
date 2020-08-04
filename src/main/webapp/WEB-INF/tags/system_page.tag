<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title" %>
<%@attribute name="head_imports" fragment="true"%>
<%@attribute name="scripts" fragment="true"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<%-- any content can be specified here e.g.: --%>
<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <t:common_imports/>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/css/style.css"/>
        <jsp:invoke fragment="head_imports"/>
    </head>

    <body>
		<nav id="system-top-nav" class="navbar navbar-expand-lg navbar-dark bg-primary rounded">
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-between" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-item nav-link active" href="${pageContext.request.contextPath}/">Home</a>
					<security:authorize access="!isAuthenticated()">
					<a class="nav-item nav-link" href="${pageContext.request.contextPath}/registration">Sign Up</a>
					<a class="nav-item nav-link" href="${pageContext.request.contextPath}/users/login">Login</a>
					</security:authorize>
					<a class="nav-item nav-link" href="#">Doctors</a>
					<a class="nav-item nav-link" href="${pageContext.request.contextPath}/reservations">Reservations</a>
					<a class="nav-item nav-link" href="#">Appointments</a>
				</div>
				
				<security:authorize access="isAuthenticated()">
				<ul class="navbar-nav mr-4">
					<li class="nav-item dropdown">
				        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				          ${user.userDetail.firstName} ${user.userDetail.lastName}
				        </a>
						<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
				          <a class="dropdown-item" href="#">Messages</a>
				          <a class="dropdown-item" href="#">Notifications</a>
				          <div class="dropdown-divider"></div>
				          <form:form action="${pageContext.request.contextPath}/logoutUser" method="POST">
							<button type="submit" class="dropdown-item">Logout</button>
						 </form:form>
				        </div>
			       </li>
				</ul>
				</security:authorize>
			</div>
		</nav>
        <jsp:doBody/>
        <jsp:invoke fragment="scripts"/>
    </body>
</html>