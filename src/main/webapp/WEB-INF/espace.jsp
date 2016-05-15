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

    <a href="accueil"><img id="logo" src="img/logo.jpg" /></a>
    <div class="brand">Cafet'Ieseg</div>
    <div class="address-bar">3 Rue de la Digue | 59000 Lille | 03 20 54 58 92</div>

	<div class="container">
	<a href="espace"><input type="Button" value="<fmt:message key="espace" />" class="espaceprive" aria-label="Espace Privé"/></a>
	</div>
	
    <!-- Menu de navigation -->
    <c:import url="nav.jsp">
	    <c:param name="pageSelectionnee" value="espace"/>
	</c:import> 
	
	<div class="container">
	
	<div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">
                    	<strong><fmt:message key="enregistrer1"/></strong>
                    </h2>
                    <hr>
								 					
                    <form action="LoginServlet" method="post" class="form">
                    
                    <c:choose>
                    	<c:when test="${messageErreur != null}">
                    		<div class="aler alert-danger">${messageErreur}<br/></div>
                    	</c:when>
                    	<c:otherwise>
                    		${messageErreur=''}
                    	</c:otherwise>
                    </c:choose>
                    
                        <div class="col">
                            <div class="form-group col-lg-12">
                                <label><fmt:message key="identifiant"/></label>
                                <input required name="id" autofocus type="text" class="form-control">
                            </div>
							
                            <div class="clearfix"></div>

							
                            <div class="form-group col-lg-12">
                                <label><fmt:message key="motdepasse"/></label>
                                <input required name="mdp" type="password" class="form-control">
                            </div>
							
                            <div class="form-group col-lg-12">
                            	<input value="<fmt:message key="enregistrer2" />" name="valider" type="submit" class="btn btn-default">
                            </div>
                        </div>
                       
                    </form>
                   
					<img class="espace" src="img/espace.png" />
						
					
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
