<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />

<!DOCTYPE html>
<html lang="${language}">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="img/icone.ico">

    <title>Cafet'Ieseg</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/business-casual.css" rel="stylesheet">

    <!-- Fonts -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

	

</head>

<body>

    <c:import url="language.jsp">
	</c:import>

    <a href="accueilprive"><img id="logo" src="img/logo.jpg" /></a>
    <div class="brand">Cafet'Ieseg</div>
    <div class="address-bar">3 Rue de la Digue | 59000 Lille | 03 20 54 58 92</div>

	<div class="container">
	<a href="espaceprive"><input type="Button" value="Administration" class="espaceprive" aria-label="Espace Privé"/></a>
	<a href="espace"><input type="Button" value="<fmt:message key="deconnecter" />" class="espaceprive" aria-label="Espace Privé"/></a>
	</div>
	
    <!-- Menu de navigation -->
    <c:import url="navprive.jsp">
	    <c:param name="pageSelectionnee" value="espace"/>
	</c:import> 
	
	<div class="container">
	
	<div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">
						<fmt:message key="espaceprive"/>
                    </h2>
                    
                    <hr>
                    
                    	<table class="table">
                    		<tr class="thead">
                    			<th class="th"><fmt:message key="produit"/></th>
                    			<th class="th"><fmt:message key="date"/></th>
                    			<th class="th"><fmt:message key="quantity"/></th>
                    			<th class="th"><fmt:message key="unitprice"/></th>
                    			<th class="th"><fmt:message key="modifier"/></th>
                    			<th class="th"><fmt:message key="supprimer"/></th>
                    		</tr>
                    		
                    		<c:forEach var="produits" items="${listeProduits}">
                    		
                    		<tr class="tr">
                    			<td class="td">${produits.nom}</td>
								<td><fmt:formatDate value="${produits.date}" pattern="d MMMM yyyy"/> 
								
								<c:if test="${ produits.days_left <= 2 }"><span style="color:red"></c:if>
								( <c:if test="${ produits.days_left <= 2 }"><b><fmt:message key="attention"/></b></c:if> ${produits.days_left} <fmt:message key="jours"/>)
								<c:if test="${ produits.days_left <= 2 }"></span></c:if>
								</td>
                    			<td class="td">${produits.quantite}</td>
                    			<td class="td">${produits.prix} €</td>
                    			<td class="td"><a href="
                    				<c:url value="modifierProduit">
  										<c:param name="produitId" value="${produits.id}"/>
										</c:url>" class="glyphicon glyphicon-edit"></a></td>
                    			<td class="td"><a href="
                    				<c:url value="supprimerProduit">
  										<c:param name="askProduitId" value="${produits.id}"/>
										</c:url>
										" class="glyphicon glyphicon-trash"></a></td>
                    		</tr>
                    		
                    		</c:forEach>
                    		
                    	</table>
     
     	<!--<h3 align="center"><A href="javascript:ouvre_popup('supprimer')">Supprimer un produit</A></h3>
		<SCRIPT>
		   function ouvre_popup(page) {
		       window.open(page,"Supprimer produit","menubar=no, status=no, menubar=no, width=600, height=500");
		   }
		</SCRIPT>-->
		
     	<h3 align="center"><A href="javascript:ouvre_popup('ajout')"><fmt:message key="produit.add"/></A></h3>
		<SCRIPT>
		   function ouvre_popup(page) {
		       window.open(page,"Ajout produit","menubar=no, status=no, menubar=no, width=600, height=500");
		   }
		</SCRIPT>
					
                </div>
            </div>
        </div>
	
	</div>
	
    <!-- /.container -->

    <footer>
		<fmt:message key="footer"/>
    </footer>

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Script to Activate the Carousel -->
    <script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>

</body>

</html>
