 //绑定需要浮动的表头
$(function(){
	$.ajaxSetup({
	    contentType : "application/x-www-form-urlencoded;charset=utf-8",
	    complete : function(XMLHttpRequest, textStatus) {
	        var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，
	        if (sessionstatus == "timeout") {
	            // 如果超时就处理 ，指定要跳转的页面
	            window.location.replace("/admin/view.shtml");
	        }
	    }
	});
	//ie版本
	Compatible_Prompt();
	//关闭通用 
	$(".back_next").click(function(){
		parent.layer.closeAll();
	});
    $(".ltable tr:nth-child(odd)").addClass("odd_bg"); //隔行变色 
    $(".rule-single-select").ruleSingleSelect();
    
	var $pageNumber = $("#pageNumber");
	var $to = $("#goto");
	var $pageSize = $('#pageSize');
    $(".personal_catalogue_box .personal_catalogue_list").click(function () {
        $(this).addClass("m_blue").siblings().removeClass("m_blue");
    });
    if($('.js-example-basic-multiple').size()>0){
    	$('.js-example-basic-multiple').select2({
            minimumResultsForSearch: -1,
            theme:"classic"
        });
    };
    //显示退出
    $(".admin_wrap").mouseover(function(){
        $(this).find(".quit_item").show();
    }).mouseleave(function(){
        $(this).find(".quit_item").hide();
    });
    //点击状态
    $(".chose_stare .label_btn").click(function(){
    	$(this).addClass("m_bg_blue").siblings().removeClass("m_bg_blue");
    	var _thechose = $(this).text();
    	$("input[name='columnTubeMode']").val(_thechose);
    });
    $(".radio_i").click(function(){
    	$(this).addClass("m_b").siblings().removeClass("m_b");
    });
    //点击头部分类
    $(".head .nav_list li a").click(function(){
    	var _theHre = $(this).attr("href");
    	setCookie("headHref",_theHre);
    	clearCookie("navHref"); 
    });
    var head_nav = getCookie("headHref");
    if( head_nav !="" && head_nav != undefined ){
    	$(".nav_list li").each(function(){
    		var _href = $(this).find("a").attr("href");
    		if( head_nav == _href ){
    			$(".nav_list li a").removeClass("curr");
    			$(this).find("a").addClass("curr"); 
    		};
    	}); 
    }
    //点击跳转页面cookie
    $(".CateItem dd a").click(function(){
    	var _theHref = $(this).text();
    	setCookie("navHref",_theHref);
    	var thePar = $(this).parent("dd");
    	$(".CateItem dd").removeClass("curr");
    	thePar.addClass("curr");
    });
    var nav_left = getCookie("navHref");
    if( nav_left !="" && nav_left != undefined ){
    	$(".CateItem dd").each(function(){
    		var _href = $(this).find("a").text();
    		if( nav_left == _href ){
    			$(".CateItem dd").removeClass("curr");
    			$(this).addClass("curr");
    			var _theObj = $(this).parents(".CateItem");
    			$(".CateItem").removeClass("curr");
    			_theObj.addClass("curr");
    		};
    	})
    };
    //退出清除cookie
    $(".quit_item_list .close_wind").click(function(){
    	clearCookie("headHref");
    	clearCookie("navHref");
    });
    //切换每页数量
    $(".pageSelect").mouseover(function() {
    	var position = $(".pageSelect").offset();
    	var left = position.left;
    	var top = position.top;
    	var height = $(".pageSelect").height();
    	$(".select").css("left", left).css("top", top + height + 1).show();
    	$(this).mouseout(function() {
    		$(".select").hide();
    	});
    });
    //切换每页数量
    $(".pageSelect").change(function() {
    	$pageSize.val($(this).val());
    	$(".importantC").closest("form").submit();
    });
    $(".select a").click(function() {
    	var $this = $(this);
    	$("#pageSize").val($this.text());
    	$(".importantC").closest("form").submit();
    }); 
    // 跳页
    $.pageTo = function(num) {
    	$pageNumber.val(num);
    	$(".importantC").closest("form").submit();
    };

    // go
    $.go = function() {
    	$pageNumber.val($to.val());
    	$(".importantC").closest("form").submit();
    };
    //消息
    $(".notic_Show").mousemove(function(){
		//$(".shard_box").show();
		$(this).find(".notic_itemWrap").show();
	});
    $(".notic_Show").mouseleave(function(){
    	//$(".shard_box").hide();
		$(".notic_itemWrap").hide();
    });
	$(".notic_itemWrap").mouseleave(function(){
		$(this).hide();
		//$(".shard_box").hide();
	}); 
    //from表单ajax控制提交，并接受返回的json数据 公用
	if( $("#form1").size() > 0 ){  
		$.Tipmsg.r=null;
		$("#form1").Validform({
			tiptype:function(msg){ 
				if( msg != null && msg != "" && msg != undefined ){
					layer.msg(msg);	
				};
			},  
			callback:function(form){  
				$(form).ajaxSubmit({
					type: 'POST',  
			        url: $(form).attr("action"),  
			        success:function(data, status, err) {
			        	if( data.code == "400" ){
			        		layer.msg(data.msg, { icon: 2, time: 1000 });
			        	}else{
		        			layer.msg(data.msg, { icon: 1, shade: 0.5 });
		        			if( data.url !="" && data.url != null){
								setTimeout(function(){
									window.location.href = data.url;
								},1000);
							}else{
								setTimeout(function(){
									parent.location.reload() ;
								},1000);
							}
			        	}  
			        } ,    
			        dataType: 'json',  
			        error : function(xhr, status, err) {  
			        	layer.msg(xhr.msg);             
			        }  
				})
				return false;   //防止表单自动提交   
			}
		 }); 
	} 
	//ifrom嵌套弹窗提交  通用
	if( $("#form_layer").size() > 0 ){  
		$.Tipmsg.r=null;
		 $("#form_layer").Validform({ 
			 tiptype:function(msg){ 
				if( msg != null && msg != "" && msg != undefined ){
					layer.msg(msg);	
				};
			}, 
			datatype:{ 
				"choseIssued":function(gets,obj,curform,regxp){ 
					var need=1,
						numselected=curform.find("input[name='ids']:checked").length;
					return  numselected >= need ? true : "请至少选择"+need+"项！";
				}, 
				"choseUser":function(gets,obj,curform,regxp){ 
					var need=1,
						numselected=curform.find("input[name='userIds']:checked").length;
					return  numselected >= need ? true : "请至少选择"+need+"项！";
				}, 
			},
			//tipSweep:true,
			callback:function(form){  
				$(form).ajaxSubmit({
					type: 'POST',  
			        url: $(form).attr("action"),  
			        success:function(data, status, err) {
			        	if( data.code == "400" ){
			        		layer.msg(data.msg, { icon: 2, time: 1000 });
			        	}else{
		        			layer.msg(data.msg, { icon: 1, shade: 0.5 });
		        			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引 再执行关闭 
		        			if( data.url != "" && data.url != null  ){
		        				setTimeout(function(){
		        					if( data.url == "/admin/controlPersonInstructi/issuedInstruction.shtml" ){
			        					clearCookie("navHref");
			        					setCookie("navHref","已下发预警");
			        				}
			        				parent.layer.close(index);
			        				//在父页面 刷新
			        				window.parent.location.href= data.url; 
								},1000);
							}else{
								setTimeout(function(){
									parent.location.reload();
								},1000);
							} 
			        	}  
			        } ,    
			        dataType: 'json',  
			        error : function(xhr, status, err) {  
			        	layer.msg(xhr.msg);             
			        }  
				})
				return false;   //防止表单自动提交   
			}
		 }); 
	};
	//搜索时 表单验证 以及判断时候有输入
	if($("#searchform").size()>0){
		$.Tipmsg.r=null;
		//验证是否输入
		$("#rylbCount input").keyup(function(){
			var _theVal = $(this).val();
			if( _theVal !="" && _theVal !=undefined && _theVal !=null ){
				$(this).attr("ignore","");
			}else{
				$(this).attr("ignore","ignore");
			}
		});
		//表单验证
    	$("#searchform").Validform({
			tiptype:function(msg){ 
				if( msg !="" && msg != null && msg != undefined ){
					layer.msg(msg);	
				};
			}, 
			//tipSweep:true,
			callback:function(form){}
		});	
	}
	/*
	 * dictionary_tab 字典数据列表 
	 * */
	$("#dictionaryList").on("click",".dictionary_list .folder_star",function(){
		var _this = $(this).parents("tr");  
		var _thePid = _this.attr("unitId");
		var _theleval = Number(_this.attr("leval"));
		var _theStare = _this.attr("stare");
		var _Indent = Number(_this.attr("indent")) || 20;
		var parId = _this.attr("pid");
		if( typeof _theStare== "undefined" ){
			if( _thePid !="" ){
				$.ajax({
					type:"post",
					url:"/admin/dictionary/subSet.shtml",
					data:{id:_thePid,gpId:parId},
					dataType:"json",
					success:function(data){
						console.log(data)
						if(typeof data != "undefined" && data.data.length>0){ 
							var _TtStr = "";  
							var townList=data.data;
							_theleval = _theleval+1;
							_Indent += 20;
							for(var i= 0; i < townList.length; i++){  
								if( townList[i].state == "1" ){
									if( _theleval >= 2){
										var _theTr = "<tr class='dictionary_list_false child_"+_Indent+"' pid='"+_thePid+"' parId='"+townList[i].gpId+"' unitId='"+townList[i].id+"' leval='"+_theleval+"' indent='"+_Indent+"' >";
										_theTr += '<td class="pdl15"><input type="checkbox" class="checkItem" name="ids" value='+townList[i].id+'><i class="folder_star"></i></td>';
										 _theTr += '<td>'+townList[i].name+'</td>'; 
										 _theTr += '<td>'+townList[i].itemValue+'</td>'; 
										 _theTr += '<td>'+townList[i].sortCode+'</td>';
										 //_theTr += '<td>'+townList[i].description+'</td>';
										 if(townList[i].isDef == "1"){
											 _theTr += '<td><em class="m_blue">是</em></td>';
										 }else{
											 _theTr += '<td><em class="m_blue">否</em></td>';
										 };
										 _theTr += '<td>'+townList[i].parentName+'</td>';
										 _theTr += '<td>'+townList[i].modifyDate+'</td>';
										 //_theTr += '<td>'+townList[i].typeName+'</td>'; 
										 _theTr += '<td align="center">'; 
										 _theTr += '	<a href="/admin/dictionary/edit.shtml?id='+townList[i].id+'&amp;&amp;dataItemId='+townList[i].dataItemId+'" class="m_df">编辑</a>'; 
										 _theTr += '</td>';
										_theTr += "</tr>";
									}else{
										var _theTr = "<tr class='dictionary_list child_"+_Indent+"' pid='"+_thePid+"' parId='"+_thePid+"' unitId='"+townList[i].id+"' leval='"+_theleval+"' indent='"+_Indent+"'>";
										_theTr += '<td class="pdl15"><input type="checkbox" class="checkItem" name="ids" value='+townList[i].id+'><i class="folder_star"></i></td>';
										 _theTr += '<td>'+townList[i].name+'</td>'; 
										 _theTr += '<td>'+townList[i].itemValue+'</td>';
										 _theTr += '<td>'+townList[i].sortCode+'</td>';
										 //_theTr += '<td>'+townList[i].description+'</td>';
										 if(townList[i].isDef == "1"){
											 _theTr += '<td><em class="m_blue">是</em></td>';
										 }else{
											 _theTr += '<td><em class="m_blue">否</em></td>';
										 };
										 _theTr += '<td>'+townList[i].parentName+'</td>';
										 _theTr += '<td>'+townList[i].modifyDate+'</td>';
										 //_theTr += '<td>'+townList[i].typeName+'</td>'; 
										 _theTr += '<td align="center">'; 
										 if(townList[i].isDef == "1"){
											 _theTr += '	<a href="/admin/dictionary/add.shtml?id='+townList[i].id+'&amp;&amp;dataItemId='+townList[i].dataItemId+'&amp;&amp;level='+_theleval+1+'" class="m_df">添加下级</a>';
											 _theTr += '<i class="m_e8">|</i>';
										 }
										 _theTr += '	<a href="/admin/dictionary/edit.shtml?id='+townList[i].id+'&amp;&amp;dataItemId='+townList[i].dataItemId+'&amp;&amp;level='+_theleval+'" class="m_df">编辑</a>'; 
										 _theTr += '</td>';
										_theTr += "</tr>";
									}; 
								}else{
									if( _theleval >= 2){
										var _theTr = "<tr class='dictionary_list_false no_dictionary_list child_"+_Indent+"' pid='"+_thePid+"' parId='"+townList[i].gpId+"' unitId='"+townList[i].id+"' leval='"+_theleval+"' indent='"+_Indent+"' >";
										_theTr += '<td class="pdl15"><input type="checkbox" class="checkItem" name="ids" value='+townList[i].id+'><i class="folder_star"></i></td>';
										 _theTr += '<td>'+townList[i].name+'</td>'; 
										 _theTr += '<td>'+townList[i].itemValue+'</td>';
										 _theTr += '<td>'+townList[i].sortCode+'</td>';
										 //_theTr += '<td>'+townList[i].description+'</td>';
										 if(townList[i].isDef == "1"){
											 _theTr += '<td><em class="m_blue">是</em></td>';
										 }else{
											 _theTr += '<td><em class="m_blue">否</em></td>';
										 };
										 _theTr += '<td>'+townList[i].parentName+'</td>';
										 _theTr += '<td>'+townList[i].modifyDate+'</td>';
										 //_theTr += '<td>'+townList[i].typeName+'</td>'; 
										 _theTr += '<td align="center">'; 
										 _theTr += '	<a href="/admin/dictionary/edit.shtml?id='+townList[i].id+'&amp;&amp;dataItemId='+townList[i].dataItemId+'&amp;&amp;level='+_theleval+'" class="m_df">编辑</a>'; 
										 _theTr += '</td>';
										_theTr += "</tr>";
									}else{
										var _theTr = "<tr class='dictionary_list no_dictionary_list child_"+_Indent+"' pid='"+_thePid+"' parId='"+_thePid+"' unitId='"+townList[i].id+"' leval='"+_theleval+"' indent='"+_Indent+"'>";
										_theTr += '<td class="pdl15"><input type="checkbox" class="checkItem" name="ids" value='+townList[i].id+'><i class="folder_star"></i></td>';
										 _theTr += '<td>'+townList[i].name+'</td>'; 
										 _theTr += '<td>'+townList[i].itemValue+'</td>';
										 _theTr += '<td>'+townList[i].sortCode+'</td>';
										 //_theTr += '<td>'+townList[i].description+'</td>';
										 if(townList[i].isDef == "1"){
											 _theTr += '<td><em class="m_blue">是</em></td>';
										 }else{
											 _theTr += '<td><em class="m_blue">否</em></td>';
										 };
										 _theTr += '<td>'+townList[i].parentName+'</td>';
										 _theTr += '<td>'+townList[i].modifyDate+'</td>';
										 //_theTr += '<td>'+townList[i].typeName+'</td>'; 
										 _theTr += '<td align="center">';
										 if(townList[i].isDef == "1"){
											 _theTr += '	<a href="/admin/dictionary/add.shtml?id='+townList[i].id+'&amp;&amp;dataItemId='+townList[i].dataItemId+'&amp;&amp;level='+_theleval+1+'" class="m_df">添加下级</a>';
											 _theTr += '<i class="m_e8">|</i>';
										 }
										 _theTr += '	<a href="/admin/dictionary/edit.shtml?id='+townList[i].id+'&amp;&amp;dataItemId='+townList[i].dataItemId+'&amp;&amp;level='+_theleval+'" class="m_df">编辑</a>'; 
										 _theTr += '</td>';
										_theTr += "</tr>";
									};
								}
								 
								_TtStr += _theTr;
							} 
							_this.attr("stare","true"); 
							_this.addClass("open"); 
							_this.after($(_TtStr)); 
						}
					}
				})
			}else{
				_this.attr("stare","true"); 
			}
		}else{
			//已加载 
			if(_this.hasClass("open")){
				//如果是展开
				//收缩下面子集
				slideUpJzdanwei(_thePid,"dictionaryList");
				_this.removeClass("open");
			}else{
				//展开下面的子集
				showJzdanwei(_thePid,"dictionaryList");
				_this.addClass("open");
			}
		} 
	});
	//删除上传的图片
	if( $(".uploader_wrap").length > 0 ){
		$("body").on("click",".uploader_wrap .files_item .icon_close",function(){
			var _theObj = $(this).parents(".uploader_wrap");
			var _theParent = $(this).parent(".files_item");
			var _theImg = _theParent.find(".files_img").attr("src");
			console.log(_theImg)
			var _theValue = _theObj.find("input[edit_type='thumb']").val();
			var newVal =  _theValue.replace(_theImg,"");
			_theParent.remove();
			_theObj.find("input[edit_type='thumb']").val(newVal);
		});
	} 
	//新增人员选择类别
	$("#Categories_btn .chose_tn").click(function(){
		var _theObj = $(this).parents("#choseCategories");
		var $select3 = _theObj.find("#chose_itemId3").find("option:selected").attr("value");
		var $select2 = _theObj.find("#chose_itemId2").find("option:selected").attr("value");
		var $select1 = _theObj.find("#chose_itemId").find("option:selected").attr("value");
		if($select1 == null || $select1 == ""){
			layer.msg("请先去字典数据添加人员类别");
			return;
		}
		var peo_CategoriesList = $("#Categories_peo .select_checked");
		var $idList = $("#Categories").val();
		var strVar = "";
		if(  $("#Categories_peo .select_checked").length >= 5 ){
			layer.msg("最多选择5个");
			return;
		}else{
			if( $select3 !="" && $select3 != null && $select3 != undefined ){
				if( $idList.indexOf($select3) == -1 ){
					strVar += '<div class="mgr10 select_checked" valId='+$select3+' ><em class="m_b">'+_theObj.find("#chose_itemId3").find("option:selected").text()+'</em> <i class="iconfont icon_close"></i> </div>';
					$idList += $select3 + ",";
					$("#Categories").val($idList).change();
				}else{
					layer.msg("请勿重复添加数据")
				}
			}else if( $select2 !="" && $select2 != null && $select2 != undefined ){ 
				if( $idList.indexOf($select2) == -1 ){
					strVar += '<div class="mgr10 select_checked" valId='+$select2+' ><em class="m_b">'+_theObj.find("#chose_itemId2").find("option:selected").text()+'</em> <i class="iconfont icon_close"></i> </div>';
					$idList += $select2 + ",";
					$("#Categories").val($idList).change();
				}else{
					layer.msg("请勿重复添加数据")
				};
			}else{ 
				if( $idList.indexOf($select1) == -1 ){
					strVar += '<div class="mgr10 select_checked" valId='+$select1+' ><em class="m_b">'+_theObj.find("#chose_itemId").find("option:selected").text()+'</em> <i class="iconfont icon_close"></i> </div>';
					$idList += $select1 + ",";
					$("#Categories").val($idList).change();
				}else{
					layer.msg("请勿重复添加数据")
				};
			};
			$("#Categories_peo dd").append(strVar);  
		}
	});
	//删除新增人员选择类别
	$("body").on("click","#Categories_peo .select_checked .icon_close",function(){
		var _this = $(this).parent(".select_checked");
		var valId = _this.attr("valId") + ",";
		var $idList = $("#Categories").val();
		var $newList = $idList.replace(valId,"");
		_this.remove();
		$("#Categories").val($newList).change(); 
	});
	//输入时验证
	$("*[verify='isIdcard']").keyup(function(e){
		var theEvent = window.event || e;
		var _keyCode = theEvent.keyCode || theEvent.which;
		//var _keyCode = event.keyCode == undefined ? 0 :  event.keyCode ;  //获取按键的keycode（编码）
		if(_keyCode != 37 && _keyCode != 39){ //左右箭头除外
			var _val = $(this).val();
			if(_val!="")
			{ 
				_val = _val.replace(/[^\d|xX]/g,"");   
				$(this).val(_val);
			}
		}
	}); 
	//离开时验证 
	/*$(".text_id").blur(function(){
		var _this = $(this);
		id_verify(_this);
	});*/
	//新增同行人员
	$(".add_pend").click(function(){
		var isnot ="true";
		if( $(".app_inp_box").size()>0 ){
			var _theLastItem = $(this).parents(".app_inp_box");
			var _name = _theLastItem.find(".text_name").val();
			var _sfzh = _theLastItem.find(".text_id").val();
			var _phone = _theLastItem.find(".text_phone").val(); 
			if( _name　!="" && _name !=null && _name!= undefined){
				 var reg =  /[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*/;
				  if (!reg.test(_name)) { 
		             layer.msg("请输入中文名");
		             isnot="false";
		             return false;
		          };
			};
			if( _sfzh　!="" && _sfzh !=null && _sfzh!= undefined){
				 var _isCheckResult = isIdcard(_sfzh);
				 if (_isCheckResult.statue == 0){ 
					 layer.msg("您输入的身份证号码不正确，请重新输入");
					 isnot="false";
					 return false;
				 };
			};
			if( _phone　!="" && _phone !=null && _phone!= undefined){
			  var reg = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/; 
			  if (!reg.test(_phone)) { 
	             layer.msg("您输入的手机号码格式有误");
	             isnot="false";
	             return false;
	          }; 
			}; 
			//去重 
			var _nameSize = $(".select_checked .clone_name[name="+_name+"]").size();
			var _sfzhSize = $(".select_checked .clone_id[name="+_sfzh+"]").size();
			var _phoneSize = $(".select_checked .clone_phone[name="+_phone+"]").size();
			if( _nameSize > 0 ){
				layer.msg("姓名不能重复");
				isnot="false";
				return false;
			};
			if( _sfzhSize > 0 ){
				layer.msg("身份证号码不能重复");
				isnot="false";
				return false;
			};
			if( _phoneSize > 0 ){
				layer.msg("手机号码不能重复");
				isnot="false";
				return false;
			}; 
			if( isnot == "true" ){
				if( _name !="" && _sfzh !="" && _phone !="" && _name != undefined && _sfzh !=undefined && _phone !=undefined ){   
					var strVar = "";
				    strVar += "<div class=\"select_checked\">\n";
				    strVar += "	    <em class=\"c_8\"><i class=\"clone_name\" name="+_name+">"+_name+"<\/i>/<i class=\"clone_id\" name="+_sfzh+">"+_sfzh+"<\/i>/<i class=\"clone_phone\" name="+_phone+">"+_phone+"<\/i><\/em>\n";
				    strVar += "	    <i class=\"iconfont icon_del\">&#xe619;<\/i>\n";
				    strVar += "<\/div>\n";
				    var tx_userinfo = $("input[name='tx_userinfo']").val();
				    if( tx_userinfo !="" && tx_userinfo!=null  ){
				    	tx_userinfo +=_name+_sfzh+_phone+",";
				    }else{
				    	tx_userinfo +=_name+_sfzh+_phone+",";
				    };
				    $(".add_pend_wrap").append($(strVar));
				    _theLastItem.find(".txt_in").val("");
				    $("input[name='tx_userinfo']").val(tx_userinfo).change();
				    
				}else{
					layer.msg("请先完善已有同行人基本信息"); 
				};
			};  
		}
	});
	//删除新增的同行人员
	$("body").on("click",".select_checked .icon_del",function(){
		var _this = $(this).parent(".select_checked");
		_this.remove();
		var len = $(".select_checked").length;
		if( len==0 ){
			 $("input[name='tx_userinfo']").val("").change();
		}
	});
	//table 显示详细
	if( $(".tab_detail").size()>0 ){ 
		$(".tab_detail").each(function(){
            var tooltips = $(this).next().text();
            $(this).pt({
                position: 'r', // 默认属性值
                align: 'c',	   // 默认属性值
                content: tooltips,
            });
        }); 
	};
	//判断input有无placeHolder属性
    if (!hasPlaceholderSupport()){
        $("input").each(function () {
            var $this = $(this);
            var $value = $(this).attr("placeholder");
            var $class = $(this).attr("class");
            //if (!$value) return false;
            //if ($this.is(":hidden")) return false;
            if( $value != "" &&  $value != null &&  $value != undefined){
            	var $textThis = $('<input type="text" value="' + $value + '" class="' + $class + '" />');
                $this.after($textThis);
                $this.hide();
                $textThis.focus(function () {
                    $textThis.hide();
                    $this.show();
                    $this.focus();
                });
                $this.blur(function () {
                    if ($this.val() == "") { 
                        $this.hide();
                        $textThis.show();
                    };
                })
            }; 
        })
    };  
    //滚动滑轮关闭时间插件
    /*$(".mainRight").mousewheel(function(e){
	    var scrollTop = $(".mainRight").scrollTop();
	    console.log(scrollTop)
	    if( scrollTop>0 ){
	    	$("#quote_time").datetimepicker("hide");
	    	$("#quote_time1").datetimepicker("hide");
	    }
    });*/
 // jquery 兼容的滚轮事件
    $(".mainRight").on("mousewheel DOMMouseScroll", function (e) {
    	var scrollTop = $(".mainRight").scrollTop();
    	if( scrollTop>0 ){
 	    	$("#quote_time").datetimepicker("hide");
 	    	$("#quote_time1").datetimepicker("hide");
 	    };
        /*var delta = (e.originalEvent.wheelDelta && (e.originalEvent.wheelDelta > 0 ? 1 : -1)) ||  // chrome & ie
                    (e.originalEvent.detail && (e.originalEvent.detail > 0 ? -1 : 1));              // firefox 
        if (delta > 0) {
            
        }else if (delta < 0) {
            // 向下滚
            console.log("wheeldown");
        }*/
    });
    //全局的input textare 有更改改变状态
    $("input").keyup(function(e){
		var _this = $(this);
		var _theObj = $(this).parents().parents(".mainRight");
		var theEvent = window.event || e;
	    var _key = theEvent.keyCode || theEvent.which;
		//var _key = event.keyCode == undefined ? 0 :  event.keyCode ;  //获取按键的keycode（编码）
		if( _key == "9" ){}else{
			_theObj.find("#eaitStare").val("1");
		};
		
	});
	$("textarea").keyup(function(e){
		var _this = $(this);
		var _theObj = $(this).parents().parents(".mainRight");
		var theEvent = window.event || e;
	    var _key = theEvent.keyCode || theEvent.which;
		//var _key = event.keyCode == undefined ? 0 :  event.keyCode ;  //获取按键的keycode（编码）
		if( _key == "9" ){}else{
			_theObj.find("#eaitStare").val("1");
		};
		
	});
});

//是否支持 placeholder的属性  
function hasPlaceholderSupport() {  
    var input = document.createElement('input');  
    return ('placeholder' in input);  
}  
//IDcard
function isIdcard(str) {
    var card = str;
    var vcity = { 11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江", 31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南", 42: "湖北", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州", 53: "云南", 54: "西藏", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "新疆", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外" };
    var theinfos = checkCard(card);
    function checkCard(card) {
        var card = card;
        var theResult = {};
        if (card === "") {
            theResult.statue = "0";
            theResult.msg = "请输入身份证号，身份证号不能为空";
            return theResult;
        }
        if (isCardNo(card) === false) {
            theResult.statue = "0";
            theResult.msg = "您输入的身份证号码不正确，请重新输入";
            return theResult;
        }
        if (checkProvince(card) === false) {
            theResult.statue = "0";
            theResult.msg = "您输入的身份证号码不正确,请重新输入";
            return theResult;
        }
        if (checkBirthday(card) === false) {
            theResult.statue = "0";
            theResult.msg = "您输入的身份证号码生日不正确,请重新输入";
            return theResult;
        }
        if (checkParity(card) === false) {
            theResult.statue = "0";
            theResult.msg = "您的身份证校验位不正确,请重新输入";
            return theResult;
        }
        theResult.statue = "1";
        theResult.msg = "格式正确";
        return theResult;
    }
    function isCardNo(card) {
        var reg = /(^\d{15}$)|(^\d{17}(\d|X|x)$)/;
        if (reg.test(card) === false) { return false }
        return true;
    }
    function checkProvince(card) {
        var province = card.substr(0, 2);
        if (vcity[province] == undefined) { return false }
        return true;
    }
    function checkBirthday(card) {
        var len = card.length;
        if (len == "15") {
            var re_fifteen = /^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/;
            var arr_data = card.match(re_fifteen);
            var year = arr_data[2];
            var month = arr_data[3];
            var day = arr_data[4];
            var birthday = new Date("19" + year + "/" + month + "/" + day);
            return verifyBirthday("19" + year, month, day, birthday);
        }
        if (len == "18") {
            var re_eighteen = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X|x)$/;
            var arr_data = card.match(re_eighteen);
            var year = arr_data[2];
            var month = arr_data[3];
            var day = arr_data[4];
            var birthday = new Date(year + "/" + month + "/" + day);
            return verifyBirthday(year, month, day, birthday);
        }
        return false;
    }
    function verifyBirthday(year, month, day, birthday) {
        var now = new Date();
        var now_year = now.getFullYear();
        if (birthday.getFullYear() == year && (birthday.getMonth() + 1) == month && birthday.getDate() == day) {
            var time = now_year - year;
            if (time >= 3 && time <= 100) { return true }
            return false;
        }
        return false;
    } function checkParity(card) {
        card = changeFivteenToEighteen(card);
        var len = card.length;
        if (len == "18") {
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
            var cardTemp = 0, i, valnum; for (i = 0; i < 17; i++) {
                cardTemp += card.substr(i, 1) * arrInt[i]
            }
            valnum = arrCh[cardTemp % 11];
            var _last_num = card.substr(17, 1);
            if (_last_num == "x") {
                _last_num = "X"
            }
            if (valnum == _last_num) { return true }
            return false;
        }
        return false;
    }
    function changeFivteenToEighteen(card){
        if (card.length == "15") {
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
            var cardTemp = 0, i; card = card.substr(0, 6) + "19" + card.substr(6, card.length - 6);
            for (i = 0; i < 17; i++) {
                cardTemp += card.substr(i, 1) * arrInt[i]
            }
            card += arrCh[cardTemp % 11];
            return card;
        }
        return card;
    }
    return theinfos;
}
//撤管 以及 列管
function Evacuated(btn){
	var _thehref = $("." +btn).attr("at");
	var _thehreList = _thehref.split("?");
	var _url=_thehreList[0];
	var personnelType = _thehreList[1].split("=")[1];
	var state = _thehreList[2].split("=")[1]; 
	var $ids = $(".tab input[name='ids']:enabled:checked");
    if ($ids.size() < 1) {
        layer.msg('请选择要操作的选项');
        return;
    }
	var $idsVal="";
	$.each($ids, function(index, value) {
        var $this = $(this); 
        $idsVal += $this.attr("value") + ",";
    }); 
	if( _url !="" && personnelType !="" && state !="" ){
		$.ajax({
			type:"post",
            url:_url,
            data:{state:state, ids:$idsVal,personnelType:personnelType},
            dataType:"json",
            success:function(data) {  	 	 
            	if(data.code == 200){
                	layer.msg(data.msg,{icon: 1, shade: 0.5 }); 
                	if( data.url != null && data.url != "" && data.url != undefined ){ 
                		setTimeout(function(){
							window.location.href = data.url;
						},2000);
                	}
            	}else if(data.code == 400){
                	layer.msg(data.msg,{icon: 2});
                	if( data.url != null && data.url != "" && data.url != undefined ){ 
                		setTimeout(function(){
							window.location.href = data.url;
						},2000);
                	}
            	}
            }
		});
	}; 
};
//收缩树
function slideUpJzdanwei(_theId,parentId){
	if($("#" +parentId+ " tr[pid='"+_theId+"']").length>0){
		//$("#" +parentId+ " tr[pid='"+_theId+"']").each(function(){
		$("#" +parentId+ " tr[unitid='"+_theId+"']").each(function(){
		var _has_Child = $(this).attr("stare"); //是否有子集
		if(typeof _has_Child != "undefined"){
			var _theId = $(this).attr("unitid") || "";
			var _leval = $(this).attr("leval") || "";
			if( _leval == "0" ){
				var parid = $(this).attr("parid") || "";
				$("#" +parentId).find(" tr[parid='"+parid+"']").slideUp();
				$("#" +parentId).find(" tr[parid='"+parid+"']").removeClass("open");
				$("#" +parentId).find(" tr[parid='"+parid+"']").eq(0).slideDown();
			}else if( _leval == "1" ){
				var parid = $(this).attr("unitid") || "";
				$("#" +parentId).find(" tr[pid='"+parid+"']").slideUp();
			}else{
				$(this).slideUp(); //收缩
			};
			/*if(typeof _theId != "undefined" && _theId!=""){
				slideUpJzdanwei(_theId);
			}else{
				$(this).slideUp(); //收缩
			}*/
		}else{
			$(this).slideUp(); //收缩
		}
	});  
	}else{
	 $("#jinzhonglist tr[unitId='"+_theId+"']").slideUp();
	} 
} 
//展开树 
function showJzdanwei(_theId,parentId){
   if($("#" +parentId+ " tr[pid='"+_theId+"']").length>0){
	   $("#" +parentId+ " tr[pid='"+_theId+"']").each(function(){
			var _has_Child = $(this).attr("child"); //是否有子集
			if(_has_Child){
				var _theId = $(this).attr("unitId") || "";
				if(typeof _theId != "undefined" && _theId!=""){
					showJzdanwei(_theId);
				}else{
					$(this).slideDown();
				}
			}else{
				$(this).slideDown();
			}
		});  
   }else{
	   $("#" +parentId+ " tr[pid='"+_theId+"']").slideDown();
   } 
}   
//全选取消按钮函数
function checkAll(chkobj) {
    if ($(chkobj).text() == "全选") {
        $(chkobj).text("取消");
        $(".checkItem").prop("checked", true);
    } else {
        $(chkobj).text("全选");
        $(".checkItem").prop("checked", false);
    }
}
//批量删除
function del(){
    var $ids = $(".tab input[name='ids']:enabled:checked");
    if ($ids.size() < 1) {
    	layer.msg('请选择要删除的选项');
        return;
    }
    //询问框
    layer.confirm('是否将此信息删除?', {
      btn: ['确认','取消'] //按钮
    }, function(){
       $.ajax({
            type:"post",
            url:"delete.shtml",
            data:$ids.serialize(),
            dataType:"json",
            success:function(data) {  	 	 
            	if(data.code == 200){
                	layer.msg(data.msg,{icon: 1});
                	$.each($ids, function(index, value) {
                        var $this = $(this);
                        $this.closest("tr").remove();
                    });
                	location.reload();
            	}else if(data.code == 400){
                	layer.msg(data.msg,{icon: 2});
            	}
            },
            error:function(data) {
            	layer.msg("删除失败",{icon: 2}); 
            }
       });
    });
} 
//反馈详情 json
function FeedbackDetail(_url,deatailId){
	$.post(_url,{id:deatailId},function(data){
		var detaiList = data.data;
		console.log(detaiList)
		if( detaiList != null && detaiList !=undefined && detaiList != "" ){
			for( var i=0;i<detaiList.length;i++ ){
				//console.log(i) 
				var num = i +1;
				var strVar = "";
				//strVar += "<div class=\"during_dl\">\n";
		        if( i==0 ){ 
		        	strVar += "<div class=\"during_dl during_dl_open \">\n";
		        		strVar += '<div class="feedback"><span class="fl feed_fl"></span><span class="fl feedback_fl"><a href="javascript:void(0)" class="icon-xiala"><em class="fl">初次反馈</em><i class="iconfont iconfont_up"></i></a></span></div>';	
		        		strVar += '<div class="feekback_dl">';
		        }else if( i==1 ){ 
		        	strVar += "<div class=\"during_dl\">\n";
		        		strVar += '<div class="feedback"><span class="fl feed_fl"></span><span class="fl feedback_fl"><a href="javascript:void(0)" class="icon-xiala"><em class="fl">再次反馈</em><i class="iconfont iconfont_up"></i></a></span></div>';
		        		strVar += '<div class="feekback_dl curr">';
		        }else{ 
		        	strVar += "<div class=\"during_dl\">\n";
		        		strVar += '<div class="feedback"><span class="fl feed_fl"></span><span class="fl feedback_fl"><a href="javascript:void(0)" class="icon-xiala"><em class="fl">'+num+'次反馈</em><i class="iconfont iconfont_up"></i></a></span></div>';
		        		strVar += '<div class="feekback_dl curr">';
		        }; 
			        if(detaiList[i].columnTubeMode !=null && detaiList[i].columnTubeMode != "" && detaiList[i].columnTubeMode != undefined){
			        	strVar +='<dl><dt>目标状态:</dt><dd>'+detaiList[i].columnTubeMode+'<dd></dl>';
			        }; 
			        if( detaiList[i].tx_userinfo != null && detaiList[i].tx_userinfo != undefined && detaiList[i].tx_userinfo != "" ){
			        	var userlist = detaiList[i].tx_userinfo.userlist;
			        	if( userlist !="" && userlist != null && userlist != undefined ){
			        		strVar +='<dl><dt>同行人员:</dt><dd>';
			        		var tx_userinfoList = detaiList[i].tx_userinfo.userlist;
					        for( var pr=0; pr<tx_userinfoList.length; pr++ ){ 
					        	strVar +='<div class="field_peo"><i class="clone_name">'+tx_userinfoList[pr].name+'</i> / <i class="clone_id">'+tx_userinfoList[pr].sfzh+'</i> / <i class="clone_phone">'+tx_userinfoList[pr].phone+'</i><i class="division">;</i></div>'
					        };
					        strVar +='</dl>';
			        	};
			        };
			        if( detaiList[i].idcard_btn != null && detaiList[i].idcard_btn !="" && detaiList[i].idcard_btn != undefined ){
				        strVar +='<dl><dt>所持证件:</dt><dd><div class="files_pend">';
					        	var papersImg = detaiList[i].idcard_btn.split(",");
					        	for( var par =0; par < papersImg.length; par++ ){
					        		strVar +='<span class="files_item"><img src='+papersImg[par]+' class="files_img"></span>';
					        	};
				        strVar +='</div></dd></dl>';
			        }; 	
			        if( detaiList[i].idcard_btn2 != null && detaiList[i].idcard_btn2 !="" && detaiList[i].idcard_btn2 != undefined ){
				        strVar +='<dl><dt>其他资料:</dt><dd><div class="files_pend">'; 
					        	var proImg = detaiList[i].idcard_btn2.split(",");
					        	for( var pro =0; pro < proImg.length; pro++ ){
					        		strVar +='<span class="files_item"><img src='+proImg[pro]+' class="files_img"></span>';
					        	}; 
				        strVar +='</div></dd></dl>'; 
			        };
			        if(detaiList[i].phone !=null && detaiList[i].phone != "" && detaiList[i].phone != undefined){
			        	strVar +='<dl><dt>手机号码:</dt><dd>'+detaiList[i].phone+'<dd></dl>';
			        }; 
			        if(detaiList[i].QQ !=null && detaiList[i].QQ != "" && detaiList[i].QQ != undefined){
			        	strVar +='<dl><dt>QQ:</dt><dd>'+detaiList[i].QQ+'<dd></dl>';
			        }; 
			        if(detaiList[i].WX !=null && detaiList[i].WX != "" && detaiList[i].WX != undefined){
			        	strVar +='<dl><dt>微信号:</dt><dd>'+detaiList[i].WX+'<dd></dl>'; 
			        };
			        if(detaiList[i].contro !=null && detaiList[i].contro != "" && detaiList[i].contro != undefined){
			        	strVar +='<dl><dt>稳控状态:</dt><dd>'+detaiList[i].contro+' '+detaiList[i].contro2+'<dd></dl>';
			        }; 
			        if(detaiList[i].rest !=null && detaiList[i].rest != "" && detaiList[i].rest != undefined){
			        	strVar +='<dl><dt>其他:</dt><dd><span>'+detaiList[i].rest+'<span><dd></dl>';
			        };
			        if(detaiList[i].feedback !=null && detaiList[i].feedback != "" && detaiList[i].feedback != undefined){
			        	strVar +='<dl><dt>反馈内容:</dt><dd><span class="fk_dateil">'+detaiList[i].feedback+'</span></dd></dl>';
			        };
		        strVar +="</div>";
		        strVar +="</div>";  
		        $(".during").append(strVar)
			};
			//console.log(strVar)
		};
	});
	//点击展开当前
	$("body").on("click",".during .during_dl .feedback .feedback_fl",function(){ 
		var _theObj = $(this).parents(".during_dl");
		var _theupDown = $(this).parents(".during_dl").find(".feekback_dl");
		if( _theObj.hasClass("during_dl_open") ){ 
			_theupDown.slideUp(500);
			_theObj.removeClass("during_dl_open");
		}else{ 
			$(".during_dl").removeClass("during_dl_open");
			$(".during_dl").find(".feekback_dl").slideUp(500); 
			_theupDown.slideDown(500);
			_theObj.addClass("during_dl_open");
		}
		 
	});
}
//重置
function resetBtn(){
	$('select').each(function(){
		$(this).find("option").attr("selected",false);
		$(this).find("option").eq(0).attr("selected","selected");
	});	 
	
	if( $(".rule-single-select").size()>0 ){
		$(".rule-single-select").ruleSingleSelect();
	};
	if( $(".select_box").size()>0 ){ 
		$(".select_box select").select2();
	};
	//多选
	if( $("#select_multiple").size()>0 ){
		$('.select_box #select_multiple option').each(function(){
			$(this).attr("selected",false); 
		});
		$("#select_multiple").select2({
   		    placeholder: "请选择轨迹类型"
   		});
	};
	//用户管理验证重置
	if( $("#forSearch").size()>0 ){
		$("#forSearch").attr('datatype','minName');
		$("#forSearch").attr('errormsg','请输入中文');
		$("#forSearch").removeAttr('onkeyup');
		$("#forSearch").removeAttr('maxlength');
	}
	$("#rylbCount input").not("#goto,:hidden").attr("value","");
	$("#rylbCount input").val("");
	$("#rylbCount input").attr("ignore","ignore");
}
//===========================工具类函数============================
//姓名
function name_verify(obj){
	var _val = $(obj).val();
	if( _val　!="" && _val !=null && _val != undefined){
		 var reg =  /[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*/;
		 if (!reg.test(_name)) { 
	        layer.msg("请输入中文名");
	        return false;
	     };
	};
}
//身份证
function id_verify(obj){ 
	var _val = $(obj).val(); 
	if( _val　!="" && _val !=null && _val != undefined){
		 var _isCheckResult = isIdcard(_val);
		 if (_isCheckResult.statue == 0){ 
			 layer.msg("您输入的身份证号码不正确，请重新输入");
			 return false;
		 };
	};
}
//手机号码验证
function Phone_data(obj){
	var _val = $(obj).val();
	_val = _val.replace(/[^\d]/g,"");  //正则处理非数字的字符进行过滤
	$(obj).val(_val);	//赋值过滤后的字符 
}
//身份证输入验证
function idCardKeyUp(obj){
	var _val = $(obj).val();
	_val = _val.replace(/[^\d|xX]/g,"");   
	$(obj).val(_val);
}

//只允许输入数字
/*function checkNumber(e) {
    var keynum = window.event ? e.keyCode : e.which;
    if ((48 <= keynum && keynum <= 57) || (96 <= keynum && keynum <= 105) || keynum == 8) {
        return true;
    } else {
        return false;
    }
}
//只允许输入小数
function checkForFloat(obj, e) {
    var isOK = false;
    var key = window.event ? e.keyCode : e.which;
    if ((key > 95 && key < 106) || //小键盘上的0到9
        (key > 47 && key < 60) ||  //大键盘上的0到9
        (key == 110 && obj.value.indexOf(".") < 0) || //小键盘上的.而且以前没有输入.
        (key == 190 && obj.value.indexOf(".") < 0) || //大键盘上的.而且以前没有输入.
        key == 8 || key == 9 || key == 46 || key == 37 || key == 39) {
        isOK = true;
    } else {
        if (window.event) { //IE
            e.returnValue = false;   //event.returnValue=false 效果相同.
        } else { //Firefox
            e.preventDefault();
        }
    }
    return isOK;
}
//检查短信字数
function checktxt(obj, txtId) {
    var txtCount = $(obj).val().length;
    if (txtCount < 1) {
        return false;
    }
    var smsLength = Math.ceil(txtCount / 62);
    $("#" + txtId).html("您已输入<b>" + txtCount + "</b>个字符，将以<b>" + smsLength + "</b>条短信扣取费用。");
}
//四舍五入函数
function ForDight(Dight, How) {
    Dight = Math.round(Dight * Math.pow(10, How)) / Math.pow(10, How);
    return Dight;
} */
//取得cookie     
function getCookie(name) { 
    var nameEQ = name + "="; 
    var ca = document.cookie.split(';');    //把cookie分割成组     
    for (var i = 0; i < ca.length; i++) { 
        var c = ca[i];                      //取得字符串     
        while (c.charAt(0) == ' ') {          //判断一下字符串有没有前导空格     
            c = c.substring(1, c.length);      //有的话，从第二位开始取     
        } 
        if (c.indexOf(nameEQ) == 0) {       //如果含有我们要的name     
            return unescape(c.substring(nameEQ.length, c.length));    //解码并截取我们要值     
        } 
    } 
    return false; 
} 

//清除cookie     
function clearCookie(name) { 
    setCookie(name, "", -1); 
}; 
//设置cookie     
function setCookie(name, value, seconds) { 
    seconds = seconds || 0;   //seconds有值就直接赋值，没有为0，这个根php不一样。     
    var expires = ""; 
    if (seconds != 0) {      //设置cookie生存时间     
        var date = new Date(); 
        date.setTime(date.getTime() + (seconds * 1000)); 
        expires = "; expires=" + date.toGMTString(); 
    } 
    document.cookie = name + "=" + escape(value) + expires + "; path=/";   //转码并赋值     
}; 
//单选下拉框
$.fn.ruleSingleSelect = function () {
    var singleSelect = function (parentObj) {
        if (parentObj.find("select").length == 0) {
            parentObj.remove();
            return false;
        }
        parentObj.addClass("single-select"); //添加样式
        parentObj.children('.boxwrap').remove(); //防止重复初始化
        parentObj.children().hide(); //隐藏内容
        var divObj = $('<div class="boxwrap"></div>').prependTo(parentObj); //前插入一个DIV
        //创建元素
        var titObj = $('<a class="select-tit" href="javascript:;"><span></span><i class="iconfont icon-xiala">&#xe618;</i></a>').appendTo(divObj);
        var itemObj = $('<div class="select-items"><ul></ul></div>').appendTo(divObj);
        var selectObj = parentObj.find("select").eq(0); //取得select对象
        //遍历option选项
        selectObj.find("option").each(function (i) {
            var indexNum = selectObj.find("option").index(this); //当前索引
            var liObj = $('<li>' + $(this).text() + '</li>').appendTo(itemObj.find("ul")); //创建LI
            if ($(this).prop("selected") == true) {
                liObj.addClass("selected");
                titObj.find("span").text($(this).text());
            }
            //检查控件是否启用
            if ($(this).prop("disabled") == true) {
                liObj.css("cursor", "default");
                return;
            }
            //绑定事件
            liObj.click(function () {
                $(this).siblings().removeClass("selected");
                $(this).addClass("selected"); //添加选中样式
                selectObj.find("option").prop("selected", false);
                selectObj.find("option").eq(indexNum).prop("selected", true); //赋值给对应的option
                titObj.find("span").text($(this).text()); //赋值选中值
                itemObj.hide(); //隐藏下拉框
                selectObj.trigger("change"); //触发select的onchange事件
                //alert(selectObj.find("option:selected").text());
            });
        });
        //设置样式
        //titObj.css({ "width": titObj.innerWidth(), "overflow": "hidden" });
        //itemObj.children("ul").css({ "max-height": $(document).height() - titObj.offset().top - 62 });

        //检查控件是否启用
        if (selectObj.prop("disabled") == true) {
            titObj.css("cursor", "default");
            return;
        }
        //绑定单击事件
        titObj.click(function (e) {
            e.stopPropagation();
            if (itemObj.is(":hidden")) {
                //隐藏其它的下位框菜单
                $(".single-select .select-items").hide();
                $(".single-select .arrow").hide();
                //位于其它无素的上面
                itemObj.css("z-index", "1");
                //显示下拉框
                itemObj.show();

                //5.0新增判断下拉框上或下呈现
                if(parentObj.parents('.tab-content').length > 0){
                    var tabObj = parentObj.parents('.tab-content');
                    //容器高度-下拉框TOP坐标值-容器TOP坐标值
                    var itemBttomVal = tabObj.innerHeight() - itemObj.offset().top + tabObj.offset().top - 12;
                    if(itemBttomVal < itemObj.height()){
                        var itemTopVal = tabObj.innerHeight() - itemBttomVal - 61;
                        if(itemBttomVal > itemTopVal){
                            itemObj.children('ul').height(itemBttomVal);
                        }else{
                            if(itemTopVal < itemObj.height()){
                                itemObj.children('ul').height(itemTopVal);
                            }
                            if(!parentObj.hasClass('up')){
                                parentObj.addClass("up"); //添加样式
                            }
                        }
                    }
                }

            } else {
                //位于其它无素的上面
                itemObj.css("z-index", "");
                //隐藏下拉框
                itemObj.hide();
            }
        });
        //绑定页面点击事件
        $(document).click(function (e) {
            selectObj.trigger("blur"); //触发select的onblure事件
            itemObj.hide(); //隐藏下拉框
        });
    };
    return $(this).each(function () {
        singleSelect($(this));
    });
}
/**
 * @description 处理网站兼容性问题  
 * @param {Number} MinSub 最低IE兼容哪个版本
 */
function Compatible_Prompt(MinSub) {
    if (MinSub == undefined) {
        MinSub = 9;
    }

    var agent = navigator.userAgent.toLowerCase();
    var regStr_ie = /msie [\d.]+;/gi;
    var regStr_ff = /firefox\/[\d.]+/gi
    var regStr_chrome = /chrome\/[\d.]+/gi;
    var regStr_saf = /safari\/[\d.]+/gi;
    var _theBrowser = null;
    //IE
    if (agent.indexOf("msie") > 0) {
        _theBrowser = agent.match(regStr_ie);
    }
    //firefox
    if (agent.indexOf("firefox") > 0) {
        _theBrowser = agent.match(regStr_ff);
    }
    //Chrome
    if (agent.indexOf("chrome") > 0) {
        _theBrowser = agent.match(regStr_chrome);
    }
    //Safari
    if (agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0) {
        _theBrowser = agent.match(regStr_saf);
    }

    if (_theBrowser == undefined || _theBrowser == null) return false;
    _theBrowser = _theBrowser.toString();
    var theIeLengthLength = _theBrowser.indexOf("msie");  //判断是否为IE浏览器 

    if (theIeLengthLength >= 0) {  //是否为IE浏览器
        //获取浏览器的版本 
        var theBanben = _theBrowser.replace(/[^0-9.]/ig, "");  
        theBanben = isNaN(Number($.trim(theBanben))) ? 9 : Number($.trim(theBanben));
        if (theBanben < MinSub) {
            //显示兼容信息
            ShowCompatibleLoyout();
        };
        if( theBanben == "9.0" || theBanben == "9" ){
        	$(".new-people-box .new_peo_list dd .sel").each(function(){
        		$(this).css({"background":"none","padding":"0px 10px 0px 10px"})
        	});
        };
    }
}
/**
 * @description 现实兼容性弹窗
 * @extends {window} 
 */
function ShowCompatibleLoyout() { 
    var defaults = {
        title: "\u4F60\u77E5\u9053\u4F60\u7684Internet Explorer\u662F\u8FC7\u65F6\u4E86\u5417?", // title text
        text: "\u4E3A\u4E86\u5F97\u5230\u6211\u4EEC\u7F51\u7AD9\u6700\u597D\u7684\u4F53\u9A8C\u6548\u679C,\u6211\u4EEC\u5EFA\u8BAE\u60A8\u5347\u7EA7\u5230\u6700\u65B0\u7248\u672C\u7684Internet Explorer\u6216\u9009\u62E9\u53E6\u4E00\u4E2Aweb\u6D4F\u89C8\u5668.\u4E00\u4E2A\u5217\u8868\u6700\u6D41\u884C\u7684web\u6D4F\u89C8\u5668\u5728\u4E0B\u9762\u53EF\u4EE5\u627E\u5230.<br /><br />"
    }; 
    var Cover_layout = "<div class='loading'></div>";
    var Cover_Content = "<span>" + defaults.title + "</span>"
                  + "<p> " + defaults.text + "</p>"
                  + "<div class='browser'>"
                  + "<ul>"
                  + "<li><a class='chrome' href='https://www.google.com/chrome/' target='_blank'></a></li>"
                  + "<li><a class='firefox' href='http://www.mozilla.org/en-US/firefox/new/' target='_blank'></a></li>"
                  + "<li><a class='ie11' href='http://windows.microsoft.com/en-US/internet-explorer/downloads/ie/' target='_blank'></a></li>"
                  + "<li><a class='safari' href='http://www.apple.com/safari/download/' target='_blank'></a></li>"
                  + "<li><a class='opera' href='http://www.opera.com/download/' target='_blank'></a></li>"
                  + "<ul>"
                  + "</div>";
    var Cover_Container = "<div class='layout_Contianer'>" + Cover_Content + "</div>";
    var w_height = window.innerHeight || document.documentElement.clientHeight; //窗口的高度

    var CompatibleContainer = "<div class='CompatibleContainer'>" + Cover_layout + Cover_Container + "</div>";
    
    var _CompatibleContainerObj = document.createElement("div");
    setTimeout(function () { 
        _CompatibleContainerObj.innerHTML = CompatibleContainer;
         document.body.appendChild(_CompatibleContainerObj); 
    }, 1000); 
}
//最大输入
function checkLen(obj) {
	obj.value = obj.value.replace(/\ +/g,"");
	var maxChars = $(obj).attr("maxlength") ? $(obj).attr("maxlength") : $(obj).attr("maxLength");//最多字符数   
	if (obj.value.length > maxChars)  obj.value = obj.value.substring(0,maxChars);   
	var curr = maxChars - obj.value.length;   
	$(".text_keyup_num").empty().text(obj.value.length);　
} 

$(".refresh_html").click(function(){ 
	window.location.reload();
});
