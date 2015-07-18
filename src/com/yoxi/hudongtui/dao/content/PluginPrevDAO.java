package com.yoxi.hudongtui.dao.content;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.yoxi.hudongtui.vo.content.PluginPrevVO;

@DAO
public interface PluginPrevDAO {
	/**
	 * 模版预告列表信息
	 * 
	 * @param startRow
	 *            每页开始行
	 * @param pageSize
	 *            每页显示数
	 * @return List<ChannelVO>
	 */
	@SQL("select a.id, a.name,a.description,a.seq,DATE_FORMAT(a.shelTime,'%Y-%m-%d') as shelTime from t_plugin_prev a order by a.seq,a.createTime  LIMIT :1,:2")
	public List<PluginPrevVO> findPluginPrevVO(int startRow, int pageSize) throws Exception;
}
