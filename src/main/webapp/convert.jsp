
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="javax.servlet.jsp.*"%>
<%@page import="servlets.*"%>

<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <title>Language Translation and Text-to-Speech</title>
    </head>
    <body>
        <h1>Translation</h1>
		<p><b>Input Text (SPANISH):</b> <% out.print(request.getParameter("inputes")); %></p>
		<p><b>Translated Text (ENGLISH):</b> <% out.print(session.getAttribute("outen")); %></p>
		
		
		<h3>Convert Translated Text to WAV</h3>
		<a href="/TalkServlet">Download File</a>
    </body>
</html>
