var listePlat_chauds = JSON.parse($('#JSONlistePlat_chauds').html());
var listeSandwichs = JSON.parse($('#JSONlisteSandwichs').html());
var listeSalades = JSON.parse($('#JSONlisteSalades').html());
var listePlats = JSON.parse($('#JSONlistePlats').html());

var listePetit_dessert = JSON.parse($('#JSONlistePetit_dessert').html());
var listeGrand_dessert = JSON.parse($('#JSONlisteGrand_dessert').html());
var listeBoissons = JSON.parse($('#JSONlisteBoissons').html());

setPrix();

function setPrix()
{
	
	/* Fonction js pour pouvoir afficher le prix d'un menu composé par l'utilisateur sur la page composer son menu */
	
	
	var prix = 0;

	var selectedPlat_chaud = $('#selectPlat_chauds option:selected').text();
	console.log(listePlat_chauds);
	for (i = 0; i < listePlats.length; i++)
	{
		console.log(listePlats[i].nom);
		if (listePlats[i].nom == selectedPlat_chaud)
			prix += listePlats[i].prix_menu;
	}
	for (i = 0; i < listePlat_chauds.length; i++)
	{
		console.log(listePlat_chauds[i].nom);
		if (listePlat_chauds[i].nom == selectedPlat_chaud)
			prix += listePlat_chauds[i].prix_menu;
	}
	for (i = 0; i < listeSalades.length; i++)
	{
		console.log(listeSalades[i].nom);
		if (listeSalades[i].nom == selectedPlat_chaud)
			prix += listeSalades[i].prix_menu;
	}
	for (i = 0; i < listeSandwichs.length; i++)
	{
		console.log(listeSandwichs[i].nom);
		if (listeSandwichs[i].nom == selectedPlat_chaud)
			prix += listeSandwichs[i].prix_menu;
	}

	console.log(prix);
	$('#prixFinal').html(prix.toFixed(2));
}

function menuChanged()
{
	setPrix();
}