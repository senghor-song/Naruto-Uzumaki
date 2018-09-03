<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="/ruiec-tags" prefix="ruiec" %>
<!DOCTYPE html >
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<title><ruiec:title/></title>
    <link rel="stylesheet" href="${base }/resources/admin/css/layer.css" >
    <link rel="stylesheet" href="${base }/resources/admin/css/common.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/style.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/index.css">
    <link rel="stylesheet" href="${base }/resources/admin/css/quote.css">
</head>
<body class="bodyCss">
          
       <!--头部-->
        <jsp:include page="../common/header.jsp"></jsp:include> 
        <!---->
        <section>
        	<jsp:include page="../common/leftNav5.jsp"></jsp:include> 
            <div class="mainRight">
                <div class="pancl_pd">
                    <div class="new-people-box early_warning_box">
                        <form action="/admin/banner/update.shtml" method="post" id="form1">
                            <div class="tit_menu_list">
                                <a href="/admin/common/main.shtml" class="m_menu_item">
                                    <i class="iconfont"></i>
                                    <em>首页</em>
                                </a>
                               <i class="fl split_gt">&gt;</i>
                                <a href="javascript:;" class="m_menu_item">
                                    <em>宣传管理</em>
                                </a>
                                <i class="fl split_gt">&gt;</i>
                                <a href="/admin/banner/list.shtml" class="m_menu_item">
                                    <em>登录图片列表</em>
                                </a>
                                <i class="fl split_gt">&gt;</i>
                                <a href="javascript:;" class="m_menu_item">
                                    <em>编辑登录图片信息</em>
                                </a>
                                <span class="fr">
	                        	   <input type="button" onclick="javascript:history.back(-1);" class="fr back_btn" value="返回上一页"/>
	                            </span>
                            </div>
                            <input type="hidden" name="id" value="${banner.id}">
						<div class="mgt20 lieguan-box">
							<dl class="new_peo_list">
								<dt>设置为登录图片：</dt>
								<dd>
									<div class="fl rule-single-select single-select">
										<select class="sel" name="isshow">
											<option value="1">是</option>
											<option <c:if test="${banner.isshow==0 }">selected="selected"</c:if>
												value="0">否</option>
										</select>
									</div>
								</dd>
							</dl>
							<%-- <dl class="new_peo_list">
                                    <dt>上传图片：</dt>
                                    <dd>
                                        <div class="uplad_img">
									        <img id="face_pic"  class="fl img_upload" style="width: 120px;height: 100px;"  name="image" src="${banner.image==null ? '/resources/admin/images/Avatar.png':banner.image}" /> 
                                            <sapn href="javascript:void(0)" id="uploaderFace" class="fl uploader_btn">上传图片</sapn>
                                        </div>
                                        <div class="file_img">
                       				       <input type="file" id="upload_file" style="display: none;" />
                  				        </div>
                  				           <input id="fileUrl"  datatype="*" isNot="true"  nullmsg="请上传图片" type="hidden" name="image" value="${banner.image }"> 
                                    </dd>
                                </dl> --%>
							<div class="img_show">
								<dl class="new_peo_list">
									<dt>上传图片：</dt>
									<dd>
										<span class="fl img_box" style="width: 200px;height: 150px;"> 
										      <img id="face_pic" src="${banner.image==null ? '/resources/admin/images/Avatar.png':banner.image}"  style="width:200px;"/>
										</span> 
										<span class="upload_btn"> 
										<a class="upd_btn" id="uploaderFace">上传图片</a>
										</span> 
										<input type="file" id="upload_file" style="display: none;" />
										<input id="fileUrl" datatype="*" isNot="true" nullmsg="请上传图片" value="${banner.image}" type="hidden" name="image">
									</dd>
								</dl>
							</div>
						</div>
						<div class="state-save sub_btn_box">
                                <input type="submit" value="保存" class="sub_btn" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    <!--js-->
    <script src="${base }/resources/admin/js/jquery-1.9.1.min.js"></script>
    <script src="${base }/resources/admin/js/layer.js"></script>
    <!--验证-->
    <script src="${base }/resources/admin/js/jquery.form.js"></script>
    <script src="${base }/resources/admin/js/Validform_v5.3.2/Validform_v5.3.2.js"></script>
    <!--自写-->
    <script src="${base }/resources/admin/js/layout.js"></script>
    <!--拖拽-->
    <script src="${base }/resources/admin/js/gridster/jquery.gridster.min.js"></script>
    <!---下拉框 -->
    <script src="${base }/resources/admin/js/common.js"></script>
    <!--日期-->
    <script src="${base }/resources/admin/js/My97DatePicker/lang/zh-cn.js"></script>
    <script src="${base }/resources/admin/js/My97DatePicker/WdatePicker.js"></script>
	<script src="${base }/resources/admin/js/webuploader/webuploader.min.js"></script>
	
	<link href="${base }/resources/admin/js/tooltips/tooltips.css" rel="stylesheet" id="skin">
    <script src="${base }/resources/admin/js/tooltips/bootstrap-tooltip.js"></script>
    <script src="${base }/resources/admin/js/tooltips/bootstrap-affix.js"></script>
    <script src="${base }/resources/admin/js/tooltips/application.js"></script>
    <script src="${base }/resources/admin/js/tooltips/jquery.pure.tooltips.js"></script>
    
     <script src="${base }/resources/admin/js/datetimepicker/jquery.datetimepicker.js"></script>
    <script src="${base }/resources/admin/js/datetimepicker/timer.js"></script>
  	<script src="${base }/resources/admin/js/webuploader/myuploader.js"></script> 
	<!-- <script type="text/javascript">
	$(function(){
		var webuploader = WebUploader.create({
	   		swf:"/resources/home/js/webuploader/Uploader.swf",
	   		server:"/userCenter/upload.jsp",
	   		auto:true,
	   		pick:"#uploaderFace"
		  	}); 
			webuploader.on("uploadSuccess",function(file,data){
				layer.msg('上传中', {
				  	icon: 16
				  	,shade: 0.1
				});
		  		if (data.error == 0) {
	         		$("#face_pic").attr('src',data.url);
	         		$("#fileUrl").attr('value',data.url);
	          	} else {
	          		layer.msg(data.message);
	          	}
				layer.closeAll('loading');
		  	});
		  	webuploader.on("uploadError",function(){
	          	layer.msg("上传文件异常!请联系管理员!");
		  	}); 
	})
	</script> -->
	<script type="text/javascript">
	 $(function(){
			//绑定页面的上传图片的JS
			var Myuploader = new $.MyWebUploader();
			if($(".img_show").length>0){
				$(".upload_btn").each(function(){
					var _btnId = $(this).find(".upd_btn").eq(0).attr("id");
					var _thumbContId = $(".img_box").find("img").eq(0).attr("id");
					var _thumbInputId = $(".img_show").find("input[ name='image']").eq(0).attr("id");
					var _option = {
						btnId: _btnId, //按钮id
						thumbContId:_thumbContId,//关联缩略图的id
						thumbInputId:_thumbInputId
					}
					Myuploader.Init(_option);
				});
			}
		})
	</script>
</body>
</html>