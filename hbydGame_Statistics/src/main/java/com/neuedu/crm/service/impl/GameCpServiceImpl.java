package com.neuedu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.crm.dao.GameCpDao;
import com.neuedu.crm.service.IGameCpService;
import com.neuedu.crm.vo.ComboboxDataVo;

@Service
public class GameCpServiceImpl implements IGameCpService {

	@Autowired
	private GameCpDao gameCpDao;

	@Override
	public List<ComboboxDataVo> getCpCombobox() {
		List<ComboboxDataVo> list = this.gameCpDao.getCpCombobox();
		return list;
	}

}
