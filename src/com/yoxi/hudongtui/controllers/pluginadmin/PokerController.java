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
import com.yoxi.hudongtui.model.cj.poker.ActPokerConf;
import com.yoxi.hudongtui.model.cj.poker.ActPokerRecord;
import com.yoxi.hudongtui.service.cj.IActPokerConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import com.yoxi.hudongtui.vo.cj.poker.PokerAwardsVo;
import com.yoxi.hudongtui.vo.cj.poker.PokerRecordVo;

/**
 * 我坐庄
 * 
 * @author jjb
 *
 * 2015-1-22
 *
 */
@LoginRequired
public class PokerController {

	@Autowired
	private IActPokerConfService actPokerConfService;
	
	   
    /**
     * 跳转到添加页
     * @param inv
     * @param userPluginId
     * @return
     */
	@ActOptRequired
    @Get("add/{userPluginId}")
    public String add(final Invocation inv, @Param("userPluginId") final Integer userPluginId) {
    	inv.addModel("userPluginId", userPluginId);
        return "poker/add";
    }
    
    /**
     * 添加
     * @param inv
     * @param actPokerConf
     * @param startTime
     * @param endTime
     * @param userPluginId
     * @return
     * @throws Exception
     */
	@Post("add_do")
    public String add_do(final Invocation inv, ActPokerConf actPokerConf, @Param("activityTime") final String activityTime, 
    		@Param("userPluginId") final Integer userPluginId) throws Exception{
		if(activityTime == null || activityTime.length() == 0 || actPokerConf.getAwards() == null ||
				actPokerConf.getAwards().toString().length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/poker/add/"+userPluginId);
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actPokerConf.setStartTime(DateUtils.converDate(time[0]));
			actPokerConf.setEndTime(DateUtils.converDate(time[1]));
		}
		actPokerConf.setCreateTime(new Date());
    	actPokerConfService.save(actPokerConf, userPluginId);
    	actPokerConfService.savePrize(actPokerConf.getActivityId(), actPokerConf.getAwards().toString());
    	inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath()+"/pc/my/actList");
    	return null;
    }
	
	/**
	 * 修改页跳转
	 * @param inv
	 * @param id  活动id
	 * @return
	 */
	@ActOptRequired
	@Get("edit/{activityId:[0-9]+}")
    public String edit(final Invocation inv, @Param("activityId") final Integer activityId) {
		ActPokerConf actPokerConf = actPokerConfService.findByActId(activityId);
		if(actPokerConf != null){
    			List<PokerAwardsVo> pokerAwardsVoList = actPokerConfService.parseJson(actPokerConf.getAwards().toString());
    			inv.addModel("awardslist", pokerAwardsVoList);
    	    	inv.addModel("conf", actPokerConf);
    	 }
        return "poker/edit";
    }
	
	
	/**
	 * 修改
	 * @param inv
	 * @param actPokerConf
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	@Post("edit_do")
    public String edit_do(final Invocation inv, ActPokerConf actPokerConf, @Param("createTime") final Date createTime,
    		@Param("activityTime") final String activityTime)throws Exception{
		if(activityTime == null || activityTime.length() == 0 || actPokerConf.getAwards() == null ||
				actPokerConf.getAwards().toString().length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/poker/edit/"+actPokerConf.getActivityId());
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actPokerConf.setStartTime(DateUtils.converDate(time[0]));
			actPokerConf.setEndTime(DateUtils.converDate(time[1]));
		}
		actPokerConf.setCreateTime(createTime);
    	actPokerConfService.update(actPokerConf);
    	actPokerConfService.savePrize(actPokerConf.getActivityId(), actPokerConf.getAwards().toString());
    	inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath()+"/pc/my/actList");
    	return null;
    }
	
	@ActOptRequired
	@Get("record/{activityId:[0-9]+}")
	public String record(final Invocation inv, @Param("activityId") final Integer activityId,
			@Param("currentPage") final int currentPage, @Param("count") final int count,
			@Param("upid") final Integer userPluginId){
		Pagination<ActPokerRecord> paginationRecord = actPokerConfService.getRecordPage(activityId, currentPage, Globals.PAGE_SHOW_NUMBER);
		inv.addModel("recordVos", paginationRecord);
		inv.addModel("activityId", activityId);
		inv.addModel("userPluginId", userPluginId);
		inv.addModel("rankUrl", ReadProperties.getPara("httpPath")+"/cj/poker/rank/"+activityId);
		return "poker/record";
	}
	
	//参与人数
	@ActOptRequired
	@Get("join/{activityId:[0-9]+}")
	public String join(final Invocation inv, @Param("activityId") final Integer activityId,
			@Param("currentPage") final int currentPage, @Param("count") final int count,
			@Param("upid") final Integer userPluginId){
		Pagination<ActPokerRecord> paginationRecord = actPokerConfService.getJoinPage(activityId, currentPage, Globals.PAGE_SHOW_NUMBER);
		inv.addModel("recordVos", paginationRecord);
		inv.addModel("activityId", activityId);
		inv.addModel("userPluginId", userPluginId);
		return "poker/join";
	}
	
	@Post("getPrizeJson")
	public String getPrizeJson(final Invocation inv, @Param("activityId") final Integer activityId){
		return "@json:" + actPokerConfService.findAwards(activityId);
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
			total = actPokerConfService.countAllRecord(activityId);
			if(total > Globals.EXCEL_MAX_EXPORT_NUMBER){
				return "@<script>alert('"+"导出数量超过"+Globals.EXCEL_MAX_EXPORT_NUMBER+"，请分段导出"+"');window.history.go(-1);</script>";
			}
		}
		actPokerConfService.exportRecord(activityId, inv, startRow, total);
		return null;
	}
	
	@Get("exportJoin/{activityId:[0-9]+}")
	public String exportJoin(final Invocation inv, @Param("activityId") final Integer activityId,
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
			total = actPokerConfService.countAllRecord(activityId);
			if(total > Globals.EXCEL_MAX_EXPORT_NUMBER){
				return "@<script>alert('"+"导出数量超过"+Globals.EXCEL_MAX_EXPORT_NUMBER+"，请分段导出"+"');window.history.go(-1);</script>";
			}
		}
		actPokerConfService.exportJoin(activityId, inv, startRow, total);
		return null;
	}
	
	//增加虚拟记录
	@Post("addRecord")
	public String addRecord(final Invocation inv, @Param("activityId") final Integer activityId,
			PokerRecordVo pokerRecordVo){
		int ret = actPokerConfService.addRecord(activityId, pokerRecordVo);
		return "@json:"+ret;
	}
	
	//修改兑换状态
	@Post("changeOpStatus")
	public String changeOpStatus(final Invocation inv, @Param("id") final Integer id){
		String res = actPokerConfService.changeOpStatus(id);
		if(res == null){
			return "@json:"+ "{\"status\":\""+"1"+"\"}";
		}else{
			return "@json:"+ "{\"status\":\""+"0"+"\",\"exchangeTime\":\""+res+"\"}";
		}
	}
	
}
