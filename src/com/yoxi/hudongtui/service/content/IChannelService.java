package com.yoxi.hudongtui.service.content;

import java.util.List;

import com.yoxi.hudongtui.model.content.Channel;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.user.ChannelVO;

/**
 * 
 * 渠道
 * 
 * @author wql
 * 
 * @Date 2015年3月22日
 * 
 */
public interface IChannelService {

	/**
	 * 保存渠道
	 * 
	 * @param channel
	 * @return
	 * @throws Exception
	 */
	public Integer save(Channel channel);

	/**
	 * 查找我的渠道列表
	 * 
	 * @param userId
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<ChannelVO> findMyChannelRecList(Integer userId, int start, int count) throws Exception;

	/**
	 * 查找推荐渠道列表
	 * 
	 * @param agentId
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<ChannelVO> findChannelRecList(String zncondition, Integer agentId, int start, int count) throws Exception;

	/**
	 * 查找渠道列表
	 * 
	 * @param agentId
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<ChannelVO> findChannelList(String xFlag, String typeConStr, String orderStrCon, Integer agentId, int start, int count) throws Exception;

	public List<ChannelVO> findAgentChannelList(String xFlag, String typeConStr, String orderStrCon, Integer agentId, int start, int count) throws Exception;

	/**
	 * 查找渠道加载更多列表
	 * 
	 * @param agentId
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<ChannelVO> findChannelLoadList(String xFlag, String typeConStr, String orderStrCon, Integer agentId, int start, int count) throws Exception;

	public List<ChannelVO> findAgentChannelRecList(String zncondition, Integer agentId, int start, int count) throws Exception;

	public Pagination<ChannelVO> findAgentChannelPage(int currPage, String xFlag, String typeConStr, String orderStrCon, Integer agentId, int start, int count) throws Exception;

	/**
	 * 组装类型条件
	 * 
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	public String getTypeCon(String typeCon) throws Exception;

	/**
	 * 组装排序条件
	 * 
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	public String getOderFlag(String orderCon, String xFlag) throws Exception;

	/**
	 * 查询渠道分页对象
	 * 
	 * @param typeCon
	 *            插件类型条件
	 * @param condition
	 *            首页排序字段
	 * @param currPage
	 *            每页开始行
	 * @param count
	 *            每页显示数
	 * @return List<PluginVo>
	 * 
	 *         public Pagination<ChannelVO> findChannelPage(String typeCon,
	 *         String oderByCon, int currPage, int pageSize) throws Exception;
	 */
	/**
	 * 查询插件分页对象
	 * 
	 * @param typeCon
	 *            插件类型条件
	 * @param condition
	 *            首页排序字段
	 * @param currPage
	 *            每页开始行
	 * @param count
	 *            每页显示数
	 * @return List<PluginVo>
	 */
	public Pagination<ChannelVO> findChannelPage(int currPage, String xFlag, String typeConStr, String orderStrCon, Integer agentId, int start, int count) throws Exception;

	/**
	 * 获取渠道个数
	 * 
	 * @param userId
	 *            用户id条件
	 * @throws Exception
	 */
	public int getChannelCount(String userId) throws Exception;
}
