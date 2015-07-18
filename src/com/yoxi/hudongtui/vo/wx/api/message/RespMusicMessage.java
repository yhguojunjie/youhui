package com.yoxi.hudongtui.vo.wx.api.message;

/** 
 * 音乐消息
 *  
 * @author wangql
 * 
 * 2013-12-26
 * 
 */ 
public class RespMusicMessage extends RespBaseMessage {
    // 音乐 
    private Music Music; 
 
    public Music getMusic() { 
        return Music; 
    } 
 
    public void setMusic(Music music) { 
        Music = music; 
    } 
}
