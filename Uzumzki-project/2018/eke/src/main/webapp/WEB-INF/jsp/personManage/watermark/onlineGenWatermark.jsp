<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	
<link href='<%=path%>/Static/Css/watermarkConfig_acf53edf2fe626c9366e185375703e8d.css' rel='stylesheet' type='text/css'></link>
<script src='<%=path%>/Static/Js/onlineGenWatermark.js' type='text/javascript'></script>

<link rel="stylesheet" href="<%=path%>/Static/Css/jQuery-MiniColors/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/Static/Css/jQuery-MiniColors/jquery.minicolors.css">
<script src="<%=path%>/Static/Js/bootstrap.min.js"></script>
<script src="<%=path%>/Static/Js/jquery.min.js"></script>
<script src="<%=path%>/Static/Js/jquery.minicolors.js"></script>
<script src="<%=path%>/Static/Js/jquery.minicolors.min.js"></script>


<style>
		dl {
			margin: 20px 0;
		}
		dt {
			font-size: 120%;
		}
		dd {
			padding: 10px 20px 20px 20px;
		}
		dd:last-child {
			border-bottom: none;
		}
		code {
			color: black;
			border: none;
			background: rgba(128, 128, 128, .1);
		}
		pre {
			background: #f8f8f8;
			border: none;
			color: #333;
			padding: 20px;
		}
		h2 {
			margin-top: 50px;
		}
		h3 {
			color: #aaa;
		}
</style>
<script type="text/javascript">
$(document).ready( function() {
    $('.demo').each( function() {
		$(this).minicolors({
			control: $(this).attr('data-control') || 'hue',
			defaultValue: $(this).attr('data-defaultValue') || '',
			inline: $(this).attr('data-inline') === 'true',
			letterCase: $(this).attr('data-letterCase') || 'lowercase',
			opacity: $(this).attr('data-opacity'),
			position: $(this).attr('data-position') || 'bottom left',
			change: function(hex, opacity) {
				if( !hex ) return;
				if( opacity ) hex += ', ' + opacity;
				try {
					console.log(hex);
				} catch(e) {}
			},
			theme: 'bootstrap'
		});
        
    });
});


</script>
</head>
<!-- 表格区 -->
<div>
	<div id="onlienWMLeft">
		<div class="onlienWMTop" onclick="onlienWMUp();"></div>
			<div class="onlienWMContent"><div class="main"></div></div>
		<div class="onlineWMBottom" onclick="onlienWMDown();"></div>
	</div>
	<div id="onlienWMRight">
		<div id="descContainer">
  			<div class="showWMText">
  				文字内容:<input type="input" id="setWmText" value="" style="width: 290px;" onkeyup="wmTextKeyUp(this)"></input>
  			</div>
   			<div class="showWMText" style="margin-left: 90px;">字体:
   			<select id="setWmFontName" style="width: 115px;">
				<option value="宋体">宋体</option>
				<option value="微软雅黑">微软雅黑</option>
				<option value="楷体">楷体</option>
				<option value="黑体">黑体</option>
				<option value="仿宋">仿宋</option>
				<option value="隶书">隶书</option>
				<option value="幼圆">幼圆</option>
			</select>  &nbsp;
			大小:<select id="setWmFontSite">
				<option value="16">16</option>
				<option value="17">17</option>
				<option value="18">18</option>
				<option value="19">19</option>
				<option value="20">20</option>
				<option value="21">21</option>
				<option value="22">22</option>
				<option value="23">23</option>
				<option value="24">24</option>
				<option value="26">26</option>
				<option value="28">28</option>
				<option value="30">30</option>
				<option value="32">32</option>
				<option value="34">34</option>
				<option value="36">36</option>
			</select>&nbsp;
			<input type="hidden" id="wheel-demo" class="form-control demo" data-control="wheel" value="#ff99ee"></input>&nbsp;
			<b><input type="hidden" id="setWmB" value=0></input><a href="javascript:setWmB()">B</a></b>&nbsp;
			<i><input type="hidden" id="setWmI" value=0></input><a href="javascript:setWmI()">I</a></i>
			<input type="hidden" id="setWmURL"></input>
   			</div>
	   		<div class="showWM">
	   		<div class="showWMIMG">
				<div id="setWmText"></div>
	   		</div>
	   		</div>
	   		<div class="wmButton" style="margin: 5px 100px;">
	   			<input type="button" value="确定" onClick="wmOK()"></input>&nbsp;&nbsp;&nbsp;
	   			<input type="button" value="取消" onClick="wmNO()"></input>
	   		</div>
		</div>
	</div>
</div>
</html>