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
	
    <a href="accueilprive"><img id="logo" src="img/logo.jpg" /></a>
    <div class="brand">Cafet'Ieseg</div>
    <div class="address-bar">3 Rue de la Digue | 59000 Lille | 03 20 54 58 92</div>

	<div class="container">
	<a href="espaceprive"><input type="Button" value="Administration" class="espaceprive" aria-label="Espace Privé"/></a>
	<a href="deconnexion"><input type="Button" value="<fmt:message key="deconnecter" />" class="espaceprive" aria-label="Espace Privé"/></a>
	</div>
	
    <!-- Menu de navigation -->
    <c:import url="navprive.jsp">
	    <c:param name="pageSelectionnee" value="carte"/>
	</c:import> 

    <div class="container">

        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    
                    <h1 class="brand-name" align="center"><fmt:message key="boisson.titre" /></h1>
					
                    <hr class="tagline-divider">
                   
				   <h3 align="center"><fmt:message key="boisson.text1" /></h3><h4 align="center"><fmt:message key="boisson.text2" /></h4>
				   
                    <hr class="tagline-divider2">
                    
                    <table class="table">
                    		<tr class="thead">
                    			<th class="th">#</th>
                    			<th class="th"><fmt:message key="name"/></th>
                    			<th class="th"><fmt:message key="prix"/></th>
                    			<th class="th"><fmt:message key="modifier"/></th>
                    			<th class="th"><fmt:message key="supprimer"/></th>
                    		</tr>
                    		
                    		<c:forEach var="boisson" items="${listeBoissons}">
                    		
                    		<tr class="tr">
                    			<td class="td">${boisson.id}</td>
                    			<td class="td">${boisson.nom}</td>
                    			<td class="td">${boisson.prix} €</td>
                    			<td class="td"><a href="
				        <c:url value="modifierBoisson">
				  			<c:param name="boissonId" value="${boisson.id}"/>
						</c:url>
				        " class="glyphicon glyphicon-edit" ></a></td>
                    			<td class="td"><a href="
				        	<c:url value="supprimerBoisson">
				  				<c:param name="askBoissonId" value="${boisson.id}"/>
							</c:url>
						" class="glyphicon glyphicon-trash"></a></td>
                    		</tr>
                    		
                    		</c:forEach>
                    		
                    	</table>
                    
                    <hr>
                    
					
			     	
     				<h3 align="center"><A href="ajouterBoisson"><fmt:message key="boisson.add"/></A></h3>
                  
                  <div class="nextandprevious">
                  
                  <div class="gauche">
                  	<a href="saladeprive"><span class="glyphicon glyphicon-arrow-left"></span></a>
                  </div>
                  
                  <div class="droit">
                  	<a href="petitdessertprive"><span class="glyphicon glyphicon-arrow-right"></span></a>
                  </div>
                  
                  </div>
                 
                </div>
            </div>
        </div>
    
    <hr class="tagline-divider">

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