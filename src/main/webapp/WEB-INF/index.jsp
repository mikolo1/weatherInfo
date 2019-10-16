<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8"/>
    <title>Main Page</title>
    <link rel="stylesheet" href="<spring:url value='resources/css/style.css'/>"/>
    <link rel="stylesheet" href="<c:url value='webjars/bootstrap/4.3.1/css/bootstrap.min.css'/>"/>

</head>
<body>
<h1 class="header">Temperature service for some locations.</h1>
<div class="formedit" style="width: 500px">
    <form method="post" action="/findtemp">
        <div class="input-group">
            <input type="search" class="form-control width100" id="city" name="city"
                   placeholder="Enter location name to search"
                   autofocus="autofocus">
            <span class="input-group-btn">
                <button style="margin-left: 30px;" class="btn btn-success" type="submit">Search</button>
            </span>
        </div>
        <div class="clear"></div>
    </form>

</div>
<div class="cityTable">
    <c:if test="${text=='empty'}">
        <div class="warn">
            Enter location name!
        </div>
    </c:if>
    <c:if test="${chosenCity!=null}">
        <div>
            Searched phrase: ${chosenCity}
        </div>
    </c:if>
    <c:if test="${cityList!=null}">
        <div>
            Founded ${cityList.size()} locations:
        </div>
        <div class="chosenCity">
            Click on location name to see temperature for location.
        </div>

        <c:set var="counter" value="1"/>
        <div class="cityTableDiv">
            <table class="cityTable">
                <tr class="tableHead">
                    <th>Nr.</th>
                    <th>City name</th>
                    <th>Country</th>
                </tr>
                <c:forEach var="city" items="${cityList}">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/showtemp?cityid=${city.id}">${counter}</a></td>
                        <td><a href="${pageContext.request.contextPath}/showtemp?cityid=${city.id}">${city.name}</a></td>
                        <td><a href="${pageContext.request.contextPath}/showtemp?cityid=${city.id}">${city.country}</a></td>
                    </tr>
                    <c:set var="counter" value="${counter+1}"/>
                </c:forEach>
            </table>
        </div>
    </c:if>
    <div>
        <span class="notFound">
            ${notfound}
        </span>
    </div>
</div>


<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="webjars/popper.js/1.14.6/dist/umd/popper.min.js"></script>
<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
