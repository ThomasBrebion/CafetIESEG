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

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <c:import url="language.jsp">
	</c:import>

   <a href="accueil"><img id="logo" src="img/logo.jpg" /></a>
    <div class="brand">Cafet'Ieseg</div>
    <div class="address-bar">3 Rue de la Digue | 59000 Lille | 03 20 54 58 92</div>

	<div class="container">
	<a href="espace"><input type="Button" value="<fmt:message key="espace" />" class="espaceprive" aria-label="Espace Privé"/></a>
	</div>
	
    <!-- Menu de navigation -->
    <c:import url="nav.jsp">
	    <c:param name="pageSelectionnee" value="menu"/>
	</c:import>
	
	<div id="JSONlisteBoissons" style="display:none;"><c:out value="${JSONlisteBoissons}" escapeXml="false" /></div>
    <div id="JSONlisteGrand_dessert" style="display:none;"><c:out value="${JSONlisteGrand_dessert}" escapeXml="false" /></div>
    <div id="JSONlistePetit_dessert" style="display:none;"><c:out value="${JSONlistePetit_dessert}" escapeXml="false" /></div>
    <div id=JSONlistePlat_chauds style="display:none;"><c:out value="${JSONlistePlat_chauds}" escapeXml="false" /></div>
    <div id="JSONlisteSalades" style="display:none;"><c:out value="${JSONlisteSalades}" escapeXml="false" /></div>
    <div id="JSONlisteSandwichs" style="display:none;"><c:out value="${JSONlisteSandwichs}" escapeXml="false" /></div>
    <div id="JSONlistePlats" style="display:none;"><c:out value="${JSONlistePlats}" escapeXml="false" /></div>
    
    <div align="center" class="container">
    <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">
                    	<fmt:message key="menu.composer"/>
                    </h2>
                    
                    <hr>

       <form>
       		<h3><fmt:message key="plat"/> : </h3>
	       	<select onchange="menuChanged()" id="selectPlat_chauds">
			  <optgroup label="<fmt:message key="platchaud"/>">
			  
			  	<c:forEach var="plat_chaud" items="${listePlat_chauds}">
                 
                 <option value="${plat_chaud.nom}">${plat_chaud.nom}</option>
			    
                </c:forEach>
                    		
			  </optgroup>
			  
			  <optgroup label="<fmt:message key="salade"/>">
			  
			 	<c:forEach var="salade" items="${listeSalades}">
                    		
			    <option value="${salade.nom}">${salade.nom}</option>
			    
                </c:forEach>
                    		
			  </optgroup>
			  
			  <optgroup label="Sandwich">
			  
			  	<c:forEach var="sandwich" items="${listeSandwichs}">
                    		
			    <option value="${sandwich.nom}">${sandwich.nom}</option>
			    
                </c:forEach>
                    		
			  </optgroup>
			</select>
			
       		<h3><fmt:message key="petitdessert"/> : </h3>
			<select onchange="menuChanged()" id="selectPetit_dessert">
			  
			  	<c:forEach var="petit_dessert" items="${listePetit_dessert}">
                    		
			    <option value="${petit_dessert.nom}">${petit_dessert.nom}</option>
			    
                </c:forEach>
                	
			</select>
			  
			<h3><fmt:message key="granddessert"/> : </h3>
			<select onchange="menuChanged()" id="selectGrand_dessert">
			  
			  	<c:forEach var="grand_dessert" items="${listeGrand_dessert}">
                    		
			    <option value="${grand_dessert.nom}">${grand_dessert.nom}</option>
			    
               </c:forEach>
                    		
			</select>
			
       		<h3><fmt:message key="boisson"/> : </h3>
			<select onchange="menuChanged()" id="selectBoissons">
			
				<c:forEach var="boisson" items="${listeBoissons}">
                    		
			    <option value="${boisson.nom}">${boisson.nom}</option>
			    
                </c:forEach>
                    		
			</select>
			
		</form>
		
		<h2><fmt:message key="text.prix.menu"/><span id="prixFinal">${plat_chaud.prix}</span> €</h2>

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
    
    <!-- Money Counter -->
    <script src="js/moneyCounter.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
