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
 * @author tmnuwan12 This class is responsible for creating pool of worker
 *         threads and invoking runnable tasks with given time delays.
 * 
 */
public class PingExecutorService {

	final Logger logger = LoggerFactory.getLogger(PingExecutorService.class);

	private ScheduledExecutorService scheduledExecutorPool;

	private Object mutex = new Object();

	public PingExecutorService(final int poolSize) {
		this.scheduledExecutorPool = Executors.newScheduledThreadPool(poolSize);

		logger.debug("Scheduled execution thread pool created successfully");

	}

	@SuppressWarnings("rawtypes")
	public void fireRequests() {

		ScheduledFuture scheduledFuture;
		try {

			scheduledFuture = scheduledExecutorPool.scheduleAtFixedRate(
					new Communicator(), 1, 3, TimeUnit.SECONDS);
			
			//need to regis
			Runtime.getRuntime().addShutdownHook(new Thread() // add a shutdown
			// hook
			{
				
				@Override
				public void run() {
					
					// do any required clean ups
					logger.warn("Taking down HttpUrlConnection Utility");
					
					synchronized (mutex) {
						mutex.notify();
						logger.debug("Notifing waiting thread on mutex");
					}
				}
				
			});

			try {

				synchronized (mutex) {
					mutex.wait(); // wait until this thread get notified
				}

			} catch (InterruptedException e) {

				logger.warn("Waiting thread has been inturrupted");
			}

		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
}
