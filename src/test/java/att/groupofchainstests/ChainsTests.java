package att.groupofchainstests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.att.biq.db.Chains;
import com.att.biq.db.DbConnection;
import com.mysql.jdbc.Connection;

public class ChainsTests extends DbConnection {

	public ChainsTests() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void CreateChainInDBGoodTest() throws SQLException, IOException {
		Connection con = connect();
		PreparedStatement preparedStatement = null;
		boolean res = new Chains(con).createChain("Yada", null, "Vandaly ind.");
		assertTrue(res);
		ResultSet rs = null;
		preparedStatement = con.prepareStatement("SELECT * FROM groups where group_name='Yada'");
		rs = preparedStatement.executeQuery();
		assertTrue(rs.next());
		String parent = rs.getString("parent_group_id");
		assertNull(parent);
		String type = rs.getString("group_type");
		assertEquals("Vandaly ind.", type);

	}

}
