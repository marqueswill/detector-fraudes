---
tags:
  - concurso
  - dev
---
## Épicos
O sitema é separado em 4 módulos principais, usando como base as tecnologias exigidas no [[Conteúdo TI]]

- **Backend e API de transações (Épico 1):** 
	- **Tecnologias:** Java, Banco de Dados (PostgreSQL e MongoDB), SQL2008, Estruturas de Dados e Ansible.
	- É o core do sistema, contém a API para conexão com endpoints para o app mobile e para o painel de gestão. 
- **Detecção de Fraudes (Épico 2)**
	- **Tecnologias:** Python e bibliotecas de IA/ML.
	- Módulo feito em python, utiliza técnicas de machine learning para detecção e prevenção de fraudes, categorização de transações, entre outros.
- **Aplicativo Mobile (Épico 3)**
	- **Tecnologias:** React Native, Kotlin, Swift, Android, iOS. 
	- Módulo que contém o código para o aplicativo mobile que o usuário utilizará. Se conecta com o resto do sistema por meio da API de pagamentos.
- **Painel de Gestão (Épico 4)**
	- **Tecnologias:** TypeScript, CSS. 
	- Webapp que serve como painel de gestão e monitoramento para um analista da instituição.



## Histórias de Usuário


> [!info]
Toda história de usuário deve caber em uma única frase com três partes essenciais:
> **Como** `[Persona / Tipo de Usuário]`, **eu quero** `[Ação / Funcionalidade]`, **para que** `[Benefício / Valor gerado]`.
> 
> - **Persona:** Quem está pedindo isso? (Ex: Cliente, Sistema, Gestor, Administrador).
>- **Ação:** O que o software tem que fazer? (Ex: transferir dinheiro, gerar um relatório, bloquear conta).
>- **Valor:** Qual é o objetivo final? (Ex: pagar uma dívida, evitar fraudes, consultar saldo).

## Épico 1: Back-end e API

### Feature 1.1: Identificação e Autenticação
- Como sistema, eu quero validar o CPF e senha recebidos e gerar um token de sessão criptografado, para garantir que as requisições seguintes do usuário sejam seguras e autenticadas.
- Logout: Como sistema, eu quero revogar e invalidar o token de sessão gerado quando o usuário solicitar o logout, para garantir que o aparelho não fique vulnerável após o uso.

### Feature 1.2: Cadastro e Abertura de Contas
- Como sistema, quero cadastrar um novo cliente e gerar uma conta para ele, para que ele possa usar o banco.

### Feature 1.3: Processamento de Transações Financeiras
- Como sistema, eu quero processar pedidos de transferência recebidos da API, verificando regras de negócio (se há saldo suficiente), para garantir que nenhuma conta fique negativada indevidamente.

### Feature 1.4: Persistência de Dados
- Como sistema, eu quero salvar os dados transacionais estruturados no **PostgreSQL** e os logs de acesso brutos no **MongoDB**, para cobrir diferentes necessidades de persistência.
- Como sistema, eu quero armazenar contas bloqueadas temporariamente em uma estrutura de _Árvore Binária de Busca_, para que a validação de bloqueio durante uma transferência seja extremamente rápida.

### Feature 1.5: Consulta e Organização de Extratos
- Como sistema, eu quero buscar o histórico de transações no banco e ordená-lo na memória (usando estruturas de dados como _Bubble_ ou _Insertion Sort_ em Java) antes de enviá-lo ao cliente, para entregar o extrato estruturado corretamente.

**Feature 1.6: Auditoria e Rastreabilidade (Logs Administrativos)**
- Como sistema, eu quero registrar em logs de auditoria qual usuário interno (analista) realizou ações manuais críticas, como bloqueio de contas ou aprovação de transações, para que a instituição possa rastrear e prevenir fraudes internas.

**Feature 1.7: Gestão de Limites de Transação**

- Como sistema, eu quero validar os limites diários e noturnos configurados pelo usuário antes de autorizar uma transferência, para garantir o cumprimento das regras de segurança estabelecidas pelo cliente.

___
## Épico 2: Detecção de Fraudes

### Feature 2.1: Análise Preditiva de Transações Suspeitas

- Como motor de inteligência, eu quero analisar as transferências recentes usando algoritmos de aprendizado supervisionado (como _Random Forest_ ou _SVM_), para calcular e registrar no banco um score de fraude (0 a 100%) para cada transação.


### Feature 2.2: Segmentação de Clientes por Comportamento

- Como motor de inteligência, eu quero varrer a base de usuários usando algoritmos de agrupamento não supervisionado (_K-Means_), para separar automaticamente os clientes em clusters (grupos de risco).

### Feature 2.3: Triagem Automatizada de Suporte via NLP
- Como motor de inteligência, eu quero processar os textos de erro e ajuda enviados pelos clientes usando Processamento de Linguagem Natural (NLP e vetorização), para pré-categorizar o assunto antes de chegar ao painel do gestor.

___
## Épico 3: Aplicativo Mobile
### Feature 3.1: Autenticação Segura no Dispositivo
- **Biometria:** Como cliente, eu quero logar usando a biometria registrada no meu celular (FaceID/TouchID usando módulos nativos), para acessar o aplicativo de forma mais rápida.
- **Login tradicional:** Como cliente, eu quero fazer login usando meu CPF e senha, para conseguir acessar minha conta caso a leitura de biometria falhe ou não esteja disponível.
- **Redefinição de senha:** Como cliente (ou analista), eu quero redefinir minha senha esquecida validando meus dados, para recuperar o acesso à minha conta sem depender de intervenção manual do banco.


### Feature 3.2: Consulta de Saldos e Movimentações
- Como cliente, quero ver as minhas movimentações recentes rapidamente.
- Como cliente, eu quero visualizar o saldo atual da minha conta logo na tela inicial, para que eu saiba rapidamente quanto dinheiro tenho disponível.
- Como cliente, eu quero ter acesso a uma tela com meus dados financeiros completos (limite e extrato estruturado com data, remetente, destinatário e status), para controlar meus gastos.

### Feature 3.3: Execução de Pagamentos e Transferências
- Como cliente, quero enviar dinheiro para outra conta para pagar uma dívida.

### Feature 3.4: Central de Ajuda e Relato de Erros
- Como cliente, eu quero ser capaz de escrever e enviar mensagens para reportar erros ou pedir ajuda, para resolver problemas com a minha conta.

### Feature 3.5: Notificações

- Como cliente, eu quero receber notificações _push_ no meu celular sempre que uma transferência for realizada ou retida preventivamente, para ter controle em tempo real sobre a segurança do meu dinheiro.

**Feature 3.6: Onboarding e KYC (Conheça seu Cliente)**

- Como um novo usuário, eu quero enviar meus dados iniciais, fotos do meu documento (RG/CNH) e uma selfie pelo aplicativo, para que o banco valide minha identidade e conclua a abertura da minha conta.


**Feature 3.7: Geração e Compartilhamento de Comprovantes**

- Como cliente, eu quero gerar um comprovante em PDF ou imagem detalhando uma transferência realizada, para que eu possa compartilhar e comprovar o pagamento ao destinatário.


**Feature 3.8: Gestão de Perfil e Segurança**

- Como cliente, eu quero acessar uma área de perfil para editar meus dados de contato (telefone e e-mail), para que meu cadastro no banco esteja sempre atualizado.

- Como cliente, eu quero ajustar os meus limites de transferência diurnos e noturnos diretamente no aplicativo, para que eu tenha mais controle sobre a exposição do meu dinheiro em caso de roubo do celular.

___
## Épico 4: Painel de gestão

### Feature 4.1: Acesso Restrito Administrativo
- Como analista, eu quero fazer login no sistema usando meu CPF e senha, para acessar o ambiente restrito do banco.
- Como analista, eu quero ter acesso a um painel administrativo pelo navegador (desenvolvido em TypeScript), para que eu consiga gerenciar o sistema de qualquer computador do escritório.

### Feature 4.2: Monitoramento e Mitigação de Fraudes
- Como analista, eu quero ver uma lista contendo as transações classificadas como suspeitas (com seus respectivos scores de fraude de 0 a 100%), para que eu possa aprová-las ou bloqueá-las manualmente.
- Como analista, eu quero aprovar ou bloquear permanentemente as transações listadas como suspeitas, para garantir a precisão do atendimento ou evitar o prejuízo financeiro.

### Feature 4.3: Gestão de Grupos de Risco e Carteira de Clientes
- Como analista, eu quero visualizar uma lista que separa os clientes em grupos de risco (os clusters gerados pela IA), para direcionar campanhas ou políticas de crédito.


### Feature 4.4: Governança e Saúde dos Modelos de IA
- Como analista, eu quero acessar uma seção técnica com os scores de confiança dos modelos de detecção (métricas de overfitting e underfitting), para monitorar a saúde da inteligência artificial.

### Feature 4.5: Dashboard de Indicadores de Negócio
- Como analista, eu quero ver um dashboard com dados consolidados do negócio (quantidade de clientes, volume financeiro de transferências, chamados de suporte em aberto), para tomar decisões gerenciais mais rápidas.

**Feature 4.6: Atendimento e Resolução de Chamados (Help Desk)**
- Como analista, eu quero visualizar, responder e encerrar os chamados de suporte abertos pelos clientes (já pré-categorizados pela IA), para que eu possa resolver os problemas relatados e manter a qualidade do atendimento.

**Feature 4.7: Controle de Acessos e Permissões (RBAC)**
- Como administrador do sistema, eu quero criar novas contas para a equipe interna e gerenciar suas permissões de acesso (ex: perfil "Atendente" vs "Gestor de Risco"), para que cada funcionário acesse apenas as funcionalidades permitidas para o seu cargo.