/*
 * TalkServlet.java
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

import com.google.gson.reflect.TypeToken;
import com.ibm.watson.developer_cloud.service.WatsonService;
import com.ibm.watson.developer_cloud.text_to_speech.v1.*;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.util.ResponseUtil;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.net.URL;
import javax.sound.sampled.*;
import java.io.*;

import connectors.*;

import org.json.*;


@WebServlet("/TalkServlet")
public class TalkServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TalkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// get english text
		HttpSession s = request.getSession();
		String englishText = (String)s.getAttribute("outen");
		
		// System.out.println("in talk servlet: outen = " + englishText);
		
		// convert text to speech
        T2SConn conn2 = new T2SConn();
		TextToSpeech t2s = new TextToSpeech();
		t2s.setUsernameAndPassword(conn2.getUsername(), conn2.getPassword());

		String format = "audio/wav";

		InputStream speech = t2s.synthesize(englishText, format);
		
		response.setContentType("audio/wav");
		response.setHeader("Content-disposition", "attachment; filename=\"output.wav\"");
    	OutputStream output = response.getOutputStream();

	    byte[] buf = new byte[2046];
		int len;
		while ((len = speech.read(buf)) > 0) {
			output.write(buf, 0, len);
		}

		OutputStream os = output;

		os.flush();
		os.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        
	}
}
