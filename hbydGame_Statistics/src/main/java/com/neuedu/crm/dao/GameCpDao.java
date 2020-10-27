package com.neuedu.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.neuedu.crm.vo.ComboboxDataVo;

@Repository
public interface GameCpDao {
	// 获取cp下拉别表
	public List<ComboboxDataVo> getCpCombobox();

}
