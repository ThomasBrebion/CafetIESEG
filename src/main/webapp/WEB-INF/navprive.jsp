<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page pageEncoding="utf-8" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />

<!DOCTYPE html>
<html lang="${language}">
<!-- Navigation -->
    <nav class="navbar navbar-default" role="navigation" >
        <div class="container" >
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header" >
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
                <p class="navbar-brand">Cafet'Ieseg</p>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" >
                <ul class="nav navbar-nav" >
                    <li class="${param.pageSelectionnee == 'accueil' ? 'active' : ''}">
                        <a href="accueilprive"><fmt:message key="nav.accueil"/></a>
                    </li>
                    <li class="${param.pageSelectionnee == 'carte' ? 'active' : ''}">
                        <a href="carteprive"><fmt:message key="nav.carte"/></a>
                    </li>
                    <li class="${param.pageSelectionnee == 'menu' ? 'active' : ''}">
                        <a href="menuprive"><fmt:message key="nav.menu"/></a>
                    </li>
                    <li class="${param.pageSelectionnee == 'contact' ? 'active' : ''}">
                        <a href="contactprive">Contact</a>
                    </li>				
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>