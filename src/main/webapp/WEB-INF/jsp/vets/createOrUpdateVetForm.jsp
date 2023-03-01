<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">
    <h2>
        <c:if test="${vetForm.vet['new']}">New </c:if> Vet
    </h2>
    <form:form modelAttribute="vetForm" class="form-horizontal" id="vetForm">
        <div class="form-group has-feedback">
            <petclinic:inputField label="First Name" name="vet.firstName"/>
            <petclinic:inputField label="Last Name" name="vet.lastName"/>
            <form:checkboxes class="col-sm-2 control-label" path="specialties" items="${specialties}" delimiter = "<br />" />	

        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${vetForm.vet['new']}">
                        <button class="btn btn-default" type="submit">Add Vet</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Vet</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
