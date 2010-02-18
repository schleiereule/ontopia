<%
/**
* View Jsp for TopicName Portlet. This page shows information on a given topic.
*/
%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri='http://psi.ontopia.net/jsp/taglib/tolog' prefix='tolog'%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<portlet:defineObjects />
<tolog:context topicmap="liferay.ltm">
  <tolog:set var="topic" reqparam="topic"/>

  <tolog:choose>
    <tolog:when var="topic">
      This is the page for the topic:
      <h3><tolog:out var="topic"/></h3>

      <tolog:if query="subject-identifier(%topic%, $psi)?">
        Read more on:
        <a href="<tolog:out var="psi"/>"><tolog:out var="psi"/></a>
      </tolog:if>
    </tolog:when>

    <tolog:otherwise>
      TopicName: topic parameter as not been passed!
      Please provide a topic id to this url!
    </tolog:otherwise>
  </tolog:choose>
</tolog:context>