<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="beans.Utilisateur" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Examen en ligne</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/RESSOURCES/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- styles -->
    <link href="${pageContext.request.contextPath}/RESSOURCES/css/styles.css" rel="stylesheet">
  </head>
  <html>
  	<body>
	<div class="header">
	     <div class="container">
	        <div class="row">
	           <div class="col-md-5">
	              <!-- Logo -->
	              <div class="logo">
	                 <h1><a href="index.html">Examen en ligne</a></h1>
	              </div>
	           </div>
	            <div class="col-md-5">
	              <div class="row">
	                <div class="col-lg-12">
	                  <div class="input-group form">
	                     
	                  </div>
	                </div>
	              </div>
	           </div>
	           <% Utilisateur user = (Utilisateur)session.getAttribute("Utilisateur"); %>
	     	   <% if( user != null){ %>
	           <div class="col-md-2">
	              <div class="navbar navbar-inverse" role="banner">
	                  <nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
	                    <ul class="nav navbar-nav">
	                      <li class="dropdown">
	                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Mon profile<b class="caret"></b></a>
	                        <ul class="dropdown-menu animated fadeInUp">
	                          <li><a href="#">Profile</a></li>
	                          <li><a href="deconnect?action=deconnexion">Deconnecter</a></li>
	                        </ul>
	                      </li>
	                    </ul>
	                  </nav>
	              </div>
	           </div>
	           <%}%>
	        </div>
	     </div>
	</div>
	
	<% if( request.getAttribute("msg") != null ){ %>
	<div class="alert alert-success" role="alert">
	  <a href="#" class="alert-link"><%= request.getAttribute("erreur") %></a>
	</div>
	<% } %>
	<% if( request.getAttribute("erreur") != null ){ %>
	<div class="alert alert-danger" role="alert">
	  <a href="#" class="alert-link"><%= request.getAttribute("erreur") %></a>
	</div>
	<%}%>
