# Card Events Mini App


# Overview
- Produce/Consume cards events

# Version

- feature/json-simple using Json Serdes
- feature/json-kafka-binder using Json Serdes

- feature/avro-simple using Avro and Confluent registry WIP
- feature/avro-kafka-binder using Avro WIP

# Usefull links
https://www.freeformatter.com/json-escape.html

# Commands to use registry
📝 1. Define a Schema (Avro Example)
```json

// user.avsc
{
"type": "record",
"name": "User",
"namespace": "com.example",
"fields": [
{ "name": "name", "type": "string" },
{ "name": "age", "type": "int" }
]
}
```
📤 2. Register Schema via REST API
```bash
curl -X POST http://localhost:8081/subjects/user-value/versions \
-H "Content-Type: application/vnd.schemaregistry.v1+json" \
-d '{
"schema": "{\"type\":\"record\",\"name\":\"User\",\"namespace\":\"com.example\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"age\",\"type\":\"int\"}]}"
}'
```
-  Success response
```json
{
  "error_code": 500,
  "message": "Invalid Avro schema"
}
```
-  Error response
```json
{
  "error_code": 409,
  "message": "Schema being registered is incompatible with an earlier schema"
}
```
📥 3. Retrieve Schema (by subject or ID)
- Get latest version:
```bash
curl http://localhost:8081/subjects/user-value/versions/latest
```
- Get by version:
```bash
curl http://localhost:8081/subjects/user-value/versions/1
```
- Get by ID (from Kafka message):
```bash
curl http://localhost:8081/schemas/ids/1
```

4. Delete schemas
> Note may be not allowed from the registry
- Delete a specific version 
```bash
curl -X DELETE http://localhost:8081/subjects/user-value/versions/1
```
- Delete all versions
```bash
curl -X DELETE http://localhost:8081/subjects/user-value
```