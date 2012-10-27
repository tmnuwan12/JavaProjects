/**
 * 
 */
package communicator.http_transmission;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tmnuwan12
 * This class is responsible for creating pool of 
 * worker threads and invoking runnable tasks with
 * given time delays.
 *
 */
public class PingExecutorService {

	
	final Logger logger = LoggerFactory.getLogger(PingExecutorService.class);
	
	private ScheduledExecutorService scheduledExecutorPool;
	
	public PingExecutorService(final int poolSize)
	{
		this.scheduledExecutorPool = Executors.newScheduledThreadPool(poolSize);
		
		logger.debug("Scheduled execution thread pool created successfully");
		
	}
	
	@SuppressWarnings("rawtypes")
	public void fireRequests()
	{
		
		
		ScheduledFuture scheduledFuture;
		try{
		
		scheduledFuture = scheduledExecutorPool.scheduleAtFixedRate(new Communicator(), 1, 3, TimeUnit.SECONDS);
		
		}finally{
			
			//TODO: Get hook to running threads and stop them while releasing all resources
			//Communicator.httpUrlConnection.disconnect(); //globally disconnect all socket connections
			//logger.debug("Disconnected HttpUrlConnection successfully (globally disconnect all socket connections)");
		}
	}
}
