package com.neuedu.crm.vo;

import java.io.Serializable;
import java.util.List;

public class DataResult<E> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 总记录数
	 */
//    private int total;

	/**
	 * 查询结果集列表
	 */
//    private List<E> rows;

	// 根据layui的格式返回
	private List<E> data;
	private int code;
	private String msg;
	private int count;

	public DataResult(List<E> resultList) {
//        this.rows = resultList;
		this.data = resultList;
	}

	public DataResult(List<E> resultList, int totalRecordNumber) {
//        this.rows = resultList;
		this.data = resultList;
		this.count = totalRecordNumber;
	}

	public DataResult(List<E> data, int code, String msg, int count) {
		super();
		this.data = data;
		this.code = code;
		this.msg = msg;
		this.count = count;
	}

//
//    public List<E> getRows() {
//        return this.rows;
//    }

//    public int getTotal() {
//        return this.total;
//    }

//    public void setRows(List<E> rows) {
//        this.rows = rows;
//    }

//    public void setTotal(int total) {
//        this.total = total;
//    }
//    

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
