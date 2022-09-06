package kr.or.connect.todo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.connect.todo.dao.TaskDao;
import kr.or.connect.todo.dto.Task;

/**
 * Servlet implementation class main
 */
@WebServlet("/arc/*")
public class arc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TaskDao dao = new TaskDao();

	
	public arc() {
        super();
        //int bigint, String title, String name, int sequence, String type, String regdate
        //Task task = new Task(1,"Studying algorithm","Callie Park",1,"Doing","2021-07-22");
        
    }

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	resp.setCharacterEncoding("utf-8");
    	resp.setContentType("application/json");
    	ArrayList<Task> all = dao.taskShowType("arc");
    	

		PrintWriter out = resp.getWriter();
		
		//Task task = new Task(2,"Studying algorithm","Callie Park",2,"Doing","2021-07-22");
		out.println("[");
		for(int i=0;i<all.size() ;i++) {
			ObjectMapper objectMapper =new ObjectMapper();
			String json = objectMapper.writeValueAsString(all.get(i));
			out.println(json);
			if(i!=all.size()-1) {
				out.println(",");
			}
		}
		out.println("]");
		out.close();

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TaskDao dao = new TaskDao();
		String target =req.getPathInfo();
		String id = target.split("/")[1];
		dao.deleteTask(id);
		PrintWriter out = resp.getWriter();
    	ArrayList<Task> all = dao.taskShowType("arc");

		out.println("[");
		for(int i=0;i<all.size() ;i++) {
			ObjectMapper objectMapper =new ObjectMapper();
			String json = objectMapper.writeValueAsString(all.get(i));
			out.println(json);
			if(i!=all.size()-1) {
				out.println(",");
			}
		}
		out.println("]");
		out.close();	
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
