<html>
  <head>
  <title>网站后台管理系统-${pageTitle!''}管理</title>
  <#include "../include/comm_jlb_macro.ftl"/>
  <link href="/css/product.css" rel="stylesheet" type="text/css" />
  <@gmc_common_js "select" />
</head>
  <body>
    <div class="right" data-name="tgProduct_list">
      <div class="location">
        <div class="location01">您现在的位置是：首页 &gt; ${moduleName!''} &gt;<strong class="m_title" ></strong></div>
      </div>
      <div class="sort">
        <div class="sort1 m_title" ></div>
        <div class="query">
          <form id="queryForm" ><!--查询表单-->
            <div class="query_content">
              <div class="query_item"><span class="q-title">商品名称:</span><span><input name="name" type="text"  class="input"  id="name" value="${name!''}"/></span></div>
              <div class="query_item"><span class="q-title">所属分类:</span>
                <select id="category" name="category">
                  <option value="" > 请选择 </option>
                  <#list DictionaryUtil.getTypes(DictionaryType.PRODUCT_CATEGORY.getCode()) as c>
                  <option value="${c.dictionaryId}" <#if  category?? && category!="" > <#if category?number==c.dictionaryId> selected </#if> </#if>  > ${c.showName!''} </option>
                  </#list>
                </select>
              </div>
              <div class="query_item"><span class="q-title">价格区间:</span>
                <span>
                  <input name="start_price" type="text"   class="input input-2 "  id="start_price" value="${start_price!''}"/>-
                  <input name="end_price" type="text"   class="input input-2 "  id="end_price" value="${end_price!''}"/>
                </span>
              </div>
              <div class="query_item"><span class="q-title">商品来源:</span>
                <select id="origin" name="origin">
                  <option value="" > 请选择 </option>
                  <#list DictionaryUtil.getTypes(DictionaryType.PRODUCT_ORIGIN.getCode()) as c>
                  <option value="${c.dictionaryId}" <#if  origin?? && origin!="" > <#if origin?number==c.dictionaryId> selected </#if> </#if>  > ${c.showName!''} </option>
                  </#list>
                </select>
              </div>
              <div class="query_item"><span class="q-title">商品状态:</span>
                <select id="status" name="status">
                  <option value="" > 请选择 </option>
                  <option value="0" <#if  status?? && status!="" > <#if status?number==0> selected </#if> </#if>> 待上架 </option>
                  <option value="1" <#if  status?? && status!="" > <#if status?number==1> selected </#if> </#if> > 已上架 </option>
                  <option value="2" <#if  status?? && status!="" > <#if status?number==2> selected </#if> </#if> > 已下架 </option>
                </select>
              </div>
              <div class="query_item"><span class="q-title">排序方式:</span>
                <span>
                  录入时间: <input class="input-radio" type="radio" <#if  sort?? && sort!="" > <#if sort=="in_time"> checked="checked" </#if> <#else> checked="checked" </#if> name="sort" value="in_time" />
                  价&nbsp;&nbsp;格: <input class="input-radio" type="radio" <#if  sort?? && sort!="" > <#if sort =="price"> checked="checked" </#if> </#if> name="sort" value="price" />
                </span>
              </div>
              <div class="query_bottom"><button type="button" class="btn btn-default" id="queryBtn" >查&nbsp;询</button></div>
            </div>
          </form>
        </div>
      </div>
      <div class="form">
      <#if subject.isPermitted("tgProduct:add")>  <#--tgProduct:add  -->
        <div class="btn-group">
          <button type="button" class="btn btn-default"  onclick="product.toAdd();">添加商品</button>
        </div>
      </#if>
        <div class="form2">     
          <table width="100%"  border="1" align="left" cellpadding="0" cellspacing="0" bordercolor="#ffffff" style="border-collapse:collapse">
            <tr class="lan">
              <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>分类</strong></td>
              <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>名称</strong></td>
              <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>价格</strong></td>
              <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>单位</strong></td>
              <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>来源</strong></td>
              <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>来源网址</strong></td>
              <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>备注</strong></td>
              <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>上架或下架状态</strong></td>
              <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>录入人</strong></td>
              <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>录入时间</strong></td>
              <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>更新人</strong></td>
              <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>更新时间</strong></td>
              <td height="37" align="center" valign="middle" background="/images/erji_22.jpg"><strong>操 作</strong></td>
            </tr>
            <#list pager.data as c>
            <tr>
              <td align="center" class="hui">
              <#if c.category??>
              ${DictionaryUtil.getName(c.category)} 
              </#if>
              </td>
              <td align="center" class="hui">${c.name!''}</td>
              <td align="center" class="hui">${c.price!''}</td>
              <td align="center" class="hui">${c.unit!''}</td>
              <td align="center" class="hui">
              <#if c.origin??>
              ${DictionaryUtil.getName(c.origin)} 
              </#if>
              </td>
              <td align="center" class="hui">${c.originUrl!''}</td>
              <td align="center" class="hui">${c.remark!''}</td>
              <td align="center" class="hui">
              <#if c.isDelete??>
                <#if c.isDelete == 0>
                	待上架
                <#elseif c.isDelete == 1>
                	已上架
                <#elseif c.isDelete == 2>
                	已下架
                <#else>
                  	未知
                </#if>
              <#else>
               	 未知
              </#if>
              </td>
              <td align="center" class="hui">
              <#if c.inPersonName??>
              ${c.inPersonName}
              </#if>
              </td>
              <td align="center" class="hui">
              <#if c.inTime??>
              ${c.inTime?string("yyyy-MM-dd")}
              </#if>
              </td>
              <td align="center" class="hui">
              <#if c.updatePersonName??>
              ${c.updatePersonName} 
              </#if>
              </td>
              <td align="center" class="hui">
              <#if c.udpateTime??>
              ${c.udpateTime?string("yyyy-MM-dd")}
              </#if>
              </td>

              <td align="center" class="hui" style="width:300px;"  >
              <div class="btn-group">
              <#if subject.isPermitted("tgProduct:edit")>   <#--tgProduct:detail  -->
              <button type="button" class="btn btn-default"  onclick="product.toDetail('${c.id}')">详情</button>
              </#if>

              <#if subject.isPermitted("tgProduct:edit")>  <#--tgProduct:edit  -->
              <button type="button" class="btn btn-default"  onclick="product.toEdit('${c.id}')">修改</button>
              </#if>

              <#if subject.isPermitted("tgProduct:disableAndEnabled")>  <#--tgProduct:disableAndEnabled  -->
              <#--tgProduct:disableAndEnabled  控制启用的逻辑在此，不过由于字段冲突，暂时屏蔽-->
              </#if>
              <#if subject.isPermitted("tgProduct:delete")>  <#--tgProduct:delete  -->
              <button type="button" class="btn btn-default"  onclick="product.toDelete('${c.id}')">删除</button>
              </#if>
              </div>
              </td>
              </tr>
              </#list>
              <tr>
              <td colspan="10" valign="middle" class="d">
              <#if subject.isPermitted("accountExpandInfo:detail")>  <#--tgProduct:delete  -->
              <div class="btn-group" style="display:none;">
              <button type="button" class="btn btn-default"  onclick="javascipt:void(0);">删除</button>
              </div>
              </#if>
              </td>
            </tr>
          </table>
        </div>
          <@pageBar   pager=pager url="/tgProduct/list.action?jsonParam=${jsonParam!''}" join="&"> </@pageBar> 
        </div>
    </div>
    <!-- 弹窗 结束 -->
    <#include "../include/deleteConfirmModal.ftl">
    <script src="/js/product.js"></script>
    <script>
      product.initPage();
    </script>
  </body>
</html>