<#include "../include/comm_jlb_macro.ftl"/>
<@gmc_common_js "add" />
<#include "../include/dropzone.ftl"/>
<link href="/css/product.css" rel="stylesheet" type="text/css" />
<form id="addform" name="form"  method="post">
  <input type="hidden" name="id" id="id"  value="${pid!''}"  />
  <input type="hidden" name="onlyName" id="onlyName" />
  <!-- 右侧 开始 -->
  <div class="right" data-name="tgProduct_add">
    <div class="location">
      <div class="location01">您现在的位置是：首页 &gt; ${moduleName!''} &gt;<strong class="m_title" ></strong></div>
    </div>
    <div class="nav">
      <div class="basic">
        <div class="basic01 m_title" >添加</div>
      </div>
      <div class="query1">
        <table width="100%" border="0" align="left">
          <tr class="add_item">
            <td colspan="4" class="red">* 号为必填项</td>
          </tr>
          <tr class="add_item">
            <td  align="right" class="hui1 a-title"><span class="red">*</span>分类：</td>
            <td  align="left" valign="middle">
              <select id="category" class="validate[required]" name="category">
                <option value="" > 请选择 </option>
                <#list DictionaryUtil.getTypes(DictionaryType.PRODUCT_CATEGORY.getCode()) as c>
                <option value="${c.dictionaryId}"> ${c.showName!''} </option>
                </#list>
              </select>
            </td>
          </tr>
          <tr class="add_item">
            <td  align="right" class="hui1 a-title"><span class="red">*</span>商品名称：</td>
            <td  align="left" valign="middle">
              <input name="name" id="name" type="text" class="input validate[required]">
            </td>
          </tr>
          <tr class="add_item">
            <td  align="right" class="hui1 a-title"><span class="red">*</span>商品最高价：</td>
            <td  align="left" valign="middle">
              <input name="price" id="price" type="text" class="input validate[required,custom[number]]">
            </td>
          </tr>
          <tr class="add_item">
            <td  align="right" class="hui1 a-title"><span class="red">*</span>单位：</td>
            <td  align="left" valign="middle">
              <input name="unit" id="unit" type="text" class="input validate[required]">
            </td>
          </tr>
          <tr class="add_item">
            <td  align="right" class="hui1 a-title">来源：</td>
            <td  align="left" valign="middle">
              <select id="origin" name="origin">
                <option value="" > 请选择 </option>
                <#list DictionaryUtil.getTypes(DictionaryType.PRODUCT_ORIGIN.getCode()) as c>
                <option value="${c.dictionaryId}"> ${c.showName!''} </option>
                </#list>
              </select>
            </td>
          </tr>
          <tr class="add_item">
            <td  align="right" class="hui1 a-title">商品链接：</td>
            <td  align="left" valign="middle">
              <input name="originUrl" id="originUrl" type="text" class="input" style=" width: 90%;">
            </td>
          </tr>
          <tr class="add_item">
            <td  align="right" class="hui1 a-title">备注：</td>
            <td  align="left" valign="middle">
              <textarea cols="45" rows="5" class="input text-input mokuainr ckeditor" name="remark"  id="remark" ></textarea>
            </td>
          </tr>
          <tr class="add_item">
          <td  align="right" class="hui1 a-title"><span class="red">*</span>是否上架：</td>
          <td  align="left" valign="middle">
            <select id="isDelete" class="validate[required]" name="isDelete">
              <option value="" > 请选择 </option>
              <option value="0"> 待上架 </option>
              <option value="1"> 已上架 </option>
              <option value="2"> 已下架 </option>
            </select>
          </td>
          </tr>
          <tr class="add_item">
          <td  align="right" class="hui1 a-title">商品实物图：</td>
          <td  align="left" valign="middle">
          <div class="picture-container">
            <!-- 图片上传 -->
            <div class="dropzone needsclick dz-clickable dz-started">
              
            </div>
            <!-- /图片上传 -->
          </div>
          </td>
          </tr>
        </table>
      </div>
      <div class="info" style="display: none;">
        <div class="item">
          <span class="">录入人：</span><span name="inPersonName" id="inPersonName"></span>
        </div>
        <div class="item">
          <span class="">录入时间：</span><span name="inTime" id="inTime"></span>
        </div>
        <div class="item">
          <span class="">更新人：</span><span name="updatePersonName" id="updatePersonName"></span>
        </div>
        <div class="item">
          <span class="">更新时间：</span><span name="udpateTime" id="udpateTime"></span>
        </div>
      </div>
    </div>
    <div class="anniu">
      <div class="btn-group">
        <button type="button" class="btn btn-default" id="addBtn" >保 &nbsp;存</button>
        <button type="button" class="btn btn-default" onclick="product.tolist();">返&nbsp; 回</button>
      </div>
    </div>
  </div>  
  <!-- 右侧 结束 -->
</form>
<#include "../include/deleteConfirmModal.ftl">
<div id="preview-template" style="display: none;">
  <div class="dz-preview dz-file-preview">
    <div class="dz-image"><img data-dz-thumbnail=""></div>
    <div class="dz-progress"><span class="dz-upload" data-dz-uploadprogress=""></span></div>
  </div>
</div>
<script src="/js/product.js"></script>
<script>
  product.initPage();
</script>
<!-- 图片上传 -->
<style type="text/css">
.dropzone { border: 2px dashed #0087F7; border-radius: 5px; background: white; }
.dropzone .dz-message { font-weight: 400; }
.dropzone .dz-message .note { font-size: 0.8em; font-weight: 200; display: block; margin-top: 1.4rem; }
.dropzone {display: block; clear: both; height: 200px; width: 95%; margin-left: 5px;}
.dropzone .dz-preview{float: left; margin-right: 10px;}
</style>
<script>
  Dropzone.autoDiscover = false;
  Dropzone.options.myAwesomeDropzone = false;
  
  var myDropzone = new Dropzone("div.dropzone", {
    url: "/file/upload2.action",
    previewTemplate: document.querySelector('#preview-template').innerHTML,
    parallelUploads: 2,
    thumbnailHeight: 120,
    thumbnailWidth: 120,
    maxFilesize: 10,
    filesizeBase: 1000,
    acceptedFiles:"image/*",
    dictDefaultMessage: "上传文件!",
    dictRemoveFile: true,
    thumbnail: function(file, dataUrl) {
      if (file.previewElement) {
        file.previewElement.classList.remove("dz-file-preview");
        var images = file.previewElement.querySelectorAll("[data-dz-thumbnail]");
        for (var i = 0; i < images.length; i++) {
          var thumbnailElement = images[i];
          thumbnailElement.alt = file.name;
          thumbnailElement.src = dataUrl;
        }
        setTimeout(function() { file.previewElement.classList.add("dz-image-preview"); }, 1);
      }
    }
  });
  console.log(myDropzone);
  
  myDropzone.on("addedfile", function(file) {
    console.log(myDropzone.getAcceptedFiles());
  });
  
  myDropzone.on("maxfilesreached", function(file) {
    alert("选择文件太大，不支持上传，换一个小点的文件试试!");
    
  });
  
  myDropzone.on("sending", function(file, xhr, formData) {
    // Will send the filesize along with the file as POST data.
    //formData.append("filesize", file.size);
  });
  
  myDropzone.on("canceled", function(file, response) {
    console.log("canceled");
    
    //取消上传
  });
  
  myDropzone.on("error", function(file) {
    myDropzone.removeFile(file);
    
    //上传失败，可以给用户提示上传失败请从试
  });
  
  myDropzone.on("success", function(file, response) {
    //上传成功，设置服务器返回的信息
    file.serverName = response.fileName;
    
    console.log(getAllFiles());
  });
  
  myDropzone.on("complete", function(file) {
    console.log("complete");
    //可以进行清理工作,比如隐藏等待框什么的。。
  });
  
  //最后提交表单时，获取所有上传的图片列表
  function getAllFiles(){
    var files = myDropzone.getAcceptedFiles();
    var fileNames = new Array();
    for(var i=0,j=files.length; i<j; i++)
      fileNames.push(files[i].serverName);
    return fileNames;
  }
</script>
<!-- /图片上传 -->
