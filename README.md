# Teste Pagamentos

### Tecnologias necessárias

* java 21
* node 22
* docker

### Inicializar projeto
**Passo 1:** Ir para pasta do projeto <br>
**Passo 2:** Iniciar banco de dados oracle

`
    docker-compose up
`

**Passo 3:** Iniciar backend

`
    sdk env
`

`
     mvn clean spring-boot:run
`

**Passo 4:** Iniciar frontEnd

`
    nvm use
`

`
    npx ng s
`
