<%-- 
    Document   : Search
    Created on : Apr 6, 2018, 1:38:27 PM
    Author     : Zach
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="default_package.Bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
textarea
{
    overflow-y:scroll;
}
</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Microminer</title>
    </head>
    <body>
        <h2>KWIC Microminer</h2>
        
        <%-- Set up input, output results, and previous search text --%>     
        <% 
           Bean bean = (Bean) request.getSession().getAttribute("bean");
           ArrayList<String> input = new ArrayList<String>();
           ArrayList<String> results = new ArrayList<String>();
           if(bean != null){
                System.out.println("Bean not null");
                input = bean.getInput();
                results = bean.getResults();
           }
           String search = (String)request.getAttribute("search");
           if(search == null) search = "";
        %>
        
        <%-- Textarea for database input --%>
        <form action="${pageContext.request.contextPath}/InputServlet" method="post">
            <textarea type="text" placeholder="Input" name="input" rows="15" cols="150">
<% if(input != null) for(int i = 0; i < input.size(); i++) { %>     
<%=input.get(i) %>
<% } %>
            </textarea>
            <br>
            <input type="submit" name="submit" value="Submit Input"/>
        </form>
        
        <%-- Search bar for keywords and textarea for database results --%>
        <form action="${pageContext.request.contextPath}/SearchServlet" method="post">
            <br><br>
            <input type="text" name="search" placeholder="Search" value=<%=search%>>
            <input type="submit" name="submit" value="Submit"/>
            <br><br>
            <textarea name="output" rows="15" cols="150">
<% if(results != null) for(int i = 0; i < results.size(); i++) { %>     
<%=results.get(i) %>
<% } %>
            </textarea>
        </form>
            
        <%-- Clear database button --%>
        <form action="${pageContext.request.contextPath}/ClearDBServlet" method="post">
            <input type="submit" name="clearDB" value="Clear Database"/>
        </form>
    </body>
<%--Here's the original line 47 if bean doesn't work--%>
<%--
    <% if(output != null) for(int i = 0; i < output.size(); i++) { %>     
    <%=output.get(i) %>
    <% } %>
--%>
</html>

