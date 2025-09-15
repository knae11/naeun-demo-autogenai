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
#### Original
```bash
curl --location 'http://localhost:8080/summarise' \
--header 'Content-Type: application/json' \
--data '{"input":"input text sample"}'
```

```bash
curl --location 'http://localhost:8080/usage/hopeful-ai?since=2025-01-01' \
--header 'Content-Type: application/json'
```
#### Changed
- `src/docs/asciidoc/index.adoc`

# Todo

- [x] Documentate APIs with test.
- [x] Develop API summarise request to get different response by input model.
- [x] Develop list up api by model parameters.
- [x] Develop pagination list up api.
- [x] Write test code.
- [x] Implement front-end code from original repo. [cloned repo](https://github.com/knae11/naeun-demo-autogenai-ts)
  - checkout to branch 0912
  - `npm run dev --prefix ./frontend`. front-server port: `3001`

## To run
### front
1. clone: [cloned from original repo](https://github.com/knae11/naeun-demo-autogenai-ts) 
2. checkout branch to `0912`
3. `npm run dev --prefix ./frontend`. 
4. access `http://localhost:3001`

### back
1. clone: this repo
2. checkout branch to `naeun-develop`
3. `./gradlew bootRun`
4. if you want to access in-memory DB, access to `http://localhost:8080/h2-console`
5. if you want to see API specification, run test code `./gradlew test` and see `src/docs/asciidoc/index.adoc`
