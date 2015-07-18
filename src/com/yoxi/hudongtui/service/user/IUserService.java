package com.yoxi.hudongtui.service.user;

import java.util.List;

import net.paoding.rose.jade.annotation.SQLParam;

import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.user.DevVO;
import com.yoxi.hudongtui.vo.user.RoleVO;
import com.yoxi.hudongtui.vo.user.UserVO;

/**
 * 
 * 平台用户service
 * 
 * @author wql
 * 
 *         2014-10-13
 * 
 */
public interface IUserService {

	/**
	 * 保存
	 * 
	 * @param user
	 * @return
	 */
	public Integer save(User user);

	/**
	 * 重置密码
	 * 
	 * @param user
	 * @return
	 */
	public Integer updataUserSetPassWord(Integer userId, String str);

	/**
	 * 按用id查找
	 * 
	 * @param userId
	 * @return
	 */
	public User findByUserId(Integer userId);

	/**
	 * 获取用户的权限
	 * 
	 * @param userId
	 *            用户id条件
	 * @return
	 * @throws Exception
	 */
	public List<RoleVO> findUserRole(String userId) throws Exception;

	/**
	 * 更新用户常用字段
	 * 
	 * @param user
	 * @return
	 */
	public Integer updataUserSet(User user);

	/**
	 * 获取用户好友
	 * 
	 * @param userId
	 *            用户id条件
	 * @param count
	 *            查询的个数
	 * @return
	 * @throws Exception
	 */
	public List<User> findUserFriendes(String userId, int count) throws Exception;

	/**
	 * 获取用户好友
	 * 
	 * @param userId
	 *            用户id条件
	 * @param count
	 *            查询的个数
	 * @return
	 * @throws Exception
	 */
	public int findUserFriendCount(String userId) throws Exception;

	/**
	 * 获取用户好友分页
	 * 
	 * @param userId
	 *            用户id条件
	 * @param count
	 *            查询的个数
	 * @return
	 * @throws Exception
	 */
	public Pagination<User> findUserFriendes4Page(String userId, int currPage, int pageSize) throws Exception;

	/**
	 * 获取其他开发者
	 * 
	 * @param userId
	 *            用户id条件
	 * @param count
	 *            查询的个数
	 * @return
	 * @throws Exception
	 */
	public List<UserVO> findOtherDevUsers(String userId, int count) throws Exception;

	/**
	 * 删除好友
	 * 
	 * @param userId
	 * @param removeIds
	 * @throws Exception
	 */
	public void removeUserFriends(String userId, @SQLParam("removeIds") String removeIds) throws Exception;

	/**
	 * 按拼凑的字符串查找，返回为实体
	 * 
	 * @return
	 * @throws Exception
	 */
	public User getByStr(String getStr) throws Exception;

	/**
	 * 按拼凑的字符串查找，返回为列表
	 * 
	 * @param findStr
	 * @return
	 * @throws Exception
	 */
	public List<User> findByStr(@SQLParam("findStr") String findStr) throws Exception;

	/**
	 * 获取开发者信息
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public DevVO getDevInfo(Integer userId) throws Exception;
}
