
## InstaCook API
Acest API este realizat in scopul sustinerii unei aplicatii de social media pentru pasionatii de gatit. 
Scopul aplicatiei, si implicit al API-ului, consta in conectarea pasionatilor de gatit de pretutindeni si impartasirea retetelor proprii, intr-o maniera simpla dar competitiva in acelasi timp.

### Descrierea API-ului:
Utilizatorii isi pot face vizibile propriile retete, pe care le pot organiza asa cum doresc, si pot urmari activitatea culinara a prietenilor lor, putand comenta sau reactiona la retetele acestora. Pentru a motiva utilizatorii sa posteze cat mai multe retete, am adaugat posibilitatea ca acestia sa participe la diverse concursuri tematice (pe un anumit tip de retete, de exemplu: bucataria mexicana, thailandeza, etc) si sa castige puncte daca au reteta cu cele mai multe aprecieri. Va fi disponibil un clasament global “Cei mai buni bucatari”, cu toti utilizatorii aplicatiei, in functie de numarul total de puncte castigate.

### Regulile de la baza API-ului:
- Utilizatorii isi pot crea colectii in care sa adauge diverse retete realizate de ei. O colectie se caracterizeaza prin tematica retetelor sale, o descriere si o poza sugestiva.
- O colectie poate fi publica sau privata. O colectie privata va contine doar retete care sunt vizibile autorului lor, iar o colectie publica va oferi acces tuturor celorlalti utilizatori asupra retetelor incluse.
- Utilizatorii isi pot adauga doar retetele create de ei in colectiile lor. Nu se pot adauga retete realizate de alti utilizatori.
- O reteta devine privata daca face parte dintr-o colectie privata. O reteta este publica daca face parte dintr-o colectie publica, si deci este vizibila oricarui utilizator.
- Un utilizator poate adauga unul sau mai multe comentarii atat unei retete realizate de alt utilizator, cat si unei retete proprii. Singura conditie pentru a adauga un comentariu este ca reteta respectiva sa fie publica.
- Un utilizator poate reactiona pozitiv (poate vota) asupra unei retete (fie ca este a sa, fie ca este a altcuiva).
- Utilizatorii pot vedea toate retetele la care au reactionat pozitiv.
- O bucatarie reprezinta un stil unic de gatit, o gama de retete specifice unei tari (de exemplu: bucataria chinezeasca, bucataria indiana, etc). Aceasta va fi vizibila tuturor utilizatorilor si reprezinta totalitatea retetelor publice cu acest specific.
- Fiecare bucatarie va avea, la un moment dat, cate un concurs disponibil. Un concurs poate avea o durata de aproximativ o saptamana, timp in care utilizatorii pot incarca retete cu specificul bucatariei respective. Reteta care va aduna cele mai multe aprecieri va primi un anumit numar de puncte iar concursul se va incheia, dar va ramane vizibil in continuare.
- Pentru a participa la concursul deschis dintr-o anumita bucatarie, utilizatorul isi poate inscrie una sau mai multe retete prin intermediul unui buton de “adauga reteta in concurs”. (Acest buton va seta true campul de participa_concurs de pe reteta respectiva)
- Concursul va determina castigatorul pe baza retetei cu numarul cel mai mare de aprecieri, dintre toate retetele publice si inscrise la concurs, apartinand bucatariei respective. Nu am realizat o relatie intre concurs si retetele participante deoarece acestea pot fi obtinute printr-o interogare asupra tabelei Reteta (retetele care fac parte din bucataria concursului activ si care au campul de participa_concurs setat true).
- Nu pot fi deschise mai multe concursuri simultan pentru aceeasi bucatarie. O bucatarie poate avea mai multe concursuri, insa doar unul va fi cel activ (restul se vor fi terminat deja).


### Descrierea entitatilor:
#### User:
- Se leaga de tabela Collection printr-o relatie de tipul one-to-many. 

#### Collection:
- Se leaga de tabela User si reprezinta o unitate de organizare pentru retetele proprii. Un utilizator poate avea zero sau mai multe colectii, iar o colectie apartine unui singur utilizator. 

#### Recipe
- o	Contine toate detaliile necesare realizarii unei retete si poate apartine unui singur utilizator (autorul sau) si unei singure colectii.

#### User_Recipe
- Caracterizeaza relatia many-to-many dintre tabela User si Recipe (un utilizator poate reactiona la mai multe retete iar o reteta poate primi reactii de la mai multi utilizatori). 
- O reteta poate avea zero sau mai multe reactii(voturi).

#### Comment 
- Poate fi vazuta ca o relatie many-to-many dintre tabela User si Recipe (un utilizator poate comenta la mai multe retete iar o reteta poate primi comentarii de la mai multi utilizatori). 
- Cheia primara a acestei tabele este una artificiala (id_comentariu), ci nu una compusa ca in cazul tabelei User_Recipe, deoarece acelasi utilizator poate adauga mai multe comentarii aceleiasi retete.

#### Kitchen
- Stocheaza toate retetele publice care se incadreaza intr-o anumita categorie de bucatarie (mexicana, chinezeasca, etc), de la toti utilizatorii.
- Se leaga de tabela Recipe printr-o relatie de tipul one-to-many.

#### Contest
- Detine toate concursurile asociate unui anumit tip de bucatarie, asadar contribuie la realizarea relatiei de one-to-many dintre tabelele Kitchen si Contest 
- O bucatarie poate avea zero sau mai multe concursuri iar un concurs apartine unei singure bucatarii.


### Metode expuse:

**GET /users**: listarea tuturor utilizatorilor<br/>
**POST /users**: adaugarea unui nou utilizator, trimitand un body cu detaliile acestuia<br/>
**PUT /users/{userId}**: actualizarea unui utilizator cu id-ul userId <br/>
**DELETE /users/{userId}**: stergerea unui utilizator cu id-ul userId <br/>
**POST /users/{userId}/reactions?recipeId={id}**: adaugarea unei reactii a utiliatorului cu id-ul userId pentru reteta cu id-ul specificat<br/>
<br/>
**GET /collections**: listarea tuturor colectiilor
**POST /collections**: adaugarea unei noi colectii, trimitand un body cu detaliile acesteia<br/>
**PUT /collections/{collectionId}**: actualizarea unei colectii cu id-ul collectionId <br/>
**DELETE /collections/{collectionId}**: stergerea unei colectii cu id-ul collectionId <br/>
<br/>
**GET /recipes**: listarea tuturor retetelor
**GET /recipes/{recipeId}/comments**: afisarea tuturor comentariilor unei retete cu id-ul recipeId
**POST /recipes?collectionId={collectionId}**: adaugarea unei noi retete in colectia cu id-ul collectionId, trimitand un body cu detaliile retetei<br/>
**PUT /recipes/{recipeId}**: actualizarea unei retete cu id-ul recipeId <br/>
**POST /recipes/kitchen?collectionId={collectionId}&kitchenId={kitchenId}**: adaugarea unei retete noi in colectia cu id-ul collectionId si in bucataria cu id-ul kitchenId <br/>
**DELETE /recipes/{recipeId}**: stergerea unei retete cu id-ul recipeId <br/>
<br/>
**GET /kitchens**: listarea tuturor bucatariilor <br/>
**POST /kitchens**: adaugarea unei noi bucatarii, trimitand un body cu detaliile acesteia<br/>
**PUT /kitchens/{kitchenId}**: actualizarea unei bucatarii cu id-ul kitchenId <br/>
**DELETE /kitchens/{kitchenId}**: stergerea unei bucatarii cu id-ul kitchenId <br/>
<br/>
**GET /contests**: listarea tuturor concursurilor <br/>
**POST /contests?kitchenId={kitchenId}**: adaugarea unui nou concurs pentru bucataria cu id-ul kitchenId <br/>
**PUT /contests/{contestId}**: actualizarea unui concurs cu id-ul contestId <br/>
**DELETE /contests/{contestId}**: stergerea unui concurs cu id-ul contestId <br/>
<br/>
**GET /comments**: listarea tuturor comentariilor <br/>
**POST /comments?userId={userId}&recipeId={recipeId}**: adaugarea unui nou comentariu, trimitand un body cu detaliile acestuia, conectandu-l la utilizatorul cu id-ul userId si la reteta cu id-ul recipeId<br/>
**PUT /comments/{commentId}**: actualizarea unui comentariu cu id-ul commentId <br/>
**DELETE /comments/{commentId}**: stergerea unui utilizator cu id-ul commentId <br/>

