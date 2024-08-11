<div align="center">

# Microsserviço E-mail

</div>

## Descrição

Projeto para trabalhar envio de emails usando Spring Mail via requisição http ou por meio de consumo de mensagens com Spring AMQP + RabbitMQ. Alêm disso contém validações, migrações com Flyway, Logs com Slf4j + Log4j2 e persistência no banco de dados MySQL.

## Tecnologias e dependências do projeto

- **RabbitMQ:** Broker de mensagens para comunicação assíncrona.
- **Spring AMQP:** Integração do Spring com RabbitMQ para envio e recebimento de mensagens.
- **Spring Boot:** Base para a criação de microsserviços.
- **Spring Web:** Criação de API RESTful.
- **Spring Devtools:** Ferramentas adicionais para desenvolvimento, como recarga automática.
- **Spring Data JPA:** Abstração para acesso a dados e manipulação de entidades JPA.
- **Spring Validation:** Validação de dados de entrada.
- **Slf4j + Log4j2:** Frameworks para logging com configuração avançada e personalizada.
- **Lombok:** Redução de boilerplate de código, como getters, setters e construtores.
- **MySQL:** Banco de dados relacional (SQL) para persistência de dados.
- **Flyway:** Ferramenta de migração de banco de dados para versionamento e controle de esquema.

## Testar o projeto

- Com docker e docker-compose instalados navegue até o diretório do projeto, abra seu terminal e digite o seguinte comando

```bash
docker-compose up -d 
```

- Adicione seu <i>username</i> e <i>password</i> nas configurações do Spring-Mail do projeto.

- Adicione seu <i>address</i> nas configurações do RabbitMQ do projeto

- Inicie o projeto.

```bash
./gradlew bootRun
```

## Endpoints

| route                                  | description                                                               |
|----------------------------------------|---------------------------------------------------------------------------|
| <kbd>POST /email/post/send-email</kbd> | Envia e-mail passando o JSON no body                                      |
| <kbd>GET /email/get/emails</kbd>       | Recebe uma Page de e-mails passando argumentos <i>page</i> e <i>items</i> |

#### Objeto JSON
```json
{
  "ownerRef": "12345",
  "emailFrom": "example@domain.com",
  "emailTo": "recipient@domain.com",
  "subject": "Assunto do Email",
  "text": "Conteúdo do email aqui."
}
```

## Contribuição

- Faça um fork deste repositório e envie suas alterações por meio de pull requests.
- Para reportar bugs ou sugerir melhorias, abra um issue na página do projeto.