package com.yoxi.hudongtui.vo.wx.api.message;

import java.util.List;

/** 
 * 图文消息
 *  
 * @author wangql
 * 
 * 2013-12-26
 * 
 */ 
public class RespNewsMessage extends RespBaseMessage {
    // 图文消息个数，限制为10条以内 
    private int ArticleCount; 
    // 多条图文消息信息，默认第一个item为大图 
    private List<Article> Articles; 
    
    public RespNewsMessage(){
    	
    }
    
    public RespNewsMessage(RespBaseMessage respBaseMessage){
		super(respBaseMessage.ToUserName, respBaseMessage.FromUserName, respBaseMessage.CreateTime, respBaseMessage.MsgType);
    }
    
    public int getArticleCount() { 
        return ArticleCount; 
    } 
 
    public void setArticleCount(int articleCount) { 
        ArticleCount = articleCount; 
    } 
 
    public List<Article> getArticles() { 
        return Articles; 
    } 
 
    public void setArticles(List<Article> articles) { 
        Articles = articles; 
    } 
}
