# BAE-buy-and-eat-app

## Visão Geral
- Aplicativo onde o cliente pode fazer um pedido de comida e retirar diretamente com o atendente da loja.

## Alunos
- Enzo Paiva
- Fabio Silva
- Murilo Lobo

## Tecnologias
- Android v14. API34 UpsideDownCake.
- Java
- XML
- Firebase

### Recursos Utilizados
- Fragmentos
- Strings
- Cores
- Imagens
- Sons
- Notificações
- Menu

## Usuários e Funcionalidades

### Administrador
- **Criar conta e fazer login:** Registrar-se no aplicativo e fazer login para acessar sua conta.
- **Cadastrar e gerenciar produtos:** Adicionar, editar e remover itens do menu.
- **Gerenciar pedidos:** Visualizar, atualizar o status dos pedidos e enviar notificações aos clientes.
- **Visualizar relatórios:** Gerar e visualizar relatórios de pedidos.
- **Configurações:** Ajustar configurações gerais do aplicativo.

### Cliente
- **Explorar o menu:** Visualizar itens disponíveis, com descrições e preços.
- **Fazer pedidos:** Selecionar itens, adicionar ao carrinho e enviar pedidos para a loja.
- **Acompanhar pedidos:** Visualizar o status do pedido em tempo real.
- **Receber notificações:** Receber atualizações e notificações sobre o status do pedido.

## Requisitos Funcionais

### Entradas Necessárias
- **Administrador:**
  - Informações pessoais para cadastro (nome, email, senha).
  - Informações de login.
  - Detalhes dos produtos para cadastro (nome, descrição, preço, imagem).
  - Checar Status dos pedidos e alterar.

- **Cliente:**
  - Informações de login.
  - Seleção de itens do menu.
  - Confirmação de pedidos.

### Processamento
- **Autenticação e Autorização:** Verificar credenciais de login e determinar permissões do usuário.
- **Gerenciamento de Produtos:** Adicionar, atualizar e remover produtos no menu.
- **Gerenciamento de Pedidos:** Registrar novos pedidos, atualizar status e notificar clientes.
- **Notificações:** Enviar notificações push sobre status de pedidos e promoções.

## Testes de Caixa Preta
- **Acesso ao sistema sem cadastro:** Verificar se usuários não cadastrados conseguem acessar o sistema.
- **Campos vazios:** Garantir que campos obrigatórios, como nome e senha, não estejam vazios.
- **Divisão por zero:** Testar funcionalidades que possam causar divisões por zero e garantir que sejam tratadas adequadamente.
- **Entrada inválida:** Testar se campos numéricos rejeitam entradas de texto e vice-versa.
