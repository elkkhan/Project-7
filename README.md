## Java client for Edamam Recipe API

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/58414515fa724f818b3891f308d475c3)](https://app.codacy.com/app/elkkhan/Project-7?utm_source=github.com&utm_medium=referral&utm_content=elkkhan/Project-7&utm_campaign=Badge_Grade_Dashboard)

The project uses Edamam Recipe API for retrieving information about recipes.<br>
This branch implements a Java client for an easier usage of the API.

### Usage:

```java
import tos.common.api.client.ApiClient;
import tos.common.api.entities.Recipe;
import tos.common.api.query.ApiQuery;
```

```java
ApiClient apiClient = new ApiClient();
ApiQuery query = apiClient.createQuery("chicken pizza").build();
List<Recipe> chickenPizza = apiClient.executeQuery(query);
```
***createQuery()*** returns an ApiQuery.Builder, which has additional functions for setting detailed search parameters.
When done setting all the search parameters, a query needs to be built by ***build()*** .

Check https://developer.edamam.com/edamam-docs-recipe-api for more info.
