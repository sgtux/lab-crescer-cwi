# Configurações de Segurança

## Injections
- Prevenção de XSS
    - Previne XSS em DOM e Refletido.
- Prevenção de XSS Armazenado
    - Previne o XSS que foi armazenado no banco de dados.

## Cookie
- **HttpOnly:**
    - Atributo utilizado para evitar que o cookie seja acessado  pelo JavaScript, apenas pelo browser.
    - Evita ataques como por exemplo sequestro de sessão.
- **Secure:**
    - Com este atributo o cookie será trafegado apenas entre domínios seguros (que contém HTTPs).
- **Domínio:**
    - Restringe o envio do cookie apenas para um domínio específico.
- **SameSite:**

    | Atributo	| Requisição Cross-Site GET | Requisição Cross-Site POST | Usos Típicos |
    |-|-|-|-|
    |Strict | ❌ Não enviado | ❌ Não enviado	| Autenticação segura.|
    |Lax | ✅ Enviado | ❌ Não enviado | Navegação básica com links.|
    |None | ✅ Enviado | ✅ Enviado | Serviços de múltiplos domínios.|

## Autenticação
- **Tempo de Sessão:** Tempo de sessão do usuário pode ser alterado.
- **Tipo Autenticação:**
    - Cookie Base64
    - JWT
    - Token Opaco

## HTTP Response Headers
- **Content Security Policy (CSP):**

Evitar que o site seja carregado dentro de um iframe de qualquer outro site (Similar ao header X-Frame-Options)
```
frame-ancestors 'none'
```
Restringir para que apenas o site especificado possa carregar o site dentro de um iframe. (Similar ao header X-Frame-Options)
```
frame-ancestors www.crescer.lab
frame-ancestors localhost:8080
```

Evitar que o site carregue um iframe de qualquer fonte.
```
frame-src 'none'
```
Restringir para que o site possa carregar dentro de um iframe o conteúdo apenas das fontes especificadas. (Similar ao header X-Frame-Options)
```
frame-src www.crescer.lab
frame-src localhost:8080
```

- **X-Frame-Options**
    - **Deny:** Evita que o site seja carregado dentro de um iframe.
    - **Same Origin:** Permite que o site seja carregado apenas dentro de um iframe do próprio site (mesma origem).