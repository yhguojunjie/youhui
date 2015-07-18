package com.yoxi.hudongtui.dao.content;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.content.Channel;
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
@DAO
public interface ChannelDAO {
	/**
	 * 查询渠道总数
	 * 
	 * @param condition
	 *            查询条件
	 * @return List<PluginVo>
	 */
	@SQL("select count(*) from t_channel a " + " LEFT JOIN t_channel_rec b on b.channelId=a.id ##(:condition)")
	public int getChannelCount(@SQLParam("condition") String condition) throws Exception;

	/**
	 * 查询渠道总数（代理商）
	 * 
	 * @param condition
	 *            查询条件
	 * @return List<PluginVo>
	 */
	@SQL("select count(*) from t_agent_channel c left join t_channel a on c.channelId=a.id " + " LEFT JOIN t_channel_rec b on b.channelId=a.id ##(:condition)")
	public int getAgentChannelCount(@SQLParam("condition") String condition) throws Exception;

	/**
	 * 添加
	 * 
	 * @param channel
	 * @return
	 */
	@SQL("insert into t_channel (#if(:1.attentionLink){attentionLink,}#if(:1.microNum){microNum,}#if(:1.downloadLink){downloadLink,}#if(:1.mobile){mobile,}#if(:1.email){email,}#if(:1.userId){userId,}#if(:1.type){type,}#if(:1.name){name,}#if(:1.logo){logo,}"
			+ "#if(:1.introduce){introduce,}#if(:1.qrcode){qrcode,}#if(:1.website){website,}"
			+ "#if(:1.fansNum){fansNum,}#if(:1.price){price,}#if(:1.qq){qq,}#if(:1.createTime){createTime,}"
			+ "#if(:1.updateTime){updateTime,}#if(:1.auditState){auditState,}#if(:1.agentId){agentId})"
			+ "values (#if(:1.attentionLink){:1.attentionLink,}#if(:1.microNum){:1.microNum,}#if(:1.downloadLink){:1.downloadLink,}#if(:1.mobile){:1.mobile,}#if(:1.email){:1.email,}#if(:1.userId){:1.userId,}#if(:1.type){:1.type,}#if(:1.name){:1.name,}#if(:1.logo){:1.logo,}"
			+ "#if(:1.introduce){:1.introduce,}#if(:1.qrcode){:1.qrcode,}#if(:1.website){:1.website,}"
			+ "#if(:1.fansNum){:1.fansNum,}#if(:1.price){:1.price,}#if(:1.qq){:1.qq,}#if(:1.createTime){:1.createTime,}"
			+ "#if(:1.updateTime){:1.updateTime,}#if(:1.auditState){:1.auditState,}#if(:1.agentId){:1.agentId})")
	public Identity add(Channel channel);

	/**
	 * 渠道推荐列表信息
	 * 
	 * @param startRow
	 *            每页开始行
	 * @param pageSize
	 *            每页显示数
	 * @return List<ChannelVO>
	 */
	@SQL("select a.id, a.name,a.logo, " + " a.introduce,a.fansNum," + " a.price, a.type,a.updateTime,a.createTime," + " a.qq,a.website,a.qrcode,a.userId from t_channel a "
			+ " ##(:condition) LIMIT :2,:3 ")
	public List<ChannelVO> findChannelRecVO(@SQLParam("condition") String condition, int startRow, int pageSize) throws Exception;

	/**
	 * 渠道推荐列表信息（代理商）
	 * 
	 * @param startRow
	 *            每页开始行
	 * @param pageSize
	 *            每页显示数
	 * @return List<ChannelVO>
	 */
	@SQL("select a.id, a.name,a.logo, " + " a.introduce,a.fansNum," + " a.price, a.type,a.updateTime,a.createTime,"
			+ " a.qq,a.website,a.qrcode,a.userId from t_agent_channel b left join t_channel a on b.channelId=a.id" + " ##(:condition) LIMIT :2,:3 ")
	public List<ChannelVO> findAgentChannelRecVO(@SQLParam("condition") String condition, int startRow, int pageSize) throws Exception;

	// @SQL("select a.id, a.name,a.logo, " + " a.introduce,a.fansNum," +
	// " a.price, a.type,a.updateTime,a.createTime," +
	// " a.qq,a.website,a.qrcode,a.userId from t_channel a "
	// + " INNER JOIN t_channel_rec b on b.channelId=a.id LIMIT :1,:2 ")
	// public List<ChannelVO> findChannelRecVO(int startRow, int pageSize)
	// throws Exception;

	/**
	 * 我的渠道列表信息
	 * 
	 * @param startRow
	 *            每页开始行
	 * @param pageSize
	 *            每页显示数
	 * @return List<ChannelVO>
	 */
	@SQL("select a.id, a.name,a.logo, " + " a.introduce,a.fansNum," + " a.price, a.type,a.updateTime,a.createTime," + " a.qq,a.website,a.qrcode,a.userId from t_channel a "
			+ " LEFT JOIN t_channel_rec b on b.channelId=a.id ##(:condition) LIMIT :2,:3 ")
	public List<ChannelVO> findMyChannelRecVO(@SQLParam("condition") String condition, int startRow, int pageSize) throws Exception;

	/**
	 * 渠道列表信息
	 * 
	 * @param startRow
	 *            每页开始行
	 * @param pageSize
	 *            每页显示数
	 * @return List<ChannelVO>
	 */
	@SQL("select a.id, a.name,a.logo, " + " a.introduce,a.fansNum," + " a.price, a.type,a.updateTime,a.createTime," + " a.qq,a.website,a.qrcode,a.userId from t_channel " + " a  where "
			+ " ##(:condition) LIMIT :2,:3")
	// @SQL("select a.id, a.name,a.logo, " + " a.introduce,a.fansNum," +
	// " a.price, a.type,a.updateTime,a.createTime," +
	// " a.qq,a.website,a.qrcode,a.userId from t_channel "
	// + " a LEFT JOIN t_agent_info b on b.id=a.agentId where a.id " +
	// " not in (select c.channelId from t_channel_rec c) ##(:condition) LIMIT :2,:3")
	public List<ChannelVO> findChannelVO(@SQLParam("condition") String condition, int startRow, int pageSize) throws Exception;

	/**
	 * 渠道列表信息（代理商）
	 * 
	 * @param startRow
	 *            每页开始行
	 * @param pageSize
	 *            每页显示数
	 * @return List<ChannelVO>
	 */
	@SQL("select a.id, a.name,a.logo, " + " a.introduce,a.fansNum," + " a.price, a.type,a.updateTime,a.createTime,"
			+ " a.qq,a.website,a.qrcode,a.userId from t_agent_channel c left join t_channel a on c.channelId=a.id " + "  where " + " ##(:condition) LIMIT :2,:3")
	// @SQL("select a.id, a.name,a.logo, " + " a.introduce,a.fansNum," +
	// " a.price, a.type,a.updateTime,a.createTime," +
	// " a.qq,a.website,a.qrcode,a.userId from t_channel "
	// + " a LEFT JOIN t_agent_info b on b.id=a.agentId where a.id " +
	// " not in (select c.channelId from t_channel_rec c) ##(:condition) LIMIT :2,:3")
	public List<ChannelVO> findAgentChannelVO(@SQLParam("condition") String condition, int startRow, int pageSize) throws Exception;

	/**
	 * 渠道列表加载更多信息
	 * 
	 * @param startRow
	 *            每页开始行
	 * @param pageSize
	 *            每页显示数
	 * @return List<ChannelVO>
	 */
	@SQL("select a.id, a.name,a.logo, " + " a.introduce,a.fansNum," + " a.price, a.type,a.updateTime,a.createTime," + " a.qq,a.website,a.qrcode,a.userId from t_channel "
			+ " a LEFT JOIN t_agent_info b on b.id=a.agentId and a.id " + " not in (select c.channelId from t_channel_rec c) ##(:condition) LIMIT :2,:3")
	public List<ChannelVO> findChannelLoadVO(@SQLParam("condition") String condition, int startRow, int pageSize) throws Exception;

}
