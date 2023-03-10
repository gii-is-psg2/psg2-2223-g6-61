<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<petclinic:layout pageName="vets">
    <h2><fmt:message key="vets"/></h2>

    <table id="vetsTable" class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="vet.name"/></th>
            <th><fmt:message key="vet.specialty"/></th>
            <th>&nbsp;</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vets.vetList}" var="vet">
            <tr>
                <td>
                    <c:out value="${vet.firstName} ${vet.lastName}"/>
                </td>
                <td>
                    <c:forEach var="specialty" items="${vet.specialties}">
                        <c:out value="${specialty.name} "/>
                    </c:forEach>
                    <c:if test="${vet.nrOfSpecialties == 0}">none</c:if>
                </td>
                <td>
                	<spring:url value="/vets/{vetId}/delete" var="deleteUrl">
                         <spring:param name="vetId" value="${vet.id}"/>
                    </spring:url>
                   <a class="btn btn-default" href="${fn:escapeXml(deleteUrl)}">Delete Veterinary</a>
                   
                   <spring:url value="/vets/{vetId}/edit" var="editUrl">
                   		<spring:param name="vetId" value="${vet.id}"/>
                   </spring:url>
                   <a class="btn btn-default" href="${editUrl}">Edit Vet</a> 
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table-buttons">
        <tr>
            <td> 	
                <a href="<spring:url value="/vets.xml" htmlEscape="true" />">View as XML</a>
            </td>            
        </tr>
        <tr>
            <td>
            	<a class="btn btn-default" href='<spring:url value="/vets/new" htmlEscape="true"/>'>Add Vet</a>
            </td>              
        </tr>
    </table>
</petclinic:layout>
