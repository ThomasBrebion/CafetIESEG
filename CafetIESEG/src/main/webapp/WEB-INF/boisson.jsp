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
	<a href="espace"><input type="Button" value="Espace Privé" class="espaceprive" aria-label="Espace Privé"/></a>
	</div>
    <!-- Menu de navigation -->
    <c:import url="nav.jsp">
	    <c:param name="pageSelectionnee" value="carte"/>
	</c:import> 

    <div class="container">

        <div class="row">
            <div class="box">
                <div class="col-lg-12 text-center">
                    
                    <h1 class="brand-name"><fmt:message key="boisson.titre" /></h1>
					
                    <hr class="tagline-divider">
                   
				   <h3><fmt:message key="boisson.text1" /></h3><h4><fmt:message key="boisson.text1" /></h4>
				   
                    <hr class="tagline-divider2">
                    
                    <c:forEach var="boisson" items="${listeBoissons}">
                    
                   		<h5>${boisson.nom}</h5><p>${boisson.prix}€</p>
                    		
                    </c:forEach>
                  
                  <div class="nextandprevious">
                  
                  <div class="gauche">
                  	<a href="salade"><span class="glyphicon glyphicon-arrow-left"></span></a>
                  </div>
                  
                  <div class="droit">
                  	<a href="petitdessert"><span class="glyphicon glyphicon-arrow-right"></span></a>
                  </div>
                  
                  </div>
                 
                </div>
            </div>
        </div>

    </div>
    <!-- /.container -->

    <footer>
		<fmt:message key="footer" />
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