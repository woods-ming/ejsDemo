<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 分支 -->
<c:if test="${ bean.canRetrivePassword}">
	${ bean.result }
</c:if>

<c:choose>
	<c:when test='${ bean.account.equals("test")}'>
   		相当于case
   	</c:when>
	<c:when test='${ bean.account.equals("niit")}'>
   		相当于case
   	</c:when>
	<c:otherwise>
   		相当于default
   </c:otherwise>
</c:choose>

<!-- 循环 -->
<c:forEach items="${bean.favorites }" var="favorite">
	<c:out value="${favorite}"></c:out>
</c:forEach>
