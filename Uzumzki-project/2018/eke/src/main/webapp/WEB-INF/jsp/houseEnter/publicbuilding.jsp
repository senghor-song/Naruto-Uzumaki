<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>公告二手房</title>
	<link href='<%=path%>/Static/Css/common_a0e9b1c01536bf61476dc3e0dc7028fa.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/saleManager_1b2829a78572fecaf2ba3f19b64abfa1.css' rel='stylesheet' type='text/css'></link>
	<link href='<%=path%>/Static/Css/lightbox.min.css' rel='stylesheet' type='text/css'></link>
</head>
<body>
    <div id="saleHouse" class="saleHouse">
        <ul class="sale-tab-sub">
            <li class="sale-sub-menu selected">
                <a href="">网络房源</a>
            </li>
        </ul>
        <!-- 隐藏域开始 -->
        <input type="hidden" id="basePath" value="/release-web"></input>
        <input type="hidden" id="staticPath" value="//demo.com"></input>
        <input type="hidden" id="district" value=""></input>
        <input type="hidden" id="area" value=""></input>
        <input type="hidden" id="cell" value=""></input>
        <input type="hidden" id="demandType" value="0"></input>
        <input type="hidden" id="pageNow" value="1"></input>
        <!-- 隐藏域结束 -->
        <div class="pbSelect">
            <ul class="sale-content-tip">
                <li>
                    <span class="sale-tip-img"></span>
                    <span>贴心提示:</span>
                    <span class="sale-tip-colse"></span>
                </li>
                <li>1.由专业的拍摄人员和高清单反相机实地取景，保证每套房源图片的真实性。</li>
                <li>2.房源图片为统一大小800*600象素，无论从取景的角度、亮度、象素大小都将满足于各家公司的实勘要求。</li>
            </ul>
            <div>
                <div class="keyWordBox">
                    <input id="search-input" type="text" class="search-input" placeholder="在此输入您关注的小区" onfocus="areaKeyUp()"></input>
                    <dl class="keyList">
                    </dl>
                </div>
            </div>
            <ul class="search-list clear">
                <li class="search-list-content search-eare">
                    <span>区域：</span>
                    <a class="selected" tId="" sid="">全部</a>

                    <a tId="search-arealist_luohuqu" sid="luohuqu" id="id_luohuqu">罗湖区</a>

                    <a tId="search-arealist_futianqu" sid="futianqu" id="id_futianqu">福田区</a>

                    <a tId="search-arealist_nanshanqu" sid="nanshanqu" id="id_nanshanqu">南山区</a>

                    <a tId="search-arealist_yantianqu" sid="yantianqu" id="id_yantianqu">盐田区</a>

                    <a tId="search-arealist_baoanqu" sid="baoanqu" id="id_baoanqu">宝安区</a>

                    <a tId="search-arealist_longgangqu" sid="longgangqu" id="id_longgangqu">龙岗区</a>

                    <a tId="search-arealist_longhuaqu" sid="longhuaqu" id="id_longhuaqu">龙华区</a>

                    <a tId="search-arealist_guangmingxinqu" sid="guangmingxinqu" id="id_guangmingxinqu">光明新区</a>

                    <a tId="search-arealist_pingshanqu" sid="pingshanqu" id="id_pingshanqu">坪山区</a>

                    <a tId="search-arealist_dapengxinqu" sid="dapengxinqu" id="id_dapengxinqu">大鹏新区</a>

                </li>

                <li id="search-arealist_luohuqu" class="search-arealist">
                    <a class="selected" sid="">全部</a>

                    <a sid="baishida" parent="luohuqu" id="id_baishida">百仕达</a>

                    <a sid="buxin" parent="luohuqu" id="id_buxin">布心</a>

                    <a sid="cuizhu" parent="luohuqu" id="id_cuizhu">翠竹</a>

                    <a sid="chunfenglu" parent="luohuqu" id="id_chunfenglu">春风路</a>

                    <a sid="diwang" parent="luohuqu" id="id_diwang">地王</a>

                    <a sid="dongmen" parent="luohuqu" id="id_dongmen">东门</a>

                    <a sid="honghu" parent="luohuqu" id="id_honghu">洪湖</a>

                    <a sid="huangbeiling" parent="luohuqu" id="id_huangbeiling">黄贝岭</a>

                    <a sid="luohukouan" parent="luohuqu" id="id_luohukouan">罗湖口岸</a>

                    <a sid="liantang" parent="luohuqu" id="id_liantang">莲塘</a>

                    <a sid="qingshuihe" parent="luohuqu" id="id_qingshuihe">清水河</a>

                    <a sid="sungang" parent="luohuqu" id="id_sungang">笋岗</a>

                    <a sid="wanxiangcheng" parent="luohuqu" id="id_wanxiangcheng">万象城</a>

                    <a sid="xinxiu" parent="luohuqu" id="id_xinxiu">新秀</a>

                    <a sid="yinhu" parent="luohuqu" id="id_yinhu">银湖</a>

                </li>

                <li id="search-arealist_pingshanqu" class="search-arealist">
                    <a class="selected" sid="">全部</a>

                    <a sid="pingshan" parent="pingshanqu" id="id_pingshan">坪山</a>

                </li>

                <li id="search-arealist_yantianqu" class="search-arealist">
                    <a class="selected" sid="">全部</a>

                    <a sid="meisha" parent="yantianqu" id="id_meisha">梅沙</a>

                    <a sid="shatoujiao" parent="yantianqu" id="id_shatoujiao">沙头角</a>

                    <a sid="yantiangang" parent="yantianqu" id="id_yantiangang">盐田港</a>

                </li>

                <li id="search-arealist_baoanqu" class="search-arealist">
                    <a class="selected" sid="">全部</a>

                    <a sid="baoanzhongxin" parent="baoanqu" id="id_baoanzhongxin">宝安中心</a>

                    <a sid="fuyong" parent="baoanqu" id="id_fuyong">福永</a>

                    <a sid="songgang" parent="baoanqu" id="id_songgang">松岗</a>

                    <a sid="shajing" parent="baoanqu" id="id_shajing">沙井</a>

                    <a sid="shiyan" parent="baoanqu" id="id_shiyan">石岩</a>

                    <a sid="taoyuanju" parent="baoanqu" id="id_taoyuanju">桃源居</a>

                    <a sid="xinan" parent="baoanqu" id="id_xinan">新安</a>

                    <a sid="xicheng1" parent="baoanqu" id="id_xicheng1">曦城</a>

                    <a sid="xixiang" parent="baoanqu" id="id_xixiang">西乡</a>

                </li>

                <li id="search-arealist_nanshanqu" class="search-arealist">
                    <a class="selected" sid="">全部</a>

                    <a sid="baishizhou" parent="nanshanqu" id="id_baishizhou">白石洲</a>

                    <a sid="daxuecheng3" parent="nanshanqu" id="id_daxuecheng3">大学城</a>

                    <a sid="hongshuwan" parent="nanshanqu" id="id_hongshuwan">红树湾</a>

                    <a sid="houhai" parent="nanshanqu" id="id_houhai">后海</a>

                    <a sid="huaqiaocheng1" parent="nanshanqu" id="id_huaqiaocheng1">华侨城</a>

                    <a sid="kejiyuan" parent="nanshanqu" id="id_kejiyuan">科技园</a>

                    <a sid="nanshanzhongxin" parent="nanshanqu" id="id_nanshanzhongxin">南山中心</a>

                    <a sid="nantou" parent="nanshanqu" id="id_nantou">南头</a>

                    <a sid="qianhai" parent="nanshanqu" id="id_qianhai">前海</a>

                    <a sid="shekou" parent="nanshanqu" id="id_shekou">蛇口</a>

                    <a sid="shenzhenwan" parent="nanshanqu" id="id_shenzhenwan">深圳湾</a>

                    <a sid="xili1" parent="nanshanqu" id="id_xili1">西丽</a>

                </li>

                <li id="search-arealist_guangmingxinqu" class="search-arealist">
                    <a class="selected" sid="">全部</a>

                    <a sid="gongming" parent="guangmingxinqu" id="id_gongming">公明</a>

                </li>

                <li id="search-arealist_longgangqu" class="search-arealist">
                    <a class="selected" sid="">全部</a>

                    <a sid="bujiguan" parent="longgangqu" id="id_bujiguan">布吉关</a>

                    <a sid="bujizhan" parent="longgangqu" id="id_bujizhan">布吉站</a>

                    <a sid="bujidafen" parent="longgangqu" id="id_bujidafen">布吉大芬</a>

                    <a sid="bujishuijing" parent="longgangqu" id="id_bujishuijing">布吉水径</a>

                    <a sid="bujimancheng" parent="longgangqu" id="id_bujimancheng">布吉慢城</a>

                    <a sid="bantian" parent="longgangqu" id="id_bantian">坂田</a>

                    <a sid="bujijie" parent="longgangqu" id="id_bujijie">布吉街</a>

                    <a sid="bujinanling" parent="longgangqu" id="id_bujinanling">布吉南岭</a>

                    <a sid="danzhutou" parent="longgangqu" id="id_danzhutou">丹竹头</a>

                    <a sid="dayunxincheng" parent="longgangqu" id="id_dayunxincheng">大运新城</a>

                    <a sid="henggang" parent="longgangqu" id="id_henggang">横岗</a>

                    <a sid="longgangbaohe" parent="longgangqu" id="id_longgangbaohe">龙岗宝荷</a>

                    <a sid="pingdi" parent="longgangqu" id="id_pingdi">坪地</a>

                    <a sid="pinghu" parent="longgangqu" id="id_pinghu">平湖</a>

                    <a sid="zhongxinchengdong" parent="longgangqu" id="id_zhongxinchengdong">中心城东</a>

                    <a sid="zhongxinchengbei" parent="longgangqu" id="id_zhongxinchengbei">中心城北</a>

                    <a sid="zhongxinchengnan" parent="longgangqu" id="id_zhongxinchengnan">中心城南</a>

                </li>

                <li id="search-arealist_dapengxinqu" class="search-arealist">
                    <a class="selected" sid="">全部</a>

                    <a sid="dapengbandao" parent="dapengxinqu" id="id_dapengbandao">大鹏半岛</a>

                </li>

                <li id="search-arealist_futianqu" class="search-arealist">
                    <a class="selected" sid="">全部</a>

                    <a sid="baihua" parent="futianqu" id="id_baihua">百花</a>

                    <a sid="bagualing" parent="futianqu" id="id_bagualing">八卦岭</a>

                    <a sid="chegongmiao" parent="futianqu" id="id_chegongmiao">车公庙</a>

                    <a sid="chiwei" parent="futianqu" id="id_chiwei">赤尾</a>

                    <a sid="futianzhongxin" parent="futianqu" id="id_futianzhongxin">福田中心</a>

                    <a sid="futianbaoshuiqu" parent="futianqu" id="id_futianbaoshuiqu">福田保税区</a>

                    <a sid="huaqiangnan" parent="futianqu" id="id_huaqiangnan">华强南</a>

                    <a sid="huaqiangbei" parent="futianqu" id="id_huaqiangbei">华强北</a>

                    <a sid="huanggang" parent="futianqu" id="id_huanggang">皇岗</a>

                    <a sid="huangmugang" parent="futianqu" id="id_huangmugang">黄木岗</a>

                    <a sid="jingtian" parent="futianqu" id="id_jingtian">景田</a>

                    <a sid="lianhua" parent="futianqu" id="id_lianhua">莲花</a>

                    <a sid="meilin" parent="futianqu" id="id_meilin">梅林</a>

                    <a sid="shangxiasha" parent="futianqu" id="id_shangxiasha">上下沙</a>

                    <a sid="shangbu" parent="futianqu" id="id_shangbu">上步</a>

                    <a sid="shixia" parent="futianqu" id="id_shixia">石厦</a>

                    <a sid="xiangmihu" parent="futianqu" id="id_xiangmihu">香蜜湖</a>

                    <a sid="xinzhou1" parent="futianqu" id="id_xinzhou1">新洲</a>

                    <a sid="yuanling" parent="futianqu" id="id_yuanling">园岭</a>

                    <a sid="zhuzilin" parent="futianqu" id="id_zhuzilin">竹子林</a>

                </li>

                <li id="search-arealist_longhuaqu" class="search-arealist">
                    <a class="selected" sid="">全部</a>

                    <a sid="dalang" parent="longhuaqu" id="id_dalang">大浪</a>

                    <a sid="guanlan" parent="longhuaqu" id="id_guanlan">观澜</a>

                    <a sid="longhuazhongxin" parent="longhuaqu" id="id_longhuazhongxin">龙华中心</a>

                    <a sid="minzhi" parent="longhuaqu" id="id_minzhi">民治</a>

                    <a sid="shenzhenbeizhan" parent="longhuaqu" id="id_shenzhenbeizhan">深圳北站</a>

                </li>

                <li class="search-list-content search-price">
                    <span>价格：</span>
                    <a class="selected" tId="" sid="">全部</a>

                    <a sid="75501">200万以下</a>

                    <a sid="75502">200-300万</a>

                    <a sid="75503">300-400万</a>

                    <a sid="75504">400-500万</a>

                    <a sid="75505">500-800万</a>

                    <a sid="75506">800-1000万</a>

                    <a sid="75507">1000万以上</a>

                </li>
                <li class="search-list-content search-houseArea">
                    <span>面积：</span>
                    <a class="selected" tId="" sid="">全部</a>

                    <a sid="75501">50平以下</a>

                    <a sid="75502">50-70平</a>

                    <a sid="75503">70-90平</a>

                    <a sid="75504">90-110平</a>

                    <a sid="75505">110-140平</a>

                    <a sid="75506">140-170平</a>

                    <a sid="75507">170-200平</a>

                    <a sid="75508">200平以上</a>

                </li>
                <li class="search-list-content search-roomInfo">
                    <span>户型：</span>
                    <a class="selected" tId="" sid="">全部</a>

                    <a sid="75501">一室</a>

                    <a sid="75502">二室</a>

                    <a sid="75503">三室</a>

                    <a sid="75504">四室</a>

                    <a sid="75505">五室</a>

                    <a sid="75506">五室以上</a>

                    <div class="float-r">
                        <label for="chked"><input type="checkbox" id="chked" checked="checked"/>通过审核的</label>
                        <input type="checkbox" id="pbSet" checked="checked" name="psSet"></input>
                        <span onclick="getPbSetPop()" style="cursor:pointer;color:#4375c6">订阅房源</span>
                    </div>
                </li>
            </ul>
        </div>
        <!--内容区域 -->
        <div id="bp_content">
            <table class="h_list" width="100%" cellspacing="0" cellpadding="0s">
                <thead>
                    <tr>
                        <td>房源信息</td>
                        <td>克隆情况</td>
                        <td>更新时间</td>
                        <td>操作</td>
                    </tr>
                </thead>
                <tbody id="pbTable">










                    <tr id="htr105100049161">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105100049161')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/ec60e6765ce428697fed88c93bb8da2ae954221cedf823f1832e0df89c3d510297636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="海上世界 海滨花园 朝南 三居室" class="font-bold">海上世界 海滨花园 朝南 三居室</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">南山区</span> <span class="pb_areaCode">蛇口</span> <span>海滨花园</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>低层/20</span>
                                        <span>3室1厅</span> 南 150.0㎡
                                        <span>750.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：1</li>

                                最后克隆:2018-05-15

                            </ul>
                        </td>
                        <td>
                            2018-05-15
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105100049161')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105100059226">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105100059226')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/c106d19438fb8607ff635982992235fd866495aa7fffe0093aaa11dfeac3e73c97636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="中海康城大5房  8米长大阳台" class="font-bold">中海康城大5房  8米长大阳台</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">龙岗区</span> <span class="pb_areaCode">大运新城</span> <span>中海康城国际一期</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>低层/18</span>
                                        <span>4室2厅</span> 南 240.07㎡
                                        <span>850.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：0</li>

                            </ul>
                        </td>
                        <td>
                            2018-05-15
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105100059226')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105100006250">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105100006250')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/2e8d5b703ccdaf22e7349f568935793676343d4f44ff08bc75b463f6809b2e2b97636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="丽湖花园 满五 精装 采光好 可拎包入住" class="font-bold">丽湖花园 满五 精装 采光好 可拎包入住</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">龙岗区</span> <span class="pb_areaCode">布吉水径</span> <span>丽湖花园二期</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>高层/8</span>
                                        <span>3室2厅</span> 北 80.85㎡
                                        <span>255.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：0</li>

                            </ul>
                        </td>
                        <td>
                            2018-05-14
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105100006250')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105100101995">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105100101995')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/101e1b7d5e81f85e0b60d8ff30bd14a30759e7cc6be2c7b83543ee52cddceb9897636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="御河堤 1室0厅 190万" class="font-bold">御河堤 1室0厅 190万</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">福田区</span> <span class="pb_areaCode">上步</span> <span>御河堤</span>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>低层/33</span>
                                        <span>1室0厅</span> 北 32.7㎡
                                        <span>190.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：0</li>

                            </ul>
                        </td>
                        <td>
                            2018-05-14
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105100101995')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105100147471">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105100147471')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/77c6db0aa1ead6537854aeaf708383cb442f2ba31609d5313c4cad438efaa0ec97636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="高层南向二房，景观好，生活方便！" class="font-bold">高层南向二房，景观好，生活方便！</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">福田区</span> <span class="pb_areaCode">皇岗</span> <span>星河华居</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>高层/32</span>
                                        <span>2室1厅</span> 东南 68.67㎡
                                        <span>410.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：0</li>

                            </ul>
                        </td>
                        <td>
                            2018-05-14
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105100147471')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105090188276">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105090188276')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/cff5f352b3cdd1fccae7dc212bc1db10cf8f97399554fec5a533374f799ca8c197636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="大陆庄园 3室2厅 580万" class="font-bold">大陆庄园 3室2厅 580万</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">南山区</span> <span class="pb_areaCode">南头</span> <span>大陆庄园</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>中层/16</span>
                                        <span>3室2厅</span> 东南 119.75㎡
                                        <span>580.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：0</li>

                            </ul>
                        </td>
                        <td>
                            2018-05-13
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105090188276')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105100132825">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105100132825')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/910251125e16c2cf0ed9ff6f7926bc4bb2b38975fc3929212149be957ea4d96497636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="银雅居刚需三房，高楼层，精装修，环境好" class="font-bold">银雅居刚需三房，高楼层，精装修，环境好</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">龙华区</span> <span class="pb_areaCode">龙华中心</span> <span>银雅居</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>高层/14</span>
                                        <span>3室2厅</span> 南 88.31㎡
                                        <span>470.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：1</li>

                                最后克隆:2018-05-15

                            </ul>
                        </td>
                        <td>
                            2018-05-13
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105100132825')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105100508366">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105100508366')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/b86b576cd9c55e366851998317ba4d98c2b001e986266937ddcd5b4a90c6d29697636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="荣超花园 3室1厅 330万" class="font-bold">荣超花园 3室1厅 330万</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">龙岗区</span> <span class="pb_areaCode">布吉关</span> <span>荣超花园</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>高层/7</span>
                                        <span>3室1厅</span> 南 89.0㎡
                                        <span>330.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：0</li>

                            </ul>
                        </td>
                        <td>
                            2018-05-13
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105100508366')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105100518869">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105100518869')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/14a0672d00b6379e789d4dd9229502a1ce426fee89580e41a2b2ebe28585c9ac97636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="新装修 依山傍水实用面积大 峰度天下三房 新秀莲塘" class="font-bold">新装修 依山傍水实用面积大 峰度天下三房 新秀莲塘</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">罗湖区</span> <span class="pb_areaCode">新秀</span> <span>峰度天下</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>中层/30</span>
                                        <span>3室1厅</span> 北 79.62㎡
                                        <span>430.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：0</li>

                            </ul>
                        </td>
                        <td>
                            2018-05-13
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105100518869')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105091269788">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105091269788')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/673c2ad1f0a02ffc0243e56b888b3d8ad6421c4127f361796a32da8f886f734c97636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="雍翠豪园2房2厅2卫生间，一梯两户南北通。" class="font-bold">雍翠豪园2房2厅2卫生间，一梯两户南北通。</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">罗湖区</span> <span class="pb_areaCode">莲塘</span> <span>雍翠豪园</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>中层/9</span>
                                        <span>2室2厅</span> 南 84.51㎡
                                        <span>415.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：0</li>

                            </ul>
                        </td>
                        <td>
                            2018-05-12
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105091269788')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105100201945">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105100201945')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/f8bf5b2b940d582aa5bfd3a9558362e57ad2cb122408e03ee4867edc96f3eccb97636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="畔山花园 三房 满五年家庭唯一一套 红本在手 带花园" class="font-bold">畔山花园 三房 满五年家庭唯一一套 红本在手 带花园</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">罗湖区</span> <span class="pb_areaCode">莲塘</span> <span>畔山花园</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>低层/8</span>
                                        <span>3室2厅</span> 南 87.89㎡
                                        <span>430.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：0</li>

                            </ul>
                        </td>
                        <td>
                            2018-05-12
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105100201945')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105100375502">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105100375502')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/98171249d57e0c71cf6709d9826affb6f77e3e87f8a9b42c7af73ae75dd406d297636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="龙珠花园 2室1厅 220万" class="font-bold">龙珠花园 2室1厅 220万</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">龙岗区</span> <span class="pb_areaCode">布吉关</span> <span>龙珠花园</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>中层/31</span>
                                        <span>2室1厅</span> 西 57.22㎡
                                        <span>220.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：0</li>

                            </ul>
                        </td>
                        <td>
                            2018-05-12
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105100375502')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105100451175">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105100451175')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/2c7edd38c551b33e77d9d4e956427d0e01e36be2f1fb3b56f9090910e89b61e197636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="花园城一期 4室2厅 1250万" class="font-bold">花园城一期 4室2厅 1250万</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">南山区</span> <span class="pb_areaCode">蛇口</span> <span>花园城一期</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>低层/10</span>
                                        <span>4室2厅</span> 南 168.16㎡
                                        <span>1250.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：0</li>

                            </ul>
                        </td>
                        <td>
                            2018-05-12
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105100451175')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105100746172">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105100746172')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/6b674e643717fc0324eae3d06ed9a7b23795c7dc821c6bbc5d3fe1578c503fe197636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="美丽湾国际公寓 精装修 交通便利 配套完善" class="font-bold">美丽湾国际公寓 精装修 交通便利 配套完善</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">南山区</span> <span class="pb_areaCode">南山中心</span> <span>美丽湾国际公寓</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>高层/14</span>
                                        <span>2室1厅</span> 南 42.62㎡
                                        <span>310.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：1</li>

                                最后克隆:2018-05-14

                            </ul>
                        </td>
                        <td>
                            2018-05-12
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105100746172')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105100046582">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105100046582')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/3536151c0d37891def252358efb7926728215dcfe412618831e700c86489f98597636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="优品建筑 精装3房  诚心出售" class="font-bold">优品建筑 精装3房  诚心出售</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">龙华区</span> <span class="pb_areaCode">龙华中心</span> <span>优品建筑</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>中层/11</span>
                                        <span>3室1厅</span> 南 114.55㎡
                                        <span>518.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：0</li>

                            </ul>
                        </td>
                        <td>
                            2018-05-11
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105100046582')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105100077100">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105100077100')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/79b19d65764d2cf1698cc620fe6dddf2034abe23cd277e40355464a877cf133397636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="金丽豪苑 2室1厅 355万" class="font-bold">金丽豪苑 2室1厅 355万</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">罗湖区</span> <span class="pb_areaCode">翠竹</span> <span>金丽豪苑</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>中层/30</span>
                                        <span>2室1厅</span> 北 53.36㎡
                                        <span>355.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：0</li>

                            </ul>
                        </td>
                        <td>
                            2018-05-11
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105100077100')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105100354128">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105100354128')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/05131e112a49e22d7c3a2657b24c2577cd88f1aa00f202e927792d7b948aef3f97636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="城市天地广场   双窗户  户型最棒 看深圳景观" class="font-bold">城市天地广场   双窗户  户型最棒 看深圳景观</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">罗湖区</span> <span class="pb_areaCode">万象城</span> <span>城市天地广场</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>高层/41</span>
                                        <span>1室1厅</span> 东北 49.66㎡
                                        <span>270.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：0</li>

                            </ul>
                        </td>
                        <td>
                            2018-05-11
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105100354128')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105100504812">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105100504812')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/242eb6cde4211e4e53c94ef9d3ea1719e42338379e9f7074889be75b79e9ff4e97636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="莲塘 东方尊峪 精装修 万科物业 高区3房710万" class="font-bold">莲塘 东方尊峪 精装修 万科物业 高区3房710万</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">罗湖区</span> <span class="pb_areaCode">莲塘</span> <span>万科东方尊峪</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>高层/24</span>
                                        <span>3室2厅</span> 西北 124.81㎡
                                        <span>710.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：0</li>

                            </ul>
                        </td>
                        <td>
                            2018-05-11
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105100504812')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105100762797">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105100762797')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/537aacbbadeb343c6bfccda67ce401dee0de214b924deab5b5bef7546513337d97636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="桃源盛景十六区 两居室 适合做婚房朝南，总有适合你的" class="font-bold">桃源盛景十六区 两居室 适合做婚房朝南，总有适合你的</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">宝安区</span> <span class="pb_areaCode">桃源居</span> <span>桃源盛景园十六区</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>低层/18</span>
                                        <span>1室1厅</span> 南 69.36㎡
                                        <span>350.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：0</li>

                            </ul>
                        </td>
                        <td>
                            2018-05-11
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105100762797')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                    <tr id="htr105100853844">
                        <td>
                            <div class="h_info">
                                <div class="h_pic">




                                    <a href="javascript:getImgByHouseId('105100853844')"><img src="http://ali.aimg.fcxms.com/77/755/IMG_I/d182cdd160dc0a586fb8a7c38e0ac0ba3101472b9700106afeb922c53b62a9ad97636ad5ff160f50_small.jpg" width="80" height="60"></a>
                                </div>
                                <ul class="r-content">
                                    <li>
                                        <span title="锦绣花园二期中高层全南朝向，客厅房间全方位朝南。" class="font-bold">锦绣花园二期中高层全南朝向，客厅房间全方位朝南。</span>
                                    </li>
                                    <li>
                                        <span class="pb_districtCode">南山区</span> <span class="pb_areaCode">华侨城</span> <span>锦绣花园二期</span>

                                        <div class="float-r"><span class="more_pics"></span></div>

                                    </li>
                                    <li>
                                        <span class="float-l h-property-left">
									<span>中层/33</span>
                                        <span>4室2厅</span> 南 180.3㎡
                                        <span>1480.0</span>万元
                                        </span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                        <td>
                            <ul style="text-align: left">
                                <li>克隆次数：0</li>

                            </ul>
                        </td>
                        <td>
                            2018-05-11
                        </td>
                        <td>
                            <ul>
                                <li><a href="javascript:cloneHouse('105100853844')">克隆</a></li>
                            </ul>

                        </td>
                    </tr>

                </tbody>
            </table>
        </div>
    </div>
    <script src='<%=path%>/Static/Js/jquery-1.8.3.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/placeholder.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/common_529e5aa7d21eb88f430b82d10ab03696.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/map.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/jquery.endless-scroll-1.3_c150dbe1c2e0a30e701f292622a0e96a.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/jquery.lazyload.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/lazyload_b48db04a5a2ab61ce6f6ec45530c3bfb.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/publicbuilding_f864a4784e87729f46dc0fbce5bf7c50.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/plugins/artDialog/jquery.artDialog.js?skin=green' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/iframeTools.source.js' type='text/javascript'></script>
    <script src='<%=path%>/Static/Js/lightbox.min_7036adb83f9be3e168896fb077b8e4f0.js' type='text/javascript'></script>
</body>

</html>