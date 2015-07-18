package com.yoxi.hudongtui.base;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基础类,加载测试所需环境
 * 
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 使用junit4进行测试
@ContextConfiguration(locations = { "classpath:test-applicationContext-*.xml" })
// 加载配置文件
public class BaseTestCate extends AbstractTransactionalJUnit4SpringContextTests {

	protected final Logger logger = Logger.getLogger(getClass());

}
