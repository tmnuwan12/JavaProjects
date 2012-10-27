/**
 * 
 */
package communicator.http_transmission;

import java.io.IOException;

/**
 * @author tmnuwan12
 *
 */
public class PingExecutor {

	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
	

		PingExecutorService pingService = new PingExecutorService(5);
		pingService.fireRequests();
	

	}

}
