<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="beans.Candidature" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="theme/header.jsp" %>

<div class="page-content">
	<div class="row">
		<div class="col-md-2">
			<%@ include file="theme/menu.jsp" %>
		</div>
	
	
		<div class="col-md-10">
			<div class="row">
			  	<div class="content-box-large">
  				<div class="panel-heading">
					<div class="panel-title">Responsive Tables</div>
				</div>
  				<div class="panel-body">
  					<div class="table-responsive">
  						<table class="table">
			              <thead>
			                <tr>
			                  <th>#</th>
			                  <th>Sujet</th>
			                  <th>Date</th>
			                  <th>CIN</th>
			                  <th>Note</th>
			                </tr>
			              </thead>
			              <tbody>
			              	<% ArrayList<Candidature> candidatures = (ArrayList<Candidature>) request.getAttribute("candidatures"); %>
							<% ArrayList<Float> notes = (ArrayList<Float>) request.getAttribute("notes"); %>
							<% for( int i = 0 ; i < candidatures.size() ; i++){ %>
				                <tr>
				                  <td><%= i+1 %></td>
				                  <td><%=candidatures.get(i).getExamen().getSujet() %></td>
				                  <td><%=candidatures.get(i).getExamen().getDate() %></td>
				                  <td><%=candidatures.get(i).getUtilisateur().getCin()%></td>
				                  <td><%=notes.get(i) %></td>
				                  <td>
				                  	<form action="printPDF" method="post">
				                  		<input type="hidden" name="code" value="<%=candidatures.get(i).getExamen().getCode()%>" />
				                  		<input type="hidden" value="<%=(candidatures.get(i).getExamen().getDate().getYear()+1900)+"/"+(candidatures.get(i).getExamen().getDate().getMonth()+1)%>" name="date">
				                  		<input type="submit"  class="form-control" name="exportPdf" value="Exporter PDF" />
				                  	</form>
				                  </td>
				                </tr>
			                <%} %>
			              </tbody>
			            </table>
  					</div>
  				</div>
  			</div>
			</div>
		</div>
	
	</div>
</div>
	
<%@ include file="theme/footer.jsp" %>
	
	