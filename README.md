# scholarshipCompass

--- English

The project is a class registration system, as well as all the necessary requirements (students, coordinators, scrum masters, instructors and squads), for the company Compass.

To run the project use the file: EnrollApplication.java;

There are some requirements that need to be met before starting a class, namely: 1 coordinator, 1 Scrum Master and 3 instructors linked to it;

Every class has 3 possible statuses (waiting, started and finished), in order to change from waiting to started, a minimum of 15 students linked to the class are required, in addition, no class can exceed the number of 30 students linked.

--- Português

O projeto se trata de um sistema de cadastro de turmas, bem como todos os requsitios necessários para (alunos, coordenadores, scrum masters, instrutores e squads), para a empresa Compass.

Para executar o projeto utilize o arquivo: EnrollApplication.java;

Existem alguns requisitos que precisam ser atendidos antes do inicio de uma turma, estes sendo: 1 coordenador, 1 Scrum Master e 3 instrutores vinculados a ela;

Toda turma tem 3 possiveis status (waiting, started e finished), para a alteração de waiting para started são obrigatórios um minimo de 15 alunos vinculados a turma, além disso nem uma turma pode exceder o número de 30 alunos vinculados.

#Documentação

--- Português
Iniciei programando a parte referente aos coordenadores, considerei que como o sistema é focado apenas em gestão de cadastros para eles apenas o nome e email eram importantes. Pensando nas operações necessárias, decidi que apenas os métodos post e get eram necessários (pensei em adicionar um delete também, visto a possibilidade de desligamento, mas considerando que teria que criar muito mais códigos para impedir desligamentos enquanto classes vinculadas a ele estivessem acontecendo ou um sistema de substituição de coordenadores optei por deixar mais simples). Segui a mesma lógica para Scrum Masters e Instructors.

Quanto a Students, eu escolhi armazenar apenas o nome e o email também, mas adicionei a função de exclusão, visto a possibilidade de desistências, quando o sistema exclui um usuário ele exclui a relação do student com affiliation também.

Tive alguns problemas com o git-github (de alguma forma toda vez que eu puxava para um dos lados o código ficava estranho), recriei ambos e simulei meus primeiros passos na criação do projeto, para que ficasse similar ao que estava antes do erro.

Escolhi renomear o que seria Class para affiliation, visto que class poderia gerar erro pela própria nomenclatura do java, então para evitar qualquer confusão ou erro, resolvi fazer essa troca de adjetivos. Também foi essa classe que adicionei a função de edição, visto a necessidade de alteração de status, fiz validações que permitissem que o status só fosse alterado quando houvesse ao menos 15 estudantes e que os valores permitidos fossem apenas “waiting”, ”Started” ou ”finished”. Já o sistema que eu escolhi fazer de relação entre affiliation e student foi por meio de uma terceira tabela, que une os ids de affiliation e de student, por isso ambos precisam ser previamente cadastrados no sistema e então realizar a conexão entre eles (por meio de uma cadastro na tabela com os ids de ambos). Também utilizei uma tabela CombinedId, que une os ids de Student e Affiliation para que na tabela de conexão delas (RelationStudentAffiliation) apareça apenas 1 id.

Quanto à relação "aluno-squad", meu objetivo inicial era criar um endpoint que separasse os alunos em squads e as cadastrasse, mas por falta de tempo e conhecimento técnico, optei por um código mais simples onde o usuário do sistema faria o cadastro manualmente dos squads (que tem a vantagem de controlar quais alunos estarão em cada squad e quantos integrantes estarão em cada um deles, embora o sistema ainda não permita mais de 5 integrantes em cada squad).

E o endpoint responsável pelo cadastro inicial de 3 coordinators, instructors e scrumMaster, bem como 14 students foi posto em CoordinatorService, visto que considerei que o cadastro do coordinator aconteceria antes de qualquer outro, tentei procurar um jeito que permitisse que o cadastro acontecesse sozinho assim que o sistema fosse iniciado, mas não consegui pensar em como fazer a distinção de quando é realmente a primeira vez que o código está sendo rodado ou como executar, então deixei de forma mais simples, onde o usuário precisa rodar esse endpoint (“/coordinators/post/All”). 

Quanto às exceções, criei a classe CustomResponse, que possui duas variáveis, uma do tipo boolean e outra do tipo string, as exceções são então criadas com uma mensagem específica para cada evento.

E Eu não consegui chegar a adicionar uma parte de segurança para login de usuários (por falta de tempo), mas considerei que quem utilizasse o sistema conheceria todos os membros necessários para a criação de uma affiliation(classe), por isso o cadastro pede os ids de coordinator, instructor(s) e ScrumMaster.

--- English

I started programming the part referring to the coordinators, I considered that as the system is only aimed at managing their records, only their name and e-mail were important. Thinking about the necessary operations, I decided that only the post and get methods would be needed (I thought about adding a delete too, given the possibility of shutdown, but considering that I would have to create a lot more code to prevent a coordinator from shutdown while connected to a class ongoing or a coordinator replacement system, I chose to keep it simple). I followed the same logic for Scrum Masters and Instructors.

As for the Students, I chose to keep only the name and e-mail as well, but I added the delete function, given the possibility of dropouts, when the system deletes a user it also deletes the student's link with the affiliation.

I had some problems with git-github (somehow every time I pulled it to one side the code got weird), I recreated both and simulated my first steps in creating the project, so that it stayed as it was before the error.

I chose to rename what would be Class for affiliation, since class could generate errors due to the java nomenclature itself, so to avoid any confusion or error, I decided to change the adjectives.It was also in Affiliation that I added the editing function, given the need to change the status, I made validations that allowed the status to only be changed when there were at least 15 students and that the allowed values were only “waiting”, “Started” or "finished". The system I chose to make the relationship between affiliation and student was through a third table, which unites affiliation and student IDs, so both need to be previously registered in the system and then make the connection between them (through a registration in the table with the ids of both). I also used a table called "CombinedId" which combines the student and affiliation IDs so that only 1 id appears in the connection table (RelationStudentAffiliation).

As for the "student-squad" relationship, my initial objective was to create an endpoint that would separate students into squads and register them, but due to lack of time and technical knowledge, I opted for a simpler code where the system user would register manually the squads (which has the advantage of controlling which students will be in each squad and how many members will be in each of them, although the system still does not allow more than 5 members in each squad).

As for the exceptions, I created the CustomResponse class, which has two variables, one of the boolean type and the other of the string type, the exceptions are then created with a specific message for each event.

The endpoint responsible for the initial registration of 3 coordinators, instructors and scrumMaster, in addition to 14 students was placed in the CoordinatorService, as I considered that the registration of the coordinator would happen before anyone else, I looked for a way that would allow the registration to happen alone as soon as the system was started, but I couldn't think of how to distinguish when it's actually the first time the code is running or how to run it, so I left it simpler, where the user needs to run this endpoint ("/coordinators/post/All").

I was not able to add a security part for user login (due to lack of time), but I considered that whoever used the system would know all the members needed to create an affiliation (class), so the registration asks for the ids coordinator, instructor(s) and ScrumMaster, and after that the students too.

