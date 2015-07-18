package com.yoxi.hudongtui.utils.common;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yoxi.hudongtui.constants.Globals;


/**
 * 
	 *        
	 * 名称：PageTag    
	 *
	 * 作用：分页标签处理类
	 *    
	 * 作者：wangql 2013-4-16     
	 *
 */

@SuppressWarnings("serial")
public class PageTag extends BodyTagSupport {

	private Log logger = LogFactory.getLog(getClass());
	
	protected String page;

	protected String url;

	protected String formname = "forms[0]";

	private StringBuffer sbPage = null;

	/**
	 * 
	 */
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		Pagination c = (Pagination) this.pageContext.findAttribute(page);

		/**if (c == null || c.getTotalCount() == 0) {
			return SKIP_BODY;
		}**/

		int pageNo = c.getCurrentPage(); //当前页数
		int pageSize = c.getCount(); //每页显示的记录 数
		int recordCount = c.getTotalCount(); //总记录数
		int totalpage = c.getTotalPage(); //总页数
		
		int listStep = Globals.PAGE_SHOW_NUMBER; //最多显示分页数
		
		if(totalpage < pageNo){
			pageNo = totalpage;//如果分页变量大总页数，则将分页变量设计为总页数
		}
		
		if(pageNo < 1){
			pageNo = 1; //如果分页变量小于１,则将分页变量设为１
		}

		int listBegin = (pageNo - (int) Math.ceil((double) listStep / 2));//从第几页开始显示分页信息
		if (listBegin < 1) {
			listBegin = 1;
        }
		
		int listEnd = pageNo + listStep/2;//分页信息显示到第几页
		
		 if (listEnd > totalpage) {
			 listEnd = totalpage + 1;
          }
		
		//显示数据部分
		int recordBegin = (pageNo - 1) * pageSize;//起始记录
		int recordEnd = 0; 
		recordEnd = recordBegin + pageSize;
		
		//最后一页记录显示处理
		
		if (pageNo == totalpage) {
            recordEnd = (int) (recordBegin + pageSize * (recordCount % pageSize) * 0.1);
        }
		

		sbPage = new StringBuffer();
		String root = ((HttpServletRequest) this.pageContext.getRequest())
				.getContextPath();
	//	addLine("<input type=\"hidden\" name=\"" + SystemConstants.PAGE_CURRENT + "\" value=\"" + pageNo + "\">");
	//	addLine("<input type=\"hidden\" name=\"" + SystemConstants.PAGE_COUNT + "\" value=\"" + pageSize + "\">");
		addLine(makeLikeBaiduScript(recordCount, pageSize, pageNo, totalpage,listBegin,listEnd));
	
		addLine("<script language=\"JavaScript\" type=\"text/JavaScript\">");
		
		addLine("   function go(page) {");
//		addLine("       if (!isSubmit()) {");
//		addLine("           return false;");
//		addLine("       }");
		addLine("       if (isNum(page)) {");
		addLine("           document." + formname + "." + Globals.PAGE_CURRENT + ".value=page;");
		addLine("           document." + formname + "." + Globals.PAGE_COUNT + ".value="+pageSize+";");

		if (url != null && url.length() > 0) {
			addLine("       document.action = " + url);
		}
		
		addLine("           document." + formname + ".submit();");
		addLine("        } else{");
		addLine("            alert(\" 请输入整数！\");");
		addLine("        }");
		addLine("   }");
		addLine("</script>");

		try {
			out.println(sbPage.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}

		sbPage = null;

		return EVAL_PAGE;

	}

	/**
	 * 
	 * @param str
	 */
	private void addLine(String str) {
		sbPage.append(str + "\n");
	}

	/**
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	private int getIntValue(Map map, String key) {

		int i = 0;

		if (map.get(key) != null) {
			i = ((Integer) map.get(key)).intValue();
		}

		return i;

	}
	
	
	/**
	 * 
	    * 类百度、谷歌分页脚本  
		* @param       
		* @return
		* @exception
	 */
	private String makeLikeBaiduScript(int totalRecordNumber, int pageSize,
			int currentPage, int totalPage, int listBegin, int listEnd){
//		logger.info("---------totalRecordNumber---------"+totalRecordNumber);
//		logger.info("---------pageSize---------"+pageSize);
//		logger.info("---------currentPage---------"+currentPage);
//		logger.info("---------totalPage---------"+totalPage);
//		logger.info("---------listBegin---------"+listBegin);
//		logger.info("---------listEnd---------"+listEnd);
		if(totalPage > 1){
			
			StringBuffer sHTML = new StringBuffer();
			sHTML.append("<TABLE  align=\"center\">" + "\r\n");
			sHTML.append("<TR>" + "\r\n");
			sHTML.append("<TD>");
			//显示上一页
			if(currentPage > 1){
				sHTML.append("<a href='javascript:go(" + (currentPage - 1)	+ ");'  class='t1'>上一页</a>&nbsp;" + "\r\n");
			}
			//显示分页码
			for(int i=listBegin; i<listEnd; i++){
				if(i != currentPage){
					sHTML.append("<a href='javascript:go("+i+");' class='t1'>"+i+"</a>&nbsp;"+ "\r\n");
				}else{
					sHTML.append(i+"&nbsp;"+ "\r\n");
				}
			}
			
			//显示下一页
			if(currentPage !=totalPage ){
				sHTML.append("<a href='javascript:go(" + (currentPage + 1)	+ ");'  class='t1'>下一页</a>&nbsp;" + "\r\n");
			}
			sHTML.append("</TD");
			
			//添加跳转项
			sHTML.append("<TD>");
		
			
			if (totalRecordNumber > pageSize) {
				
				sHTML.append(" <input type=\"text\" id=\"gotopageno\" name=\"gotopageno\" size=3 maxsize=3  value="
								+ currentPage
								+ " onkeydown=\"keyDown(event);\" > "
								+ "\r\n");
				sHTML.append("<a href=\"#\" onclick=\"javascript:gotopage(1,"+ totalPage + ")\">" + "跳转" + "</a>\r\n");
				sHTML.append(currentPage+"/"+totalPage);
			}
			sHTML.append("<input type=\"text\"  name=\"t1\" style=\"DISPLAY: none\">"	+ "\r\n");
			sHTML.append("</TD>");
			
			sHTML.append("<script language=\"JavaScript\" type=\"text/JavaScript\">" + "\r\n");
			sHTML.append(" function gotopage(firstpageno,lastpageno){" + "\r\n");
			sHTML.append("      var gotopageno = document.getElementById(\"gotopageno\").value;"+ "\r\n");
			//sHTML.append("       if(!isNaN(gotopageno)){ alert('页号格式不正确')}");
			sHTML.append("      if(gotopageno < firstpageno || gotopageno > lastpageno){" + "\r\n");
			sHTML.append("          alert(\"请输入正确的页号,范围从[\"+firstpageno+\"]\"+\"到[\"+lastpageno+\"]\");"	+ "\r\n");
			sHTML.append("          document.getElementById(\"gotopageno\").focus();"+ "\r\n");
			sHTML.append("          return ;" + "\r\n");
			sHTML.append("      } else {" + "\r\n");
			sHTML.append("          go(gotopageno);}" + "\r\n");
			sHTML.append(" }" + "\r\n");
			sHTML.append("\r\n");
			sHTML.append("var ns=false;" + "\r\n");
			sHTML.append("var ie=false;" + "\r\n");
			sHTML.append("var BrowName = navigator.appName;" + "\r\n");
			sHTML.append("if (BrowName.indexOf(\"Microsoft Internet Explorer\") != -1)"	+ "\r\n");
			sHTML.append("ie=true;" + "\r\n");
			sHTML.append("if (BrowName.indexOf(\"Netscape\") != -1)" + "\r\n");
			sHTML.append("ns=true;" + "\r\n");
			sHTML.append("if (ns) document.captureEvents(Event.KEYDOWN);" + "\r\n");
			sHTML.append("function keyDown(e){" + "\r\n");
			sHTML.append("  var keyValue;" + "\r\n");
			sHTML.append("  if(ns) {" + "\r\n");
			sHTML.append("      keyValue=e.which;" + "\r\n");
			sHTML.append("  }" + "\r\n");
			sHTML.append("  if(ie) {" + "\r\n");
			sHTML.append("      keyValue=event.keyCode;" + "\r\n");
			sHTML.append("  }" + "\r\n");
			sHTML.append("  if(keyValue==13) {");
			sHTML.append("      gotopage(1," + totalPage + ")");
			sHTML.append("  }" + "\r\n");
			sHTML.append("}" + "\r\n");
			sHTML.append("</script>" + "\r\n");
			
			sHTML.append("</TR></TABLE>" + "\r\n");
			
			return sHTML.toString();
		}else{
			return "";
		}
	}
	
    /**
     * 
        * 普通分页   
    	* @param       
    	* @return
    	* @exception
     */
	private String makeScript(int totalRecordNumber, int pageSize,
			int currentPage, int totalPage) {
		
		StringBuffer sHTML = new StringBuffer();
		sHTML.append("<TABLE border=0 cellPadding=0 cellSpacing=0 width=100% style=\"font-size:12px;\" class=\"reportbar\" align=\"center\">" + "\r\n");
		sHTML.append("<TR>" + "\r\n");
		// 共有 ? 条记录，第?/?页
		sHTML.append("<TD class=\"t4\" width=\"38%\" >&nbsp; 共有<u><font color=#ff0000 >");
		sHTML.append( totalRecordNumber + "</font></u>条记录，第" + currentPage + "/" + totalPage + "页 </TD>" + "\r\n");
		sHTML.append("<TD align=right width=\"62%\">" + "\r\n");
		// 首页,上一页
		if (currentPage > 1) {
			sHTML.append("[<a href='javascript:go(1);' class='t1'>首 页</a>]&nbsp;"+ "\r\n");
			sHTML.append("[<a href='javascript:go(" + (currentPage - 1)+ ");'  class='t1'>上一页</a>]&nbsp;" + "\r\n");
		}

		if (currentPage < totalPage) {
			sHTML.append("[<a href='javascript:go(" + (currentPage + 1)	+ ");'  class='t1'>下一页</a>]&nbsp;" + "\r\n");
			sHTML.append("[<a href='javascript:go(" + totalPage	+ ");' class='t1'>末 页</a>]&nbsp;" + "\r\n");
		}

		sHTML.append("<script>" + "\r\n");
		sHTML.append(" function gotopage(firstpageno,lastpageno){" + "\r\n");
		sHTML.append("      var gotopageno = document.getElementById(\"gotopageno\").value;"+ "\r\n");
		//sHTML.append("       if(!isNaN(gotopageno)){ alert('页号格式不正确')}");
		sHTML.append("      if(gotopageno < firstpageno || gotopageno > lastpageno){" + "\r\n");
		sHTML.append("          alert(\"请输入正确的页号,范围从[\"+firstpageno+\"]\"+\"到[\"+lastpageno+\"]\");"	+ "\r\n");
		sHTML.append("          document.getElementById(\"gotopageno\").focus();"+ "\r\n");
		sHTML.append("          return ;" + "\r\n");
		sHTML.append("      } else {" + "\r\n");
		sHTML.append("          go(gotopageno);}" + "\r\n");
		sHTML.append(" }" + "\r\n");
		sHTML.append("\r\n");
		sHTML.append("var ns=false;" + "\r\n");
		sHTML.append("var ie=false;" + "\r\n");
		sHTML.append("var BrowName = navigator.appName;" + "\r\n");
		sHTML.append("if (BrowName.indexOf(\"Microsoft Internet Explorer\") != -1)"	+ "\r\n");
		sHTML.append("ie=true;" + "\r\n");
		sHTML.append("if (BrowName.indexOf(\"Netscape\") != -1)" + "\r\n");
		sHTML.append("ns=true;" + "\r\n");
		sHTML.append("if (ns) document.captureEvents(Event.KEYDOWN);" + "\r\n");
		sHTML.append("function keyDown(e){" + "\r\n");
		sHTML.append("  var keyValue;" + "\r\n");
		sHTML.append("  if(ns) {" + "\r\n");
		sHTML.append("      keyValue=e.which;" + "\r\n");
		sHTML.append("  }" + "\r\n");
		sHTML.append("  if(ie) {" + "\r\n");
		sHTML.append("      keyValue=event.keyCode;" + "\r\n");
		sHTML.append("  }" + "\r\n");
		sHTML.append("  if(keyValue==13) {");
		sHTML.append("      gotopage(1," + totalPage + ")");
		sHTML.append("  }" + "\r\n");
		sHTML.append("}" + "\r\n");
		sHTML.append("</script>" + "\r\n");

		if (totalRecordNumber > pageSize) {
			sHTML.append("第 <input type=\"text\" id=\"gotopageno\" name=\"gotopageno\" size=3 maxsize=3  value="
							+ currentPage
							+ " onkeydown=\"keyDown(event);\" > 页"
							+ "\r\n");
			sHTML.append("<a href=\"#\" onclick=\"javascript:gotopage(1,"+ totalPage + ")\">" + "跳转" + "</a>\r\n");
		}
		sHTML.append("<input type=\"text\"  name=\"t1\" style=\"DISPLAY: none\">"	+ "\r\n");
		sHTML.append("</TD></TR></TABLE>" + "\r\n");

		return sHTML.toString();
	}


	/**
	 * @return
	 */
	public String getUrl() {
		return url;
	}



	/**
	 * @param string
	 */
	public void setUrl(String string) {
		url = string;
	}

	/**
	 * @return
	 */
	public String getFormname() {
		return formname;
	}

	/**
	 * @param string
	 */
	public void setFormname(String string) {
		formname = string;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

}
