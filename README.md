# Refactoring task T1 e miglioramenti

Di seguito una descrizione riassuntiva del nostro lavoro.
Tutte le informazioni riguardanti il refactoring del task T1 e i miglioramenti apportati al sistema complessivo si possono trovare nella 𝒅𝒐𝒄𝒖𝒎𝒆𝒏𝒕𝒂𝒛𝒊𝒐𝒏𝒆, presente nella ripository, con anche una descrizione generale del progetto e degli altri task, dei diagrammi con le parti interessate.

---

Il nostro team (composto da Lorenzo Poli matr. M63001560 - Francesco Della Valle matr. M63001500 - Michele Morgillo matr. M63001467) ha intrapreso un lavoro importante di refactoring del task T1, con l’obiettivo di  migliorare la manutenibilità, la testabilità e la scalabilità dell’applicazione, andando a lavorare sul Controller il quale, gestendo insieme sia il routing sia la logica di business, comprometteva la chiarezza del codice e la modularità dell’applicazione stessa. 
Abbiamo quindi ristrutturato il task T1 con la seguente strategia:
- **Separazione delle funzionalità**:  sono state separate dal controller le funzionalità di routing da quella della logica, che invece è stata delegata a servizi specifici.
- **Implementazione di Servizi:**: la logica di business è stata estratta in servizi dedicati, facilitando la testabilità e il riutilizzo. Così facendo le operazioni vengono affidate ai servizi, mantenendo il codice separato e focalizzato.

In particolare, i servizi implementati sono:
1. **AdminService**: è pensato per essere utilizzato dai controller dell’applicazione che gestiscono le richieste amministrative, fornendo una logica centralizzata per l’amministrazione delle classi e degli utenti admin.
   - Contiene i metodi di Autenticazione, tramite inclusione della gestione di token JWT da JwtService per l’autenticazione e l’autorizzazione degli amministratori, e la registrazione di nuovi amministratori.
   - Si occupa della gestione delle Classi tramite una serie di operazioni CRUD (Create, Read, Update, Delete), oltre a filtraggi e ordinamenti delle classi stesse.

2. **JwtService**: è responsabile della generazione e della validazione dei token JWT (JSON Web Token) per gestire l’autenticazione e l’autorizzazione degli utenti amministrativi all’interno dell’ap-
plicazione. Questo servizio garantisce che solo gli utenti autenticati possano accedere a determinate funzionalità, verificando la validità dei token JWT associati alle richieste.
   - Fornisce il metodo _generateToken_ per generare un token JWT per un utente amministrativo, che può essere utilizzato come strumento di autenticazione per le richieste successive.
   - Il metodo _isJwtValid_ consente di validare i token JWT ricevuti, assicurandosi che siano autentici e non scaduti.

3. **ScalataService**: gestisce le operazioni relative all’entità Scalata, includendo operazioni CRUD (Creazione, Lettura e Cancellazione), e utilizza la validazione JWT per limitare l’accesso.

4. **Util**: offre diversi metodi di utilità per gestire le interazioni con l’entità interaction, gestendo le interazioni come "like" e "report" associati a una determinata classe, oltre ad ottenere un elenco
di report.

5. **AchievementService**: estisce la logica relativa agli achievement (obiettivi) e alle statistiche dell’applicazione. Questa classe fornisce metodi per visualizzare, creare, elencare e cancellare
achievement e statistiche, assicurando che le operazioni siano eseguite solo se il token JWT è valido (tramite JwtService).

Oltre alla ristrutturazione del codice, sono stati introdotti diversi miglioramenti, tra cui:
- ripristino della funzionalità **reCAPTCHA** (Vedi ISSUE #27) in fase di registrazione dei Players.
- quando l'admin carica una classe (sia da sola che con i test pre-generati) è stata aggiunto un **messaggio di corretto Upload Classe**, seguito dal reindirizzamento automatico alla pagina dell'home admin.
- tasto **Go Back** in Upload Class, evitando il ritorno alla pagina di amministrazione dell'admin tramite browser.
