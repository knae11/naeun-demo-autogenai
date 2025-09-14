# About
- Converting TS backend server to Java.
- Some details could be different.
- [original autogenai repo](https://github.com/autogenai/typescript-interview)

# To start
- Tech: Java(17), Gradle, SpringBoot, JPA, H2 
### Run
- `./gradlew bootRun`
- `GET http://localhost:8080` would show `Hello World!`.
### DB
- To access in-memory DB, `http://localhost:8080/h2-console`.
- Access information is on `src/main/resources/application.yml`
### APIs
```bash
curl --location 'http://localhost:8080/summarise' \
--header 'Content-Type: application/json' \
--data '{"input":"input text sample"}'
```

```bash
curl --location 'http://localhost:8080/usage/hopeful-ai?since=2025-01-01' \
--header 'Content-Type: application/json'
```

# Todo
- [ ] Develop API summarise request to get different response by input model.
- [ ] Develop list up api by model parameters.
- [ ] Develop pagination list up api.
- [ ] Write test code.
- [ ] Implement front-end code from original repo. 
