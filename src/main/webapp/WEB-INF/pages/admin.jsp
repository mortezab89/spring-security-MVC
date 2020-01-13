<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>
<html>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

	<%--principal--%>
	<h1>Hello ${pageContext.request.userPrincipal.name}</h1>

    <h1> Hello <sec:authentication property="principal.username"/> </h1>

    <h1>
        <sec:authorize access="hasRole('ADMIN')">
            It will display only is user is admin
        </sec:authorize>
    </h1>



    <c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>Welcome : ${pageContext.request.userPrincipal.name} | <a href="<c:url value="/logout" />" > Logout</a></h2>
	</c:if>

</body>
</html>