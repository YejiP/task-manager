package kr.or.connect.todo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.Serializable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.todo.dao.TaskDao;
import kr.or.connect.todo.dto.Task;

/**
 * Servlet implementation class taskById
 */
@WebServlet("/task/*")
public class taskById extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public taskById(){
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		TaskDao dao= new TaskDao();
		/*String pathInfo = request.getPathInfo();
		String[] pathParts = pathInfo.split("/");
		String idStr = pathParts[1];
		int id = Integer.parseInt(idStr);
		*/
		
		
		ArrayList<Task> all = dao.taskShow();

		PrintWriter out = response.getWriter();
		
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
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TaskDao dao= new TaskDao();
		

		String target = req.getPathInfo();
		
		String id= target.split("/")[1];
		String status = target.split("/")[2];
		
		dao.taskUpdate(id,status);

		ArrayList<Task> all = dao.taskShow();

		
		PrintWriter out = resp.getWriter();		
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
