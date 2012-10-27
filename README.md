HttpUrlConnection Utility

Monitor remote host specified by URL object.

This application can be built and assembled as a jar executable using maven. This program will send request to remote URL: http://www.google.com (which can be changed as required) and try to retrieve the web page. It will read the web page via an InputStream within given specific read timeout period. Requests are fired by multiple treads which are in a pool. Currently only 5 threads are running in the pool.

Results are evaluated as folloows: **Inspect response code from the server **Check the reponse body can be retrived within given timeout period. If a complete data retrival is done we evaluate that request initiation a successful one otherwise un-successful.

There are more improvments have to done such like adding more flexible Spring capability so depedencies can be changed along with other parameters like request timeouts and all cache variables. Application will store results in .csv format in a folder in user's home directory and this directory is truncated daily. All read/writes to data logs are synchronized.