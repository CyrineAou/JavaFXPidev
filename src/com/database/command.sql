SELECT 
logement.id as logement_id,filename, titre, description,equipement.nom addresse, is_active 
from logement,equipement 
WHERE logement.hote_id = equipement.id 
and logement.id LIKE '%1%' 
ORDER BY equipement.nom ASC;