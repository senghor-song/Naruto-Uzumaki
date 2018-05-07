/**
 * 类型搜索组件：按指定属性进行关键字过滤搜索
 */
function initSearcher(searcherId, properties, property, keyword) {
    	var label = '<label> <span>搜索：</span>'
		+ '<select id="select_search_ruiec" class="form_text">'
		+ '<option value="all">请选择</option>'
		+ '</select>'
		+ '<input id="input_keyword_ruiec" type="text" class="form_text" placeholder="关键词"/>'
		+ '<input type="submit" value="查询" class="form_search" /></label>';
    	$("#"+searcherId).append($(label));
    	var $select_search = $("#select_search_ruiec");
    	var $input_keyword = $("#input_keyword_ruiec");
    	$.each(properties, function(i, prop) {
    		$select_search.append($('<option value="'+prop['key']+'">'+prop['value']+'</option>'));
    	});
    	if (property) {
    		$select_search.val(property);
    		$input_keyword.val(keyword);
    		$select_search.attr("name", "property");
    		$input_keyword.attr("name", "keyword");
    	} else {
    		$input_keyword.attr("disabled", true);
    	}
    	$select_search.change(function() {
			var $this = $select_search;
			if ($this.val() == 'all') {
				$input_keyword.attr("disabled", true);
				$this.attr("name", "");
				$input_keyword.attr("name", "");
			} else {
				$input_keyword.attr("disabled", false);
				$this.attr("name", "property");
				$input_keyword.attr("name", "keyword");
			};
		});
    };
    
