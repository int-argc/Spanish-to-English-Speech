/*
 * SpanishServlet.java
 * @author: jeff
*/

package servlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.watson.developer_cloud.language_translation.v2.LanguageTranslation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationResult;

import java.io.*;

import connectors.*;

import org.json.*;


@WebServlet("/SpanishServlet")
public class SpanishServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpanishServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String input = request.getParameter("inputes");
		
		// System.out.println("inputes = " + input);

        LangTransConn conn = new LangTransConn();
        LanguageTranslation trans = new LanguageTranslation();
        trans.setUsernameAndPassword(conn.getUsername(), conn.getPassword());

        TranslationResult result = trans.translate(input, "es", "en");
        String jsonStr = result.toString();
        // sample result: { "translations": [ { "translation": "Bonjour" } ], "word_count": 1, "character_count": 5 }
        JSONObject obj = new JSONObject(jsonStr);
        JSONArray arr = obj.getJSONArray("translations");

        String englishText = arr.getJSONObject(0).getString("translation");
		
		HttpSession s = request.getSession();
		s.setAttribute("outen", englishText);
		
		// System.out.println("outen = " + englishText);
        response.setContentType("text/html");
		response.setStatus(200);
		request.getRequestDispatcher("convert.jsp").forward(request,response);
		
		// response.sendRedirect("convert.jsp");
	}
}
