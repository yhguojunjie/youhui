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
import com.yoxi.hudongtui.model.cj.guessgame.ActGuessGameAdmin;
import com.yoxi.hudongtui.model.cj.guessgame.ActGuessGameConf;
import com.yoxi.hudongtui.service.cj.IActGuessGameConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.JsonUtils;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import com.yoxi.hudongtui.vo.cj.guessgame.ActGuessGameRecordVo;

/**
 * 
 * 猜比赛后台管理
 * 
 * @author jjb
 *
 * 2015-3-25
 * 
 */
@LoginRequired
public class GuessgameController {

	@Autowired
	private IActGuessGameConfService actGuessGameConfService;
    
	 /**
     * 增加活动页面跳转
     * @param inv
     * @param userPluginId
     * @return
     */
	@ActOptRequired
    @Get("add/{userPluginId}")
    public String add(final Invocation inv, @Param("userPluginId") final Integer userPluginId) {
    	inv.addModel("userPluginId", userPluginId);
        return "guessgame/add";
    }
    
    /**
     * 添加
     * @param inv
     * @param actGuessGameConf
     * @param startTime
     * @param endTime
     * @param userPluginId
     * @return
     * @throws Exception
     */
    @Post("add_do")
    public String add_do(final Invocation inv, ActGuessGameConf actGuessGameConf, @Param("activityTime") final String activityTime, 
    		@Param("userPluginId") final Integer userPluginId) throws Exception{
		if(activityTime == null || activityTime.length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/guessgame/add/"+userPluginId);
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actGuessGameConf.setStartTime(DateUtils.converDate(time[0]));
			actGuessGameConf.setEndTime(DateUtils.converDate(time[1]));
		}
    	actGuessGameConf.setCreateTime(new Date());
    	actGuessGameConfService.save(actGuessGameConf, userPluginId);
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
    	ActGuessGameConf actGuessGameConf = actGuessGameConfService.findByActId(activityId);
    	if( actGuessGameConf != null){
        	inv.addModel("conf", actGuessGameConf);
    	}
        return "guessgame/edit";
    
    }
    
    /**
     * 修改
     * @param inv
     * @param actGuessGameConf
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @Post("edit_do")
    public String edit_do(final Invocation inv, ActGuessGameConf actGuessGameConf,@Param("createTime") final Date createTime,  
    		 @Param("activityTime") final String activityTime)throws Exception{
		if(activityTime == null || activityTime.length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/guessgame/edit/"+actGuessGameConf.getActivityId());
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actGuessGameConf.setStartTime(DateUtils.converDate(time[0]));
			actGuessGameConf.setEndTime(DateUtils.converDate(time[1]));
		}
		actGuessGameConf.setCreateTime(createTime);
    	actGuessGameConfService.update(actGuessGameConf);
    	inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath()+"/pc/my/actList");
    	return null;
    }
    
  @ActOptRequired
	@Get("gameadmin/{activityId:[0-9]+}")
	public String gameadmin(final Invocation inv, @Param("activityId") final Integer activityId,
			@Param("currentPage") final int currentPage, @Param("count") final int count,
			@Param("upid") final Integer userPluginId){
		Pagination<ActGuessGameAdmin> paginationRecord = actGuessGameConfService.getGameAdminPage(activityId, currentPage, Globals.PAGE_SHOW_NUMBER);
		inv.addModel("bJoinType", actGuessGameConfService.getJoinType(activityId));
		inv.addModel("recordVos", paginationRecord);
		inv.addModel("activityId", activityId);
		inv.addModel("userPluginId", userPluginId);
		return "guessgame/gameadmin";
	}
	
	@Post("editGame")
	public String editGame(final Invocation inv, @Param("id") final Integer id){
		ActGuessGameAdmin admin = actGuessGameConfService.editGame(id);
		if(admin == null){
			return "@json:"+ "{\"status\":\""+"1"+"\"}";
		}else{
			return "@json:"+ "{\"status\":\""+"0"+"\",\"admin\":"+JsonUtils.toJson(admin)+"}";
		}
	}
	
	@Post("saveGame/{activityId:[0-9]+}")
	public String saveGame(final Invocation inv, ActGuessGameAdmin actGuessGameAdmin,
			@Param("activityTime") final String activityTime) throws Exception{
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actGuessGameAdmin.setStartTime(DateUtils.converDate(time[0]));
			actGuessGameAdmin.setEndTime(DateUtils.converDate(time[1]));
		}
		actGuessGameAdmin.setUpdateTime(new Date());
		int ret = actGuessGameConfService.saveGame(actGuessGameAdmin);
		if(ret == 0){
			return "@<script>alert('编辑失败');window.history.go(-1);</script>";
		}else{
			inv.getResponse().sendRedirect(WebApplicationUtils.getContextPath()+"/pluginadmin/guessgame/gameadmin/"+actGuessGameAdmin.getActivityId());
	    	return null;
		}
	}
	
	@Post("addGame/{activityId:[0-9]+}")
	public String addGame(final Invocation inv, ActGuessGameAdmin actGuessGameAdmin,
			@Param("activityTime") final String activityTime) throws Exception{
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actGuessGameAdmin.setStartTime(DateUtils.converDate(time[0]));
			actGuessGameAdmin.setEndTime(DateUtils.converDate(time[1]));
		}
		actGuessGameAdmin.setUpdateTime(new Date());
		int ret = actGuessGameConfService.addGame(actGuessGameAdmin);
		if(ret == 0){
			return "@<script>alert('新增失败');window.history.go(-1);</script>";
		}else{
			inv.getResponse().sendRedirect(WebApplicationUtils.getContextPath()+"/pluginadmin/guessgame/gameadmin/"+actGuessGameAdmin.getActivityId());
	    	return null;
		}
	}
	
	@Post("removeGame")
	public String removeGame(final Invocation inv, @Param("id") final Integer id){
		int res = actGuessGameConfService.delete(id);
		if(res == 0){
			return "@json:"+ "{\"status\":\""+"1"+"\"}";
		}else{
			return "@json:"+ "{\"status\":\""+"0"+"\"}";
		}
	}
    
    @ActOptRequired
	@Get("betrecord/{activityId:[0-9]+}")
	public String betrecord(final Invocation inv, @Param("activityId") final Integer activityId,
			@Param("currentPage") final int currentPage, @Param("count") final int count,
			@Param("upid") final Integer userPluginId){
		Pagination<ActGuessGameRecordVo> paginationRecord = actGuessGameConfService.getBetRecordPage(activityId, currentPage, Globals.PAGE_SHOW_NUMBER);
		inv.addModel("recordVos", paginationRecord);
		inv.addModel("activityId", activityId);
		inv.addModel("userPluginId", userPluginId);
		return "guessgame/betrecord";
	}
	
	//导出记录
	@Get("exportBetRecord/{activityId:[0-9]+}")
	public String exportBetRecord(final Invocation inv, @Param("activityId") final Integer activityId,
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
			total = actGuessGameConfService.countAllBetRecord(activityId);
			if(total > Globals.EXCEL_MAX_EXPORT_NUMBER){
				return "@<script>alert('"+"导出数量超过"+Globals.EXCEL_MAX_EXPORT_NUMBER+"，请分段导出"+"');window.history.go(-1);</script>";
			}
		}
		actGuessGameConfService.exportBetRecord(activityId, inv, startRow, total);
		return null;
	}
	
	//中奖记录
    @ActOptRequired
	@Get("record/{activityId:[0-9]+}")
	public String record(final Invocation inv, @Param("activityId") final Integer activityId,
			@Param("currentPage") final int currentPage, @Param("count") final int count,
			@Param("upid") final Integer userPluginId){
		Pagination<ActGuessGameRecordVo> paginationRecord = actGuessGameConfService.getPrizeRecordPage(activityId, currentPage, Globals.PAGE_SHOW_NUMBER);
		inv.addModel("recordVos", paginationRecord);
		inv.addModel("activityId", activityId);
		inv.addModel("userPluginId", userPluginId);
		return "guessgame/record";
	}
	
	//导出中奖记录
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
			total = actGuessGameConfService.countAllPrizeRecord(activityId);
			if(total > Globals.EXCEL_MAX_EXPORT_NUMBER){
				return "@<script>alert('"+"导出数量超过"+Globals.EXCEL_MAX_EXPORT_NUMBER+"，请分段导出"+"');window.history.go(-1);</script>";
			}
		}
		actGuessGameConfService.exportPrizeRecord(activityId, inv, startRow, total);
		return null;
	}

	//修改兑换状态
	@Post("changeOpStatus")
	public String changeOpStatus(final Invocation inv, @Param("id") final Integer id){
		String res = actGuessGameConfService.changeOpStatus(id);
		if(res == null){
			return "@json:"+ "{\"status\":\""+"1"+"\"}";
		}else{ 
			return "@json:"+ "{\"status\":\""+"0"+"\",\"exchangeTime\":\""+res+"\"}";
		}
	}
	
	@Post("delete")
	public String delete(final Invocation inv, @Param("id") final Integer id){
		int res = actGuessGameConfService.delete(id);
		if(res == 0){
			return "@json:"+ "{\"status\":\""+"1"+"\"}";
		}else{
			return "@json:"+ "{\"status\":\""+"0"+"\"}";
		}
	}
}
