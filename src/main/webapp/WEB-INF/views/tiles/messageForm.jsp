<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div>

    <!--<start id="securityAuthorizeTag_access"/>-->
    <sec:authorize access="hasRole('ROLE_AUTHOR')">
        <!--<co id="co_renderForRoleSpitter"/>-->
        <s:url value="/messages" var="message_url" />
        <sf:form modelAttribute="message"
                 method="POST"
                 action="${message_url}">
            <sf:label path="text"><s:message code="label.message"
                                             text="Enter message:"/></sf:label>
            <sf:textarea path="text" rows="2" cols="40" />
            <sf:errors path="text" />

            <br/>
            <div class="sendItSubmitIt">
                <input type="submit" value="Send it!"
                       class="status-btn round-btn disabled" />
            </div>
        </sf:form>
    </sec:authorize>
    <!--<end id="securityAuthorizeTag_access"/>-->
</div>
