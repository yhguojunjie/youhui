package com.yoxi.hudongtui.controllers.pluginadmin;

import java.util.Date;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.controllers.ActOptRequired;
import com.yoxi.hudongtui.controllers.LoginRequired;
import com.yoxi.hudongtui.model.cj.foolredpacket.ActFoolRedPacketConf;
import com.yoxi.hudongtui.service.cj.IActFoolRedPacketConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;

/**
 * 
 * 愚人红包
 * 
 * @author jjb
 *
 * 2015-3-22
 * 
 */
@LoginRequired
public class FoolredpacketController {

	@Autowired
	private IActFoolRedPacketConfService actFoolRedPacketConfService;
    
	 /**
     * 增加小鸟活动页面跳转
     * @param inv
     * @param userPluginId
     * @return
     */
	@ActOptRequired
    @Get("add/{userPluginId}")
    public String add(final Invocation inv, @Param("userPluginId") final Integer userPluginId) {
    	inv.addModel("userPluginId", userPluginId);
        return "foolredpacket/add";
    }
    
    /**
     * 添加
     * @param inv
     * @param actFoolRedPacketConf
     * @param startTime
     * @param endTime
     * @param userPluginId
     * @return
     * @throws Exception
     */
    @Post("add_do")
    public String add_do(final Invocation inv, ActFoolRedPacketConf actFoolRedPacketConf, @Param("activityTime") final String activityTime, 
    		@Param("userPluginId") final Integer userPluginId) throws Exception{
		if(activityTime == null || activityTime.length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/foolredpacket/add/"+userPluginId);
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actFoolRedPacketConf.setStartTime(DateUtils.converDate(time[0]));
			actFoolRedPacketConf.setEndTime(DateUtils.converDate(time[1]));
		}
    	actFoolRedPacketConf.setCreateTime(new Date());
    	actFoolRedPacketConfService.save(actFoolRedPacketConf, userPluginId);
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
    	ActFoolRedPacketConf actFoolRedPacketConf = actFoolRedPacketConfService.findByActId(activityId);
    	if( actFoolRedPacketConf != null){
        	inv.addModel("conf", actFoolRedPacketConf);
    	}
        return "foolredpacket/edit";
    
    }
    
    /**
     * 修改
     * @param inv
     * @param actFoolRedPacketConf
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @Post("edit_do")
    public String edit_do(final Invocation inv, ActFoolRedPacketConf actFoolRedPacketConf,@Param("createTime") final Date createTime,  
    		 @Param("activityTime") final String activityTime)throws Exception{
		if(activityTime == null || activityTime.length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/foolredpacket/edit/"+actFoolRedPacketConf.getActivityId());
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actFoolRedPacketConf.setStartTime(DateUtils.converDate(time[0]));
			actFoolRedPacketConf.setEndTime(DateUtils.converDate(time[1]));
		}
		actFoolRedPacketConf.setCreateTime(createTime);
    	actFoolRedPacketConfService.update(actFoolRedPacketConf);
    	inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath()+"/pc/my/actList");
    	return null;
    }
}
