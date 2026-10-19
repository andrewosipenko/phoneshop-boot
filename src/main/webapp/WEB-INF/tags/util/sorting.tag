<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@ attribute name="field" required="true" %>
<c:set var="upperArrow" value="&#x25B2;"/>
<c:set var="downArrow" value="&#x25BC;"/>
<util:sortLink field="${field}" order="asc" arrow="${upperArrow}"/>
<util:sortLink field="${field}" order="desc" arrow="${downArrow}"/>
