package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Servlet implementation class Multiply
 */
@WebServlet(name="servlet/MultiplyServlet", urlPatterns={"/multiply"})
public class MultiplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MultiplyServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String operand1 = request.getParameter("operand1");
		String operand2 = request.getParameter("operand2");
		
		
		int result = Integer.parseInt(operand1) * Integer.parseInt(operand2);
	
		// joson mapper
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		rootNode.put("operand1", operand1);
		rootNode.put("operand2", operand2);
		rootNode.put("result", result);
		//mapper.writeValue(new File("/home/kzk/test.json"), rootNode);
		
		PrintWriter pw = response.getWriter();
		mapper.writeValue(pw, rootNode);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
