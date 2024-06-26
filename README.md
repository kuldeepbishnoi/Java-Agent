# Java-Agent

## Repository Structure
- `post-service` branch -> contains code for simple post service
- `agent-service` branch -> contains code for the java agent

## Problem Statement
Facilitate mocking of Java libraries such that their functionality is overridden without making any change in the business logic code.

## Description
The Java-Agent project aims to provide a solution for mocking Java libraries to override their functionality without altering the business logic code. It operates in two modes: RECORD and REPLAY.

### RECORD Mode
In RECORD mode, the following actions are performed:
- Real outbound HTTP calls are made.
- Real data is inserted into a real database.
- Java agent intercepts outbound DB calls and HTTP calls.
- Requests and responses are logged on the console.

### REPLAY Mode
In REPLAY mode, the following conditions are met:
- The same scenario as RECORD mode is played.
- But a static data is returned | and no data is sent to DB & no outbound HTTP call
- The DB and HTTP hosts used are `1.2.3.4:1234`.

## Deliverables
- Source code with a Dockerfile for building the project.
- Docker build creates jars of the spring boot app and java-agent and starts the spring boot app with the java agent.
- The application accepts DB credentials and the HTTP endpoint as environment variables.
- An environment variable `HT_MODE` determines if the java agent functions in RECORD mode or REPLAY mode. Values should be `RECORD` or `REPLAY`.

## Environment Variables
- `DB_HOST`: Hostname for the database (default: host.docker.internal)
- `DB_PORT`: Port for the database (default: 3306)
- `DB_NAME`: Name of the database (default: post_database)
- `DB_USER`: Username for the database (default: root)
- `DB_PASSWORD`: Password for the database (default: rootroot)
- `HT_MODE`: Determines the mode of the java agent (RECORD or REPLAY)

## Docker image link
- `docker pull kuldeepbishnoi/post-service:LATEST`
- `docker run -p 8000:8000 kuldeepbishnoi/post-service:LATEST -e HT_MODE=REPLAY`
- `curl --location 'localhost:8000/api/createNewPost' \
--header 'Content-Type: application/json' \
--data '{
  "postName": "Delhi",
  "postContents": "Temperatures in India'\''s capital surged to a record-breaking 52.3."
}'`

## Note (Good idea to change these env variable according to your MySQL config)
- `DB_PORT`=3306
- `DB_NAME`=post_database
- `DB_USER`=root
- `DB_PASSWORD`=rootroot

## Resources
- [Learn more about monkey patching](https://en.wikipedia.org/wiki/Monkey_patch)
- [Opentelemetry project on GitHub](https://github.com/open-telemetry)
- [Byte Buddy for manipulation of library functions](https://bytebuddy.net/)
