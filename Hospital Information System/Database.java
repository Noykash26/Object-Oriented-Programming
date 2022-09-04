
import java.sql.*;

public class Database {

	private Connection conn;
	private static Statement stmt;
	

	public Database(){//constructor
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/test";
			conn=DriverManager.getConnection(url, "root", "root");
			stmt=conn.createStatement();
		} 
		catch (ClassNotFoundException e) {
		}
		catch (SQLException e) {
		}
		createTables("Patients");
	} 

	//Create Table
	public void createTables(String table1Name) {
		try{
			// if table already exists, drop it
			// if table already exists, SQL can't override it
			stmt.executeUpdate("DROP TABLE IF EXISTS " + table1Name);
			// create new empty table with given name
			stmt.executeUpdate("CREATE TABLE "+table1Name+" (IdPatient Varchar(50), agePatient int, Cure int, IdDoctor Varchar(50), typeTreatment int)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(String table, Prescription P) {
		try{
			stmt.executeUpdate("INSERT INTO "+table+" VALUES("+P.getpatientId()+", "+ P.getDrug()+" , '"+ P.getDoctorId()+"' , '"+ P.getTreatmentType()+"')");
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}
}

