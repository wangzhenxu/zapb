//侧边导航栏高亮
$.fn.highLight = function (obj,abbr) {
	var temp=window.location.href; 
	var links=$("."+obj+" li a");
	for (var i = 0; i < links.length; i++) {
		links[i].className = "";
		if(temp.indexOf(links[i])!=-1){
			 links[i].className = abbr;
		}else if(temp.indexOf("toadd.action")!=-1||temp.indexOf("toupdate.action")!=-1||temp.indexOf("check.action")!=-1){
			 links[1].className = abbr;
		}else if(temp.indexOf("userView.action")!=-1){
			 links[3].className = abbr;
		};
	}
}