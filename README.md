# MCP server

## Utiliser le serveur

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

Après avoir mis votre "base_path", sauvegardé et redémarrer claude pour qu'il prenne en compte cette nouvelle config.
Maintenant vous pouvez demander par exemple :

"En utilisant le serveur mcp, donne moi le prochain train entre stop_area:SNCF:87471003 et  stop_area:SNCF:87391003 "

## Debug mode d'intellij 

Lancer cette commande qui permet de mettre à jour la jar pour le debug et de lancer en local **MCP inspector**.
Il nous permet de simuler les tools utiliser par un LLM (à lancer à chaque changement dans le code):

````bash
mvn clean install && npx @modelcontextprotocol/inspector -e JAVA_TOOL_OPTIONS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 java -jar target/mcp-server-0.0.1-SNAPSHOT.jar --port 8080 --host localhost
````

Puis sur la redirection de MCP inspector, connectez vous.

Puis créer et lancer une configuration sur intellij avec JVM Debug :

*   host: localhost
*   port: 5005


Et mettez vos points d'arrêts.
