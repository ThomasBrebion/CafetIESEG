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
	    <c:param name="pageSelectionnee" value="contact"/>
	</c:import> 

    <div class="container">

        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">
                        <strong><fmt:message key="info"/></strong>
                    </h2>
                    <hr>
                </div>
                <div class="col-md-8">
                    <!-- Embedded Google Map using an iframe - to select your location find it on Google maps and paste the link as the iframe src. If you want to use the Google Maps API instead then have at it! -->
                    <iframe frameborder="0" scrolling="no" marginheight="0" marginwidth="0" width="700"height="400" src ="https://maps.google.com/maps?hl=fr&q=3 rue de la digue lille&ie=UTF8&t=m&z=16&iwlox=B&output=embed"></iframe>
                </div>
                <div class="col-md-4">
                    <p><fmt:message key="phone"/> :
                        <strong>03 20 54 58 92</strong>
                    </p>
                    <p>Mail :
                        <strong>cafetieseg@ieseg.fr</strong>
                    </p>
                    <p><fmt:message key="adresse"/> :
                        <strong>3 Rue de la Digue
                            <br>59000 Lille</strong>
                    </p>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>

        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center"> <fmt:message key="nouscontacter"/>
                        
                    </h2>
                    <hr>
                    <form action="mailto:thomas.brebion@hei.fr" method=POST enctype="text/plain">
                        <div class="row">
                        	<div class="form-group col-lg-5">
                                <label><fmt:message key="sujet"/></label><br>
                                <input class="marge" type="radio" name="subject" value="renseignement"><fmt:message key="renseignement"/>
                                <input class="marge" type="radio" name="subject" value="commentaire"><fmt:message key="commentaire"/>
                                <input class="marge" type="radio" name="subject" value="actualité"><fmt:message key="actualite"/>
                                <input class="marge" type="radio" name="subject" value="sponsor">Sponsor
                            </div>
                            <div class="form-group col-lg-2">
                                <label><fmt:message key="name"/></label>
                                <input type="text" name="nom" class="form-control">
                            </div>
                            <div class="form-group col-lg-3">
                                <label>Mail</label>
                                <input required type="email" name="mail" class="form-control">
                            </div>
                            <div class="form-group col-lg-2">
                                <label><fmt:message key="phonenumber"/></label>
                                <input type="tel" class="form-control" name="numero" placeholder="XX XX XX XX XX">
                            </div>
                            <div class="clearfix"></div>
                            <div class="form-group col-lg-12">
                                <label>Message</label>
                                <textarea required class="form-control" name="message" rows="6"></textarea>
                            </div>
                            <div class="form-group col-lg-12">
                                <input type="hidden">
                                <button type="submit" class="btn btn-default"><fmt:message key="envoyer"/></button>
                            </div>
                        </div>
                    </form>
		
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

</body>

</html>
