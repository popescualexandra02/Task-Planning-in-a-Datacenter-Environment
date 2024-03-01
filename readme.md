Tema 2 - APD
MyDispatcher:
- In cadrul functiei addTask() din MyDispatcher, pe care am sincronizat-o, am 
facut 4 if-uri pentru fiecare algoritm :
        - pentru ROUND_ROBIN am folosit formula din cadrul enuntului 
        - pentru SHORTEST_QUEUE am calculat hostul care are coada cea mai 
        scurta pentru a putea trimite task-uri, folosind un for pentru a 
        trimite la cea cu ID-ul cel mai mic
        - pentru SIZE_INTERVAL_TASK_ASSIGNMENT am verificat type-ul si am 
        trimis in functie de acesta
        - pentru LEAST_WORK_LEFT, am facut aceeasi logica ca la 
        SHORTEST_QUEUE, dar in functie de getWorkLeft()
MyHost:
- Am facut o clasa NumberComparator, care ma ajuta pentru PriorityBlockingQueue,
 sortand elementele dupa prioritate, iar in cazul in care acestea sunt egale 
 dupa timpul de start
- In shutdown() am creat o variabila run_now care devine falsa atunci cand 
este apelata functia, iar run() ruleaza cat timp run_now este true.
- Pentru getWorkLeft() am calculat timpul ramas in total pentru o coada, 
parcurgand fiecare task si facand o suma de timpul ramas la fiecare.
- Functia getQueueSize() returneaza size-ul cozii, iar addTask() adauga un 
task in coada.
- In run() extrag elementul din varful cozii si cat timp acesta mai are timp 
primeste sleep(1000) si se opreste daca yes este 0(adica daca apare un task 
care trebuie sa se ruleze,acesta se opreste). Daca un task este preemtibil, 
se ia alt task. Atunci cand left devine 0 , pentru ca si acesta scade pe masura
 ce trece timpul de rulare, task-ul primeste finish si este eliminat din coada.

