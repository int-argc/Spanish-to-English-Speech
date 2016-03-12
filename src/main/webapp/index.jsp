
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
        <form action="SpanishServlet" method="post">
            Spanish Text: <input type="text" name="inputes" />
            <input type="submit" value="Convert" />
        </form>
    </body>
</html>
