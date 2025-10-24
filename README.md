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


## Lancer le serveur et utiliser Claude Desktop (ne fonctionne pas encore)
Dans Claude desktop :

Fichier -> Paramètre -> Développeur -> Modifier la config -> claude_desktop_config
et mettre :
```json
{
  "mcpServers": {
   "mcp-server": {
      "command": "java",
      "args": [
        "-jar",
        "YOUR_BASE_PATH/mcp-server/target/mcp-server-0.0.1-SNAPSHOT.jar"
      ]
    }
  }
}
```

Après avoir mis votre "base_path", sauvegarder et redémarrer claude pour qu'il prenne en compte cette nouvelle config.
Maintenant vous pouvez demander par exemple `En utilisant le serveur mcp, donne moi le prochain train entre Rennes et Paris`

