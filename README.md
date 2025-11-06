SCLab

Bem-vindo ao SCLab — um sistema voltado para o gerenciamento de equipamentos laboratoriais, a gestão de leitores RFID, comunicação de portas, e rastreamento de tags, desenvolvido em Java com o framework Jmix.

⚠️ Observação: ajuste esta frase conforme o real escopo do seu projeto.

🔍 Visão Geral

Este projeto tem como objetivo oferecer uma solução de backend + interface web para gerenciamento de dispositivos de leitura RFID, armazenamento de dados de leitura, registro de qual porta foi utilizada, e identificação de tags.
Desenvolvido utilizando Java, Jmix, Gradle, com padrão MVC, visando sistemas laboratoriais ou de automação em que seja necessário rastrear leituras de TAGs via porta física ou virtual.

Funcionalidades principais:

  - Cadastro de leitores RFID com os seguintes atributos: ID, data da leitura (lidoEm), porta utilizada (portaUtilizada), código da TAG (tagCodigo), versão.

  - Interface de administração para visualizar registros históricos.

  - Gerenciamento de versão de entidades (campo version) para controle de concorrência.

  - Funções básicas de CRUD para a entidade principal RfidLeitor.

  - Utilização do framework Jmix para acelerar o desenvolvimento de CRUDs, interface web e persistência.

🛠 Tecnologias utilizadas:

  - Java (versão conforme build.gradle)

  - Framework Jmix para geração de aplicações empresariais com Spring Boot.

  - Gradle como sistema de build.

  - Banco de dados relacional (por exemplo, H2, PostgreSQL ou MySQL — especifique conforme seu ambiente).

  - CSS/HTML para a camada de interface (já que o projeto contém arquivos CSS e HTML).

  - Git & GitHub para controle de versão.
