package com.yoxi.hudongtui.service.content.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.content.ChannelDAO;
import com.yoxi.hudongtui.model.content.Channel;
import com.yoxi.hudongtui.service.content.IChannelService;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.user.ChannelVO;

@Service
public class ChannelService implements IChannelService {

	@Autowired
	private ChannelDAO channelDAO;

	/**
	 * 保存渠道
	 * 
	 * @param channel
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer save(Channel channel) {
		return channelDAO.add(channel).intValue();
	}

	/**
	 * 查找推荐渠道列表
	 * 
	 * @param agentId
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ChannelVO> findChannelRecList(String zncondition, Integer agentId, int start, int count) throws Exception {
		// TODO Auto-generated method stub
		List<ChannelVO> channelVO = channelDAO.findChannelVO(zncondition, start, count);
		return channelVO;
	}

	/**
	 * 查找推荐渠道列表（代理商）
	 * 
	 * @param agentId
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ChannelVO> findAgentChannelRecList(String zncondition, Integer agentId, int start, int count) throws Exception {
		// TODO Auto-generated method stub
		List<ChannelVO> channelVO = channelDAO.findAgentChannelVO(zncondition, start, count);
		return channelVO;
	}

	/**
	 * 查找我的渠道列表
	 * 
	 * @param userId
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ChannelVO> findMyChannelRecList(Integer userId, int start, int count) throws Exception {
		// TODO Auto-generated method stub
		String zncondition = " where a.auditState=1 and a.userId = " + userId + "";
		List<ChannelVO> channelVO = channelDAO.findMyChannelRecVO(zncondition, start, count);
		return channelVO;
	}

	/**
	 * 获取渠道个数
	 * 
	 * @param userId
	 *            用户id条件
	 * @throws Exception
	 */
	public int getChannelCount(String userId) throws Exception {
		String condition = " where a.auditState=1 and a.userId = " + userId;
		return channelDAO.getChannelCount(condition);
	}

	/**
	 * 查找渠道列表
	 * 
	 * @param agentId
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ChannelVO> findChannelList(String xFlag, String typeConStr, String orderStrCon, Integer agentId, int start, int count) throws Exception {
		String zncondition = " a.auditState=1 " + typeConStr + orderStrCon;
		// String zncondition = " and a.auditState=1 and a.agentId = " + agentId
		// + typeConStr + orderStrCon;
		List<ChannelVO> channelVO = channelDAO.findChannelVO(zncondition, start, count);
		return channelVO;
	}

	/**
	 * 查找渠道列表（代理商）
	 * 
	 * @param agentId
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ChannelVO> findAgentChannelList(String xFlag, String typeConStr, String orderStrCon, Integer agentId, int start, int count) throws Exception {
		String zncondition = " c.hideState='0' and a.auditState='1' and c.agentId = " + agentId + typeConStr + orderStrCon;
		// String zncondition = " and a.auditState=1 and a.agentId = " +
		// agentId;
		// + typeConStr + orderStrCon;
		List<ChannelVO> channelVO = channelDAO.findAgentChannelVO(zncondition, start, count);
		return channelVO;
	}

	/**
	 * 查找渠道加载更多列表
	 * 
	 * @param agentId
	 * @param start
	 * @param count
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ChannelVO> findChannelLoadList(String xFlag, String typeConStr, String orderStrCon, Integer agentId, int start, int count) throws Exception {
		// TODO Auto-generated method stub
		String zncondition = " where a.auditState=1 and a.agentId = " + agentId + typeConStr + orderStrCon;
		List<ChannelVO> channelVO = channelDAO.findChannelVO(zncondition, start, count);
		return channelVO;
	}

	/**
	 * 组装类型条件
	 * 
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getTypeCon(String typeCon) throws Exception {
		String typeCondition = "";
		// 0表示全部，1.表示即买即用
		if ("1".equals(typeCon)) {
			typeCondition = " and a.type = '1' ";
		}
		return typeCondition;
	}

	/**
	 * 组装排序条件
	 * 
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getOderFlag(String orderCon, String xFlag) throws Exception {
		// orderFlag 1表示发布时间publishTime，2.表示粉丝数buyNum,3.表示价格price
		String orderConStr = "createTime";
		if ("2".equals(orderCon)) {
			orderConStr = "fansNum";
		} else if ("3".equals(orderCon)) {
			orderConStr = "price";
		}
		// xFlag 0表示降序，1表示升序
		String sxFlag = " DESC ";
		// 0表示降序，1表示升序
		if ("1".equals(xFlag)) {
			sxFlag = "  ASC ";
		}
		return " ORDER BY  a." + orderConStr + sxFlag + " ";
	}

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
	@Override
	public Pagination<ChannelVO> findChannelPage(int currPage, String xFlag, String typeConStr, String orderStrCon, Integer agentId, int start, int count) throws Exception {
		// 根据台排序字段组织语句
		String zncondition = "a.auditState=1 and a.agentId = " + agentId + typeConStr + orderStrCon;
		// 获取插件总数
		int totalCount = channelDAO.getChannelCount(zncondition);
		// 计算开始行
		int startRow = (currPage - 1) * count;
		if (startRow < 0)
			startRow = 0;

		List<ChannelVO> channelVO = channelDAO.findChannelVO(zncondition, start, count);
		return new Pagination<ChannelVO>(totalCount, count, currPage, channelVO);
	}

	/**
	 * 查询插件分页对象（代理商）
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
	@Override
	public Pagination<ChannelVO> findAgentChannelPage(int currPage, String xFlag, String typeConStr, String orderStrCon, Integer agentId, int start, int count) throws Exception {
		// 根据台排序字段组织语句
		String zncondition = "a.auditState=1 and a.agentId = " + agentId + typeConStr + orderStrCon;
		// 获取插件总数
		int totalCount = channelDAO.getAgentChannelCount(zncondition);
		// 计算开始行
		int startRow = (currPage - 1) * count;
		if (startRow < 0)
			startRow = 0;

		List<ChannelVO> channelVO = channelDAO.findAgentChannelVO(zncondition, start, count);
		return new Pagination<ChannelVO>(totalCount, count, currPage, channelVO);
	}
}
