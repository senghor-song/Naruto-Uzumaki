<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h5 style="margin-bottom: 4px;">推荐标签：<a class="tip-close" href="javascript:tipWindowClose()"><span class="tip-img"></span></a></h5>
<ul>
	
		<li><a name="house_label_link">南北通透</a></li>
	
		<li><a name="house_label_link">精装修</a></li>
	
		<li><a name="house_label_link">学区房</a></li>
	
		<li><a name="house_label_link">婚房</a></li>
	
		<li><a name="house_label_link">地铁房</a></li>
	
		<li><a name="house_label_link">低首付</a></li>
	
		<li><a name="house_label_link">低总价</a></li>
	
		<li><a name="house_label_link">毛坯房</a></li>
	
		<li><a name="house_label_link">复式</a></li>
	
		<li><a name="house_label_link">急售</a></li>
	
		<li><a name="house_label_link">拎包入住</a></li>
	
		<li><a name="house_label_link">小户型</a></li>
	
		<li><a name="house_label_link">随时看房</a></li>
	
		<li><a name="house_label_link">满五唯一</a></li>
	
		<li><a name="house_label_link">产证满二</a></li>
	
	<li><a onclick="moreHouseLabel(this)" class="chooseTagMore">更多</a></li>
</ul>
<div class="clear" id="houseLabel_more_div" style="display: none;">
	<div>
		<div class="house-label-tab">
			<ul id="chooseTag_tab_nav" class="chooseTag_tab_nav" style="margin:0px;padding:0px;float:left">
				<li class="chooseTag_tab_nav_item active" data-sub-type="100">小区</li>
	            <li class="chooseTag_tab_nav_item" data-sub-type="101">户型</li>
	            <li class="chooseTag_tab_nav_item" data-sub-type="102">房屋结构</li>
	            <li class="chooseTag_tab_nav_item" data-sub-type="103">位置</li>
	            <li class="chooseTag_tab_nav_item" data-sub-type="104">装修</li>
	            <li class="chooseTag_tab_nav_item" data-sub-type="105">附加</li>
			</ul>
        </div>
        <div class="chooseTag_Tab_List" id="chooseTag_Tab_List">
            <div class="chooseTag_Tab_ct_item" data-sub-type="100" data-type-name="小区">
				<a name="house_label_link">投资首选</a>
				<a name="house_label_link">住户素质高</a>
				<a name="house_label_link">安全性高</a>
				<a name="house_label_link">繁华地段</a>
				<a name="house_label_link">高品质小区</a>
				<a name="house_label_link">交通便利</a>
				<a name="house_label_link">次新小区</a>
				<a name="house_label_link">金牌物业</a>
				<a name="house_label_link">品牌开发商</a>
				<a name="house_label_link">环境优美</a>
				<a name="house_label_link">配套成熟</a>
				
					<a name="house_label_link">学区房</a>
				
				<a name="house_label_link">地铁房</a>
				<a name="house_label_link">低密度小区</a>
			</div>
			<div class="chooseTag_Tab_ct_item" data-sub-type="101" data-type-name="户型" style="display:none">
				<a name="house_label_link">主卧朝南</a>
				<a name="house_label_link">客厅朝南</a>
				<a name="house_label_link">两房朝南</a>
				<a name="house_label_link">三房朝南</a>
				<a name="house_label_link">全南户型</a>
				<a name="house_label_link">南北通透</a>
				<a name="house_label_link">厨卫朝北</a>
				<a name="house_label_link">卫生间全明</a>
				<a name="house_label_link">开放式厨房</a>
				<a name="house_label_link">大开间</a>
				<a name="house_label_link">大两房</a>
				<a name="house_label_link">小户型</a>
				<a name="house_label_link">客厅带阳台</a>
				<a name="house_label_link">双阳台</a>
				<a name="house_label_link">带飘窗</a>
				<a name="house_label_link">景观房</a>
				<a name="house_label_link">一门关</a>
            </div>
			<div class="chooseTag_Tab_ct_item" data-sub-type="102" data-type-name="房屋结构" style="display:none">
				<a name="house_label_link">独立电梯</a>
				<a name="house_label_link">一梯两户</a>
				<a name="house_label_link">一梯三户</a>
				<a name="house_label_link">复式房</a>
				<a name="house_label_link">错层式住宅</a>
				<a name="house_label_link">跃层式住宅</a>
				<a name="house_label_link">loft户型</a>
				<a name="house_label_link">大平层</a>
				<a name="house_label_link">带阁楼</a>
				<a name="house_label_link">带采光天井</a>
				<a name="house_label_link">下沉式花园</a>
				<a name="house_label_link">法式小高层</a>
				<a name="house_label_link">新式里弄</a>
				<a name="house_label_link">独栋别墅</a>
				<a name="house_label_link">联排别墅</a>
				<a name="house_label_link">叠加别墅</a>
            </div>
			<div class="chooseTag_Tab_ct_item" data-sub-type="103" data-type-name="位置" style="display:none">
				<a name="house_label_link">小区中心位置</a>
				<a name="house_label_link">小区入口附近</a>
				<a name="house_label_link">不靠近马路</a>
				<a name="house_label_link">靠近花园</a>
				<a name="house_label_link">位置安静</a>
				<a name="house_label_link">小区景观位置</a>
				<a name="house_label_link">非底楼</a>
				<a name="house_label_link">非顶楼</a>
				<a name="house_label_link">底楼</a>
				<a name="house_label_link">顶楼</a>
			</div>
			<div class="chooseTag_Tab_ct_item" data-sub-type="104" data-type-name="装修" style="display:none">
                <a name="house_label_link">中式风格</a>
                <a name="house_label_link">美式风格</a>
                <a name="house_label_link">欧式风格</a>
                <a name="house_label_link">地中海风格</a>
                <a name="house_label_link">田园风格</a>
            </div>
			<div class="chooseTag_Tab_ct_item" data-sub-type="105" data-type-name="附加" style="display:none">
				<a name="house_label_link">附赠露台</a>
				<a name="house_label_link">带车库</a>
				<a name="house_label_link">带花园</a>
				<a name="house_label_link">带中央空调</a>
				<a name="house_label_link">带地暖</a>
				<a name="house_label_link">带游泳池</a>
				<a name="house_label_link">送家具家电</a>
				<a name="house_label_link">送车位</a>
				<a name="house_label_link">指纹入户</a>
				<a name="house_label_link">采光充足</a>
				<a name="house_label_link">得房率高</a>
				<a name="house_label_link">房型正气</a>
				<a name="house_label_link">黄金楼层</a>
				<a name="house_label_link">婚房首选</a>
				<a name="house_label_link">拎包入住</a>
				<a name="house_label_link">视野好</a>
			</div>
		</div>
	</div>
</div>
<div class="clear">
	<hr></hr>
</div>
<div class="clear col-red tip-window-font" style="height: 45px;">
	<span> *最多六个标签，标签之间用1个空格分隔，每个标签限2~5字！<br>
		   *更多标签无法发到固定标签的网站，例搜房，House365等
	</span>
</div>
<script>
	function moreHouseLabel(thisObj) {
		if ($(thisObj).html() == '更多') {
			$(thisObj).html('隐藏');
			$("#houseLabel_more_div").show();
		} else {
			$(thisObj).html('更多');
			$("#houseLabel_more_div").hide();
		}
	}
</script>