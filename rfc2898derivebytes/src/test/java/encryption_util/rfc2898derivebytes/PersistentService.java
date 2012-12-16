/**
 * 
 */
package encryption_util.rfc2898derivebytes;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.BeansException;

/**
 * Handles CRUD operations with MySQL database.
 * 
 * @author NThusitha
 * 
 */

public class PersistentService {

	//private static ApplicationContext context;
	//private  DataSource ds;
	@Resource
	private DataSource mySqlDataSource;
	
	public PersistentService() throws BeansException {
		//context = new ClassPathXmlApplicationContext("spring-config.xm");

		//ds = (DataSource) context.getBean("mySqlDataSource"); // retrieve a
																// datasource
																// from config

		
	}
	

	
	public DataSource getMySqlDataSource()
	{
		return this.mySqlDataSource;
	}

}
