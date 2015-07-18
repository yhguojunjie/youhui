package com.yoxi.hudongtui.service.user.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.dao.user.UserDAO;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.service.user.IUserService;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.ValidateUtil;
import com.yoxi.hudongtui.vo.user.DevVO;
import com.yoxi.hudongtui.vo.user.RoleVO;
import com.yoxi.hudongtui.vo.user.UserVO;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public Integer save(User user) {
		return userDAO.add(user).intValue();
	}

	@Override
	public User findByUserId(Integer userId) {
		User user = userDAO.findByUserId(userId);
		// 设置头像
		if (user.getHeadimgUrl() != null) {
			if (user.getHeadimgUrl().startsWith("group")) {
				user.setHeadimgUrl(ReadProperties.getPara("fileAccessPath") + user.getHeadimgUrl());
			}
		}
		return user;
	}

	/**
	 * 更新用户常用户字段
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public Integer updataUserSet(User user) {
		return userDAO.updateUserSet(user);
	}

	/**
	 * 重置密码
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public Integer updataUserSetPassWord(Integer userId, String str) {
		return userDAO.upByStr(userId, str);
	}

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
	public List<User> findUserFriendes(String userId, int count) throws Exception {
		String condition = "";
		if (!ValidateUtil.isEmpty(userId)) {
			condition = " and a.userId = " + userId;
		}
		List<User> friendList = userDAO.findUserFriendes(condition, count);

		if (CollectionUtils.isNotEmpty(friendList)) {
			for (User user : friendList) {
				// 设置头像
				if (user.getHeadimgUrl() != null) {
					if (user.getHeadimgUrl().startsWith("group")) {
						user.setHeadimgUrl(ReadProperties.getPara("fileAccessPath") + user.getHeadimgUrl());
					}
				}
			}
		}
		return friendList;
	}

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
	public Pagination<User> findUserFriendes4Page(String userId, int currPage, int pageSize) throws Exception {
		String condition = "";
		if (!ValidateUtil.isEmpty(userId)) {
			condition = " and a.userId = " + userId;
		}
		// 获取用户好友总数
		int totalCount = userDAO.findUserFriendCount(condition);
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0)
			startRow = 0;
		List<User> friendList = userDAO.findUserFriende4Page(condition, startRow, pageSize);

		if (CollectionUtils.isNotEmpty(friendList)) {
			for (User user : friendList) {
				// 设置头像
				if (user.getHeadimgUrl() != null) {
					if (user.getHeadimgUrl().startsWith("group")) {
						user.setHeadimgUrl(ReadProperties.getPara("fileAccessPath") + user.getHeadimgUrl());
					}
				}
			}
		}
		return new Pagination<User>(totalCount, pageSize, currPage, friendList);
	}

	/**
	 * 获取用户好友数
	 * 
	 * @param userId
	 *            用户id条件
	 * @return
	 * @throws Exception
	 */
	public int findUserFriendCount(String userId) throws Exception {
		String condition = "";
		if (!ValidateUtil.isEmpty(userId)) {
			condition = " and a.userId = " + userId;
		}
		return userDAO.findUserFriendCount(condition);
	}

	/**
	 * 获取用户的权限
	 * 
	 * @param userId
	 *            用户id条件
	 * @return
	 * @throws Exception
	 */
	public List<RoleVO> findUserRole(String userId) throws Exception {
		String condition = "";
		if (!ValidateUtil.isEmpty(userId)) {
			condition = " where  b.userId = " + userId;
		}
		return userDAO.findUserRole(condition);
	}

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
	public List<UserVO> findOtherDevUsers(String userId, int count) throws Exception {
		String condition = " and a.role = '2' and a.userId != " + userId;

		List<UserVO> otherDevList = userDAO.findOtherDevUsers(condition, count);

		if (CollectionUtils.isNotEmpty(otherDevList)) {
			for (UserVO userVO : otherDevList) {
				// 设置头像
				if (userVO.getHeadimgUrl() != null) {
					if (userVO.getHeadimgUrl().startsWith("group")) {
						userVO.setHeadimgUrl(ReadProperties.getPara("fileAccessPath") + userVO.getHeadimgUrl());
					}
				}
			}
		}

		return otherDevList;
	}

	/**
	 * 删除好友
	 * 
	 * @param userId
	 * @param removeIds
	 * @throws Exception
	 */
	public void removeUserFriends(String userId, String removeIds) throws Exception {
		removeIds = removeIds.substring(0, removeIds.length() - 1);
		userDAO.removeUserFriends(userId, "(" + removeIds + ")");
	}

	@Override
	public User getByStr(String getStr) throws Exception {
		return userDAO.getByStr(getStr);
	}

	@Override
	public List<User> findByStr(String findStr) throws Exception {
		return userDAO.findByStr(findStr);
	}

	@Override
	public DevVO getDevInfo(Integer userId) throws Exception {
		return userDAO.getDevInfo(userId);
	}

}
