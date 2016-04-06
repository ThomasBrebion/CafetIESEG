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
<div class="container">
		<div class="row">
			<div class="col-md-8">
				<h3><fmt:message key="sandwich.add"/></h3>
				<c:if test="${messageErreur != null}">
					<div class="alert alert-danger">${messageErreur}</div>
				</c:if>
				<form method="post" class="form-horizontal">
					<div class="form-group">
						<label for="titre" class="col-sm-2 control-label"><fmt:message key="name"/></label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="nom" id="nom">
						</div>
					</div>
					<div class="form-group">
						<label for="texte" class="col-sm-2 control-label"><fmt:message key="prixsolo"/></label>
						<div class="col-sm-3">
							<input type="text" placeholder="Ex : 2.4" class="form-control" name="prix_solo" id="prix_solo">
						</div>
					</div>
					<div class="form-group">
						<label for="auteur" class="col-sm-2 control-label"><fmt:message key="prixmenu"/></label>
						<div class="col-sm-3">
							<input type="text" placeholder="Ex : 2.4" class="form-control" name="prix_menu" id="prix_menu">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input class="btn btn-primary" type="submit" value="<fmt:message key="enregistrer"/>">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>