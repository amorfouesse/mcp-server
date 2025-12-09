# MCP server

## Utiliser le serveur avec MCP Inspector

Faire un `mvn clean install` ensuite un `java -jar ./target/mcp-server-0.0.1-SNAPSHOT.jar`,
le serveur va se lançer, ensuite sur un autre terminal faire la commande `npx @modelcontextprotocol/inspector`
qui permet d'avoir l'interface de debug des tools sans LLM.

Puis sur la redirection de MCP inspector, mettez un transport ``Streamable HTTP`` et en url `http://localhost:8080/mcp`,
un type de connection `Via Proxy` puis connectez-vous.

Si vous souhaitez mettre des points d'arrêts dans intellij, créer et lancer une configuration sur intellij avec JVM Debug :

*   host: localhost
*   port: 5005

Et mettez vos points d'arrêts.

Ensuite sur MCP inspector cliquer sur Tools en haut, puis `list tools` et tester votre tool.


## Lancer le serveur avec le client MCP mcp for ollama
pré requis : (https://github.com/jonigl/mcp-client-for-ollama)

- ollama
- Python 3.10+
- UV package manager

Puis faire cette commande pour installer le client MCP pour ollama :

```
pip install --upgrade ollmcp
```

Pour le lancer en streamable http :

```
ollmcp -u http://localhost:8080/mcp
```

faire cette commande pour changer de model :

```
m
```

puis tester votre client mcp.