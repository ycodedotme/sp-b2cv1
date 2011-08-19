<%@ include file="../include.jsp" %>
<%@page import="com.spshop.utils.AllConstants"%>

<c:forEach items="${pageForm.categories}" var="category">
<c:if test='${category.name != "home"}'>
<dl class="main_Categories font_size10 lineheight15 float_left">
	<dt class="fontbold font_size11">
		<c:if test="${empty category.url}">
			<a class="blue" title="${category.displayName}" href="<%=AllConstants.HTTP_PROTOCOL%>${pageForm.site.domain}/<%= AllConstants.CATEGORY_URL %>/${category.name}">
				${category.displayName}
			</a>
		</c:if>
		<c:if test="${!empty category.url}">
			<a class="blue" title="${category.displayName}" href="<%=AllConstants.HTTP_PROTOCOL%>${category.url}">
				${category.displayName}
			</a>
		</c:if>
	</dt>
	<c:forEach items="${category.subCategories}" var="subCategory">
	<dd>
		<c:if test="${empty subCategory.url}">
			<a class="Light_gray2" title="${subCategory.displayName}" href="<%=AllConstants.HTTP_PROTOCOL%>${pageForm.site.domain}/<%= AllConstants.CATEGORY_URL %>/${subCategory.name}">
				${subCategory.displayName}
			</a>
		</c:if>
		<c:if test="${!empty subCategory.url}">
			<a class="Light_gray2" title="${subCategory.displayName}" href="<%=AllConstants.HTTP_PROTOCOL%>${subCategory.url}">
				${subCategory.displayName}
			</a>
		</c:if>
	</dd>
	</c:forEach>
</dl>
</c:if>
</c:forEach>