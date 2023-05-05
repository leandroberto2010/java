package database;
import java.sql.*;
import java.util.LinkedList;

import entities.Product;
public class DbHandler {

	private String driver="com.mysql.cj.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="root";
	private String password="java";
	private String db="javaMarketB";
	private String options="?useLegacyDatetimeCode=false&serverTimezone=Asia/Hong_Kong";
	//private String options="";
	private Connection conn=null;
	
	public DbHandler() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() {
		try {
			if(conn==null || conn.isClosed())
			conn=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+options, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	private void releaseConnection() {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public LinkedList<Product> list() {
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn;
		
		try {
			conn = this.getConnection();
			LinkedList<Product> prods = new LinkedList<Product>();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from product");
			while (rs.next() && rs != null) {
				Product p = new Product();
				
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setDescription(rs.getString("description"));
				p.setPrice(rs.getDouble("precio"));
				p.setStock(rs.getInt("stock"));
				p.setShippingIncluded(rs.getBoolean("shippingIncluded"));
				prods.add(p);
			}
			return prods;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		
		}finally {
			try {
				if (rs!=null) rs.close();
				if (stmt!=null) stmt.close();
				this.releaseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		

	}
	
	public Product search(Product p) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn;
		
		try {
			Product prod = new Product();
			conn = this.getConnection();
			stmt = conn.prepareStatement("select * from product where id = ?");
			stmt.setInt(1, p.getId());
			rs = stmt.executeQuery();
			
			if (rs!= null && rs.next()){
				prod.setId(rs.getInt("id"));
				prod.setName(rs.getString("name"));
				prod.setDescription(rs.getString("description"));
				prod.setPrice(rs.getDouble("precio"));
				prod.setStock(rs.getInt("stock"));
				prod.setShippingIncluded(rs.getBoolean("shippingIncluded"));
			}
			return prod;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				this.releaseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public void newProduct(Product newprod) {
		PreparedStatement stmt = null;
		ResultSet keyRS = null;
		Connection conn;
		
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement("insert into product(name, description, price, stock, shippingIncluded)" + "values(?. ?. ?. ?, ?)"
					, Statement.RETURN_GENERATED_KEYS);
					stmt.setString(1, newprod.getName());
					stmt.setString(2, newprod.getDescription());
					stmt.setDouble(3, newprod.getPrice());
					stmt.setInt(4, newprod.getStock());
					stmt.setBoolean(5, newprod.isShippingIncluded());
			stmt.executeUpdate();
			keyRS = stmt.getGeneratedKeys();
			
			if(keyRS !=null && keyRS.next()) {
				newprod.setId(keyRS.getInt("id"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try{
				if(keyRS!=null) keyRS.close();
				if(stmt!=null) stmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteProduct(Product delprod) {
		PreparedStatement stmt = null;
		Connection conn;
		
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement("delete * from product where id = ?");
			stmt.setInt(1, delprod.getId());
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (stmt!=null) stmt.close();
				this.releaseConnection();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public void updateProduct(Product produpd) {
		PreparedStatement stmt = null;
		Connection conn;
		
		try {
			conn = this.getConnection();
			stmt = conn.prepareStatement("update product " + "set name = ?, description = ?, price = ?, stock = ?, shippingIncluded = ?" + "where id = ?");
			stmt.setString(1, produpd.getName());
			stmt.setString(2, produpd.getDescription());
			stmt.setDouble(3, produpd.getPrice());
			stmt.setInt(4, produpd.getStock());
			stmt.setBoolean(5, produpd.isShippingIncluded());
			stmt.setInt(6, produpd.getId());
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (stmt!=null) stmt.close();
				this.releaseConnection();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
	