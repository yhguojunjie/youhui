package com.yoxi.hudongtui.service.cj;

import java.util.List;

import net.paoding.rose.web.Invocation;

import com.yoxi.hudongtui.model.cj.question.ActQuestionBank;
import com.yoxi.hudongtui.model.cj.question.ActQuestionConf;
import com.yoxi.hudongtui.model.cj.question.ActQuestionRecord;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.vo.cj.question.QuestionAwardsVo;
import com.yoxi.hudongtui.vo.cj.question.QuestionRankVo;

/**
 * 一战到底
 * 
 * @author jjb
 *
 * 2015-3-1
 *
 */
public interface IActQuestionConfService {

	public Integer save(ActQuestionConf actQuestionConf, final Integer userPluginId) throws Exception;
	
	public Integer update(ActQuestionConf actQuestionConf);
	
	public ActQuestionConf findByActId(final Integer activityId);
	
	public List<QuestionAwardsVo> parseJson(final String content);
	//分页获取记录
	public Pagination<ActQuestionRecord> getRecordPage(final Integer activityId, int currPage, int pageSize);
	
	public Pagination<QuestionRankVo> getRankPage(final Integer activityId, int currPage, int pageSize);
	
	public void exportRecord(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public void exportRank(final Integer activityId, final Invocation inv, final Integer startRow,
			final Integer pageSize);
	
	public int countAllRecord(final Integer activityId);
	
	public int countAllScore(final Integer activityId);
	
	public String changeOpStatus(final Integer id);
	
	public int updateBestScore(final Integer id, final Integer playTime, final Integer rightNum);
	
	public void savePrize(final Integer activityId, final String awards);
	
	public Pagination<ActQuestionBank> getBankPage(final Integer activityId, int currPage, int pageSize);
	
	public Integer addQuestion(ActQuestionBank actQuestionBank);
	
	public Integer updateQuestion(ActQuestionBank actQuestionBank);
	
	public Integer removeQuestion(final Integer id);
}
