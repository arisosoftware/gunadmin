package cn.enilu.guns.admin.core.page;

import java.util.List;

import cn.enilu.guns.bean.vo.query.Page;

public class PageInfoBT<T> {

	// 结果集
	private List<T> rows;

	// 总数
	private long total;

	public PageInfoBT(Page<T> page) {
		this.rows = page.getRecords();
		this.total = page.getTotal();
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
