SCLab

Bem-vindo ao SCLab — um sistema voltado para a gestão de leitores RFID, comunicação de portas, rastreamento de tags e gerenciamento de equipamentos laboratorias, desenvolvido em Java com o framework Jmix.

🔍 Visão Geral

Este projeto tem como objetivo oferecer uma solução de backend + interface web para gerenciamento de dispositivos de leitura RFID, armazenamento de dados de leitura, registro de qual porta foi utilizada, e identificação de tags.
Desenvolvido utilizando Java, Jmix, Gradle, com padrão MVC, visando sistemas laboratoriais ou de automação em que seja necessário rastrear leituras de TAGs via porta física ou virtual.

Funcionalidades principais

Cadastro de leitores RFID com os seguintes atributos: ID, data da leitura (lidoEm), porta utilizada (portaUtilizada), código da TAG (tagCodigo), versão.

Interface de administração para visualizar registros históricos.

Gerenciamento de versão de entidades (campo version) para controle de concorrência.

Funções básicas de CRUD para a entidade principal RfidLeitor.

Utilização do framework Jmix para acelerar o desenvolvimento de CRUDs, interface web e persistência.

🛠 Tecnologias utilizadas

Java (versão conforme build.gradle)

Framework Jmix para geração de aplicações empresariais com Spring Boot.

Gradle como sistema de build.

Banco de dados relacional (por exemplo, H2, PostgreSQL ou MySQL — especifique conforme seu ambiente).

CSS/HTML para a camada de interface (já que o projeto contém arquivos CSS e HTML).

Git & GitHub para controle de versão.

🚀 Como rodar localmente

Siga os passos abaixo para configurar e executar o projeto em seu ambiente local:

Clone o repositório

git clone https://github.com/ErosAssis/SCLab.git  
cd SCLab  


Configure o banco de dados no arquivo application.properties ou application.yml. Por exemplo:

main.datasource.url=jdbc:hsqldb:file:.jmix/hsqldb/sclab 
spring.datasource.username=seu_usuario  
spring.datasource.password=sua_senha  

Por padrão, virá:

main.datasource.url=jdbc:hsqldb:file:.jmix/hsqldb/sclab  
spring.datasource.username=sa 
spring.datasource.password=  

Execute o projeto.

Acesse a interface web no navegador:

http://localhost:8080  

Faça login no sistema de administração (usuário: admin / senha: admin) ou crie um usuário via linha de comando.



