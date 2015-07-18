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
import com.yoxi.hudongtui.model.cj.ActGiftboxConf;
import com.yoxi.hudongtui.model.cj.ActGiftboxRecord;
import com.yoxi.hudongtui.service.cj.IActGiftboxConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.Pagination;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import com.yoxi.hudongtui.vo.cj.giftbox.GiftboxAwardsVo;
import com.yoxi.hudongtui.vo.cj.giftbox.GiftboxRecordVo;

/**
 * 拆礼盒后台管理
 * 
 * @author jjb
 *
 *2014-11-17
 *
 */
@LoginRequired
public class GiftboxController {
	
	@Autowired
	private IActGiftboxConfService actGiftboxConfService;
	
	   
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
        return "giftbox/add";
    }
    
    /**
     * 添加
     * @param inv
     * @param actGiftboxConf
     * @param startTime
     * @param endTime
     * @param userPluginId
     * @return
     * @throws Exception
     */
	@Post("add_do")
    public String add_do(final Invocation inv, ActGiftboxConf actGiftboxConf, @Param("activityTime") final String activityTime, 
    		@Param("userPluginId") final Integer userPluginId) throws Exception{
		if(activityTime == null || activityTime.length() == 0 || actGiftboxConf.getAwards() == null ||
				actGiftboxConf.getAwards().toString().length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/giftbox/add/"+userPluginId);
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actGiftboxConf.setStartTime(DateUtils.converDate(time[0]));
			actGiftboxConf.setEndTime(DateUtils.converDate(time[1]));
		}
		actGiftboxConf.setCreateTime(new Date());
    	actGiftboxConfService.save(actGiftboxConf, userPluginId);
    	actGiftboxConfService.savePrize(actGiftboxConf.getActivityId(), actGiftboxConf.getAwards().toString());
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
		ActGiftboxConf actGiftboxConf = actGiftboxConfService.findByActId(activityId);
		if(actGiftboxConf != null){
    			List<GiftboxAwardsVo> giftboxAwardsVoList = actGiftboxConfService.parseJson(actGiftboxConf.getAwards().toString());
    			inv.addModel("awardslist", giftboxAwardsVoList);
    	    	inv.addModel("giftconf", actGiftboxConf);
    	 }
        return "giftbox/edit";
    }
	
	
	/**
	 * 修改
	 * @param inv
	 * @param actGiftboxConf
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	@Post("edit_do")
    public String edit_do(final Invocation inv, ActGiftboxConf actGiftboxConf, @Param("createTime") final Date createTime,
    		@Param("activityTime") final String activityTime)throws Exception{
		if(activityTime == null || activityTime.length() == 0 || actGiftboxConf.getAwards() == null ||
				actGiftboxConf.getAwards().toString().length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/giftbox/edit/"+actGiftboxConf.getActivityId());
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actGiftboxConf.setStartTime(DateUtils.converDate(time[0]));
			actGiftboxConf.setEndTime(DateUtils.converDate(time[1]));
		}
		actGiftboxConf.setCreateTime(createTime);
    	actGiftboxConfService.update(actGiftboxConf);
    	actGiftboxConfService.savePrize(actGiftboxConf.getActivityId(), actGiftboxConf.getAwards().toString());
    	inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath()+"/pc/my/actList");
    	return null;
    }
	
	@ActOptRequired
	@Get("record/{activityId:[0-9]+}")
	public String record(final Invocation inv, @Param("activityId") final Integer activityId,
			@Param("currentPage") final int currentPage, @Param("count") final int count,
			@Param("upid") final Integer userPluginId){
		Pagination<ActGiftboxRecord> paginationRecord = actGiftboxConfService.getRecordPage(activityId, currentPage, Globals.PAGE_SHOW_NUMBER);
		inv.addModel("recordVos", paginationRecord);
		inv.addModel("activityId", activityId);
		inv.addModel("userPluginId", userPluginId);
		return "giftbox/record";
	}
	
	//参与人数
	@ActOptRequired
	@Get("join/{activityId:[0-9]+}")
	public String join(final Invocation inv, @Param("activityId") final Integer activityId,
			@Param("currentPage") final int currentPage, @Param("count") final int count,
			@Param("upid") final Integer userPluginId){
		Pagination<ActGiftboxRecord> paginationRecord = actGiftboxConfService.getJoinPage(activityId, currentPage, Globals.PAGE_SHOW_NUMBER);
		inv.addModel("recordVos", paginationRecord);
		inv.addModel("activityId", activityId);
		inv.addModel("userPluginId", userPluginId);
		return "giftbox/join";
	}
	
	@Post("getPrizeJson")
	public String getPrizeJson(final Invocation inv, @Param("activityId") final Integer activityId){
		return "@json:" + actGiftboxConfService.findAwards(activityId);
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
			total = actGiftboxConfService.countAllRecord(activityId);
			if(total > Globals.EXCEL_MAX_EXPORT_NUMBER){
				return "@<script>alert('"+"导出数量超过"+Globals.EXCEL_MAX_EXPORT_NUMBER+"，请分段导出"+"');window.history.go(-1);</script>";
			}
		}
		actGiftboxConfService.exportRecord(activityId, inv, startRow, total);
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
			total = actGiftboxConfService.countAllRecord(activityId);
			if(total > Globals.EXCEL_MAX_EXPORT_NUMBER){
				return "@<script>alert('"+"导出数量超过"+Globals.EXCEL_MAX_EXPORT_NUMBER+"，请分段导出"+"');window.history.go(-1);</script>";
			}
		}
		actGiftboxConfService.exportJoin(activityId, inv, startRow, total);
		return null;
	}
	
	//增加虚拟记录
	@Post("addRecord")
	public String addRecord(final Invocation inv, @Param("activityId") final Integer activityId,
			GiftboxRecordVo giftboxRecordVo){
		int ret = actGiftboxConfService.addRecord(activityId, giftboxRecordVo);
		return "@json:"+ret;
	}
	
	//修改兑换状态
	@Post("changeOpStatus")
	public String changeOpStatus(final Invocation inv, @Param("id") final Integer id){
		String res = actGiftboxConfService.changeOpStatus(id);
		if(res == null){
			return "@json:"+ "{\"status\":\""+"1"+"\"}";
		}else{
			return "@json:"+ "{\"status\":\""+"0"+"\",\"exchangeTime\":\""+res+"\"}";
		}
	}
	
}
