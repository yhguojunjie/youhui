package com.yoxi.hudongtui.controllers.pc;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.yoxi.hudongtui.controllers.GlobalRequired;
import com.yoxi.hudongtui.model.content.Joinboard;
import com.yoxi.hudongtui.service.agent.IAgentBusService;
import com.yoxi.hudongtui.service.agent.IAgentInfoService;
import com.yoxi.hudongtui.service.agent.IOtherService;
import com.yoxi.hudongtui.service.content.IBrandService;
import com.yoxi.hudongtui.utils.common.JsonUtils;
import com.yoxi.hudongtui.vo.agent.AboutusInfoVO;
import com.yoxi.hudongtui.vo.agent.QuestionVO;

/**
 * 
 * 系统与其它相关的页面
 * 
 * @author wql
 * 
 * 
 */
@GlobalRequired
public class OtherController {
	@Autowired
	private IOtherService otherService;
	@Autowired
	private IAgentBusService agentBusService;
	@Autowired
	private IAgentInfoService agentInfoService;
	@Autowired
	private IBrandService brandService;

	/**
	 * 代理展示
	 * 
	 * @param inv
	 * @return
	 */
	@Get("joinus")
	public String index(Invocation inv) throws Exception {
		return "joinboard";
	}

	/**
	 * 提交代理商
	 * 
	 * @param joinboardVO
	 * @return
	 * @throws Exception
	 */
	@Post("doBeJoinboard")
	// @Token(needRemoveToken = true)
	public String doBeJoinboard(Invocation inv, @Param("contact") String contact, @Param("mobile") String mobile, @Param("email") String email, @Param("qq") String qq, @Param("remark") String remark)
			throws Exception {
		// User user = (User) session.getAttribute(Globals.SESSION_USER);

		Joinboard joinboard = new Joinboard();
		joinboard.setContact(contact);
		joinboard.setMobile(mobile);
		joinboard.setEmail(email);
		joinboard.setQq(qq);
		joinboard.setRemark(remark);
		joinboard.setCreateTime(new Date());
		Integer id = otherService.save(joinboard);

		// WebApplicationUtils.channelMsg("已经提交申请，1个工作日内工作人员会联系你完成审核！", "",
		// WebApplicationUtils.getBasePath() + "/pc/other/index", true);

		return "@json:" + JsonUtils.toJson(id);
	}

	/**
	 * 关于我们
	 * 
	 * @return
	 * @throws Exception
	 */
	@Get("aboutus")
	public String help(final Invocation inv) throws Exception {
		String findStr = "";
		AboutusInfoVO aboutusInfoVO = otherService.findAboutusInfoVO(findStr);
		inv.getRequest().setAttribute("aboutusInfoVO", aboutusInfoVO);
		return "aboutus";
	}

	/***
	 * 联系我们
	 * 
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Get("contactus")
	public String contactus(final Invocation inv, HttpServletRequest request) throws Exception {
		// AgentInfoVO ageInfo = SessionUtil.getAgentInfo(inv.getRequest());
		// ContactUs contactUs = otherService.getContactUs(ageInfo.getId());
		// inv.getRequest().setAttribute("contactUs", contactUs);
		return "contactus";
	}

	/***
	 * 常见问题
	 * 
	 * @param inv
	 * @return
	 * @throws Exception
	 */
	@Get("question")
	public String question(Invocation inv) throws Exception {
		String findStr = " ORDER BY seq DESC ";
		List<QuestionVO> otherInfoVos = otherService.findQuestionInfoVO(findStr);
		inv.getRequest().setAttribute("otherInfoVos", otherInfoVos);
		return "question";
	}

	/**
	 * 跳转到错误页面
	 * 
	 * @param inv
	 * @return
	 */
	@Get("toError")
	public String toError(final Invocation inv) {
		return "error";
	}

	/**
	 * 跳转到提示页
	 * 
	 * @param inv
	 * @return
	 */
	@Get("toTip")
	public String toTip(final Invocation inv) {
		return "tip";
	}

	/**
	 * 跳转到提示页
	 * 
	 * @param inv
	 * @return
	 */
	@Get("toClassicCase")
	public String toClassicCase(final Invocation inv) {
		return "classiccase";
	}

}
