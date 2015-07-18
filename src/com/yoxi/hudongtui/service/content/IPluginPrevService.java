package com.yoxi.hudongtui.service.content;

import java.util.List;

import com.yoxi.hudongtui.vo.content.PluginPrevVO;

/**
 * 
 * 模板预告
 *
 * @author wql
 *
 * @Date 2015年3月22日
 *
 */
public interface IPluginPrevService {

	public List<PluginPrevVO> findPluginPrevList(int start, int count)throws Exception;
}
