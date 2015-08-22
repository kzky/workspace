package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SetDataServlet
 */
@WebServlet(name="SetDataSetvlet", urlPatterns={"/setData"},
initParams={@WebInitParam(name="init-name", value="init-value")})
public class SetDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetDataServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	
		String name = new String("Java太郎");
		String id = new String("java");
		String prog = new String("Java-Register");
	
		// request scope
		ServletConfig scf = getServletConfig();
		
		request.setAttribute("init-name", scf.getInitParameter("init-name"));
		
		// session scope
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		
		// context scope
		ServletContext sc = getServletContext();
		sc.setAttribute("prog", prog);
				
		// context parameter
			// get context parameter 
		// System.out.println(getServletContext().getInitParameter("context-name"));
		
		// forward
		RequestDispatcher rd = request.getRequestDispatcher("./getData");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
