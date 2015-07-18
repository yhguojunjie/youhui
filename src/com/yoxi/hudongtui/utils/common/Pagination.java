package com.yoxi.hudongtui.utils.common;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 名称：Pagination
 * 作用：分页对象
 * 作者：wangql 2013-4-16
 *
 */
@SuppressWarnings("serial")
public class Pagination<E> implements Serializable {
	protected final Log logger = LogFactory.getLog(this.getClass());

	// 从多少条
	private int from;
	// 到多少条
	private int end;
	// 一共有多少条记录
	private int totalCount = 0;
	// 每页显示多少条记录
	private int count = 0;
	// 当前第几页
	private int currentPage = 1;
	// 记录集
	private List<E> pageResult;

	private int totalPage = 1;

	private boolean hasNextPage = false;

	private boolean hasPrePage = false;

	public Pagination() {}

	public Pagination(int count, int currentPage) {
		this.setCount(count);
		this.setCurrentPage(currentPage);
	}

	
	public Pagination(int totalCount, int count, int currentPage, List<E> pageResult) {
		super();
		this.totalCount = totalCount;
		this.count = count;
		this.currentPage = currentPage;
		this.pageResult = pageResult;
	}
	
//	public static Pagination makePagination() {
//		return new Pagination();
//	}
//
//	public static Pagination makePagination(int count, int currentPage) {
//		return new Pagination(count, currentPage);
//	}

	public int getCount() {
		
		return count;
	}

	public void setCount(int count) {
		if (count < 0) {
			this.count = 0;
		}
		else {
			this.count = count;
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 设置当前页
	 * 
	 * 
	 * @param currentPage
	 */
	public void setCurrentPage(int currentPage) {
		if (currentPage <= 0) {
			this.currentPage = 1;
		}
		else {
			this.currentPage = currentPage;
		}
	}

	/**
	 * 是否还有下一页
	 * 
	 * 
	 * @return
	 */
	public boolean hasNextPage() {
		this.hasNextPage = this.currentPage < this.getTotalPage() ? true : false;
		return this.hasNextPage;
	}

	/**
	 * 是否还有上一页
	 * 
	 * 
	 * @return
	 */
	public boolean hasPrePage() {
		this.hasPrePage = this.currentPage > 1 ? true : false;
		return this.hasPrePage;
	}

	/**
	 * 获得取记录的起始位置
	 * 
	 * @return
	 */
	public int getStartIndex() {
		int startIndex = (this.currentPage - 1) * count;
		return startIndex;
	}

	/**
	 * 获得总的页数
	 * 
	 * @return
	 */
	public int getTotalPage() {
		this.totalPage = this.totalCount / count;
		if ((totalPage * count) < this.totalCount || this.totalCount == 0) {
			this.totalPage++;
		}
		return this.totalPage;
	}

	/**
	 * 返回一页的记录集
	 * 
	 * 
	 * @return
	 */
	public List<E> getPageResult() {
		return pageResult;
	}

	/**
	 * 设置一页里面的记录集
	 * 
	 * 
	 * @param pageResult
	 */
	public void setPageResult(List<E> pageResult) {
		this.pageResult = pageResult;
	}

	/**
	 * 获取from 和 end 值
	 * 
	 * @param pageResult
	 */
/*	public void getLimitValue(){
		from = ((currentPage - 1) * count <= 0) ? 1 : (currentPage - 1) * count + 1;
		end = from + count - 1;
	}*/

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}


	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPrePage() {
		return hasPrePage;
	}

	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}