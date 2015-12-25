<!-- 左侧 开始 -->
<div class="w_224">
  <div class="menu" id="1ab2">
  
  </div>
  <div><img src="http://www.loiot.com/images/1680_5.png" usemap="#QQmap" /></div>
  <map name="QQmap">
    <area shape="rect" coords="69,202,144,224" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2209660857&site=qq&menu=yes" />
    <area shape="rect" coords="69,176,144,198" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2278974223&site=qq&menu=yes" />
    <area shape="rect" coords="69,150,144,172" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1938851450&site=qq&menu=yes" />
    <area shape="rect" coords="68,125,143,146" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1975496669&site=qq&menu=yes" />
  </map>
</div>
<!-- 左侧 结束 -->
<script>
  var left=false;
	$(function(){
	    	$.getJSON("${leftJson!''}?date="+new Date().getTime(), function(data) {
			var b=""; 
		   for(i=0;i<data.length;i++) {
		          
				  b=b+"<dl id="+i+" onclick='track(this)'><dt><a href='javascript:void(0)'>"+data[i].name+"</a></dt>";
				 for(j=0;j<data[i].appList.length;j++) 
				 {
					var c='<dd id='+j+'><a href="'+data[i].appList[j].staticUrl+'">'+data[i].appList[j].name+'</a></dd>';
					 b=b+c;
				}
				 b=b+"</dl>";
			 }
		   
		   $("#1ab2").html('<div class="t">产品库</div>'+b);
       left=true;
		});
		set(0,0);
		fir_set();
    
	}) 
function fir_set(){
	var text=$(".location").find("strong").text();
	if(left){
		
		$(".menu dl dt a").each(function(i,v){
				var temp=$(this).text();
				if(temp==text){
					$(".menu dl").removeClass("show");
					$(".menu dd").removeClass("show");//移除所有show，关闭所有菜单
					$(this).parent().parent().addClass("show");
				};
		});
		$(".menu dd a").each(function(i,v){
				var temp=$(this).text();
				if(temp==text){
					$(".menu dl").removeClass("show");
					$(".menu dd").removeClass("show");//移除所有show，关闭所有菜单
					$(this).parent().addClass("show");
					$(this).parent().parent().addClass("show");
				};
		});
	}else{
			setTimeout("fir_set()",1);
	}
}
function set(i,j){
    if(left){
      $(".menu dl").removeClass("show");
      $(".menu dd").removeClass("show");//移除所有show，关闭所有菜单
      $(".menu dl").eq(i).addClass("show");
      $(".menu dd").eq(j).addClass("show");//添加指定一二级菜单show，开启指定菜单
    }else{
      setTimeout("set(0,0)",1);//第一次进入等待左侧导航栏生成完毕
    }
}
function track(t){
		  var dl=$(t).attr("id");
		  set(dl,null);
}
</script>