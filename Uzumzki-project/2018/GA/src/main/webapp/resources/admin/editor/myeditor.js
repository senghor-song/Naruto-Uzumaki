KindEditor.ready(function(K) {
	var editor;
	try {
		editor = K.create("#editor_id", {
			width : "100%",
			height : "350px",
			items : [ 'source', '|', 'undo', 'redo', '|', 'preview', 'print',
					'template', 'code', 'cut', 'copy', 'paste', 'plainpaste',
					'wordpaste', '|', 'justifyleft', 'justifycenter',
					'justifyright', 'justifyfull', 'insertorderedlist',
					'insertunorderedlist', 'indent', 'outdent', 'subscript',
					'superscript', 'clearhtml', 'quickformat', 'selectall',
					'|', 'fullscreen', '/', 'formatblock', 'fontname',
					'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight',
					'removeformat', '|', 'image', 'multiimage', 'flash',
					'media', 'insertfile', 'table', 'hr', 'emoticons',
					'baidumap', 'pagebreak', 'anchor', 'link', 'unlink', '|',
					'about' ],
			pagebreakHtml : '<hr class="pageBreak" \/>',
			filterMode : false,
			filePostName : "imgFile",
			uploadJson : '../upload.jsp',
			fileManagerJson : '../file_manager.jsp',
			allowFileManager : true,
			afterCreate : function() { this.sync();},
			afterBlur:function(){this.sync();} 
		});
	} catch (e) {

	}
	if (editor == null || typeof (editor) == "undefined") {
		editor = K.editor({
			allowFileManager : true,
			filePostName : "imgFile",
			uploadJson : '../upload.jsp',
			fileManagerJson : '../file_manager.jsp'
		});
	}
	// 图片选择和上传
	editor.loadPlugin('filemanager2', function() {
	});
	$(".browser").live('click', function() {
		$this = $(this);
		editor.plugin.filemanagerDialog2({
			imageUrl : $this.prev().val(),
			dirName : 'image',
			element : $this,
			clickFn : function(url, title, width, height, border, align) {
				$this.parent().find("input:first").val(url);
				//$this.prev("input").val(url);
           		$this.parent().find("img").attr("src", url);
				editor.hideDialog();
			}
		});
	});
});
