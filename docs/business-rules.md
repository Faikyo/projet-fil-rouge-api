# Business Rules (V1)

1. Un utilisateur inactif ne peut pas créer de commande.
2. Un service inactif ne peut pas être ajouté à une commande.
3. Une commande doit contenir au moins 1 item.
4. Total commande = somme(prixUnitaire × quantité) des items.
5. Une commande PAID ne peut plus être modifiée.
6. Une commande CANCELLED est définitive.
7. Une facture est générée uniquement au passage à PAID.
8. Une facture est non modifiable.
