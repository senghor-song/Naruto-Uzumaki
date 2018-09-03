<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>房源共享</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
    <link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
    <link href='<%=path%>/Static/Css/saleManager_1b2829a78572fecaf2ba3f19b64abfa1.css' rel='stylesheet' type='text/css'></link>
    <link href='<%=path%>/Static/Css/lightbox.min.css' rel='stylesheet' type='text/css'></link>
    <link href='<%=path%>/Static/Css/fanye.css' rel='stylesheet' type='text/css'></link>
</head>

<body>
    <input type="hidden" value="/release-web" id="basePath"></input>
    <input type="hidden" value="//s.img.xms.d.com" id="staticPath"></input>
    <div id="saleHouse" class="saleHouse">
        <ul class="sale-tab-sub">
            <li class="sale-sub-menu selected">
                <a href="">房源共享</a>
            </li>
        </ul>
        <div class="saleHouse-main saleManager-main">
            <ul class="sale-content-tip" style="margin:8px 8px 0">
                <li>
                    <span class="sale-tip-img"></span>
                    <span>贴心提示:</span>
                    <span class="sale-tip-colse"></span>
                </li>
                <li>1.可以克隆同事共享之后的房源。</li>
                <li>2.共享后的房源有效期为30天，到期后自动取消共享。</li>
            </ul>
            <div class="sharebuilding-select">
                <input class="keywordArea" id="cell" placeholder="请输入小区" style="width: 140px; height: 26px; border-radius: 3px; border: 1px solid #6bb05f;" onkeypress="javascript:enterSumbit()" />
                <input class="keywordArea" id="phone" placeholder="请输入电话号码" style="width: 140px; height: 26px; border-radius: 3px; border: 1px solid #6bb05f;" onkeypress="javascript:enterSumbit()" />
                <input id="research" class="btn-search " type="button"></input>
                <br />
                <ul class="sbdm-search-list sharebuilding-menu">
                    <li class="sharebuilding-menu-shareOrgId" id="shareOrgId">
                        <span>共享范围：</span>
                        <!-- 					<a shareOrgIdVal="0" >全部</a> -->
                        <a onclick="selectOrg(this)" class="selected" id="orgText" orgId="0" title="全部">全部</a>
                        <div id="shareOrgId-sub" style="width: 750px; margin-left: 45px; margin-top: -32px; display: none;">
                            <a shareOrgIdVal="0" class="selected">全部范围</a>
                        </div>
                    </li>
                    <li class="sharebuilding-menu-postType" id="postType"><span>类型：</span>

                        <a postTypeVal="-1" class="selected">全部</a>
                        <a postTypeVal="0">出售</a>
                        <a postTypeVal="1">出租</a>


                    </li>
                    <li class="sharebuilding-menu-district" id="district"><span>区域：</span>
                        <a districtval="0" class="selected">全部</a>
                        <a districtval="75501">福田</a> <a districtval="75502">罗湖</a> <a districtval="75503">南山</a> <a districtval="75504">宝安</a> <a districtval="75505">龙岗</a> <a districtval="75506">盐田</a> <a districtval="75507">光明新区</a> <a districtval="75508">坪山新区</a>                        <a districtval="75509">龙华新区</a> <a districtval="75510">深圳周边</a> <a districtval="75511">布吉</a> <a districtval="75512">大鹏新区</a> </li>

                    <li class="sharebuilding-menu-tagSelect" id="tagSelect"><span>标签：</span> <a tagselectval="" class="selected">全部</a> <a tagselectval="secure">放心</a> <a tagselectval="hot">急推</a> <a tagselectval="new">新房</a> <a tagselectval="focus">集攻</a> <a tagselectval="recommend">推荐</a> <a tagselectval="yrz">已认证</a>                        <a tagselectval="notTags">无标签</a> </li>
                    <li>
                        <span>排序：</span>
                        <select id="order" style="width: 160px; float: none;">
						<option value="expDate">按到期时间由新到旧排序</option>
						<option value="cloneNum">按克隆次数由多到少排序</option>
					</select>
                        <div style="float:right;margin-top: 6px;cursor: pointer;" onclick="javascript:exportToExcel()">
                            <img src="../Static/Picture/downloadExcel.png" />
                        </div>
                    </li>
                </ul>

            </div>
            <div class="saleManager-state-content content-main con1">
                <!-- 表格区 -->
                <div class="shareBuilding-content">
                    <table width="100%" cellspacing="0" cellpadding="0s" class="h_list">
                        <thead>
                            <tr>
                                <td>房源编号</td>
                                <td>基本信息</td>
                                <td>联系人</td>
                                <td>共享情况</td>
                                <td>共享期限</td>
                                <td>操作</td>
                            </tr>
                        </thead>
                        <tbody id="houseTable">
                        </tbody>
                    </table>
                </div>
                <div class="shareBuilding-tip" style="display: none">
                    您所在的公司和门店还没有人共享过房源，<a id="redirectLink">点此</a>成为第一个共享房源的人吧！
                </div>
                <!-- 底部 -->
                <div class="saleManager-bottom">
                    <div class="qx"><input type="checkbox" id="sharebuild-all"></input><label for="sharebuild-all">全选</label></div>
                    <div class="btns float-l">
                        <input onclick="javascript:ShareBuilding.batchClone()" type="button" class="btn_o" value="批量克隆"></input>
                        <input onclick="javascript:ShareBuilding.batchShare()" type="button" class="btn_o" value="批量修改"></input>
                    </div>
                    <div class="float-r">
                        <div id="saleManager-fanye" class="commom-fanye"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/placeholder.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/jquery.paginate.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js?skin=green' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/lightbox.min_7036adb83f9be3e168896fb077b8e4f0.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/sharebuilding_818d7bc4d739d7a76ea41c912368e4cb.js' type='text/javascript'></script>
    <script type="text/javascript">
        $(".sharebuilding-menu-postType a").click(function() {
            $(this).addClass("selected");
            $(this).siblings("a").removeClass("selected");
            getShareBuildings();
        });
        $(".sharebuilding-menu-district a").die().live("click", function() {
            $(this).addClass("selected");
            $(this).siblings("a").removeClass("selected");
            getShareBuildings();
        });
        $(".sharebuilding-menu-tagSelect a").die().live("click", function() {
            $(this).addClass("selected");
            $(this).siblings("a").removeClass("selected");
            getShareBuildings();
        });

        /**选择部门**/
        var manageOrgAllList = null;

        function selectOrg(tag) {
            selectOrgArt = art.dialog.open($("#basePath").val() + "/selectShareOrg.jsp", {
                title: "选择范围",
                width: 400,
                height: 410,
                lock: true,
                esc: false,
                padding: 0
            });
        }

        $(document).ready(function() {
            $("#cell").placeholder();
            $("#phone").placeholder();

            setTimeout(function() {
                if (allStores == null) {
                    parent.loadingShow();
                }
            }, 500);
        });

        var building_type_ary = ["不限", "住宅", "别墅", "商铺", "写字楼", "厂房"];
        var post_type_ary = ["出售", "出租"];
        var dialog = null;
        $(function() {
            $("#research").click(function() {
                pageNow = 1;
                getShareBuildings();
            });

            $("#redirectLink").die().live("click", function() {
                parent.$('#houseManagerSell').click();
                var href = parent.$('#houseManagerSell').attr('href');
                var url = href.substring(href.indexOf("'") + 1, href.lastIndexOf("'"));
                parent.hrefLink(url);
            });

            $("a[name=clone_link]").die().live("click", function() {
                var height = parent.$(".col_main").height() - 32;
                var i = $(this).attr("index");
                var b = buildings[i];
                var url = $("#basePath").val() + "/import/getimpview" +
                    "-" + b.postType +
                    ".html?buildingType=" + b.buildingType +
                    "&editType=C&buildingId=" + b.buildingId +
                    "&phone=" + b.phone +
                    "&contacter=" + encodeURI(encodeURI(b.contacter)) + "&sourceType=2";
                dialog = art.dialog.open(url, {
                    title: "房源克隆",
                    left: 0,
                    top: 0,
                    width: 800,
                    height: height,
                    lock: true
                });
            });
        });

        var pageNow = 1;
        var buildings;
        var shareOrgIds;
        var allStores = null;
        var tagList = null;

        function getShareBuildings() {
            if (dialog != null) {
                dialog.close();
            }
            var cell = $("#cell").val();
            cell = (cell == "请输入小区") ? "" : cell;
            var phone = $("#phone").val();
            phone = (phone == "请输入电话号码") ? "" : phone;
            $.ajax({
                type: "post",
                url: $("#basePath").val() + "/import/getShareBuildings.do",
                data: {
                    "isFirst": (($("#district a").length > 1) ? "0" : "1"),
                    "postType": $("#postType .selected").attr("postTypeVal"),
                    "shareOrgId": ($("#shareOrgId .selected").attr("orgid") == "" ?
                        "0" : $("#shareOrgId .selected").attr("orgid")),
                    "distrct": $("#district .selected").attr("districtVal"),
                    "cell": cell,
                    "phone": phone,
                    "pageNow": pageNow,
                    "tags": $("#tagSelect .selected").attr("tagSelectVal"),
                    "order": $("#order").val()
                },
                dataType: "json",
                success: function(data) {
                    $("#sharebuild-all").attr("checked", false);
                    var districts = data.districts;
                    var tagMap = data.tagMap;
                    if (districts != null) {
                        $.each(districts, function(i, n) {
                            $("#district").append("<a districtVal='" + n.postSiteId + "'>" + n.siteName + "</a> ");
                        });
                    }

                    if (data.allStores != null) {
                        allStores = data.allStores;
                        var defaultOrgId = data.defaultOrgId;
                        $("#shareOrgId-sub").html("<a shareOrgIdVal='0'>全部</a> ");
                        $.each(allStores, function(i, n) {
                            if (n.orgId == defaultOrgId) {
                                $("#orgText").attr("orgId", n.orgId);
                                $("#orgText").attr("title", n.orgName);
                                $("#orgText").html(n.orgName);

                                $("#shareOrgId-sub").append("<a shareOrgIdVal='" +
                                    n.orgId + "' class='selected'>" + n.orgName + "</a> ");
                            } else {
                                $("#shareOrgId-sub").append("<a shareOrgIdVal='" +
                                    n.orgId + "'>" + n.orgName + "</a> ");
                            }
                        });
                    }

                    if (tagList == null) {
                        tagList = data.tagList;
                        var tagSelectHtml = '<span >标签：</span> <a tagSelectVal="" class="selected">全部</a> ';
                        $.each(data.tagList, function(i, tag) {
                            tagSelectHtml += '<a tagSelectVal="' + tag.tagName + '">' + tag.tagDesc + '</a> ';
                        });
                        tagSelectHtml += '<a tagSelectVal="notTags">无标签</a> ';
                        $("#tagSelect").html(tagSelectHtml);
                    }

                    buildings = data.buildings;
                    shareOrgIds = data.shareOrgIds;
                    if (buildings == null || buildings.length == 0) {
                        $(".shareBuilding-content").hide();
                        $(".shareBuilding-tip").show();
                        $(".saleManager-bottom").hide();
                        return;
                    }
                    var pageCount = data.pageCount;
                    //翻页控件
                    $(".saleManager-bottom").show();
                    $("#saleManager-fanye").paginate({
                        count: pageCount,
                        start: pageNow,
                        display: 6,
                        border: false,
                        text_color: '#50b63f',
                        text_hover_color: '#fff',
                        background_color: '#fff',
                        background_hover_color: '#50b63f',
                        images: false,
                        mouse: 'click',
                        onChange: function() {
                            pageNow = $(".jPag-current").html();
                            getShareBuildings();
                        }
                    });

                    $("#houseTable").html("");
                    $.each(buildings, function(i, n) {
                        var imgUrl;
                        if (n.imgUrl == null) {
                            imgUrl = $("#staticPath").val() + "/release/images/null.png";
                        } else {
                            var index = n.imgUrl.lastIndexOf(".");
                            imgUrl = n.imgUrl.substring(0, index) + "_small" + n.imgUrl.substring(index);
                        }
                        var cloned = ((n.cloned == 1) ? '<span style="color:#6bb05f">已克隆</span>' : '');
                        var tags = '<div class="float-r">';

                        if (n.tags != null && n.tags != '') {
                            var tagArr = n.tags.split(",");
                            for (var j = 0; j < tagArr.length; j++) {
                                tags += '<span class="ml4 houseTagStyle ' + tagArr[j] + '_color">' + tagMap[tagArr[j]] + '</span>';
                            }
                        }
                        var multiFlag = (n.mutilFlag == 1) ? '<div class="float-r ml4"><span class="houseTagStyle mutilFlag_color">多图</span></div>' : '';
                        var videoFlag = (n.videoFlag == 1) ? '<div class="float-r ml4"><span class="houseTagStyle video_upload_color">视频</span></div>' : '';
                        tags = tags + multiFlag + videoFlag + "</div>";

                        var priceUnit = ((n.postType == 1) ? "元/月" : "万元");
                        var expDate = n.expDate.substring(0, 4) + "-" + n.expDate.substring(4, 6) + "-" +
                            n.expDate.substring(6, 8);

                        var titleContent = '(' + $("#district a[districtVal=" + n.distrct + "]").html() +
                            ' ' + n.cell + ')' + n.title;

                        titleContent = titleContent.length > 25 ? titleContent.substring(0, 25) + "..." : titleContent;
                        var cloneHtml = '';
                        if (n.cloneNum > 0) {
                            cloneHtml = '<a class="font-bold" href="javascript:getCloneDetailById(' +
                                n.buildingId + ')">' + n.cloneNum + '</a>';
                        } else {
                            cloneHtml = '<b class="F_red">' + n.cloneNum + '</b>';
                        }
                        var content =
                            '<tr id="htr' + n.buildingId + '">' +
                            '<td width="50"><label><input type="checkbox" name="buildingId" value="' + n.buildingId + '" data-userid="' + n.userId + '" data-buildingtype="' + n.buildingType + '" data-privileged="' + n.privileged + ' "style="margin:0px"></input>' + n.buildingId + '</lable></td>' +
                            '<td>' +
                            '<dl class="h_info h_relative">' +
                            '<dt class="h_pic">' +
                            '<a href="javascript:getBuildingInfo(\'' + n.buildingId + '\',\'' + n.buildingType + '\',\'' + n.userId + '\',0)">' +
                            '<img width="80" height="60" src="' + imgUrl + '">' +
                            '</a>';
                        if (shareOrgIds[n.buildingId][1] > 10) {
                            content += '<span class="shareBuilding-dz"></span>';
                        }
                        content += '</dt>' +
                            '<dd>' +
                            '<div>' +
                            '<b style="color:#5b8b00; font-weight:normal">' +
                            '【' + post_type_ary[n.postType] + '】【' +
                            building_type_ary[n.buildingType] + '】' +
                            '</b>' +
                            tags +
                            '</div>' +
                            '</dd>' +
                            '<dd>' +
                            '<a title="' + n.title + '" href="javascript:getBuildingInfo(\'' + n.buildingId + '\', \'' + n.buildingType + '\', \'' + n.userId + '\',1)">' + titleContent + '</a>' +
                            '</dd>' +
                            '<dd class="h_property">' +
                            '<span class="float-l h-property-left">' +
                            '<span>' + n.curFloor + '/' + n.maxFloor + '层</span>&nbsp;' +
                            '<span>' + n.room + '室' + n.hall + '厅' + n.toilet + '卫</span>&nbsp;' +
                            n.houseArea + '㎡&nbsp;' +
                            '<b class="F_red">' + n.price + '</b>' + priceUnit + '&nbsp;' + cloned +
                            '</span>' +
                            '</dd>' +
                            '</dl>' +
                            '</td>' +
                            '<td width="55">' +
                            n.contacter + '<br>' + n.phone +
                            '</td>' +
                            '<td width="85"><span class="float-l">范围：</span><span class="shareWidth" title="' +
                            $("#shareOrgId a[shareOrgIdVal=" + n.shareOrgId + "]").html() + '">' +
                            $("#shareOrgId a[shareOrgIdVal=" + n.shareOrgId + "]").html() +
                            '</span><br><span class="clear">克隆数：' + cloneHtml + '</span>' +
                            '</td>' +
                            '<td width="60">' + expDate + '</td>' +
                            '<td width="30">' +
                            '<a name="clone_link" index="' + i + '">克隆</a><br>' +
                            ((n.privileged == 1) ? '<a onclick="updateshareOrgId(' +
                                n.buildingId + ', \'' + n.shareOrgId + '\')">修改</a>' : '') +
                            '</td>' +
                            '</tr>';
                        $("#houseTable").append(content);
                        $(".shareBuilding-tip").hide();
                        $(".shareBuilding-content").show();
                    });
                },
                complete: function(XMLHttpRequest, textStatus) {
                    parent.loadingHide();
                }
            });
        }

        function updateshareOrgId(buildingId, shareOrgId) {
            var url = $("#basePath").val() + "/ajax/houman/getShareHouesOrg.do?buildingId=" +
                buildingId + "&shareOrgId=" + shareOrgId + "&from=1";
            sharHouseOrg = art.dialog.open(url, {
                title: '修改共享范围',
                width: 280,
                height: 340,
                lock: true,
                esc: false
            });
        }

        //获取克隆详情
        function getCloneDetailById(buildingId) {
            var url = $("#basePath").val() + "/ajax/sharebuilding/getCloneDetailById.do?buildingId=" + buildingId;
            sharHouseOrg = art.dialog.open(url, {
                title: '克隆人员详情',
                width: 360,
                height: 400,
                lock: true,
                esc: false
            });
        }

        function newWindow(url, param) {
            window.open(url + param);
        }

        getShareBuildings();

        function enterSumbit() {
            var event = arguments.callee.caller.arguments[0] || window.event; //消除浏览器差异
            if (event.keyCode == 13) {
                getShareBuildings();
            }
        }

        function getBuildingInfo(buildingId, buildingType, userId, type) {
            $.ajax({
                url: $("#basePath").val() + "/ajax/houman/getBuildingInfo.do",
                type: "post",
                dataType: "json",
                data: {
                    "isDraft": 0,
                    "userId": userId,
                    "buildingType": buildingType,
                    "buildingId": buildingId
                },
                success: function(data) {
                    if (type == 1) {
                        var html = '<div style="width:800px;height:480px;overflow:auto">' + data.houseDescribe + '</div>';
                        art.dialog({
                            content: html,
                            title: "房源描述",
                            left: 0,
                            top: 0,
                            padding: 8,
                            width: 800,
                            height: 500,
                            lock: true
                        });
                    } else {
                        $(".data-releaseImg").remove();
                        var releaseImgSet = data.releaseImgSet;
                        if (releaseImgSet != null && releaseImgSet.length > 0) {
                            var html = '<div style="display:none" class="data-releaseImg">';
                            $.each(releaseImgSet, function(i, releaseImg) {
                                html += '<a id="releaseImg_' + buildingId + '_' + i + '" href="' + releaseImg.imgUrl + '" data-lightbox="releaseImg_' + buildingId + '"></a>';
                            });
                            html += '</div>';
                            $("#htr" + buildingId).find(".h_pic").append(html);
                            $("#releaseImg_" + buildingId + "_0").click();
                        }
                    }
                }
            });
        }

        function exportToExcel() {
            var cell = $("#cell").val();
            cell = (cell == "请输入小区") ? "" : cell;
            var phone = $("#phone").val();

            var temp = document.createElement("form");
            temp.action = '../release-web/import/exportShareBuildingsExcel.do';
            temp.method = "post";
            temp.style.display = "none";

            var opt = document.createElement("textarea");
            opt.name = 'postType';
            opt.value = $("#postType .selected").attr("postTypeVal");
            temp.appendChild(opt);

            opt = document.createElement("textarea");
            opt.name = 'shareOrgId';
            opt.value = ($("#shareOrgId .selected").attr("orgid") == "" ?
                "0" : $("#shareOrgId .selected").attr("orgid"));
            temp.appendChild(opt);

            opt = document.createElement("textarea");
            opt.name = 'distrct';
            opt.value = $("#district .selected").attr("districtVal");
            temp.appendChild(opt);

            opt = document.createElement("textarea");
            opt.name = 'cell';
            opt.value = cell;
            temp.appendChild(opt);

            opt = document.createElement("textarea");
            opt.name = 'phone';
            opt.value = phone;
            temp.appendChild(opt);

            opt = document.createElement("textarea");
            opt.name = 'tags';
            opt.value = $("#tagSelect .selected").attr("tagSelectVal");
            temp.appendChild(opt);

            opt = document.createElement("textarea");
            opt.name = 'order';
            opt.value = $("#order").val();
            temp.appendChild(opt);

            document.body.appendChild(temp);
            temp.submit();
        }
    </script>
</body>
</html>