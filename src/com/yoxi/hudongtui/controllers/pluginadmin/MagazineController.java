package com.yoxi.hudongtui.controllers.pluginadmin;

import java.util.Date;
import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.controllers.ActOptRequired;
import com.yoxi.hudongtui.controllers.LoginRequired;
import com.yoxi.hudongtui.model.cj.ActMagazineConf;
import com.yoxi.hudongtui.service.cj.IActMagazineConfService;
import com.yoxi.hudongtui.utils.common.DateUtils;
import com.yoxi.hudongtui.utils.common.ReadProperties;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;
import com.yoxi.hudongtui.vo.cj.magazine.MagazineContentVo;

/**
 * 
 * 微杂志后台管理
 * 
 * @author jjb
 *
 * 2014-11-13
 * 
 */
@LoginRequired
public class MagazineController {
	
	@Autowired
	private IActMagazineConfService actMagazineConfService;
    
	 /**
     * 增加微场景活动页面跳转
     * @param inv
     * @param userPluginId
     * @return
     */
	@ActOptRequired
    @Get("add/{userPluginId}")
    public String add(final Invocation inv, @Param("userPluginId") final Integer userPluginId) {
    	inv.addModel("userPluginId", userPluginId);
        return "magazine/add";
    }
    
    /**
     * 添加
     * @param inv
     * @param actMagazineConf
     * @param startTime
     * @param endTime
     * @param userPluginId
     * @return
     * @throws Exception
     */
    @Post("add_do")
    public String add_do(final Invocation inv, ActMagazineConf actMagazineConf, @Param("activityTime") final String activityTime, 
    		@Param("userPluginId") final Integer userPluginId) throws Exception{
		if(activityTime == null || activityTime.length() == 0 || actMagazineConf.getContent() == null ||
				actMagazineConf.getContent().toString().length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/magazine/add/"+userPluginId);
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actMagazineConf.setStartTime(DateUtils.converDate(time[0]));
			actMagazineConf.setEndTime(DateUtils.converDate(time[1]));
		}
    	actMagazineConf.setCreateTime(new Date());
    	actMagazineConfService.save(actMagazineConf, userPluginId);
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
    	ActMagazineConf actMagazineConf = actMagazineConfService.findByActId(activityId);
    	if( actMagazineConf != null){
    		List<MagazineContentVo> magazineContentVoList = actMagazineConfService.parseJson(actMagazineConf.getContent().toString());
        	inv.addModel("contentlist", magazineContentVoList);
        	inv.addModel("magazineconf", actMagazineConf);
    	}
        return "magazine/edit";
    
    }
    
    /**
     * 修改
     * @param inv
     * @param actMagazineConf
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @Post("edit_do")
    public String edit_do(final Invocation inv, ActMagazineConf actMagazineConf, @Param("createTime") final Date createTime,
    		@Param("activityTime") final String activityTime)throws Exception{
		if(activityTime == null || activityTime.length() == 0 || actMagazineConf.getContent() == null ||
				actMagazineConf.getContent().toString().length() == 0){
			inv.addModel("tip", "非法操作，请重试");
			inv.addModel("backUrl", ReadProperties.getPara("httpPath")+"/pluginadmin/magazine/edit/"+actMagazineConf.getActivityId());
			return "../pc/tip.jsp";
		}
		if(activityTime.length() != 0){
			String[] time = activityTime.split(" - ");
			actMagazineConf.setStartTime(DateUtils.converDate(time[0]));
			actMagazineConf.setEndTime(DateUtils.converDate(time[1]));
		}
		actMagazineConf.setCreateTime(createTime);
    	actMagazineConfService.update(actMagazineConf);
    	inv.getResponse().sendRedirect(WebApplicationUtils.getBasePath()+"/pc/my/actList");
    	return null;
    }
}
