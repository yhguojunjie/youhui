package com.yoxi.hudongtui.service.cj;

import net.paoding.rose.web.Invocation;

import com.yoxi.hudongtui.model.cj.vote.ActVoteConf;
import com.yoxi.hudongtui.model.cj.vote.ActVoteVoter;
import com.yoxi.hudongtui.utils.common.Pagination;

/**
 * 投票
 * 
 * @author jjb
 *
 * 2015-3-15
 *
 */
public interface IActVoteConfService {

	public Integer save(ActVoteConf actVoteConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActVoteConf actVoteConf);
	
	public ActVoteConf findByActId(final Integer activityId);
	
	public Pagination<ActVoteVoter> getRankPage(final Integer activityId, int currPage, int pageSize);
	
	public void exportRank(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public int countAllRank(final Integer activityId);
	
	public int updateVoteNum(final Integer id, final Integer voteNum);
	
	public int delete(final Integer id);
}
