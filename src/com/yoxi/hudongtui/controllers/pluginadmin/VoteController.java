package com.yoxi.hudongtui.controllers.pluginadmin;

import java.util.Date;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.constants.Globals;
import com.yoxi.hudongtui.controllers.ActOptRequired;
import com.yoxi.hudongtui.controllers.LoginRequired;
import com.yoxi.hudongtui.model.cj.vote.ActVoteConf;
import com.yoxi.hudongtui.model.cj.vote.ActVoteVoter;
import com.yoxi.hudongtui.service.cj.IActVoteConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;

/**
 * 投票
 * 
 * @author jjb
 *
 * 2015-3-15
 *
 */
@LoginRequired
public class VoteController {

	@Autowired
	private IActVoteConfService actVoteConfService;
    
	 /**
     * 增加投票活动页面跳转
     * @param inv
     * @param userPluginId
     * @return
     */
	@ActOptRequired
    @Get("add/{userPluginId}")
    public String add(final Invocation inv, @Param("userPluginId") final Integer userPluginId) {
    	inv.addModel("userPluginId", userPluginId);
        return "vote/add";
    }
    
    /**
     * 添加
     * @param inv
     * @param actVoteConf
     * @param startTime
     * @param endTime
     * @param userPluginId
     * @return
     * @throws Exception
     */
    @Post("add_do")
    public String add_do(final Invocation inv, ActVoteConf actVoteConf, @Param("activityTime") final String activityTime, 
    		@Param("userPluginId") final Integer userPluginId) throws Exception{
		if(activityTime == null || activityTime.length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/vote/add/"+userPluginId);
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actVoteConf.setStartTime(DateUtils.converDate(time[0]));
			actVoteConf.setEndTime(DateUtils.converDate(time[1]));
		}
    	actVoteConf.setCreateTime(new Date());
    	actVoteConfService.save(actVoteConf, userPluginId);
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
    	ActVoteConf actVoteConf = actVoteConfService.findByActId(activityId);
    	if( actVoteConf != null){
        	inv.addModel("conf", actVoteConf);
    	}
        return "vote/edit";
    
    }
    
    /**
     * 修改
     * @param inv
     * @param actVoteConf
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @Post("edit_do")
    public String edit_do(final Invocation inv, ActVoteConf actVoteConf,@Param("createTime") final Date createTime,  
    		 @Param("activityTime") final String activityTime)throws Exception{
		if(activityTime == null || activityTime.length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/vote/edit/"+actVoteConf.getActivityId());
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actVoteConf.setStartTime(DateUtils.converDate(time[0]));
			actVoteConf.setEndTime(DateUtils.converDate(time[1]));
		}
		actVoteConf.setCreateTime(createTime);
    	actVoteConfService.update(actVoteConf);
    	inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath()+"/pc/my/actList");
    	return null;
    }
    
    @ActOptRequired
	@Get("rank/{activityId:[0-9]+}")
	public String rank(final Invocation inv, @Param("activityId") final Integer activityId,
			@Param("currentPage") final int currentPage, @Param("count") final int count,
			@Param("upid") final Integer userPluginId){
		Pagination<ActVoteVoter> paginationRank = actVoteConfService.getRankPage(activityId, currentPage, Globals.PAGE_SHOW_NUMBER);
		inv.addModel("rankVos", paginationRank);
		inv.addModel("activityId", activityId);
		inv.addModel("userPluginId", userPluginId);
		return "vote/rank";
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
			total = actVoteConfService.countAllRank(activityId);
			if(total > Globals.EXCEL_MAX_EXPORT_NUMBER){
				return "@<script>alert('"+"导出数量超过"+Globals.EXCEL_MAX_EXPORT_NUMBER+"，请分段导出"+"');window.history.go(-1);</script>";
			}
		}
		actVoteConfService.exportRank(activityId, inv, startRow, total);
		return null;
	}
	
	@Post("updateVoteNum")
	public String updateVoteNum(final Invocation inv, @Param("id") final Integer id,
			@Param("voteNum") final Integer voteNum){
		int res = actVoteConfService.updateVoteNum(id, voteNum);
		if(res == 0){
			return "@json:"+ "{\"status\":\""+"1"+"\"}";
		}else{
			return "@json:"+ "{\"status\":\""+"0"+"\"}";
		}
	}
	
	@Post("delete")
	public String delete(final Invocation inv, @Param("id") final Integer id){
		int res = actVoteConfService.delete(id);
		if(res == 0){
			return "@json:"+ "{\"status\":\""+"1"+"\"}";
		}else{
			return "@json:"+ "{\"status\":\""+"0"+"\"}";
		}
	}
}
