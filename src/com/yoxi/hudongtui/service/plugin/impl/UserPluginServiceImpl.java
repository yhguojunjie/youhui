package com.yoxi.hudongtui.service.plugin.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.dao.plugin.UserPluginDAO;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.plugin.IUserPluginService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.plugin.UserPluginVo;

@Service
public class UserPluginServiceImpl implements IUserPluginService {
	@Autowired
	private UserPluginDAO userPluginDao;

	/**
	 * 查询用户插件分页对象
	 * @param param 用户ID
	 * @param param 条件
	 * @param start 每页开始行
	 * @param count 每页显示数
	 * @return List<PluginVo>
	 */
	public Pagination<UserPluginVo> findUserPlugins4Page(String userId, String param, int currPage, int pageSize) throws Exception {
		// 条件
		String condition = getOderFlag(userId, param);
		// 获取用户插件总数
		int totalCount = userPluginDao.getUserPluginCount(condition);
		// 计算开始行
		int startRow = (currPage - 1) * pageSize;
		if (startRow < 0) startRow = 0;
		List<UserPluginVo> userPluginVos = userPluginDao.findUserPlugins(condition, startRow, pageSize);
		// 判断剩余有效期
		Date dt = new Date();
		for (UserPluginVo userPluginVo : userPluginVos) {
			Date overdueTime = userPluginVo.getOverdueTime();
			if (dt.after(overdueTime)) {
				userPluginVo.setIsOld("1");
			}else{
				//快过期标志[0快过期，1未快过期]
				Date newDate = DateUtils.dateAdd(Calendar.DAY_OF_MONTH,Globals.OVERDUE_TIME,new Date());
				if(newDate.after(overdueTime)){
					userPluginVo.setOverdueFlag("0");
				}else{
					userPluginVo.setOverdueFlag("1");
				}
				userPluginVo.setIsOld("0");
			}
		}
		return new Pagination<UserPluginVo>(totalCount, pageSize, currPage, userPluginVos);
	}
	
	/**
	 * 查询用户插件总数
	 * @param param 用户ID
	 * @param param 条件
	 */
	public int getUserPluginCount(String userId, String param) throws Exception{
		String condition = getOderFlag(userId, param);
		return userPluginDao.getUserPluginCount(condition);
	}

	/**
	 * 组装排序条件
	 * @param orderCon
	 * @param xFlag
	 * @return
	 * @throws Exception
	 */
	public String getOderFlag(String userId, String param) throws Exception {
		String condition = " and b.userId = " + userId;
		// 0表示全部，1表示可使用,2表示已过期
		if ("1".equals(param)) {
			condition += " and  now() <= b.overdueTime ";
		}
		else if ("2".equals(param)) {
			condition += " and  now() > b.overdueTime ";
		}
		return condition;
	}
	
	/**
	 * 查询用户插件
	 * @param userId 查询条件
	 */
	public List<UserPluginVo> findUserPlugins4AddAct(String userId) throws Exception{
		String condition =" and now() <= a.overdueTime and a.userId = "+userId;
		List<UserPluginVo> userPluginVos = userPluginDao.findUserPlugins4AddAct(condition);
		return userPluginVos;
	}

	@Override
	public UserPlugin getById(Integer id) {
		String getStr = " id = "+id;
		return userPluginDao.getByStr(getStr);
	}
}
