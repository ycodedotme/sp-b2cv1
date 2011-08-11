<%@ include file="../include.jsp" %>

<div class="item_goods_info_box_top">
	<div class="item_box_left_normal">
		<!-- class="item_box_left" to high -->
		<div class="no_float">
	
			<!-- normal picture -->
			<div class="item_normal_pic" id="item_normal_pic">
				<div class="item_normal_pic_box">
					<div></div>
					<c:forEach items="${pageForm.pageProperties.productDetail.images}" var="image" varStatus="idx" step="1">
					<c:if test="${idx.index eq 0}" >
					<a class="bighref" id="linkNormalBox" href="javascript:void(0);"
						rel="thing_item_pics"> <img
						src="http://www.mlo.me/image/endefault/thing_item/zoom_in.png"
						id="zoomIcon"> <img width="277"
						val="${image.largerUrl}"
						alt="${pageForm.pageProperties.productDetail.title}"
						src="${image.largerUrl}"
						id="imageNormalBox"> </a>
						</div>
				<div style="display: none">
					</c:if>
					<c:if test="${idx.index gt 0}" >
					<a href="${image.largerUrl}" class="noneBox"></a> 
					</c:if>
				</div>
				</c:forEach>
				<!--<div class="item_normal_zoom"> <a href="###" class="link_pic_zoom bighref" target="_blank">Enlarge the Image</a> </div>-->
				<div class="item_normal_socllbar">
					<ul>
						<c:forEach items="${pageForm.pageProperties.productDetail.images}" var="image" varStatus="idx" step="1">
							<li dis="${idx.index}" class="smallPic"
								url="http://www.mlo.me/thing/StyleZoom-id-32534-ProductsPicture-0.html"
								val="${image.smallUrl}">
								<img
								alt="${pageForm.pageProperties.productDetail.title}"
								src="${image.smallUrl}">
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>


			<!-- normal picture -->
		</div>
	</div>
	<!-- shopping function -->
	<form onsubmit="return formsubmit();" name="cusform" method="post"
		action="https://www.milanoo.com/shop/Cart.html">
		<div class=" item_shopping_fun">
			<div class="noFlow">
				<h1><c:out value="${pageForm.pageProperties.productDetail.title}" /></h1>
			</div>
			<div class="item_showWords"></div>
			<div class="item_shopping_code">Item Code:#08100032534</div>
			<div style="position: relative;" class="item_shopping_funbox">
				<div class="list_stars">
					<div title="5 out of 5 stars" class="starsSmall_box">
						<div style="width: 100%;"></div>
					</div>
					<span class="item_reviews_link"><a
						href="javascript:jq.goDiv('#pl');">4 customer reviews</a>
					</span>
				</div>
				<table>
					<tbody>
						<c:forEach items="${pageForm.pageProperties.productDetail.properties}" var="property" varStatus="idx" step="1">
							<tr>
								<td><c:out value="${property.name}" /> :</td>
								<td><c:out value="${property.value}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="itme_description"><c:out value="${pageForm.pageProperties.productDetail.abstractText}" /></div>
				<c:out value="${pageForm.pageProperties.productDetail.detail}" />
			</div>
		</div>
		<!--弹出custom-->
		<div style="display: none;" id="Customfloat" class="alert_thing_box">
			<script type="text/javascript">
            //&lt;![CDATA[
            /****** 定制尺寸js********/
            function changeunit(value){	if(value=='in')value='inch';for(var i=1;i&lt;customnum;i++){if(i==2&amp;&amp;value=="cm"&amp;&amp; ($('unit'+i).innerHTML=="kg" || $('unit'+i).innerHTML=='lb')){	$('unit'+i).innerHTML='kg';	}else if(i==2&amp;&amp;value=="inch"&amp;&amp;( $('unit'+i).innerHTML=="kg" || $('unit'+i).innerHTML=='lb')){$('unit'+i).innerHTML='lb';}else{	$('unit'+i).innerHTML=value;	}}}
            //]]&gt;
            </script>
			<div class="alert_thing_bg">
				<div class="alert_thing_top">
					<h5>Custom Options</h5>
					<div
						style="FONT-WEIGHT: bold; FONT-SIZE: 12px; LEFT: 226px; COLOR: #9e5201; POSITION: absolute; TOP: 48px"></div>
					<div class="menu_box">
						<a
							onclick="$('custom2').style.display='block';$('custom1').style.display='none';$('customcss1').className='';this.className='link_now';"
							id="customcss2" href="javascript:void(0);"><span
							id="customade"><b>Made to Measurements</b>
						</span>
						</a>
					</div>
					<div class="chose_size"></div>
					<div class="Price_box">
						US$ <span id="AmountPrice2">209.99</span>
					</div>
				</div>
				<div class="alert_thing_main">
					<div style="display: none;" id="custom1"></div>
					<script language="javascript">
var customnum = 8;
</script>
					<div id="custom2">
						<div class="left_img">
							<img width="163" height="370"
								src="http://www.mlo.me/image/endefault/mobantupian/hunsha/wedding_size5.jpg">
						</div>
						<div class="right_box">
							<table>
								<tbody>
									<tr>
										<td align="right" colspan="2">Please select:<select
											onchange="changeunit(this.value);" name="units"><option
													value="in">in</option>
												<option value="cm">cm</option>
										</select>
										</td>
									</tr>
									<tr>
										<td>A Bust Size:</td>
										<td><input type="text" size="8" name="Customszie[bust]"
											id="Customszie1"><span id="unit1">inch</span><span
											style="display: none;" id="cussize1"><img
												src="http://www.mlo.me/image/endefault/check_error.gif">
										</span>
										</td>
									</tr>
									<tr>
										<td>B Waist Size:</td>
										<td><input type="text" size="8" name="Customszie[waist]"
											id="Customszie2"><span id="unit2">inch</span><span
											style="display: none;" id="cussize2"><img
												src="http://www.mlo.me/image/endefault/check_error.gif">
										</span>
										</td>
									</tr>
									<tr>
										<td>C Hip Size:</td>
										<td><input type="text" size="8" name="Customszie[hip]"
											id="Customszie3"><span id="unit3">inch</span><span
											style="display: none;" id="cussize3"><img
												src="http://www.mlo.me/image/endefault/check_error.gif">
										</span>
										</td>
									</tr>
									<tr>
										<td>D Shoulder Width:</td>
										<td><input type="text" size="8"
											name="Customszie[shoulder]" id="Customszie4"><span
											id="unit4">inch</span><span style="display: none;"
											id="cussize4"><img
												src="http://www.mlo.me/image/endefault/check_error.gif">
										</span>
										</td>
									</tr>
									<tr>
										<td>E Hollow to hem(for short length dress):</td>
										<td><input type="text" size="8"
											name="Customszie[hellow2hem]" id="Customszie5"><span
											id="unit5">inch</span><span style="display: none;"
											id="cussize5"><img
												src="http://www.mlo.me/image/endefault/check_error.gif">
										</span>
										</td>
									</tr>
									<tr>
										<td>F Hollow to floor (for floor length dress):</td>
										<td><input type="text" size="8"
											name="Customszie[hellow2floor]" id="Customszie6"><span
											id="unit6">inch</span><span style="display: none;"
											id="cussize6"><img
												src="http://www.mlo.me/image/endefault/check_error.gif">
										</span>
										</td>
									</tr>
									<tr>
										<td>G Under Bust:</td>
										<td><input type="text" size="8"
											name="Customszie[underbust]" id="Customszie7"><span
											id="unit7">inch</span><span style="display: none;"
											id="cussize7"><img
												src="http://www.mlo.me/image/endefault/check_error.gif">
										</span>
										</td>
									</tr>
								</tbody>
							</table>
							<br>● Please provide detailed measurement information for
							customization base on "How to Measure". <br>
							<br> ● For customer who likes to choose customized service,
							the correct size measured by professional tailor with your
							underwear on is strongly recommended. <br>
							<br> ● Please measure with shoes on when you measure "Hollow
							to floor".
						</div>

					</div>
					<script language="javascript">
changeunit('in');
</script>
					<div class="hei1"></div>
					<h5>Item Remarks</h5>
					<textarea onkeyup="checkLen(this,1500)" maxlength="1500"
						style="width: 100%;" name="CustomRemarks" cols="20"></textarea>
				</div>
				<div class="alert_thing_sub">
					<input type="button" onclick="checknow();" class="submit_7"
						value="Submit" name="Submit"> <input type="reset"
						onclick="cancelCustom();" class="submit_7" value="Cancel"
						name="Cancel">

				</div>
			</div>
		</div>
		<!--custom-->
		<input type="hidden" value="yes" id="gm" name="gm">
		<div class="item_box_right">
			<div></div>
			<div class="item_property">
				<!-- item_property_show is alert-->
				<div class="item_goods_price_older">
					<s>US$ 293.99</s>
				</div>
				<div style="z-index: 2;" class="item_price">
					<span class="item_price_currency">US$ <a id="currencyAll"
						href="javascript:void(0);"><img
							src="http://www.mlo.me/image/endefault/thing_item/arrow-select.gif">
					</a>
					</span><span class="item_price_num"> <span id="money">209.99</span>
					</span>
					<div class="currencyAll">

						<a rel="nofollow" class="link_now"
							href="http://www.milanoo.com/app/currency.php?currency=EUR">
							€ </a> <a rel="nofollow" class="link_now"
							href="http://www.milanoo.com/app/currency.php?currency=JPY">
							¥ </a> <a rel="nofollow" class="link_now"
							href="http://www.milanoo.com/app/currency.php?currency=GBP">
							£ </a> <a rel="nofollow" class="link_now"
							href="http://www.milanoo.com/app/currency.php?currency=CAD">
							CA$ </a> <a rel="nofollow" class="link_now"
							href="http://www.milanoo.com/app/currency.php?currency=AUD">
							AU$ </a> <a rel="nofollow" class="link_now"
							href="http://www.milanoo.com/app/currency.php?currency=CHF">
							CHF </a> <a rel="nofollow" class="link_now"
							href="http://www.milanoo.com/app/currency.php?currency=HKD">
							HK$ </a> <a rel="nofollow" class="link_now"
							href="http://www.milanoo.com/app/currency.php?currency=RUB">
							руб. </a> <a rel="nofollow" class="link_now"
							href="http://www.milanoo.com/app/currency.php?currency=MXN">
							$MXN </a>


					</div>
				</div>
				<c:forEach items="${pageForm.pageProperties.productDetail.properties}" var="property" varStatus="idx" step="1">
					<c:if test='${property.strSelectType eq "INPUT_TEXT"}'>
						<div class="noFlow">
							<c:out value="${property.name}" />: <input type="text" name="<c:out value="${property.name}" />" id="<c:out value="${property.id}" />" value="<c:out value="${property.defaultValue}" />" size="5"
								maxlength="4" class="input_1"
								onblur="javascript:if(!Boolean(this.value))  this.value=1;if(parseInt(this.value)===0)this.value=1;this.value=parseInt(this.value,10);if(this.value&gt;9999)this.value=9999;"
								onkeyup="value=value.replace(/[^\d]/g,'');ChangePrice();">
							<div class="item_funTotal" href="javascript:void(0);">
								Total: <span>US$ <span id="AmountPrice3">209.99</span>
								</span>
							</div>
						</div>
					</c:if>
					<c:if test='${property.strSelectType=="SINGLE_LIST"}'>
						<div class="item_sizeBox">
							<div class="item_ProBox_title">
								<span><c:out value="${property.name}" />:</span><a onclick="tab_click(2);"
									href="javascript:jq.goDiv('#tab_middle');"
									class="item_funLink size_chart">Size Chart</a>
							</div>
							<select name="CustomAttributes_array[199]" id="Size0">
								<option	value="please">Please select</option>
								<c:forEach items="${property.items}" var="item" varStatus="indx" step="1">
									<option value="${item.value}">${item.displayName}</option>
								</c:forEach>
							</select>
						</div>
					</c:if>
					<c:if test='${property.strSelectType=="COLOR_SINGLE"}'>
						<div class="item_sizeBox">
							<div class="item_ProBox_title">
								<span><c:out value="${property.name}" />:</span><a onclick="tab_click(2);"
									href="javascript:jq.goDiv('#tab_middle');"
									class="item_funLink size_chart">Size Chart</a>
							</div>
							<select name="CustomAttributes_array[199]" id="Size0" MULTIPLE>
								<option	value="please">Please select</option>
								<c:forEach items="${property.items}" var="item" varStatus="indx" step="1">
									<option value="${item.value}">${item.displayName}</option>
								</c:forEach>
							</select>
						</div>
					</c:if>
					<c:if test='${property.strSelectType eq "MULTI_LIST"}'>
						<div class="item_colorBox">
							<div class="item_ProBox_title">
								<span><c:out value="${property.name}" />:</span><a onclick="tab_click(3);"
									href="javascript:jq.goDiv('#tab_middle');"
									class="item_funLink color_chart">Color Chart</a>
							</div>
							<input type="hidden" name="CustomAttributes_array[207]/"
								id="weddingdresscolor1" value="color1"><a
								dataname="weddingdresscolor1" data="color1"
								href="javascript:void(0);" title="Refer to the image "
								class="colorLink" style="border: 1px solid rgb(139, 33, 4);"><div
									class="abPosition selectImg"></div>
								<div class="select_refertotheimage"></div>
							</a>
						</div>
					</c:if>
					<input type="hidden" value="${pageForm.pageProperties.productDetail.productId}" name="ProductId">
				</c:forEach>
				<!--musictagstock start-->
				<div style="color: #F33">
					<i id="StocksInfo"></i>
				</div>
				<!--musictagstock end-->

				<p class="noFlow">
					<a onclick="tab_click(4)" class="item_funHelp"
						href="javascript:jq.goDiv('#tab_middle');">Delivery</a>
				</p>
			</div>
			<script>
	jq("select").change( function() {
			chooseSize();
			CheckStock(jq(this).val());
			
			if(jq(this).val()=='custom'){
				iscustom="custom";
				jq(this).data("custom","yes");
				jq("#any").tanCeng('click','absolute','#Customfloat',1);
				if($('customcss1')){
					$('customcss1').className='';
					$('custom1').style.display='none';
				}
				$('customcss2').className='link_now';				
				$('custom2').style.display='block';
				$('customade').style.display='block';									
			}else{
				if(jq(this).data("custom")=="yes"){
						jq(this).data("custom","no");
						iscustom="";
				}
			}
			ChangePrice();	
	});
	jq(".colorLink").click(function(){
			jq("#"+jq(this).attr('dataName')).val(jq(this).attr('data'));
			var dataName = jq(this).attr('dataName');

			
			jq(".colorLink[dataName='"+dataName+"']").css("border","1px solid #717171");
			jq(".colorLink[dataName='"+dataName+"'] div").removeClass('selectImg');
			jq(this).find("div:first").addClass('selectImg');
			jq(this).css("border","1px solid #8b2104"); 
	 	 	chooseSize();
	});
	
	jq(".item_colorBox").each(function(){ if(jq(this).find(".colorLink").size()==1){jq(this).find(".colorLink:first").click();}}); 
	</script>
			<ul style="display: block;" id="choosePro" class="choosePro">
				<li>Dress Color:Refer to the image</li>
			</ul>
			<div class="addtocart">
				<input type="submit" style="display: none;" id="sub"
					value="ADD TO MY BAG" class="item_addBag"> <input
					type="button" id="nosub" value="ADD TO MY BAG" class="item_addBag">
				<div class="sub_outDiv_normal" style="display: none;"
					id="sub_outDiv">
					<div id="notselect_tips" class="notselect_tips">
						<div class="tips_title">PLEASE SELECT</div>
						<ul id="nochoose">
							<li>Size</li>
						</ul>
					</div>
					<div style="" id="notselect_tips1" class="tip_arrow">&nbsp;</div>
				</div>
			</div>
			<a onclick="favorite('32534')" id="favorite_32534"
				class="item_potionsFavorite" href="javascript:void(0);">Add to
				my Favorite </a>

			<div class="item_funWords">
				<div id="favorite" style="display: none;" class="details_l"></div>
			</div>
			<div class="verisign_paypal">

				<span class="paypal"><a href="javascript:void(0)"
					title="paypal" rel="nofollow"
					onclick="javascript:window.open('https://www.paypal.com/verified/pal=paypal@milanoo.com','olcwhatispaypal','toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=400, height=350');"><img
						width="50" alt="paypal"
						src="http://www.mlo.me/image/endefault/paypal.verified.png">
				</a>
				</span> <span class="verisign"><a
					href="https://sealinfo.verisign.com/splash?form_file=fdf/splash.fdf&amp;dn=www.milanoo.com&amp;lang=en"
					title="Verisign Secured" target="_blank" rel="nofollow"><img
						width="90" src="http://www.mlo.me/image/endefault/vers.jpg"
						alt="Verisign Secured">
				</a>
				</span>

			</div>
		</div>
	</form>
</div>