package de.fhg.iais.roberta.guice;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import de.fhg.iais.roberta.factory.IRobotFactory;
import de.fhg.iais.roberta.robotCommunication.RobotCommunicator;

public class RobertaGuiceServletConfig extends GuiceServletContextListener {
    private Injector injector;
    private final Properties openRobertaProperties;
    private final Map<String, IRobotFactory> robotPluginMap;
    private final RobotCommunicator robotCommunicator;

    public RobertaGuiceServletConfig(Properties openRobertaProperties, Map<String, IRobotFactory> robotPluginMap, RobotCommunicator robotCommunicator) {
        this.openRobertaProperties = openRobertaProperties;
        this.robotPluginMap = robotPluginMap;
        this.robotCommunicator = robotCommunicator;
    }

    @Override
    protected Injector getInjector() {
        JerseyServletModule jerseyServletModule = new JerseyServletModule() {
            @Override
            protected void configureServlets() {
                // configure at least one JAX-RS resource or the server won't start.
                install(
                    new RobertaGuiceModule(
                        RobertaGuiceServletConfig.this.openRobertaProperties,
                        RobertaGuiceServletConfig.this.robotPluginMap,
                        RobertaGuiceServletConfig.this.robotCommunicator));
                //TODO: we have doubled the properties
                // look for guice modules from robot plugins
                //                for ( IRobotFactory robotFactory : RobertaGuiceServletConfig.this.robotPluginMap.values() ) {
                //                    AbstractModule guiceModule = robotFactory.getGuiceModule();
                //                    if ( guiceModule != null ) {
                //                        install(guiceModule);
                //                    }
                //                }
                Map<String, String> initParams = new HashMap<>();
                // initParams.put("com.sun.jersey.config.feature.Trace", "true");
                initParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
                String packages =
                    "" //
                        + "de.fhg.iais.roberta.javaServer.restServices.all,"
                        + "de.fhg.iais.roberta.javaServer.restServices.robot.ev3,"
                        + "de.fhg.iais.roberta.javaServer.restServices.robot.nao,"
                        + "de.fhg.iais.roberta.javaServer.provider";
                initParams.put("com.sun.jersey.config.property.packages", packages);
                serve("/*").with(GuiceContainer.class, initParams);
            }
        };
        this.injector = Guice.createInjector(jerseyServletModule);
        return this.injector;
    }

    public Injector getCreatedInjector() {
        return this.injector;
    }
}
