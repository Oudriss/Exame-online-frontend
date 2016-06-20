<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Examen en ligne</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/RESSOURCES/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- styles -->
    <link href="${pageContext.request.contextPath}/RESSOURCES/css/styles.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="login-bg">
  	<div class="header">
	     <div class="container">
	        <div class="row">
	           <div class="col-md-12">
	              <!-- Logo -->
	              <div class="logo">
	                 <h1><a href="/home">Examen en ligne</a></h1>
	              </div>
	           </div>
	        </div>
	     </div>
	</div>
	<% if( request.getAttribute("erreur") != null ){ %>
	<div class="alert alert-danger" role="alert">
	  <a href="#" class="alert-link"><%= request.getAttribute("erreur") %></a>
	</div>
	<%}%>
	<div class="page-content container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-wrapper">
			        <div class="box">
			            <div class="content-wrap">
			                <h6>Sign In</h6>
			                <form action="login" method="post">
				                <input class="form-control" name="user" type="text" placeholder="Username">
				                <input class="form-control" name="pass"  type="password" placeholder="Password">
				                <div class="action">
				                    <input type="submit" value="Connecter" name="action" class="btn">
				                </div> 
			                </form>               
			            </div>
			        </div>
			    </div>
			</div>
		</div>
	</div>


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/RESSOURCES/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/RESSOURCES/js/custom.js"></script>
  </body>
</html>