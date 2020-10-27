package com.neuedu.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.neuedu.crm.vo.ComboboxDataVo;

@Service
public interface IGameCpService {
	// 获取cp下拉别表
	public List<ComboboxDataVo> getCpCombobox();

}
