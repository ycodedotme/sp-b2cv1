<%@include file="../include.jsp" %>
<%@page import="com.spshop.utils.AllConstants"%>
<div id="P_list" class="correlationThing" style="">
	<div class="mainThing">
		
		<ss:categoryProductNames var="names" category="${pageForm.pageProperties.productDetail.categories[0]}">
			<c:forEach items="${names}" var="name">
				<ss:product var="product" productName="${name }"></ss:product>
				<a class=""
					href="<%=AllConstants.HTTP_PROTOCOL %>${pageForm.site.domain}<%=AllConstants.URL_SEPERATOR %>${product.name}"><img
					alt="${product.title }"
					src="<%=AllConstants.HTTP_PROTOCOL %>${pageForm.site.domain}${product.images[0].smallUrl}"><b>
					${currency} <fmt:formatNumber value=" ${product.actualPrice * currencies[currency]}" currencyCode="${currency }" maxFractionDigits="2"></fmt:formatNumber>
						</b>
				</a> 
			</c:forEach>
		</ss:categoryProductNames>
	
	</div>
</div>