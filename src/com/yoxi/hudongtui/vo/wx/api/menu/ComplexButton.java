package com.yoxi.hudongtui.vo.wx.api.menu;

import java.util.List;

/**
 * 复杂按钮（父按钮）
 * 
 * @author wangql
 * 
 *         2013-12-25
 * 
 */
public class ComplexButton extends Button {
	/*
	 * private Button[] sub_button;
	 * 
	 * public Button[] getSub_button() { return sub_button; }
	 * 
	 * public void setSub_button(Button[] sub_button) { this.sub_button =
	 * sub_button; }
	 */
	private List<Button> sub_button;

	public List<Button> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<Button> sub_button) {
		this.sub_button = sub_button;
	}

}
