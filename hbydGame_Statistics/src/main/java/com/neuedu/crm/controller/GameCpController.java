package com.neuedu.crm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.crm.service.IGameCpService;
import com.neuedu.crm.vo.ComboboxDataVo;

@RestController
@RequestMapping("gameCp")
public class GameCpController {

	@Autowired
	private IGameCpService gameCpService;

	@RequestMapping(value = "/getCpCombobox")
	public List<ComboboxDataVo> getChannelCombobox(HttpServletRequest request) {
		// Map<String, String> map = WebParameterUtils.getParameterMap(request);
		List<ComboboxDataVo> list = this.gameCpService.getCpCombobox();
		return list;
	}

}
