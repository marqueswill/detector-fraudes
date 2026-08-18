# Sistema Financeiro e Prevenção de Fraudes

Este é um projeto de estudos desenvolvido para simular a arquitetura e as operações de um sistema bancário moderno. O foco da aplicação é garantir a segurança transacional, aplicar inteligência artificial no dia a dia da operação e fornecer interfaces eficientes tanto para o cliente final quanto para a equipe interna de gestão.

O ecossistema do projeto é dividido em quatro módulos principais que trabalham de forma integrada:

- **Core e API de Transações:** Core do sistema, desenvolvido em Java. É responsável por gerenciar regras de negócio, autenticação, processamento de transferências e persistência de dados, utilizando PostgreSQL para dados relacionais e MongoDB para logs e registros brutos.
- **Motor de Inteligência e Fraudes:** Um serviço construído em Python que aplica modelos de Machine Learning e Processamento de Linguagem Natural (NLP). Ele atua analisando transações para gerar _scores_ de fraude em tempo real, agrupando clientes por perfis de risco e automatizando a triagem do suporte.
- **Aplicativo Mobile:** A interface do cliente final, desenvolvida em React Native (com integrações nativas em Kotlin e Swift). Oferece recursos completos de _onboarding_ (KYC), autenticação biométrica, transferências, gestão de limites e acompanhamento de extratos.
- **Painel de Gestão (Backoffice):** Uma aplicação web desenvolvida em TypeScript e CSS, destinada aos analistas e gestores da instituição. Funciona como uma central de monitoramento (dashboard) para gerenciar métricas de negócios, analisar e intervir em transações suspeitas e atender aos chamados de suporte dos clientes.
