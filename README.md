#Here I willl be uploading my Springboot CRUD operations

# Pagination with filtering and sorting


You can filter, sort and page your data using following payload.


Typical Payload:

```json

{
  "filters": [
    {
      "key": "string",
      "operator": "GT",
      "fieldType": "BOOLEAN",
      "valueTo": {},
      "values": [
        {}
      ],
      "value": {}
    }
  ],
  "sorts": [
    {
      "key": "string",
      "direction": "ASC"
    }
  ],
  "page": 0,
  "pageSize": 0
}
```

### Payload has 3 parts matching to following functions

## 1. Filtering (filters)
   + can combine multiple filters
   + Available operators: GT, GTE, LT, LTE,EQUAL, NOT_EQUAL, LIKE, IN, BETWEEN.
   + Available FieldTypes: BOOLEAN, CHAR, DATE (format dd-MM-yyyy HH:mm:ss), DOUBLE, INTEGER, LONG, STRING.

## 2. Sorting (sorts) 
   + can combine multiple sorts
## 3. Paging (page, pageSize)



Spin up docker container

```bash
docker compose up -d
```

## Swagger documentation

[localhost:8080/swagger-ui/index.html](localhost:8080/swagger-ui/index.html)


Example1: Filter by birth year greater than 1978

```json

{
  "filters": [
    {
      "key": "birthYear",
      "operator": "GT",
      "fieldType": "INTEGER",      
      "value": 1978
    }  
}
```

Example2: Filter by birth year BETWEEN 1978 and 2000
```json

{
  "filters": [
    {
      "key": "birthYear",
      "operator": "BETWEEN",
      "fieldType": "INTEGER",      
      "value": 1978,
      "valueTo": 2000
    }  
  ]
}
```


Example3: Filter by first name LIKE "mustafa"
```json

{
  "filters": [
    {
      "key": "firstName",
      "operator": "LIKE",
      "fieldType": "STRING",      
      "value": "mustafa"
    }
  ]
}
```


Example4: Filter by first name LIKE "mustafa"
```json

{
  "filters": [
    {
      "key": "firstName",
      "operator": "LIKE",
      "fieldType": "STRING",      
      "value": "mustafa"
    },
    {
      "key": "birthYear",
      "operator": "LT",
      "fieldType": "INTEGER",      
      "value": 1980
    }  
  ]
}
```


