<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="beans.Examen" %>
<%@ page import="beans.Question" %>
<%@ page import="beans.Utilisateur" %>
<%@ page import="java.util.List" %>
<%@ include file="theme/header.jsp" %>

<% Examen examen = (Examen)request.getAttribute("examen"); %>
<% List<Question> questions = (List<Question>)examen.getQuestions(); %>

<div class="page-content">
	<div class="row">
		<div class="col-md-2">
			<%@ include file="theme/menu.jsp" %>
		</div>
		<div class="col-md-10">
			<div class="row">
			  	<div class="content-box-large">
					<div class="content-box-header panel-heading">
		  					<div class="panel-title "><%= examen.getSujet()+ ", Date : "+examen.getDate()+", votre CIN : "+user.getCin() %></div>
							
							<div class="panel-options">
								<a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
								<a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
							</div>
			  			</div>
					<form class="blocktext" method="post" action="examen"><p class="cente">
						<% for(int i = 0; i < questions.size() ; i++ ){ %>
						<% List<String> reponses = questions.get(i).getReponses(); %>
						<p><strong><%= questions.get(i).getQuestion() %>:</strong></p>	
							<% for( int j = 0 ; j < reponses.size() ; j++){ %>
				           <input type="radio" value="<%= reponses.get(j) %>" name="<%= questions.get(i).getCode() %>" style="margin-left:10%"/><label for="r11"><%= reponses.get(j) %></label><br />
				           <%}%>
				       	<%}%>		
						<input type="submit" name="action" value="Valider les réponses" class="btn"/><br /></p>
					</form>
		  		</div>
			</div>
		</div>
	
	</div>
</div>
	
<%@ include file="theme/footer.jsp" %>
	