package kr.or.connect.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.connect.todo.dto.Task;

public class TaskDao {
	//db 로그인 정보
	/* 
	private static String dburl ="jdbc:mysql://172.16.4.63:3306/todo?allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul&useSSL=false";
	private static String dbUser = "callie";
	private static String dbpasswd ="connect123!@#";
	*/
	private static String dburl ="jdbc:mysql://us-cdbr-east-04.cleardb.com:3306/heroku_b81f66892800e66?allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul&useSSL=false";
	private static String dbUser = "b11f0c7558f1b9";
	private static String dbpasswd ="170f5cff";
	
	public int taskAdd(Task task) {
		Connection conn =null;
		PreparedStatement ps =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");		
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		//"INSERT INTO role (role_id,description) VALUES(?,?)";
		String sql = "INSERT INTO todo (id,title,name,sequence,type,regdate) VALUES (?,?,?,?,?,?)";
		int result= 0;
		try{
			conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
			ps = conn.prepareStatement(sql);
			//1부터 시작하는거 주의
			ps.setInt(1, task.getBigint());
			ps.setString(2, task.getTitle());
			ps.setString(3, task.getName());
			ps.setInt(4, task.getSequence());
			ps.setString(5, task.getType());
			ps.setString(6, task.getRegdate());
			result=ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
	        if(conn!=null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	        if(ps!=null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	    }
		return result;
	}
	
	public ArrayList<Task> taskShow(){
		ArrayList<Task> tasks = new ArrayList<>();
		
		Connection conn =null;
		PreparedStatement ps =null;
		ResultSet rs =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn= DriverManager.getConnection(dburl,dbUser,dbpasswd);
			String sql ="SELECT * FROM `todo`";
			ps = conn.prepareStatement(sql);
		//	System.out.println(ps);
			rs = ps.executeQuery();

			while(rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				int sequence = rs.getInt(4);
				String type = rs.getString(5);
				String regdate = rs.getString(6);
				Task task = new Task(id,title, name,sequence,type,regdate);
				tasks.add(task);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
	        if(conn!=null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	        if(ps!=null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	        if(rs!=null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	    }
		return tasks;
		
	}
	
	public ArrayList<Task> taskShowType(String t){
		ArrayList<Task> tasks = new ArrayList<>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
			String sql="SELECT * FROM`TODO` WHERE TYPE = ?";
			ps =conn.prepareStatement(sql);
			ps.setString(1, t);
			rs= ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				int sequence = rs.getInt(4);
				String type = rs.getString(5);
				String regdate = rs.getString(6);
				Task task = new Task(id,title, name,sequence,type,regdate);
				tasks.add(task); 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
	        if(conn!=null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	        if(ps!=null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	        if(rs!=null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	    }
		return tasks;
	}


	public int taskUpdate(String id, String status) {
		Connection conn=null;
		PreparedStatement ps=null;
		int res =0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
			String sql=null;
			if(status.equals("todo")) {
				sql="UPDATE `todo` "
						+ "SET `type`='doing' where id= " +id;
			}else if(status.equals("doing")) {
				sql="UPDATE `todo` "
						+ "SET `type`='done' where id= " +id;
			}else if(status.equals("done")){
				//arc type - new 
				sql="UPDATE `todo` "
						+ "SET `type`='arc' where id= " +id;
				}
			ps=conn.prepareStatement(sql);
			res = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
	        if(conn!=null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	        if(ps!=null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }

	    }
		return res;

	}

	public void deleteTask(String id) {
		Connection conn = null;
		PreparedStatement ps =null;
		String sql=null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
			sql="DELETE FROM `TODO` WHERE ID = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			ps.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
	        if(conn!=null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	        if(ps!=null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }

	    }
		
	}
	
}
