package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GetDataServlet
 */
@WebServlet(name="GetDataServlet", urlPatterns={"/getData"})
public class GetDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetDataServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		//(1)requestスコープのデータを取得
		String name = (String)request.getAttribute("init-name");

		//(2)sessionスコープのデータを取得
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");

		//(3)applicationスコープのデータを取得
		ServletContext sc = getServletContext();
		String prog = (String)sc.getAttribute("prog");

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		//(4)取得したデータの表示
		out.println("<HTML>");
		out.println("<BODY>");

		out.println("名前:" + name);
		out.println("<BR>");
		out.println("ID:" + id);
		out.println("<BR>");
		out.println("プログラム:" + prog);
		out.println("<BR>");
		out.println("context-name:" + 
				getServletContext().getInitParameter("context-name"));

		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
