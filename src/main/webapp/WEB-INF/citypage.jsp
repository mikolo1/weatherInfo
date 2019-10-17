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

<div class="cityTable">

    <div class="chosenCity">
        Some informations about choosen location:
    </div>


    <div class="cityTableDiv">
        <table class="cityTable">
            <tr class="tableHead">
                <th>ID</th>
                <th>City name</th>
                <th>Country</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>Temperature</th>
            </tr>
            <tr>
                <td>${city.id}</td>
                <td>${city.name}</td>
                <td>${city.country}</td>
                <td>${city.coord.lat}</td>
                <td>${city.coord.lon}</td>
                <td>${temperature}</td>
            </tr>
        </table>
        <div class="buttondiv">
            <button class="btn btn-success" type="button"
                    onclick="window.location.href='${pageContext.request.contextPath}/weatherservice'">Back to search
                page
            </button>
            <c:if test="${param.forecast == false}">
                <button class="btn btn-primary" type="button"
                        onclick="window.location.href='${pageContext.request.contextPath}/showtemp?cityid=${city.id}&forecast=true'">
                    Show forecast
                </button>
            </c:if>
            <c:if test="${param.forecast == true}">
                <button class="btn btn-secondary" type="button"
                        onclick="window.location.href='${pageContext.request.contextPath}/showtemp?cityid=${city.id}&forecast=false'">
                    Hide forecast
                </button>
            </c:if>
        </div>
        <c:if test="${param.forecast == true}">
            <table class="forecastTableDiv">
                <tr class="tableHead">
                    <th colspan="2">Weather forecast</th>
                </tr>
                <tr class="tableHead">
                    <th>Date and time:</th>
                    <th>Temperature forecast:</th>
                </tr>
                <c:forEach var="forecastList" items="${forecastList}">
                <tr>
                    <td>${fn:substring(forecastList.dtTxt, 0, 16)}</td>
                    <td>${forecastList.main.temp} °C</td>
                <tr>
                    </c:forEach>
            </table>
        </c:if>
    </div>
</div>


<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="webjars/popper.js/1.14.6/dist/umd/popper.min.js"></script>
<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
