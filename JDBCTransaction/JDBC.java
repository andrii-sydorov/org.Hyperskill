package JDBCTransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteDataSource;

public class JDBC {

	private static Connection con;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		setConnection();
		if (con == null) {
			System.exit(0);
		}
		deleteAllTables();
		createTable();
		fillTable();
		crateTableRetweets();
		fillTableRetweets();
		createTableDinosaursAndPlants();
		fillTableDinosaursAndPlants();
		query();
	}

	private static void setConnection() {
		SQLiteDataSource sq = new SQLiteDataSource();
		String url = "jdbc:sqlite:./src/JDBCTransaction/database.db";
		sq.setUrl(url);
		try {
			con = sq.getConnection();
		} catch (SQLException sqle) {
			System.out.println("The connection was not created!");
		}
	}

	private static void deleteAllTables() {
		List<String> ls = new ArrayList<>();
		try (ResultSet rs = con.getMetaData().getTables(null, null, null, null)) {
			while (rs.next()) {
				ls.add(rs.getString("TABLE_NAME"));
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		ls.forEach(x -> System.out.println(x));
		try (Statement st = con.createStatement()) {
			for (String s : ls) {
				if (s.equals("order")) {
					s = "\"order\"";
				}
				String toDelete = "DROP TABLE IF EXISTS " + s + ";";
				st.executeUpdate(toDelete);
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	private static void createTable() {
		String createInvoice = "CREATE TABLE IF NOT EXISTS \"invoice\"" + "(id INTEGER PRIMARY KEY,"
				+ "shipping_address TEXT NOT NULL," + "total_cost INTEGER NOT NULL);";
		String createOrder = "CREATE TABLE IF NOT EXISTS \"order\"" + "(id INTEGER PRIMARY KEY,"
				+ "invoice_id INTEGER NOT NULL," + "product_name TEXT NOT NULL,"
				+ "FOREIGN KEY(invoice_id) REFERENCES invoice(id));";
		int firstTable = 0;
		int secondTable = 0;
		try (Statement st = con.createStatement()) {
			firstTable = st.executeUpdate(createInvoice);
			secondTable = st.executeUpdate(createOrder);
		} catch (SQLException sq) {
			sq.printStackTrace();
			if (firstTable == 0) {
				System.out.println("The table invoice wasn't created");
			}
			if (secondTable == 0) {
				System.out.println("The table order wasn't created");
			}
		}
	}

	private static void fillTable() {
		String insertInvoiceSQL = "INSERT INTO \"invoice\"(id, shipping_address, total_cost)" + "VALUES (?, ?, ?);";
		String insertOrderSQL = "INSERT INTO \"order\"(id, invoice_id, product_name)" + "VALUES (?,?,?)";
		try {
			con.setAutoCommit(false);
			try (PreparedStatement insertInvoice = con.prepareStatement(insertInvoiceSQL);
					PreparedStatement insertOrder = con.prepareStatement(insertOrderSQL)) {

				int invoiceId = 1;
				insertInvoice.setInt(1, invoiceId);
				insertInvoice.setString(2, "Dearborn, Michigan");
				insertInvoice.setInt(3, 100500);
				insertInvoice.executeUpdate();

				int orderId = 1;
				insertOrder.setInt(1, orderId);
				insertOrder.setInt(2, invoiceId);
				insertOrder.setString(3, "Ford Model A");
				insertOrder.executeUpdate();

				con.commit();
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		}

	}

	private static void crateTableRetweets() {
		String createRetweets = "CREATE TABLE IF NOT EXISTS retweets(" + "id Integer," + "text VARCHAR(40));";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(createRetweets);
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	private static void fillTableRetweets() {
		String query = "INSERT INTO retweets(id, text)" + "VALUES(?,?);";

		try (PreparedStatement ps = con.prepareStatement(query)) {
			if (con.getAutoCommit()) {
				con.setAutoCommit(false);
			}
			for (int i = 0; i < 10; i++) {
				Savepoint s1 = con.setSavepoint();

				String text = "Congratulations to the Astronauts that left Earth today. Good choice";

				ps.setInt(1, i);
				ps.setString(2, text);
				ps.executeUpdate();

				Savepoint s2 = con.setSavepoint();

				if (i < 5) {
					con.rollback(s1);
				} else if (i < 9) {
					con.rollback(s2);
				} else {
					con.commit();
				}
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}
	
	private static void createTableDinosaursAndPlants() {
		String createDinosaurs = "CREATE TABLE IF NOT EXISTS dinosaurs("
				+ "id INTEGER NOT NULL,"
				+ "name VARCHAR(40) NOT NULL,"
				+ "domain VARCHAT(40) NOT NULL);";
		String createPlants = "CREATE TABLE IF NOT EXISTS plants("
				+ "id INTEGER NOT NULL,"
				+ "name VARCHAR(40) NOT NULL,"
				+ "kingdom VARCHAR(40) NOT NULL);";
		try(Statement st = con.createStatement()) {
			st.executeUpdate(createDinosaurs);
			st.executeUpdate(createPlants);
		} catch(SQLException sql) {
			sql.printStackTrace();
		}
	}
	
	private static void fillTableDinosaursAndPlants() {
		String insertDinosaur = "INSERT INTO dinosaurs(id, name, domain)"
				+ "VALUES(?,?,?);";
		String insertPlants = "INSERT INTO plants(id, name, kingdom)"
				+ "VALUES(?,?,?);";
		try(PreparedStatement dinosaurPreparedStatement = con.prepareStatement(insertDinosaur);
				PreparedStatement plantPreparedStatement = con.prepareStatement(insertPlants)) {
			
			dinosaurPreparedStatement.setInt(1, 1);
			dinosaurPreparedStatement.setString(2, "Triceratops");
			dinosaurPreparedStatement.setString(3, "Eukaryota");
			dinosaurPreparedStatement.executeUpdate();
			
			con.commit();
			
			plantPreparedStatement.setInt(1, 1);
	        plantPreparedStatement.setString(2, "Rose");
	        plantPreparedStatement.setString(3, "Plantae");
	        plantPreparedStatement.executeUpdate();
		} catch(SQLException sql) {
			sql.printStackTrace();
			try{
				con.rollback();
			} catch(SQLException s1) {
				s1.printStackTrace();
			}
		}
	}
	
	private static void query() {
		String query = "SELECT shipping_address FROM  \"invoice\""
				+ "WHERE id = ?;";
		try(PreparedStatement selectFromInvoice = con.prepareStatement(query)) {
			int id = 1;
			selectFromInvoice.setInt(1, id);
			ResultSet rs = selectFromInvoice.executeQuery();
			if (rs.getString(1).equals("Dearborn, Michigan")) {
				System.out.println("You are a fool!");
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

}
