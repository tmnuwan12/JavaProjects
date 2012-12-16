/**
 * 
 */
package encryption_util.rfc2898derivebytes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

/**
 * Use to manipulate security token data to/from the database.
 * 
 * @author NThusitha
 * 
 */
@Repository
public class SecurityTokenDao extends PersistentService implements
		DbOperationAware {

	public <K, V> Object doQuery(HashMap<K, V> params) {
		return params;

	}

	public <K, V> void doInsert(HashMap<K, V> params) throws SQLException{

		Connection c = DataSourceUtils
				.getConnection(super.getMySqlDataSource());

		String sql = "INSERT INTO Security.TokenLog(Username, Password, Salt, Time) "
				+ "VALUES ('"
				+ params.get("username")
				+ "',"
				+ "'"
				+ params.get("password")
				+ "',"
				+ "'"
				+ params.get("salt")
				+ "'," + "NOW()" + ");"; // Insert token information into the
										// database.
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.execute();

		} finally {
			DataSourceUtils.releaseConnection(c, super.getMySqlDataSource()); //close the connection
		}
	}

	public <K, V> void doUpdate(HashMap<K, V> params) {

	}

	public <K, V> void doDelete(HashMap<K, V> params) {

	}
}
