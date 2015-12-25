
var samllPicbool=false;
var bigPicbool=false;
function onUploadImgChange(sender){ 
	document.getElementsByName('bpicPath')[0].value=sender.value
	bigPicbool=false;
 	var objPreview = document.getElementById( 'preview' );   
	var objPreviewFake = document.getElementById( 'preview_fake' );   
	var objPreviewSizeFake = document.getElementById( 'preview_size_fake' );  
    if( !sender.value.match( /.jpg|.gif|.png|.bmp/i ) ){   
        alert('图片格式无效！');   
        return false;   
    }   
       
    if( sender.files && sender.files[0] ){   
        objPreview.style.display = 'block';   
        objPreview.style.width = '128px';   
        objPreview.style.height = '128px';   
           
        // Firefox 因安全性问题已无法直接通过 input[file].value 获取完整的文件路径   
        objPreview.src = window.URL.createObjectURL( sender.files[0]);       
    }else if( objPreviewFake.filters ){    
        // IE7,IE8 在设置本地图片地址为 img.src 时出现莫名其妙的后果   
        //（相同环境有时能显示，有时不显示），因此只能用滤镜来解决   
           
        // IE7, IE8因安全性问题已无法直接通过 input[file].value 获取完整的文件路径   
        sender.select();   
        var imgSrc = document.selection.createRange().text;   
        objPreviewFake.filters.item(   
            'DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;   
        objPreviewSizeFake.filters.item(   
            'DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;   
        autoSizePreview( objPreviewFake, 128, 128 );  
        objPreview.style.display = 'none'; 
    }   
    
    var oldForm=$(sender).closest("form");
	   $.ajaxFileUpload({
		url:'/app/checkBigImage.action', //需要链接到服务器地址
		type:"POST",
		async:false,
		secureuri:false,
	 	fileElementId:'bpic', //文件选择框的id属性
	 	oldForm:oldForm,// 原formID
	 	dataType:'text', //服务器返回的格式，可以是json
		success:function(data,status){ //相当于java中try语句块的用法 data是从服务器返回来的值 
		var jsondata=T.json.parse(data);
		var st=jsondata.s;
		var sd=jsondata.d;
			if(st==1){
				bigPicbool=true;
				$(".bpicformError").remove();
			}
			else{
				bigPicbool=false;
				$("#bpic").validationEngine("showPrompt"," 请上传大小在1M以内的图片","error");//弹出提示
			}
	 	},
		error:function(data,status){ //相当于java中catch语句块的用法
			var jsondata=T.json.parse(data);
			var st=data.s;
			var sd=data.d;
			if(st==-100){
				bigPicbool=false;
				$("#bpic").validationEngine("showPrompt","  请上传大小在1M以内的图片","error");//弹出提示
			}
		}
	 });
    
}   

function onPreviewLoad(sender){ 
    autoSizePreview( sender, sender.offsetWidth, sender.offsetHeight );   
}   

function autoSizePreview( objPre, originalWidth, originalHeight ){   
    var zoomParam = clacImgZoomParam( 300, 300, originalWidth, originalHeight );   
    objPre.style.width = zoomParam.width + 'px';   
    objPre.style.height = zoomParam.height + 'px';
}   

function clacImgZoomParam( maxWidth, maxHeight, width, height ){   
    var param = { width:width, height:height, top:0, left:0 };   
       
    if( width>maxWidth || height>maxHeight ){   
        rateWidth = width / maxWidth;   
        rateHeight = height / maxHeight;   
           
        if( rateWidth > rateHeight ){   
            param.width = maxWidth;   
            param.height = height / rateWidth;   
        }else{   
            param.width = width / rateHeight;   
            param.height = maxHeight;   
        }   
    }   
       
    param.left = (maxWidth - param.width) / 2;   
    param.top = (maxHeight - param.height) / 2;   
       
    return param;   
}   






function onUploadImgChange1(sender){ 
	document.getElementsByName('spicPath')[0].value=sender.value
	samllPicbool=false;
 	var objPreview = document.getElementById( 'preview1' );   
	var objPreviewFake = document.getElementById( 'preview_fake1' );   
	var objPreviewSizeFake = document.getElementById( 'preview_size_fake1' );  
    if( !sender.value.match( /.jpg|.gif|.png|.bmp/i ) ){   
        alert('图片格式无效！');   
        return false;   
    }   
       
    if( sender.files && sender.files[0] ){   
        objPreview.style.display = 'block';   
        objPreview.style.width = '64px';   
        objPreview.style.height = '64px';   
           
        // Firefox 因安全性问题已无法直接通过 input[file].value 获取完整的文件路径   
        objPreview.src = window.URL.createObjectURL( sender.files[0]);       
    }else if( objPreviewFake.filters ){    
        // IE7,IE8 在设置本地图片地址为 img.src 时出现莫名其妙的后果   
        //（相同环境有时能显示，有时不显示），因此只能用滤镜来解决   
           
        // IE7, IE8因安全性问题已无法直接通过 input[file].value 获取完整的文件路径   
        sender.select();   
        var imgSrc = document.selection.createRange().text;   
        objPreviewFake.filters.item(   
            'DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;   
        objPreviewSizeFake.filters.item(   
            'DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;   
        autoSizePreview( objPreviewFake, 64, 64 );  
        objPreview.style.display = 'none'; 
    }   
    var oldForm=$(sender).closest("form");
	   $.ajaxFileUpload({
		url:'/app/checkSmallImage.action', //需要链接到服务器地址
		type:"POST",
		async:false,
		secureuri:false,
	 	fileElementId:'spic', //文件选择框的id属性
	    oldForm:oldForm,// 原formID
	 	dataType:'text', //服务器返回的格式，可以是json
		success:function(data,status){ //相当于java中try语句块的用法 data是从服务器返回来的值 
		var jsondata=T.json.parse(data);
		var st=jsondata.s;
		var sd=jsondata.d;
			if(st==1){
				samllPicbool=true;
				$(".spicformError").remove();
			}
			else{
				samllPicbool=false;
			   	$("#spic").validationEngine("showPrompt"," 请上传大小在1M以内的图片","error");//弹出提示
			}
	 	},
		error:function(data,status){ //相当于java中catch语句块的用法
			var jsondata=T.json.parse(data);
			var st=jsondata.s;
			var sd=jsondata.d;
			if(st==-100){
				samllPicbool=false;
				$("#spic").validationEngine("showPrompt","  请上传大小在1M以内的图片","error");//弹出提示
			}
		}
	 });
    
}   

function onPreviewLoad1(sender){ 
    autoSizePreview( sender, sender.offsetWidth, sender.offsetHeight );   
}   

function autoSizePreview( objPre, originalWidth, originalHeight ){   
    var zoomParam = clacImgZoomParam( 300, 300, originalWidth, originalHeight );   
    objPre.style.width = zoomParam.width + 'px';   
    objPre.style.height = zoomParam.height + 'px';
}   

function clacImgZoomParam( maxWidth, maxHeight, width, height ){   
    var param = { width:width, height:height, top:0, left:0 };   
       
    if( width>maxWidth || height>maxHeight ){   
        rateWidth = width / maxWidth;   
        rateHeight = height / maxHeight;   
           
        if( rateWidth > rateHeight ){   
            param.width = maxWidth;   
            param.height = height / rateWidth;   
        }else{   
            param.width = width / rateHeight;   
            param.height = maxHeight;   
        }   
    }   
       
    param.left = (maxWidth - param.width) / 2;   
    param.top = (maxHeight - param.height) / 2;   
       
    return param;   
}   