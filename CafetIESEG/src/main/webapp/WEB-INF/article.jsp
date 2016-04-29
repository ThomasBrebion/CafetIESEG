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
			<a class="img-ieseg" href="http://cafet.ieseg.fr/"><img class="img-ieseg-img" src="img/restonet.png"/></a>
			<a class="img-ieseg" href="http://www.ieseg.fr/"><img class="img-ieseg-img" src="img/ieseg.png"/></a>
			<a href="espace"><input type="Button" value="Espace privé" class="espaceprive" aria-label="Espace Privé"/></a>
			<a href="https://twitter.com/Ieseg" class="twitter-follow-button" data-show-count="false"></a><script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>
			<div class="fb-like" data-href="https://www.facebook.com/CafetIeseg/?fref=ts" data-layout="button_count" data-action="like" data-show-faces="false" data-share="false"></div>
		</div>
   	
	<!-- Menu de navigation -->
    <c:import url="nav.jsp">
	    <c:param name="pageSelectionnee" value="accueil"/>
	</c:import>
	
	<div class="container">
		<div class="box">
			<div class="row">
				<div class="col-md-12">
					<h3 align="center"><fmt:message key="article.detail"/></h3>
					
					<hr>
	
					<div align="center" class="row">
						<label class="col-sm-2 control-label"><fmt:message key="titre"/></label>
						<div class="col-sm-8">
							<p>${article.titre}</p>
						</div>
					</div>
					<div align="center" class="row">
						<label class="col-sm-2 control-label"><fmt:message key="auteur"/></label>
						<div class="col-sm-8">
							<p>${article.auteur}</p>
						</div>
					</div>
					<div align="center" class="row">
						<label class="col-sm-2 control-label"><fmt:message key="texte"/></label>
						<div class="col-sm-8">
							<p>${article.text}</p>
						</div>
					</div>
					<div align="center" class="row">
						<div class="col-sm-offset-2 col-sm-8">
							<a class="btn btn-default" href="accueil"><fmt:message key="article.accueil"/></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<footer>
		<fmt:message key="footer"/>
    </footer>
  </body>
</html>