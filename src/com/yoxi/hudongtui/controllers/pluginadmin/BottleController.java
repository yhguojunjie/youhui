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
import com.yoxi.hudongtui.model.cj.bottle.ActBottleConf;
import com.yoxi.hudongtui.model.cj.bottle.ActBottleContent;
import com.yoxi.hudongtui.model.cj.bottle.ActBottleRecord;
import com.yoxi.hudongtui.service.cj.IActBottleConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.JsonUtils;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;

/**
 * 
 * 漂流瓶后台管理
 * 
 * @author jjb
 *
 * 2015-4-27
 * 
 */
@LoginRequired
public class BottleController {

	@Autowired
	private IActBottleConfService actBottleConfService;
    
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
        return "bottle/add";
    }
    
    /**
     * 添加
     * @param inv
     * @param actBottleConf
     * @param startTime
     * @param endTime
     * @param userPluginId
     * @return
     * @throws Exception
     */
    @Post("add_do")
    public String add_do(final Invocation inv, ActBottleConf actBottleConf, @Param("activityTime") final String activityTime, 
    		@Param("userPluginId") final Integer userPluginId) throws Exception{
		if(activityTime == null || activityTime.length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/bottle/add/"+userPluginId);
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actBottleConf.setStartTime(DateUtils.converDate(time[0]));
			actBottleConf.setEndTime(DateUtils.converDate(time[1]));
		}
    	actBottleConf.setCreateTime(new Date());
    	actBottleConfService.save(actBottleConf, userPluginId);
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
    	ActBottleConf actBottleConf = actBottleConfService.findByActId(activityId);
    	if( actBottleConf != null){
        	inv.addModel("conf", actBottleConf);
    	}
        return "bottle/edit";
    
    }
    
    /**
     * 修改
     * @param inv
     * @param actBottleConf
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @Post("edit_do")
    public String edit_do(final Invocation inv, ActBottleConf actBottleConf,@Param("createTime") final Date createTime,  
    		 @Param("activityTime") final String activityTime)throws Exception{
		if(activityTime == null || activityTime.length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/bottle/edit/"+actBottleConf.getActivityId());
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actBottleConf.setStartTime(DateUtils.converDate(time[0]));
			actBottleConf.setEndTime(DateUtils.converDate(time[1]));
		}
		actBottleConf.setCreateTime(createTime);
    	actBottleConfService.update(actBottleConf);
    	inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath()+"/pc/my/actList");
    	return null;
    }
    
    @ActOptRequired
	@Get("content/{activityId:[0-9]+}")
	public String content(final Invocation inv, @Param("activityId") final Integer activityId,
			@Param("currentPage") final int currentPage, @Param("count") final int count,
			@Param("upid") final Integer userPluginId){
		Pagination<ActBottleContent> paginationRecord = actBottleConfService.getContentPage(activityId, currentPage, Globals.PAGE_SHOW_NUMBER);
		inv.addModel("recordVos", paginationRecord);
		inv.addModel("activityId", activityId);
		inv.addModel("userPluginId", userPluginId);
		return "bottle/content";
	}
	
	@Post("editContent")
	public String editContent(final Invocation inv, @Param("id") final Integer id){
		ActBottleContent content = actBottleConfService.editContent(id);
		if(content == null){
			return "@json:"+ "{\"status\":\""+"1"+"\"}";
		}else{
			return "@json:"+ "{\"status\":\""+"0"+"\",\"content\":"+JsonUtils.toJson(content)+"}";
		}
	}
	
	@Post("saveContent/{activityId:[0-9]+}")
	public String saveContent(final Invocation inv, ActBottleContent actBottleContent) throws Exception{
		actBottleContent.setUpdateTime(new Date());
		int ret = actBottleConfService.saveContent(actBottleContent);
		if(ret == 0){
			return "@<script>alert('编辑失败');window.history.go(-1);</script>";
		}else{
			inv.getResponse().sendRedirect(WebApplicationUtils.getContextPath()+"/pluginadmin/bottle/content/"+actBottleContent.getActivityId());
	    	return null;
		}
	}
	
	@Post("addContent/{activityId:[0-9]+}")
	public String addContent(final Invocation inv, ActBottleContent actBottleContent) throws Exception{
		actBottleContent.setUpdateTime(new Date());
		int ret = actBottleConfService.addContent(actBottleContent);
		if(ret == 0){
			return "@<script>alert('新增失败');window.history.go(-1);</script>";
		}else{
			inv.getResponse().sendRedirect(WebApplicationUtils.getContextPath()+"/pluginadmin/bottle/content/"+actBottleContent.getActivityId());
	    	return null;
		}
	}
	
	//中奖记录
    @ActOptRequired
	@Get("record/{activityId:[0-9]+}")
	public String record(final Invocation inv, @Param("activityId") final Integer activityId,
			@Param("currentPage") final int currentPage, @Param("count") final int count,
			@Param("upid") final Integer userPluginId){
		Pagination<ActBottleRecord> paginationRecord = actBottleConfService.getPrizeRecordPage(activityId, currentPage, Globals.PAGE_SHOW_NUMBER);
		inv.addModel("recordVos", paginationRecord);
		inv.addModel("activityId", activityId);
		inv.addModel("userPluginId", userPluginId);
		return "bottle/record";
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
			total = actBottleConfService.countAllPrizeRecord(activityId);
			if(total > Globals.EXCEL_MAX_EXPORT_NUMBER){
				return "@<script>alert('"+"导出数量超过"+Globals.EXCEL_MAX_EXPORT_NUMBER+"，请分段导出"+"');window.history.go(-1);</script>";
			}
		}
		actBottleConfService.exportPrizeRecord(activityId, inv, startRow, total);
		return null;
	}

	//修改兑换状态
	@Post("changeOpStatus")
	public String changeOpStatus(final Invocation inv, @Param("id") final Integer id){
		String res = actBottleConfService.changeOpStatus(id);
		if(res == null){
			return "@json:"+ "{\"status\":\""+"1"+"\"}";
		}else{ 
			return "@json:"+ "{\"status\":\""+"0"+"\",\"exchangeTime\":\""+res+"\"}";
		}
	}
}
