package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet(name="SessionServelt", urlPatterns={"/session"})
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SessionServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = "java";
		HttpSession hs1 = request.getSession();
					
		// counter
		Integer count = (Integer) hs1.getAttribute("count");
        if (count == null) {
            count = new Integer(1);
        } else {
            count = (Integer) hs1.getAttribute("count");
        }
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<BODY>");

		if (hs1.isNew()) {
			hs1.setAttribute("id", id);
			out.println(hs1.getAttribute("id") + " Nice to meet you.");
			
		} else {
			out.println("Hello! " + hs1.getAttribute("id"));
		}
		
		out.println("<br>");
		out.println("カウンタ:");
       out.println(count.toString());
		out.println("<br>");
		out.println("</BODY>");
		out.println("</HTML>");
		
		hs1.setAttribute("count", count + 1);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
