<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Timekeeping</title>
    </head>
    <body>
        <jsp:useBean id="datetime" class="helper.DateTimeHelper"/>
        <h1 style="text-align: center">Timekeeping</h1>
        <h2 style="text-align: center">Month: ${requestScope.currentMonth}</h2>
        <table border="1px">
            <tr>
                <td></td>
                <c:forEach items="${requestScope.dates}" var="date">
                    <td
                        <c:if test="${datetime.getDayOfWeek(date) == 6
                                      or datetime.getDayOfWeek(date) == 7}">
                              style="background-color: yellow;"
                        </c:if>
                        >
                        <fmt:formatDate pattern = "dd" 
                                        value = "${date}" /><br/>
                        <fmt:formatDate pattern = "EEE" 
                                        value = "${date}" />
                    </td>
                </c:forEach>
                <td>Working days</td>
                <td>Working hours</td>
                <td>Leave days</td>
            </tr>
            <c:forEach items="${requestScope.employees}" var="employee">
                <tr>
                    <td>${employee.name}</td>
                    <c:forEach items="${requestScope.dates}" var="date">
                        <td 
                            <c:if test="${datetime.getDayOfWeek(date) == 6
                                          or datetime.getDayOfWeek(date) == 7}">
                                  style="background-color: yellow;"
                            </c:if>
                            >
                            <c:forEach items="${employee.works}" var="work">
                                <c:if test="${work.getCheckInDate() == date}">
                                    ${work.getWorkingHours()}
                                </c:if>
                            </c:forEach>
                            <c:forEach items="${employee.leaves}" var="leave">
                                <c:if test="${leave.from <= date and leave.to >= date}">
                                    <c:choose>
                                        <c:when test="${leave.reason == 1}">
                                            CP
                                        </c:when>
                                    </c:choose>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                    <td>${employee.getNumberOfWorkingDays()}</td>
                    <td>${employee.getNumberOfWorkingHours()}</td>
                    <td>${employee.getTotalLeaves()}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
