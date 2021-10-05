FROM adoptopenjdk/openjdk11:slim

RUN curl -LO https://github.com/jbangdev/jbang/releases/download/v0.81.1/jbang.zip \
    && jar xf jbang.zip && chmod +x jbang/bin/jbang && mv jbang/bin/jbang /usr/local/bin/ \
    && rm -rf jbang*

COPY rest.java .

CMD ["sh", "-c", "./rest.java"]
#CMD ["jbang", "--javaagent=org.jolokia:jolokia-jvm:1.7.1", "rest.java"]

EXPOSE 8080 8778
