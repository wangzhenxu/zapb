<!-- 左侧 开始 -->
<div class="w_224">
  <div class="menu" id="a1b2">
  
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
  var left=false;//左侧导航栏生成标志，true为生成完成
  $(function(){
     $.getJSON("${leftJson!''}?date="+new Date().getTime(), function(data) {
      var b=""; 
       for(i=0;i<data.length;i++) {
              var dd = data[i].staticUrl==null || data[i].staticUrl=="null" ? "javascript:void(0)" : data[i].staticUrl;
          b=b+"<dl id="+i+" onclick='track(this)'><dt><a href='"+dd+"'>"+data[i].name+"</a></dt>";
         for(j=0;j<data[i].cmsSolutionList.length;j++) 
         {
          var c='<dd id='+j+'><a href="'+data[i].cmsSolutionList[j].staticUrl+'">'+data[i].cmsSolutionList[j].name+'</a></dd>';
           b=b+c;
        }
         b=b+"</dl>";
       }
       
       $("#a1b2").html('<div class="t">系统应用</div>'+b)
       left=true;//导航栏生成完毕
    });
        set(0,0);
		fir_set();//设置导航栏

  }) 
function fir_set(){
	var text=$(".location").find("strong").text();//找到本页关键词
	if(left){//如果左侧导航栏生成完成
		
		$(".menu dl dt a").each(function(i,v){//轮询导航栏一级菜单
				var temp=$(this).text();
				if(temp==text){//如果吻合
					$(".menu dl").removeClass("show");
					$(".menu dd").removeClass("show");//移除所有show，关闭所有菜单
					$(this).parent().parent().addClass("show");//设置show
				};
		});
		$(".menu dd a").each(function(i,v){//轮询导航栏二级菜单
				var temp=$(this).text();
				if(temp==text){//如果吻合
					$(".menu dl").removeClass("show");
					$(".menu dd").removeClass("show");//移除所有show，关闭所有菜单
					$(this).parent().addClass("show");
					$(this).parent().parent().addClass("show");//设置一、二级菜单show
				};
		});
	}else{
			setTimeout("fir_set()",1);//如果左侧导航栏未完成，1ms后重试
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
