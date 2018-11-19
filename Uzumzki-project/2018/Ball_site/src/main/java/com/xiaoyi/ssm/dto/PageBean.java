package com.xiaoyi.ssm.dto;

import java.io.Serializable;

/**  
 * @Description: 分页实体
 * @author 宋高俊  
 * @date 2018年6月25日 下午7:44:15 
 */ 
public class PageBean implements Serializable {
	private static final long serialVersionUID = 8656597559014685635L;
	/** 总记录数 */
	private long total;
	/** 第几页 */
	private int pageNum = 1;
	/** 每页记录数 */
	private int pageSize = 10;
	/** 总页数 */
	private int pages;
	/** 当前页的数量 <= pageSize，该属性来自ArrayList的size属性 */
	private int size;

	public PageBean() {  
        super();  
    }  
	
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
