///usr/bin/env jbang --javaagent=org.jolokia:jolokia-jvm:1.7.1=host=0.0.0.0 "$0" "$@" ; exit $?
//DEPS org.apache.camel:camel-bom:3.11.1@pom
//DEPS org.apache.camel:camel-core
//DEPS org.apache.camel:camel-main
//DEPS org.apache.camel:camel-stream
//DEPS org.apache.camel:camel-rest
//DEPS org.apache.camel:camel-undertow
//DEPS org.slf4j:slf4j-simple:1.7.31

// Camel imports
import org.apache.camel.*;
import org.apache.camel.builder.*;
import org.apache.camel.main.*;
import org.apache.camel.spi.*;
import static org.apache.camel.builder.PredicateBuilder.*;

import static java.lang.System.*;

class rest {
    public static void main(String... args) throws Exception {
        setProperty("org.jboss.logging.provider", "slf4j");

        Main main = new Main();
        main.configure().addRoutesBuilder(new RouteBuilder() {
            public void configure() throws Exception {
                restConfiguration().port(8080);

                rest()
                    .get("/hello/{name}")
                        .to("direct:hello")
                    .get("/bye/{name}")
                        .to("direct:bye");

                from("direct:hello")
                    .transform().simple("Hello ${header.name}!");
                from("direct:bye")
                    .transform().simple("Bye ${header.name}!");
            }
        });
        main.run();
    }
}
