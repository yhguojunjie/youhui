package com.yoxi.hudongtui.dao.cj.receipt;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.core.Identity;

import com.yoxi.hudongtui.model.cj.receipt.ActReceiptPrize;

/**
 * 刮发票
 * 
 * @author jjb
 *
 * 2015-4-20
 *
 */
@DAO
public interface ActReceiptPrizeDAO {


	/**
	 * 
	 * @param actReceiptPrize
	 * @return
	 */
	@SQL("insert into t_act_receipt_prize (activityId, prizeType,realNum,deliverNum,version,finalTime) " +
			"values (:1.activityId, :1.prizeType, :1.realNum,:1.deliverNum,:1.version,:1.finalTime)")
	public Identity add(ActReceiptPrize actReceiptPrize);
	
	/**
	 * 采用乐观锁更新数据版本
	 * @param actReceiptPrize
	 * @param upstr
	 * @return
	 */
	@SQL("update t_act_receipt_prize set finalTime = NOW(), version = version + 1, ##(:upstr) " +
			"where id = :1.id and version = :1.version")
	public int upByStr(ActReceiptPrize actReceiptPrize, @SQLParam("upstr") String upstr);
	
	/**
	 * 
	 * @param activityId
	 * @return
	 */
	@SQL("select * from t_act_receipt_prize where activityId = :1 and prizeType = :2")
	public ActReceiptPrize findByActivityId(Integer activityId, Integer prizeType);
}
