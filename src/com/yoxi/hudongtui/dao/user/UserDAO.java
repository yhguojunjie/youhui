package com.yoxi.hudongtui.dao.user;

import java.util.Date;
import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.vo.user.DevVO;
import com.yoxi.hudongtui.vo.user.RoleVO;
import com.yoxi.hudongtui.vo.user.UserExsitVO;
import com.yoxi.hudongtui.vo.user.UserVO;

/**
 * 
 * 用户
 * 
 * @author wql
 * 
 *         2014-10-13
 */
@DAO
public interface UserDAO {

	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	@SQL("insert into t_user (#if(:1.account){account,}#if(:1.email){email,}#if(:1.mobile){mobile,}#if(:1.mobileOpen){mobileOpen,}"
			+ "#if(:1.qqAccount){qqAccount,}#if(:1.weixinAccount){weixinAccount,}#if(:1.nickName){nickName,}"
			+ "#if(:1.headimgUrl){headimgUrl,}#if(:1.role){role,}#if(:1.sex){sex,}#if(:1.realName){realName,}"
			+ "#if(:1.address){address,}#if(:1.personIdCard){personIdCard,}#if(:1.source){source,}#if(:1.repreCoin){repreCoin,}"
			+ "#if(:1.friendNum){friendNum,}#if(:1.qcode){qcode,}#if(:1.introduce){introduce,}#if(:1.pluginNum){pluginNum,}"
			+ "#if(:1.province){province,}#if(:1.city){city,}#if(:1.district){district,}#if(:1.password){password,}#if(:1.state){state,}"
			+ "#if(:1.agentId){agentId,}#if(:1.dealOrederNum){dealOrederNum,}#if(:1.dealAmount){dealAmount,}#if(:1.updateTime){updateTime,}#if(:1.createTime){createTime}) "
			+ "values (#if(:1.account){:1.account,}#if(:1.email){:1.email,}#if(:1.mobile){:1.mobile,}#if(:1.mobileOpen){:1.mobileOpen,}"
			+ "#if(:1.qqAccount){:1.qqAccount,}#if(:1.weixinAccount){:1.weixinAccount,}#if(:1.nickName){:1.nickName,}"
			+ "#if(:1.headimgUrl){:1.headimgUrl,}#if(:1.role){:1.role,}#if(:1.sex){:1.sex,}#if(:1.realName){:1.realName,}"
			+ "#if(:1.address){:1.address,}#if(:1.personIdCard){:1.personIdCard,}#if(:1.source){:1.source,}#if(:1.repreCoin){:1.repreCoin,}"
			+ "#if(:1.friendNum){:1.friendNum,}#if(:1.qcode){:1.qcode,}#if(:1.introduce){:1.introduce,}#if(:1.pluginNum){:1.pluginNum,}"
			+ "#if(:1.province){:1.province,}#if(:1.city){:1.city,}#if(:1.district){:1.district,}#if(:1.password){:1.password,}#if(:1.state){:1.state,}"
			+ "#if(:1.agentId){:1.agentId,}#if(:1.dealOrederNum){:1.dealOrederNum,}#if(:1.dealAmount){:1.dealAmount,}#if(:1.updateTime){:1.updateTime,}#if(:1.createTime){:1.createTime})")
	public Identity add(User user);

	/**
	 * 按指定属性修改字段
	 * 
	 * @param third
	 * @param key
	 * @param value
	 * @return
	 */
	@SQL("update t_user set ##(:key) = :value where userId = :1")
	public int updateByCriteria(Integer UserId, @SQLParam("key") String key, @SQLParam("value") String value);

	/**
	 * 修改实体
	 * 
	 * @param third
	 * @return
	 */
	@SQL("update t_user set account = :1.account, email = :1.email, mobile = :1.mobile, mobileOpen = :1.mobileOpen,"
			+ "qqAccount = :1.qqAccount,weixinAccount = :1.weixinAccount, nickName = :1.nickName,  headimgUrl = :1.headimgUrl, "
			+ "role = :1.role,sex = :1.sex, realName = :1.realName,address = :1.address,personIdCard = :1.personIdCard,source = :1.source,"
			+ "repreCoin = :1.repreCoin,friendNum = :1.friendNum,qcode = :1.qcode,introduce = :1.introduce,pluginNum = :1.pluginNum,province = :1.province,"
			+ "city = :1.city,district = :1.district,password = :1.password,state = :1.state,agentId = :1.agentId,dealOrederNum = :1.dealOrederNum,"
			+ "dealAmount = :1.dealAmount,createTime = :1.createTime,updateTime = NOW() where userId = :1.userId")
	public int update(User user);

	/**
	 * 修改前端用户设置中的内容
	 * 
	 * @param user
	 * @return
	 */
	@SQL("update t_user set  email = :1.email,mobile = :1.mobile,mobileOpen = :1.mobileOpen,weixinAccount = :1.weixinAccount,"
			+ "qqAccount = :1.qqAccount,nickName = :1.nickName,headimgUrl = :1.headimgUrl,introduce = :1.introduce,province = :1.province,"
			+ "city = :1.city,updateTime = NOW() where userId = :1.userId")
	public int updateUserSet(User user);

	/**
	 * 按userId查找实体
	 * 
	 * @param userId
	 * @return
	 */
	@SQL("select * from t_user where userId = :1")
	public User findByUserId(Integer userId);

	/**
	 * 按账号查找该用户是否存在
	 * 
	 * @param account
	 * @return
	 */
	@SQL("select userId from t_user where account = :1")
	public UserExsitVO findExistByAccount(String account);

	/**
	 * 按用户id查找更新时间
	 * 
	 * @param key
	 * @param userId
	 * @return
	 */
	@SQL("select updateTime from t_user where userId = :1")
	public Date findUpdateTimeByUserId(Integer userId);

	/**
	 * 获取用户好友
	 * 
	 * @param condition
	 *            用户id条件
	 * @param count
	 *            查询的个数
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT b.* FROM t_friends a,t_user b" + " where a.friendId = b.userId ##(:condition) LIMIT 0,:2")
	public List<User> findUserFriendes(@SQLParam("condition") String condition, int count) throws Exception;

	/**
	 * 获取用户好友
	 * 
	 * @param condition
	 *            用户id条件
	 * @param count
	 *            查询的个数
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT count(*) FROM t_friends a,t_user b" + " where a.friendId = b.userId ##(:condition)")
	public int findUserFriendCount(@SQLParam("condition") String condition) throws Exception;

	/**
	 * 获取用户的权限
	 * 
	 * @param condition
	 *            用户id条件
	 * @param count
	 *            查询的个数
	 * @return
	 * @throws Exception
	 */
	@SQL("select a.id,a.roleName,a.roleCode,b.userid from t_s_role a LEFT JOIN t_s_role_user b on a.id=b.roleid ##(:condition)")
	public List<RoleVO> findUserRole(@SQLParam("condition") String condition) throws Exception;

	/**
	 * 获取用户好友(分页)
	 * 
	 * @param condition
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT b.* FROM t_friends a,t_user b" + " where a.friendId = b.userId ##(:condition) LIMIT :2,:3")
	public List<User> findUserFriende4Page(@SQLParam("condition") String condition, int startRow, int pageSize) throws Exception;

	/**
	 * 获取其他开发者
	 * 
	 * @param condition
	 *            条件
	 * @param count
	 *            查询的个数
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT a.*,(select count(*) from t_plugin b where b.userId=a.userId  ) as countNum from t_user a where 1=1 ##(:condition) LIMIT 0,:2")
	public List<UserVO> findOtherDevUsers(@SQLParam("condition") String condition, int count) throws Exception;

	/**
	 * 删除好友
	 * 
	 * @param userId
	 * @param removeIds
	 * @throws Exception
	 */
	@SQL("DELETE  FROM t_friends  where userId = :1  and friendId in ##(:removeIds)")
	public void removeUserFriends(String userId, @SQLParam("removeIds") String removeIds) throws Exception;

	/**
	 * 按拼凑字符串来查，确保返回记录为唯一
	 * 
	 * @param getstr
	 * @return
	 */
	@SQL("SELECT * FROM t_user WHERE ##(:getStr) LIMIT 0,1")
	public User getByStr(@SQLParam("getStr") String getStr);

	/**
	 * 按拼凑字符串来查, 返回值为列表
	 * 
	 * @param findStr
	 * @return
	 */
	@SQL("SELECT * FROM t_user WHERE ##(:findStr)")
	public List<User> findByStr(@SQLParam("findStr") String findStr);

	/**
	 * 按拼凑字符串更新
	 * 
	 * @param pluginId
	 * @param upstr
	 * @return
	 */
	@SQL("update t_user set updateTime = NOW() ##(:upstr) where userId = :1")
	public int upByStr(Integer userId, @SQLParam("upstr") String upstr);

	/**
	 * 计算总人数
	 * 
	 * @return
	 */
	@SQL("SELECT COUNT(*) FROM t_user")
	public int getTotalCount();

	/**
	 * 查询开发者信息
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@SQL("SELECT userId,nickName,headimgUrl,email,mobile,mobileOpen,qqAccount,qqOpen," + "weixinAccount,weixinOpen,sex,introduce,province,city,district " + "FROM t_user where userId = :1")
	public DevVO getDevInfo(Integer userId) throws Exception;

}
