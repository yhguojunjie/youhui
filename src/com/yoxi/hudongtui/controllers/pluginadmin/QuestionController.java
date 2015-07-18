package com.yoxi.hudongtui.controllers.pluginadmin;

import java.util.Date;
import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.controllers.ActOptRequired;
import com.yoxi.hudongtui.controllers.LoginRequired;
import com.yoxi.hudongtui.model.cj.question.ActQuestionBank;
import com.yoxi.hudongtui.model.cj.question.ActQuestionConf;
import com.yoxi.hudongtui.model.cj.question.ActQuestionRecord;
import com.yoxi.hudongtui.service.cj.IActQuestionConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import com.yoxi.hudongtui.vo.cj.question.QuestionAwardsVo;
import com.yoxi.hudongtui.vo.cj.question.QuestionRankVo;

/**
 * 
 * 一战到底后台管理
 * 
 * @author jjb
 *
 * 2015-3-1
 * 
 */
@LoginRequired
public class QuestionController {

	@Autowired
	private IActQuestionConfService actQuestionConfService;
    
	 /**
     * 增加拼图活动页面跳转
     * @param inv
     * @param userPluginId
     * @return
     */
	@ActOptRequired
    @Get("add/{userPluginId}")
    public String add(final Invocation inv, @Param("userPluginId") final Integer userPluginId) {
    	inv.addModel("userPluginId", userPluginId);
        return "question/add";
    }
    
    /**
     * 添加
     * @param inv
     * @param actQuestionConf
     * @param startTime
     * @param endTime
     * @param userPluginId
     * @return
     * @throws Exception
     */
    @Post("add_do")
    public String add_do(final Invocation inv, ActQuestionConf actQuestionConf, @Param("activityTime") final String activityTime, 
    		@Param("userPluginId") final Integer userPluginId) throws Exception{
		if(activityTime == null || activityTime.length() == 0 || actQuestionConf.getAwards() == null ||
				actQuestionConf.getAwards().toString().length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/question/add/"+userPluginId);
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actQuestionConf.setStartTime(DateUtils.converDate(time[0]));
			actQuestionConf.setEndTime(DateUtils.converDate(time[1]));
		}
    	actQuestionConf.setCreateTime(new Date());
    	actQuestionConfService.save(actQuestionConf, userPluginId);
    	actQuestionConfService.savePrize(actQuestionConf.getActivityId(), actQuestionConf.getAwards().toString());
    	inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath()+"/pc/my/actList");
    	return null;
    }
    
    /**
     * 编辑活动跳转
     * @param inv
     * @param id 活动id activityId
     * @return
     * @throws Exception
     */
    @ActOptRequired
    @Get("edit/{activityId:[0-9]+}")
    public String edit(final Invocation inv, @Param("activityId") final Integer activityId)throws Exception{
    	ActQuestionConf actQuestionConf = actQuestionConfService.findByActId(activityId);
    	if( actQuestionConf != null){
    		List<QuestionAwardsVo> questionAwardsVoList = actQuestionConfService.parseJson(actQuestionConf.getAwards().toString());
        	inv.addModel("awardslist", questionAwardsVoList);
        	inv.addModel("conf", actQuestionConf);
    	}
        return "question/edit";
    
    }
    
    /**
     * 修改
     * @param inv
     * @param actQuestionConf
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @Post("edit_do")
    public String edit_do(final Invocation inv, ActQuestionConf actQuestionConf,@Param("createTime") final Date createTime,  
    		@Param("activityTime") final String activityTime)throws Exception{
		if(activityTime == null || activityTime.length() == 0 || actQuestionConf.getAwards() == null ||
				actQuestionConf.getAwards().toString().length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/question/edit/"+actQuestionConf.getActivityId());
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actQuestionConf.setStartTime(DateUtils.converDate(time[0]));
			actQuestionConf.setEndTime(DateUtils.converDate(time[1]));
		}
		actQuestionConf.setCreateTime(createTime);
    	actQuestionConfService.update(actQuestionConf);
    	actQuestionConfService.savePrize(actQuestionConf.getActivityId(), actQuestionConf.getAwards().toString());
    	inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath()+"/pc/my/actList");
    	return null;
    }
    
    @ActOptRequired
	@Get("record/{activityId:[0-9]+}")
	public String record(final Invocation inv, @Param("activityId") final Integer activityId,
			@Param("currentPage") final int currentPage, @Param("count") final int count,
			@Param("upid") final Integer userPluginId){
		Pagination<ActQuestionRecord> paginationRecord = actQuestionConfService.getRecordPage(activityId, currentPage, Globals.PAGE_SHOW_NUMBER);
		inv.addModel("recordVos", paginationRecord);
		inv.addModel("activityId", activityId);
		inv.addModel("userPluginId", userPluginId);
		return "question/record";
	}
	
    @ActOptRequired
	@Get("rank/{activityId:[0-9]+}")
	public String rank(final Invocation inv, @Param("activityId") final Integer activityId,
			@Param("currentPage") final int currentPage, @Param("count") final int count,
			@Param("upid") final Integer userPluginId){
		Pagination<QuestionRankVo> paginationRecord = actQuestionConfService.getRankPage(activityId, currentPage, Globals.PAGE_SHOW_NUMBER);
		inv.addModel("recordVos", paginationRecord);
		inv.addModel("activityId", activityId);
		inv.addModel("userPluginId", userPluginId);
		inv.addModel("rankUrl", ReadProperties.getPara("httpPath")+"/cj/question/rank/"+activityId);
		return "question/rank";
	}
	
	//导出记录
	@Get("exportRecord/{activityId:[0-9]+}")
	public String exportRecord(final Invocation inv, @Param("activityId") final Integer activityId,
			@Param("start") final Integer start,@Param("end") final Integer end){
		int startRow = 0;
		int total = 0;
		if(start != null && end != null){
			if(start > 0)startRow = start - 1;
			total = end - start + 1;
			if(total > Globals.EXCEL_MAX_EXPORT_NUMBER){
				return "@<script>alert('"+"导出数量超过"+Globals.EXCEL_MAX_EXPORT_NUMBER+"');window.history.go(-1);</script>";
			}
		}else{
			total = actQuestionConfService.countAllRecord(activityId);
			if(total > Globals.EXCEL_MAX_EXPORT_NUMBER){
				return "@<script>alert('"+"导出数量超过"+Globals.EXCEL_MAX_EXPORT_NUMBER+"，请分段导出"+"');window.history.go(-1);</script>";
			}
		}
		actQuestionConfService.exportRecord(activityId, inv, startRow, total);
		return null;
	}
	
	@Get("exportRank/{activityId:[0-9]+}")
	public String exportRank(final Invocation inv, @Param("activityId") final Integer activityId,
			@Param("start") final Integer start,@Param("end") final Integer end){
		int startRow = 0;
		int total = 0;
		if(start != null && end != null){
			if(start > 0)startRow = start - 1;
			total = end - start + 1;
			if(total > Globals.EXCEL_MAX_EXPORT_NUMBER){
				return "@<script>alert('"+"导出数量超过"+Globals.EXCEL_MAX_EXPORT_NUMBER+"');window.history.go(-1);</script>";
			}
		}else{
			total = actQuestionConfService.countAllScore(activityId);
			if(total > Globals.EXCEL_MAX_EXPORT_NUMBER){
				return "@<script>alert('"+"导出数量超过"+Globals.EXCEL_MAX_EXPORT_NUMBER+"，请分段导出"+"');window.history.go(-1);</script>";
			}
		}
		actQuestionConfService.exportRank(activityId, inv, startRow, total);
		return null;
	}
	
	//修改兑换状态
	@Post("changeOpStatus")
	public String changeOpStatus(final Invocation inv, @Param("id") final Integer id){
		String res = actQuestionConfService.changeOpStatus(id);
		if(res == null){
			return "@json:"+ "{\"status\":\""+"1"+"\"}";
		}else{
			return "@json:"+ "{\"status\":\""+"0"+"\",\"exchangeTime\":\""+res+"\"}";
		}
	}
	
	@Post("updateScore")
	public String updateScore(final Invocation inv, @Param("id") final Integer id,
			@Param("playTime") final Integer playTime, @Param("rightNum") final Integer rightNum){
		int res = actQuestionConfService.updateBestScore(id, playTime, rightNum);
		if(res == 0){
			return "@json:"+ "{\"status\":\""+"1"+"\"}";
		}else{
			return "@json:"+ "{\"status\":\""+"0"+"\"}";
		}
	}
	
    @ActOptRequired
	@Get("bank/{activityId:[0-9]+}")
	public String bank(final Invocation inv, @Param("activityId") final Integer activityId,
			@Param("currentPage") final int currentPage, @Param("count") final int count,
			@Param("upid") final Integer userPluginId){
		Pagination<ActQuestionBank> paginationRecord = actQuestionConfService.getBankPage(activityId, currentPage, Globals.PAGE_SHOW_NUMBER);
		inv.addModel("recordVos", paginationRecord);
		inv.addModel("activityId", activityId);
		inv.addModel("userPluginId", userPluginId);
		return "question/questionbank";
	}
    
	@Post("addQuestion/{activityId:[0-9]+}")
	public String addQuestion(final Invocation inv, ActQuestionBank actQuestionBank){
		actQuestionBank.setFinalTime(new Date());
		int res = actQuestionConfService.addQuestion(actQuestionBank);
		if(res == 0){
			return "@json:"+ "{\"status\":\""+"1"+"\"}";
		}else{
			return "@json:"+ "{\"status\":\""+"0"+"\"}";
		}
	}
	
	@Post("updateQuestion/{activityId:[0-9]+}")
	public String updateQuestion(final Invocation inv, ActQuestionBank actQuestionBank){
		actQuestionBank.setFinalTime(new Date());
		int res = actQuestionConfService.updateQuestion(actQuestionBank);
		if(res == 0){
			return "@json:"+ "{\"status\":\""+"1"+"\"}";
		}else{
			return "@json:"+ "{\"status\":\""+"0"+"\"}";
		}
	}
	
	@Post("removeQuestion")
	public String removeQuestion(final Invocation inv, @Param("id") final Integer id){
		int res = actQuestionConfService.removeQuestion(id);
		if(res == 0){
			return "@json:"+ "{\"status\":\""+"1"+"\"}";
		}else{
			return "@json:"+ "{\"status\":\""+"0"+"\"}";
		}
	}
}
