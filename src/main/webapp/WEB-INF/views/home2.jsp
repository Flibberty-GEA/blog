<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
    <h2>A global community of friends and strangers spitting out their
        inner-most and personal thoughts on the web for everyone else to
        see.</h2>
    <h3>Look at what these people are spitting right now...</h3>

    <ol class="message-list">
        <c:forEach var="message" items="${messages}"> <!--<co id="cp_foreach_spittles"/>-->

            <s:url value="/authors/{authorName}"
                   var="author_url" >    <!--<co id="cp_spitter_url"/>-->
                <s:param name="authorName"
                         value="${message.author.username}" />
            </s:url>

            <li><span class="messageListImage">
        <img src=
                     "http://s3.amazonaws.com/spitterImages/${message.author.id}.jpg"
             width="48"
             border="0"
             align="middle"
             onError=
                     "this.src='<s:url value="/resources/images"/>/blog_avatar.png';"/>
      </span>
      <span class="messageListText">
        <a href="${author_url}">              <!--<co id="cp_spitter_properties"/>-->
          <c:out value="${message.author.username}" /></a>
          - <c:out value="${message.text}" /><br/>
         <small><fmt:formatDate value="${message.when}"
                                pattern="hh:mma MMM d, yyyy" /></small>
      </span></li>
        </c:forEach>
    </ol>
</div>
