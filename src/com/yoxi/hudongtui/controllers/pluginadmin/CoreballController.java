package com.yoxi.hudongtui.controllers.pluginadmin;

import java.util.Date;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.controllers.ActOptRequired;
import com.yoxi.hudongtui.controllers.LoginRequired;
import com.yoxi.hudongtui.model.cj.coreball.ActCoreBallConf;
import com.yoxi.hudongtui.service.cj.IActCoreBallConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;

/**
 * 
 * 心球
 * 
 * @author jjb
 *
 * 2015-4-24
 * 
 */
@LoginRequired
public class CoreballController {

	@Autowired
	private IActCoreBallConfService actCoreBallConfService;
    
	 /**
     * 增加心球活动页面跳转
     * @param inv
     * @param userPluginId
     * @return
     */
	@ActOptRequired
    @Get("add/{userPluginId}")
    public String add(final Invocation inv, @Param("userPluginId") final Integer userPluginId) {
    	inv.addModel("userPluginId", userPluginId);
        return "coreball/add";
    }
    
    /**
     * 添加
     * @param inv
     * @param actCoreBallConf
     * @param startTime
     * @param endTime
     * @param userPluginId
     * @return
     * @throws Exception
     */
    @Post("add_do")
    public String add_do(final Invocation inv, ActCoreBallConf actCoreBallConf, @Param("activityTime") final String activityTime, 
    		@Param("userPluginId") final Integer userPluginId) throws Exception{
		if(activityTime == null || activityTime.length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/coreball/add/"+userPluginId);
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actCoreBallConf.setStartTime(DateUtils.converDate(time[0]));
			actCoreBallConf.setEndTime(DateUtils.converDate(time[1]));
		}
    	actCoreBallConf.setCreateTime(new Date());
    	actCoreBallConfService.save(actCoreBallConf, userPluginId);
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
    	ActCoreBallConf actCoreBallConf = actCoreBallConfService.findByActId(activityId);
    	if( actCoreBallConf != null){
        	inv.addModel("conf", actCoreBallConf);
    	}
        return "coreball/edit";
    
    }
    
    /**
     * 修改
     * @param inv
     * @param actCoreBallConf
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @Post("edit_do")
    public String edit_do(final Invocation inv, ActCoreBallConf actCoreBallConf,@Param("createTime") final Date createTime,  
    		 @Param("activityTime") final String activityTime)throws Exception{
		if(activityTime == null || activityTime.length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/coreball/edit/"+actCoreBallConf.getActivityId());
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actCoreBallConf.setStartTime(DateUtils.converDate(time[0]));
			actCoreBallConf.setEndTime(DateUtils.converDate(time[1]));
		}
		actCoreBallConf.setCreateTime(createTime);
    	actCoreBallConfService.update(actCoreBallConf);
    	inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath()+"/pc/my/actList");
    	return null;
    }
}
