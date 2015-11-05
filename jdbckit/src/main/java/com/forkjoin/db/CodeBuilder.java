package com.forkjoin.db;

import java.io.File;
import java.sql.Connection;

import javax.sql.DataSource;

import com.forkjoin.core.IocFactory;
import com.forkjoin.core.SpringIocFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forkjoin.jdbckit.mysql.Builder;
import com.forkjoin.jdbckit.mysql.Config;

/**
 * 
 * @author zuoge85
 *
 */
public class CodeBuilder {
	private static final Logger log = LoggerFactory.getLogger(CodeBuilder.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String path="src/main/java/";
			String resourcesPath="src/main/resources/";
			File dir=new File(path);
			File resourcesDir=new File(resourcesPath);
			log.info("代码路径:{}",dir.getAbsolutePath());
			//ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,Main.class.getResource(".").getFile());
			//ve.init();
			Config config=new Config(dir,resourcesDir);
			config.setPack("io.grass.test");
			IocFactory ioc=new SpringIocFactory("testContext.xml");
			DataSource ds=ioc.getBean("dataSource",DataSource.class);
			Connection conn=null;
			try{
				conn=ds.getConnection();
				Builder builder=new Builder(config, conn);
				builder.objectCreate();
				//builder.daoCreate();
				builder.daoImplCreate();
				builder.springXmlCreate();
			}finally{
				if(conn!=null){
					conn.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
