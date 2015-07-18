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
import com.yoxi.hudongtui.model.cj.ActPintuConf;
import com.yoxi.hudongtui.model.cj.ActPintuRecord;
import com.yoxi.hudongtui.service.cj.IActPintuConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import com.yoxi.hudongtui.vo.cj.pintu.PintuAwardsVo;

/**
 * 
 * 拼图后台管理
 * 
 * @author jjb
 *
 * 2015-1-5
 * 
 */
@LoginRequired
public class PintuController {

	@Autowired
	private IActPintuConfService actPintuConfService;
    
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
        return "pintu/add";
    }
    
    /**
     * 添加
     * @param inv
     * @param actPintuConf
     * @param startTime
     * @param endTime
     * @param userPluginId
     * @return
     * @throws Exception
     */
    @Post("add_do")
    public String add_do(final Invocation inv, ActPintuConf actPintuConf, @Param("activityTime") final String activityTime, 
    		@Param("userPluginId") final Integer userPluginId) throws Exception{
		if(activityTime == null || activityTime.length() == 0 || actPintuConf.getAwards() == null ||
				actPintuConf.getAwards().toString().length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/pintu/add/"+userPluginId);
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actPintuConf.setStartTime(DateUtils.converDate(time[0]));
			actPintuConf.setEndTime(DateUtils.converDate(time[1]));
		}
    	actPintuConf.setCreateTime(new Date());
    	actPintuConfService.save(actPintuConf, userPluginId);
    	actPintuConfService.savePrize(actPintuConf.getActivityId(), actPintuConf.getAwards().toString());
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
    	ActPintuConf actPintuConf = actPintuConfService.findByActId(activityId);
    	if( actPintuConf != null){
    		List<PintuAwardsVo> pintuAwardsVoList = actPintuConfService.parseJson(actPintuConf.getAwards().toString());
        	inv.addModel("awardslist", pintuAwardsVoList);
        	inv.addModel("conf", actPintuConf);
    	}
        return "pintu/edit";
    
    }
    
    /**
     * 修改
     * @param inv
     * @param actPintuConf
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @Post("edit_do")
    public String edit_do(final Invocation inv, ActPintuConf actPintuConf,@Param("createTime") final Date createTime,  
    		@Param("activityTime") final String activityTime)throws Exception{
		if(activityTime == null || activityTime.length() == 0 || actPintuConf.getAwards() == null ||
				actPintuConf.getAwards().toString().length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/pintu/edit/"+actPintuConf.getActivityId());
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actPintuConf.setStartTime(DateUtils.converDate(time[0]));
			actPintuConf.setEndTime(DateUtils.converDate(time[1]));
		}
		actPintuConf.setCreateTime(createTime);
    	actPintuConfService.update(actPintuConf);
    	actPintuConfService.savePrize(actPintuConf.getActivityId(), actPintuConf.getAwards().toString());
    	inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath()+"/pc/my/actList");
    	return null;
    }
    
    @ActOptRequired
	@Get("record/{activityId:[0-9]+}")
	public String record(final Invocation inv, @Param("activityId") final Integer activityId,
			@Param("currentPage") final int currentPage, @Param("count") final int count,
			@Param("upid") final Integer userPluginId){
		Pagination<ActPintuRecord> paginationRecord = actPintuConfService.getRecordPage(activityId, currentPage, Globals.PAGE_SHOW_NUMBER);
		inv.addModel("recordVos", paginationRecord);
		inv.addModel("activityId", activityId);
		inv.addModel("userPluginId", userPluginId);
		return "pintu/record";
	}
	
    @ActOptRequired
	@Get("rank/{activityId:[0-9]+}")
	public String rank(final Invocation inv, @Param("activityId") final Integer activityId,
			@Param("currentPage") final int currentPage, @Param("count") final int count,
			@Param("upid") final Integer userPluginId){
		Pagination<ActPintuRecord> paginationRecord = actPintuConfService.getRankPage(activityId, currentPage, Globals.PAGE_SHOW_NUMBER);
		inv.addModel("recordVos", paginationRecord);
		inv.addModel("activityId", activityId);
		inv.addModel("userPluginId", userPluginId);
		inv.addModel("rankUrl", ReadProperties.getPara("httpPath")+"/cj/pintu/rank/"+activityId);
		return "pintu/rank";
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
			total = actPintuConfService.countAllRecord(activityId);
			if(total > Globals.EXCEL_MAX_EXPORT_NUMBER){
				return "@<script>alert('"+"导出数量超过"+Globals.EXCEL_MAX_EXPORT_NUMBER+"，请分段导出"+"');window.history.go(-1);</script>";
			}
		}
		actPintuConfService.exportRecord(activityId, inv, startRow, total);
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
			total = actPintuConfService.countAllScore(activityId);
			if(total > Globals.EXCEL_MAX_EXPORT_NUMBER){
				return "@<script>alert('"+"导出数量超过"+Globals.EXCEL_MAX_EXPORT_NUMBER+"，请分段导出"+"');window.history.go(-1);</script>";
			}
		}
		actPintuConfService.exportRank(activityId, inv, startRow, total);
		return null;
	}
	
	//修改兑换状态
	@Post("changeOpStatus")
	public String changeOpStatus(final Invocation inv, @Param("id") final Integer id){
		String res = actPintuConfService.changeOpStatus(id);
		if(res == null){
			return "@json:"+ "{\"status\":\""+"1"+"\"}";
		}else{
			return "@json:"+ "{\"status\":\""+"0"+"\",\"exchangeTime\":\""+res+"\"}";
		}
	}
	
	@Post("updateBestScore")
	public String updateBestScore(final Invocation inv, @Param("id") final Integer id,
			@Param("score") final Integer score){
		int res = actPintuConfService.updateBestScore(id, score);
		if(res == 0){
			return "@json:"+ "{\"status\":\""+"1"+"\"}";
		}else{
			return "@json:"+ "{\"status\":\""+"0"+"\"}";
		}
	}
	
}
