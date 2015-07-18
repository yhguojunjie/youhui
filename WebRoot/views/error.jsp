<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%
Integer status = (Integer)request.getAttribute("statusCode");
String errorURI = (String)request.getAttribute("errorURI");
if(errorURI!=null && errorURI.length()>0){
	if(errorURI.indexOf("/pc/")!=-1 || errorURI.indexOf("/cj/admin/")!=-1){
		%><%@include file="/views/pc/error.jsp" %><%
	}else if(errorURI.indexOf("/wx/")!=-1){
		%><%@include file="/views/wx/error.jsp" %><%
	}else if(errorURI.indexOf("/cj/")!=-1){
		%><%@include file="/views/wx/error.jsp" %><%
	}
	else{
		%><%@include file="/views/pc/error.jsp" %><%
	}
}
%>

