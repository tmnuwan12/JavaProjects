/**
 * 
 */
package encryption_util.rfc2898derivebytes;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author NThusitha
 * 
 */
public interface DbOperationAware {

	<K, V> Object doQuery(HashMap<K, V> params);

	<K, V> void doInsert(HashMap<K, V> params) throws SQLException;

	<K, V> void doUpdate(HashMap<K, V> params);

	<K, V> void doDelete(HashMap<K, V> params);
}
