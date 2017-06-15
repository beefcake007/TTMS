<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<title>sale</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/seat-charts/css/jquery.seat-charts.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/seat-charts/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css ">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.css">
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<style type="text/css">
.row{
	margin-top:50px;
}
.front{
	width: 650px;
	height:30px;
	margin: 5px 32px 45px 32px;
	background-color: #f0f0f0;
	color: #666;
	text-align: center;
	padding: 3px;
	border-radius: 5px;
} 
.booking-details {
	margin:50px;
	float: left;
	position: relative;
	width:200px;
	height: 450px;
} 
.booking-details h3 {
	margin: 5px 5px 0 0;
	font-size: 16px;
} 
.booking-details p{
	line-height:26px; 
	font-size:16px; 
	color:#999;
} 
.booking-details p span{
	color:#666
} 
div.seatCharts-cell {
	color: #182C4E;
	line-height: 25px;
	width:48px;
	height:40px;
	margin: 5px;
	float: left;
	text-align: center;
	outline: none;
	font-size: 13px;
} 
div.seatCharts-seat {
	color: #fff;
	cursor: pointer;
	-webkit-border-radius:5px;
	-moz-border-radius:5px;
	border-radius: 5px;
} 
div.seatCharts-row {
	height: 50px;
	float:center;
} 
div.seatCharts-seat.available {
	background-color: #D8D8D8;
	background-image:url(${pageContext.request.contextPath}/images/座位.png);
	background-repeat:no-repeat;
	background-size:80%,80%;
	background-position:center;
} 
div.seatCharts-seat.focused {
	background-color: #E0E0E0;
	border: none;
	background-image:url(${pageContext.request.contextPath}/images/座位-选中.png);
	background-repeat:no-repeat;
	background-size:80%,80%;
	background-position:center;
} 
div.seatCharts-seat.selected {
	background-color: #E8E8E8;
	cursor: not-allowed;
	background-image:url(${pageContext.request.contextPath}/images/座位-selected.png);
	background-repeat:no-repeat;
	background-size:80%,80%;
	background-position:center;
} 
div.seatCharts-seat.unavailable {
	background-color: #E8E8E8;
	cursor: not-allowed;
	background-image:url(${pageContext.request.contextPath}/images/座位预定.png);
	background-repeat:no-repeat;
	background-size:80%,80%;
	background-position:center;
} 
div.seatCharts-container {
	width: 800px;
	padding: 20px;
	clear: both;
	display: block;
	margin:auto;
} 
div.seatCharts-legend {
	padding-left: 0px;
	position: absolute;
	bottom: 16px;
} 
ul.seatCharts-legendList {
	padding-left: 0px;
} 
.seatCharts-legendItem{
	float:left; 
	width:90px;
	margin-top: 10px;
	line-height: 2;
} 
span.seatCharts-legendDescription {
	margin-left: 5px;
	line-height: 30px;
}  
#selected-seats {
	max-height: 150px;
	overflow-y: auto;
	overflow-x: none;
	width: 200px;
} 
#selected-seats li{
	float:left; 
	width:72px; 
	height:26px; 
	line-height:26px; 
	border:1px solid #d3d3d3;
}
</style>
<script src="${pageContext.request.contextPath}/seat-charts/js/jquery-1.11.0.min.js"></script> 
<script src="${pageContext.request.contextPath}/seat-charts/js/jquery.seat-charts.min.js"></script> 
<script>
var price =${saleList.schedulePrice }; //票价 
var customerId=${currentUser.customerId};
	var x=${saleList.hallRow };
	var y=${saleList.hallColumn };
	var map = [];
    for(var i=0;i<x;i++){
        map[i]="";
        for(var j=0;j<y;j++){
            map[i]+="a";
        }
    }
var seat="";
var get=${seat};
console.log(get);
$(document).ready(function() { 
 var $cart = $('#selected-seats'), //座位区 
 $counter = $('#counter'), //票数 
 $total = $('#total'); //总计金额 
 var sc = $('#seat-map').seatCharts({ 
 //座位图 
 map:map, 
 legend : { //定义图例 
  node : $('#legend'), 
  items : [ 
  [ 'a', 'available', '可选座' ], 
  [ 'a', 'unavailable', '已售出'] 
  ]   
 }, 
 click: function () { //点击事件 
  if (this.status() == 'available') { //可选座 
  seat+=this.settings.row+1+'_'+this.settings.label+" ";
  //alert(seat);
  $('<li>'+(this.settings.row+1)+'排'+this.settings.label+'座</li>') 
   .attr('id', 'cart-item-'+this.settings.id) 
   .data('seatId', this.settings.id) 
   .appendTo($cart); 
  $counter.text(sc.find('selected').length+1); 
  $total.text(recalculateTotal(sc)+price); 
  return 'selected'; 
  } else if (this.status() == 'selected') { //已选中 
  //更新数量 
  $counter.text(sc.find('selected').length-1); 
  //更新总计 
  $total.text(recalculateTotal(sc)-price); 
  //删除已预订座位 
  $('#cart-item-'+this.settings.id).remove(); 
  //可选座 
  return 'available'; 
  } else if (this.status() == 'unavailable') { //已售出 
  	return 'unavailable'; 
  }else{ 
  	return this.style(); 
  } 
 } 
 }); 
 //已售出的座位 
 sc.get(get).status('unavailable'); 
}); 
//计算总金额 
function recalculateTotal(sc) { 
 var total = 0; 
 sc.find('selected').each(function () { 
 total += price; 
 }); 
 return total; 
}

function salefunction(){
	if(x==10&&y==10){
		$("div.seatCharts-cell").css("width","48px");
		$("div.seatCharts-cell").css("height","40px");
	}
	if(x==20&&y==20){
		$("div.seatCharts-cell").css("width","23px");
		$("div.seatCharts-cell").css("height","20px");
	}
	if(x==15&&y==15){
		$("div.seatCharts-cell").css("width","32px");
		$("div.seatCharts-cell").css("height","28px");
	}
}

function hello(){
	/*$("ul#selected-seats").each(function(){
	    var y = $(this).children().last();
	    alert(y.text());
	});*/
	//alert(seat);
	var scheduleId=${saleList.scheduleId};
	var seatBuy=seat;
	//alert(price);
	//alert(customerId);
	if(confirm("您确定要购买"+seat+"位置吗？")){
		//window.location.href="seat?action=buy&scheduleId="+${saleList.scheduleId}+"&seatBuy="+seat;
		$.post("seat?action=buy",{scheduleId:scheduleId,seatBuy:seatBuy,price:price,customerId:customerId},function(result){
			var result=eval('('+result+')');
			if(result.success){
				alert("购票成功");
				location.reload();
			}else{
				alert(result.errorMsg);
				location.reload();
			}
		});
	}else{
		return;
	}
}
</script>
</head>
<body onload="salefunction()">
<jsp:include page="/foreground/movie/header.jsp"></jsp:include>
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-md-8">
			<div id="seat-map"> 
			 	<div class="front">屏幕</div>   
			</div> 
		</div>
  		<div class="col-xs-6 col-md-4">
  			<div class="booking-details"> 
				 <p>影片：<span>${saleList.movieName}</span></p> 
				 <p>时间：<span><fmt:formatDate value="${saleList.scheduleBeginDate }" type="date" pattern="yyyy-MM-dd HH:mm"/></span></p> 
				 <p>座位：</p> 
				 <ul id="selected-seats"></ul> 
				 <p>票数：<span id="counter">0</span></p> 
				 <p>总计：<b>￥<span id="total">0</span></b></p> 
				 <button class="checkout-button btn btn-success" onclick="hello()">确定购买</button> 
				 <div id="legend"></div> 
			</div>
  		</div>
	</div>
</div>
</body>
</html>
