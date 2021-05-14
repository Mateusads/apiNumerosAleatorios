https://apialeatorio.herokuapp.com/swagger-ui.html#/loteria-resource  -  Deploy no Heroku.


<h1><b>API - GERAR NUMEROS ALEATÓRIOS...</b></h1>

<h4>A API precisa gerar números aleatórios(tipo loteria) conforme o usuário passa seu email.
  
*O usuário não pode receber números repetidos.

*Os dados do usuário, tanto o número quanto o e-mail devem ser armazenados em banco
de dados.<h4>


<h2>COMEÇANDO A API</h2>

Para começar usamos uma ferramenta do Spring Boot chamado Spring Initializr.

Nele vamos colocar a versão e as Dependências para o Projeto.

Como vamos trabalhar com Hibernate vamos adicionar a dependência Spring Data JPA.

E para depois colocar ele na WEB usaremos a dependência Spring Web MVC.

Obs(no application.properties vamos ativar o profile de test, depois dev e somente após tudo certo será alterado para prod.)

No profile de test será usando o banco H2.

No profile de Dev e Prod usando PostGres com a ferramenta Flyway.

Para API ficar online será usado o servidor Heroku.

<h2>COMEÇANDO A PROGRAMAR</h2>

Agora com as configurações feitas pelo Initializr, usando o STS será feita a importação do
projeto para começar criar os pacotes e as classes.

Como convenção as API rest são divididas em 3 camadas conforme o MVC (Model View Controller) a primeira camada de Repositório, Serviço e View.

Vou criar o pacote Entidade, neste pacote vai conter a classe Loteria. (Obs: se for usar o TDD pode começar a criar os testes automatizados, com spring Initializr
ja vem um pacote para testes com Junit).

<h2>CRIANDO CLASSE</h2>
  
A classe Loteria possui a função de Entidade onde vão conter os atributos necessários que serão salvo no banco de dados.Nessa classe vamos implementar o Serializable para que seja possível enviar os dados no formato Json.

Ela deve ter os atributos int Id, String email e um Set<Long> numeroAleatorio para facilitar
depois, na parte do service será explicado. 
  
Usando anotação para Hibernate no atributo email @Column(unique = true) para o email
não possa ser duplicado, e que cada email possa receber uma sequência de números sem
repetição.

As anotações @ElementCollection(targetClass=Long.class) @Fetch(FetchMode.JOIN) são
por conta que o Hibernate por padrão é Lazy e não consegue fazer as consultas com o tipoHashSet assim a anotação com o Join faz que o problema do N+1 seja resolvido, por a
instrução a ser feita já está esclarecida para o Hibernate.

<h3>Usando Beans Validation</h3>
  
Para fazer a verificação do email sem precisar criar linhas de cod, o spring mvc possui o beans validations, com apenas a anotação @Email ele já válida para você se tem um
formato parecido com de um email, se não tiver ele não salva o dado no banco. 

<h3>Classe LoteriaRepository</h3>
  
Como Spring é baseado em Injeção de Dependência, em todas as classes deve ter a anotação correta para ela, no caso essa é @Repository como exemplo abaixo Classe LoteriaResource
Na classe Resource é um endpoid onde vai entrar dados no formato Json para API, nela a anotação são duas @RestController (para o Bens do spring saber que será um controller para entrada de dados e @RequestMapping(value = "?") para informar o Spring o caminho do endpoint. Nesta api será usada (value = "/loteria")

Obs ( para injeção de dependência do Spring é usado @Autowired, essa anotação diminuí a acoplação da API sem precisar instanciar com o new, assim usando apenas o necessário evitando o desperdício de cod e processamento)

<h3>E agora precisamos da classe Service</h3>
  
Para implementar a regra de negócio, sua anotação é @Service. É nela que vamos implementar o sorteio do número e criar um objeto junto com o email.Na implementação será feito um id, tanto para identificação quanto melhor controle de repetição de e-mail. 

Para criar o número aleatório será usado a biblioteca Math.random, cria um número Pseudo Aleatório e armazenar em um Long.Na minha implementação os números irão de 10000 até 99999 para gerar usando essa biblioteca será feito como exemplo abaixo.

Certo, porém como vimos nos requisitos emails não podem ser duplicados e números não podem ser repetidos para o mesmo email, para isso devem haver verificações no service
antes de mandar para o repository.
(Obs @Email é uma anotação do Javax.Validation serve para usar como um regex, uma expressão regular para verificar se está em um formato certo, como cpf etc, como nesse
caso email)

Assim criei um método que se chama verificarEmail(), nele consigo verificar todos os emails já no banco de dados e verificar se o email que está chegando pelo endpoint não está
cadastrado, mesmo que o Beans Validation do Spring não aceite dois emails iguais para enviar para o banco de dados, o correto é fazer a verificação evitar o envio de exceptions.

Assim que verificado o email é salvo ou adicionado. Para o número aleatório estou usando a variável com HashSet, é um tipo de variável como uma lista mais que não possui
ordenação porém não aceita dados repetidos na lista isso já conseguimos controlar os números repetidos do sorteio sem precisar criar funções de verificação.Exceção (exceptions)
Como sabemos a API deve ser lançada exceções caso houver algum erro, dado invalido do usuário ou algo que possa acontecer internamente, para ser tratado erros vamos criar
algumas classes.

A primeira vai se chamar ResourceExceptionHandler no pacote resources.exceptions. A anotação para ela será @ControllerAdvice assim o spring já sabe a função da classe.
Com a anotação @ExceptionHandler criaremos um método que irá receber esse erro e passar os códigos correspondente para eles, como 404 Not_found caso seja uma pesquisa
por um ID inexistente.

Foi criado a Classe StandadError para normatizar os atributos de saída do erro no formato Json.

<h2>Testes (JUnit)</h2>
  
Os testes são de unidade. Os teste podem parecer simples, porém é de extrema importância já que ele monitora mudanças no cod e seu funcionamento, caso um dos testes
não passar o programado poderá ir direto ao problema assim poupando tempo e um rendimento melhor do tempo.
  
Usando a anotação @SpringBootTest para a classe de teste.
Injetamos a dependência via @Autowired e no método a anotação @Test Um dos métodos é testado a busca por id do email, como na classe Test.config adicionamos alguns dados no banco para realizar esse teste, a função busca esses dados e verifica se estão corretos para validar o teste.
  
Vamos testar as dependência e suas injeções usando o @Mokito( ele cria um clone, um objeto simulado para o teste assim deixando o teste muito rápido que esse é o sentido do
teste unitário) 
Criar dois pacotes na parte dos test.repository e test.service. Neles vamos criar objetos e testar se os resultados deles são o esperado.
  
<h2>Swagger</h2>
  
Para realizar os teste manuais na aplicação de Get e Post, foi realizado a configuração do Swagger. http://localhost:8080/swagger-ui.html

<h2>Mudando de Test para Dev
  
Além de mudar somente no application.properties de test para dev.
Vamos criar um application-dev e vamos mudar umas configurações nele para usar o Postgres (Vamos usar ele pela melhor sincronização com o Heroku, servidor free).

<h2>Usando Flyway</h2>
  
O hibernate por padrão já cria o banco para você apenas com as instruções normais e a JPA, porém essa criação automática pode obter problemas de tamanho de variáveis entre
outros. E para aplicações que podem ficar maiores e devem ser criadas sub rotinas entre outras coisas que o hibernate(até suporta) o Flyway consegue suprir.Como a sua instalação é fácil e apenas com uma dependência, umas pastas e um arquivo sql para criar as tabelas (e eventos se precisar) será implementado neste projeto.
  
<h2>Subindo para Heroku</h2>
  
Agora nossa aplicação está pronta e vai para produção.
Vamos começar alterando em properties de dev para prod.
Criar um arquivo properties.prod, desabilitar algumas funções do hibernate JPA como instruções sql não é sugerido manter em ambiente de produção.
Vamos usar o heroku pelo motivo de ser fácil deploy e Servidor grátis.
  
[HerokuAPP](https://apialeatorio.herokuapp.com) 
Pronto Deploy feito, agora a API está em um WebService.

[Heroku com Swagger](https://apialeatorio.herokuapp.com/swagger-ui.html#/loteria-resource)
