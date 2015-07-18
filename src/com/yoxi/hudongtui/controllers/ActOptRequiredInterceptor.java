package com.yoxi.hudongtui.controllers;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;

import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.model.plugin.PluginAct;
import com.yoxi.hudongtui.model.user.User;
import com.yoxi.hudongtui.model.user.UserPlugin;
import com.yoxi.hudongtui.service.plugin.IPluginActService;
import com.yoxi.hudongtui.service.plugin.IUserPluginService;
import com.yoxi.hudongtui.utils.common.SessionUtil;
import com.yoxi.hudongtui.utils.common.WebApplicationUtils;

/**
 * 活动配置操作权限控制
 * 
 * @author wql
 *
 */
public class ActOptRequiredInterceptor extends ControllerInterceptorAdapter {
	
	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private IPluginActService pluginActService;
	@Autowired
	private IUserPluginService userPluginService;
	
	
	public ActOptRequiredInterceptor() {
	    // 设置优先级，优先级越高的拦截器，before方法越先执行
	    this.setPriority(500);
	}
	
	
	@Override
    public Class<? extends Annotation> getRequiredAnnotationClass() {
        return ActOptRequired.class;
    }
	

    @Override
    public Object before(Invocation inv) throws Exception {
    	
    	HttpServletRequest request = inv.getRequest();
    	
    	String uri = request.getRequestURI();
    	String id = uri.substring(uri.lastIndexOf("/")+1, uri.length());
        //1.add情况下，用户权限判断, 此时id为userPluginId
    	User user = (User)SessionUtil.getUser(inv.getRequest());
    	if(uri.indexOf("add") > 0){
    		UserPlugin userPlugin = userPluginService.getById(Integer.valueOf(id));
    		if(userPlugin != null){
    			if(userPlugin.getUserId().equals(user.getUserId())){
    				if(userPlugin.getActNum() != null && userPlugin.getActNum() < 1){
    					return true;
    				}else{
    					inv.getRequest().setAttribute("tip","很抱歉，您无权限进行操作！");
                		return "r:"+WebApplicationUtils.getBasePath()+"/pc/other/toTip";
    				}
    				
    			}else{
            		inv.getRequest().setAttribute("tip","很抱歉，您无权限进行操作！");
            		return "r:"+WebApplicationUtils.getBasePath()+"/pc/other/toTip";
    			}
    		}else{
    			
    		}
    		
    	}else{//其它情况下处理,此时id为活动id
    		log.error("*****************2***************");
    		PluginAct pluginAct = pluginActService.getById(Integer.valueOf(id));
    		if(pluginAct != null){
    			if(pluginAct.getUserId().equals(user.getUserId())){
    				return true;
    			}else{
    				inv.getRequest().setAttribute("tip","很抱歉，您无权限进行操作！");
            		return "r:"+WebApplicationUtils.getBasePath()+"/pc/other/toTip";
    			}
    		}
    	}
        return true;
    }
}
