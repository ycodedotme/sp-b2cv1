<%@include file="../include.jsp" %>

<div class="index_bigad" id="index_bigad">
<c:if test='${isHome == "true"}'>
		<div class="measure_dashboard">
		<div class="measure_dashboard_content">
			<div class="measure_panel" style="display: none;">
				<a href="http://www.honeybuy.com/c/Suits"><img width="972px" height="428px" src="/image/homebanner8.jpg" alt="Suits" title="Suits"></a>
			</div>
			<div class="measure_panel" style="position:relative">
				<a href="http://www.honeybuy.com/c/Tattoos"><img alt="Tattoos" title="Tattoos" width="972px" height="428px" src="/image/homebanner4.jpg"></a>
			</div>
			<div class="measure_panel">
				<a href="http://www.honeybuy.com/c/Shoes"><img alt="Shoes" title="Shoes"  width="972px" height="428px" src="/image/homebanner2.jpg"></a>
			</div>
			<div class="measure_panel">
				<a href="http://www.honeybuy.com/c/Suits"><img alt="Suits" title="Suits"  width="972px" height="428px" src="/image/homebanner3.jpg"></a>
			</div>
			<div class="measure_panel">
				<a href="http://www.honeybuy.com/c/Sport-Shoes"><img alt="Sport-Shoes" title="Sport-Shoes"  width="972px" height="428px" src="/image/homebanner7.jpg"></a>
			</div>
			<div class="measure_panel">
				<a href="http://www.honeybuy.com/c/Cheap-Tie-In-Stock"><img alt="Cheap ties in Stock" title="Cheap ties in Stock"  width="972px" height="428px" src="/image/ties-banner.jpg"></a>
			</div>
<p class="clearBoth"></p>
		</div>
		<div class="measure_navigation">
			<div class="measure_controller">
	            <img class="pagination_active_m" src="/style/image/1x1.png" title="1">
				<img class="pagination_m" src="/style/image/1x1.png" title="2">
				<img class="pagination_m" src="/style/image/1x1.png" title="3">
				<img class="pagination_m" src="/style/image/1x1.png" title="4">
				<img class="pagination_m" src="/style/image/1x1.png" title="5">
				<img class="pagination_m" src="/style/image/1x1.png" title="6">
			</div>
		</div>
	</div>
	<script>
	var slider = jq(".measure_dashboard").slider().cycle();
</script>
</c:if>
<c:if test='${isHome != "true"}'>
		<c:out escapeXml="false" value="${pageForm.category.marketContent}"></c:out>
</c:if>
</div>