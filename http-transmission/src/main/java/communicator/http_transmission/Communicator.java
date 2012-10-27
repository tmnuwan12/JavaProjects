package communicator.http_transmission;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tmnuwan12
 * 
 *         This class is responsible for creating singleton HttpURLConnection
 *         which will be shared between threads to make connection to a remote
 *         server. This class is thread safe.
 * 
 */
public class Communicator implements Runnable {

	final static Logger logger = LoggerFactory.getLogger(Communicator.class);


	private static URL url;
	private static int TIMEOUT = 15000;
	private static boolean isUseCache = false;



	public void sendPingRequest(final File log)
			throws IOException {
		int responseCode = -1;
		String responseMessage = "";
		InputStream htmlPage = null;
		boolean isPageBodyRetrivalSuccess = false;
		final StringBuffer message = new StringBuffer();
		HttpURLConnection httpUrlConnection = null;

		try {
			
			url = new URL("http://www.google.com");
			logger.debug("Initialised Communicator url:" + url);
			
			httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setReadTimeout(TIMEOUT);
			httpUrlConnection.setConnectTimeout(TIMEOUT);
			httpUrlConnection.setUseCaches(isUseCache);
			httpUrlConnection.setDefaultUseCaches(isUseCache);

			httpUrlConnection.connect();
			responseCode = httpUrlConnection.getResponseCode();
			responseMessage = httpUrlConnection.getResponseMessage();

			htmlPage = httpUrlConnection.getInputStream();

			while (htmlPage.read() != -1) {
				// Do nothing, This is just to check that we can retrieve
				// response body on time.
				// If timeout occurs it will throw SocketTimeOut Exception.
			}
			isPageBodyRetrivalSuccess = true;

			System.out.println("Response code:"
					+ Integer.toString(responseCode));

		} catch (RuntimeException e) {

			System.out.println(e + Long.toString(System.currentTimeMillis()));
			responseCode = -1;
			responseMessage = e.toString();

		} finally {
			if (htmlPage != null) {
				htmlPage.close(); // close input stream, very important, if you
									// don't close this above while loop will
									// always
									// read the values in the memory and return
									// incorrect behaviour
			}
			if(httpUrlConnection != null)
			{
				httpUrlConnection.disconnect();
			}
			if (responseMessage.isEmpty()) {
				responseMessage = "Attempt Failed";
			}
			message.append(getCurrentTime()).append(",").append(responseCode)
					.append(",").append(responseMessage).append(",")
					.append(Boolean.toString(isPageBodyRetrivalSuccess))
					.append("\n");
			writeToLogFile(log, message);
		}
	}

	public static synchronized void writeToLogFile(final File file,
			final StringBuffer stringBuffer) {

		final boolean isAppendEndOfFile = true;
		PrintWriter writer = null;
		try {

			writer = new PrintWriter(new BufferedWriter(new FileWriter(file,
					isAppendEndOfFile))); // append to end of file
			writer.write(stringBuffer.toString());
			writer.flush();
			if (writer.checkError()) {
				throw new IOException("Error occured while writing to the file");
			}
		} catch (IOException e) {

			logger.error("Error while writing to file", e);
			// log this
		} finally {

			writer.close();
		}
	}

	public static synchronized File createLogFile() throws IOException {
		String dailyTruncatedFileName = getCurrentDate();

		logger.debug("DailyTruncatedFileName : " + dailyTruncatedFileName);

		String logFileLocation = System.getProperty("user.home").concat("/")
				.concat(dailyTruncatedFileName).concat("_transmission_log.csv");
		logger.debug("LogFileLocation : " + logFileLocation);

		File logFile = new File(logFileLocation);

		if (logFile.exists()) {
			logger.debug("Log file exists");

			return logFile; // if log file exists return it and we know it has
							// header in it
		} else {
			logFile.createNewFile();
			logger.debug("Created new file atomically");
		}
		final PrintWriter writer = new PrintWriter(new BufferedWriter(
				new FileWriter(logFile)));

		String emptyString = new String();

		final String header = emptyString.concat("Time").concat(",")
				.concat("ResponseCode").concat(",").concat("ResponseMessage")
				.concat(",").concat("isDataTransferOK").concat("\n");

		logger.debug("Header : " + "\n" + header);

		try {
			writer.write(header);
			if (writer.checkError()) {
				logger.error("Error writing header to log file");
				throw new IOException("Error writing header in to log file");
			}
		} catch (IOException e) {

			logger.error("Something wrong with writing headers to log" + "\n", e);
		} finally {
			writer.close();
		}

		// finally return log file with header
		return logFile;
	}

	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(new Date());
	}

	public static String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");
		return dateFormat.format(new Date());
	}

	public void run() {

		try {

			sendPingRequest(createLogFile());

		} catch (IOException e) {

			logger.error("Unable to create log file" + "\n", e);

		}

	}
}
