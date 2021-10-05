build:
	podman build -t jolokia .

run:
	podman run -d --name jolokia -p 8080:8080 -p 8778:8778 --network bridge localhost/jolokia

test:
	http localhost:8080/hello/test
	http localhost:8080/bye/test
	http localhost:8778/jolokia/version
