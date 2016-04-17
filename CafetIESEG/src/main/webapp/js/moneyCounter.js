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

	// var selectedPetit_dessert = $('#selectPetit_dessert option:selected').text();
	// console.log(listePetit_dessert);
	// for (i = 0; i < listePetit_dessert.length; i++)
	// {
	// 	console.log(listePetit_dessert[i].nom);
	// 	if (listePetit_dessert[i].nom == selectedPetit_dessert)
	// 		prix += listePetit_dessert[i].prix;
	// }

	// var selectedGrand_dessert = $('#selectGrand_dessert option:selected').text();
	// console.log(listeGrand_dessert);
	// for (i = 0; i < listeGrand_dessert.length; i++)
	// {
	// 	console.log(listeGrand_dessert[i].nom);
	// 	if (listeGrand_dessert[i].nom == selectedGrand_dessert)
	// 		prix += listeGrand_dessert[i].prix;
	// }

	// var selectedBoissons = $('#selectBoissons option:selected').text();
	// console.log(listeBoissons);
	// for (i = 0; i < listeBoissons.length; i++)
	// {
	// 	console.log(listeBoissons[i].nom);
	// 	if (listeBoissons[i].nom == selectedBoissons)
	// 		prix += listeBoissons[i].prix;
	// }

	console.log(prix);
	$('#prixFinal').html(prix.toFixed(2));
}

function menuChanged()
{
	setPrix();
}