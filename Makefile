APP_NAME = jolokia

build:
	podman build -t ${APP_NAME} .

run:
	podman run -d --name jolokia -p 8080:8080 -p 8778:8778 localhost/${APP_NAME}

test:
	http localhost:8080/hello/test
	http localhost:8080/bye/test
	http localhost:8778/jolokia/version
