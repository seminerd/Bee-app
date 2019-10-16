package vn.beemart.service.configuration;


import lombok.extern.apachecommons.CommonsLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.StatisticsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@CommonsLog
@Component
public class JettyConfig {

    private static volatile Server server;

    @Value("${server.port:0}")
    private int serverPort;
    @Value("${server.shutdown-wait-time:30000}")
    private long shutdownWaitTime;


    // Jetty HTTP Server
    //
    // see [1] on how to implement graceful shutdown in springboot.
    // Note that since use jetty, we need to use server.stop(), also StatisticsHandler must be
    // configured, for jetty graceful shutdown to work.
    //
    // [1]: https://github.com/spring-projects/spring-boot/issues/4657
    @Bean
    public JettyServletWebServerFactory jettyEmbeddedServletContainerFactory() {
        JettyServletWebServerFactory factory = new JettyServletWebServerFactory();

        factory.setPort(serverPort);
        log.info("Jetty configured on port: " + serverPort);

        factory.addServerCustomizers((JettyServerCustomizer) server1 -> {
            server = server1;
            log.info("Jetty version: " + server.getVersion());

            // Configure shutdown wait time.
            if (shutdownWaitTime > 0) {
                // Add StatsticsHandler, in order for graceful shutdown to work.
                StatisticsHandler handler = new StatisticsHandler();
                handler.setHandler(server.getHandler());
                server.setHandler(handler);

                log.info("Shutdown wait time: " + shutdownWaitTime + "ms");
                server.setStopTimeout(shutdownWaitTime);

                // We will stop it through JettyGracefulShutdown class.
                server.setStopAtShutdown(false);
            }
        });
        return factory;
    }

    @Bean
    public JettyGracefulShutdown jettyGracefulShutdown() { return new JettyGracefulShutdown(); }

    // Springboot closes application context before everything.
    private static class JettyGracefulShutdown implements ApplicationListener<ContextClosedEvent> {
        private static final Logger log = LoggerFactory.getLogger(JettyGracefulShutdown.class);

        @Override
        public void onApplicationEvent(ContextClosedEvent event) {
            if (server == null) {
                log.error("Jetty server variable is null, this should not happen!");
                return;
            }
            log.info("Entering shutdown for Jetty.");
            if (!(server.getHandler() instanceof StatisticsHandler)) {
                log.error("Root handler is not StatisticsHandler, graceful shutdown may not work at all!");
            } else {
                log.info("Active requests: " + ((StatisticsHandler) server.getHandler()).getRequestsActive());
            }
            try {
                long begin = System.currentTimeMillis();
                server.stop();
                log.info("Shutdown took " + (System.currentTimeMillis() - begin) + " ms.");
            } catch (Exception e) {
                log.error("Fail to shutdown gracefully.", e);
            }
        }
    }
}
