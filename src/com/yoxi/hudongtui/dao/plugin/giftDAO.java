package com.yoxi.hudongtui.dao.plugin;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

import com.yoxi.hudongtui.vo.plugin.GiftVo;

/**
 * 礼品DAO
 * @author xielj 2014-11-30
 */
@DAO
public interface giftDAO {

	/**
	 * 查询礼品情况(包含接收者信息)
	 * @param condition 查询条件
	 * @return List<gift>
	 */
	@SQL("select a.* ,(select if(isnull(k.nickName),'',k.nickName) from tchajian.t_user k where k.userId = b.drawUserId ) as drawUserName, "
			+ " (select k.headimgUrl from tchajian.t_user k where k.userId = b.drawUserId ) as drawUserheadimgUrl "
			+ " from t_gift a left JOIN t_usergift_draw b  on a.id = b.id and b.userId = :1 ")
	public List<GiftVo> findGifteInfo(String userId) throws Exception;

}
