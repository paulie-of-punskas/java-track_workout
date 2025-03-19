#### Content
- Spring Web
- Thymeleaf
- MS SQL Server Driver
- h2 for testing

#### Environment:
# azure db server settings [source](https://learn.microsoft.com/de-de/azure/azure-sql/database/connect-query-java?view=azuresql)
```bash
export AZURE_DB_URL="jdbc:sqlserver://<dbname>.database.windows.net:1433;database=db-<dbname>;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;connectionTimeout=40;loginTimeout=30;connectRetryCount=10;connectRetryInterval=10";
export AZURE_DB_USER="";
export AZURE_DB_PASSWORD=""
export AZURE_TABLE_NAME=""
```

#### Table description:
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