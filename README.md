#### Content
- Spring Web
- Thymeleaf
- MS SQL Server Driver
- h2 for testing

# Environment:
### azure db server settings:
- [JAVA conenct query](https://learn.microsoft.com/de-de/azure/azure-sql/database/connect-query-java?view=azuresql)
- [Connection properties](https://learn.microsoft.com/en-us/sql/connect/jdbc/setting-the-connection-properties?view=sql-server-ver16)

### Connection retry:
- Error no 40613:
    - `Database '%.*ls' on server '%.*ls' is not currently available. Please retry the connection later. If the problem persists, contact customer support, and provide them the session tracing ID of '%.*ls'.`

```bash
export AZURE_DB_URL="jdbc:sqlserver://<dbname>.database.windows.net:1433;database=db-<dbname>;;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;connectRetryInterval=10;"
export AZURE_DB_USER=""
export AZURE_DB_PASSWORD=""
export AZURE_TABLE_NAME=""
```

# Table description:
```
date (nvarchar)
muscle (varchar) 
exercise (varchar) 
kg (decimal)
rep (int)
comment (varchar) 
distance (decimal) 
time (int)
cal (int)
```

# To do:
1. Pakeist `testDBconnection()` i `@Configuration`. Deka to, nereikes `main` issaukdinet to metodo.
```java
dbUtils.testDBconnection(System.getenv("AZURE_DB_URL"),
        System.getenv("AZURE_DB_USER"),
        System.getenv("AZURE_DB_PASSWORD"));

```