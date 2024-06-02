package com.casa.agent_service.agent;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.web.client.RestTemplate;

/**
 * This class is a Spring Boot component that implements CommandLineRunner, meaning it will run when the application
 * starts.
 * It uses the ByteBuddy library to redefine methods in the RestTemplate and SimpleJpaRepository classes, depending on
 * the mode the application is running in.
 * The mode is determined by the 'HT_MODE' environment variable.
 */
public class Agent {

    /**
     * This method uses ByteBuddy agent to add advice that will print the input
     * of the getForEntity method of RestTemplate class.
     *
     */
    private static void recordModeRestTemplate(){
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(RestTemplate.class)
                .visit(Advice.to(CustomRestTemplate.class).on(ElementMatchers.named("getForEntity")
                        .and(ElementMatchers.takesArguments(String.class, Class.class, Object[].class))))
                .make()
                .load(
                        RestTemplate.class.getClassLoader(),
                        ClassReloadingStrategy.fromInstalledAgent());
    }

    /**
     * This method uses ByteBuddy agent to intercept the save method of SimpleJpaRepository class.
     * and delegates the call to CustomJpaRepository class, that will send a static response
     *
     */
    private static void replayJpaRepository() {
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(SimpleJpaRepository.class)
                .method(ElementMatchers.named("save").and(ElementMatchers.takesArguments(Object.class)))
                .intercept(MethodDelegation.to(CustomJpaRepository.class))
                .make()
                .load(
                        SimpleJpaRepository.class.getClassLoader(),
                        ClassReloadingStrategy.fromInstalledAgent());
    }

    /**
     * This method uses ByteBuddy agent to add advice that will print the input to the save method of
     * SimpleJpaRepository class.x
     *
     */
    private static void recordModeJpaRepository(){
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(SimpleJpaRepository.class)
                .visit(Advice.to(CustomJpaRepository.class).on(ElementMatchers.named("save")
                        .and(ElementMatchers.takesArguments(Object.class))))
                .make()
                .load(
                        SimpleJpaRepository.class.getClassLoader(),
                        ClassReloadingStrategy.fromInstalledAgent());
    }

    /**
     * This method uses ByteBuddy agent to intercept the save method of RestTemplate class.
     * and delegates the call to CustomRestTemplate class, that will send a static response
     *
     */
    private static void replayModeRestTemplate(){
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(RestTemplate.class)
                .method(ElementMatchers.named("getForEntity").and(ElementMatchers.takesArguments(String.class, Class.class, Object[].class)))
                .intercept(MethodDelegation.to(CustomRestTemplate.class))
                .make()
                .load(
                        RestTemplate.class.getClassLoader(),
                        ClassReloadingStrategy.fromInstalledAgent());
    }


    /**
     * Executes the application in either 'record' or 'replay' mode based on the value of the 'HT_MODE' environment variable.
     * In 'record' mode, the application records the input of the 'getForEntity' method of the RestTemplate class and the 'save' method of the SimpleJpaRepository class.
     * In 'replay' mode, the application intercepts the 'getForEntity' method of the RestTemplate class and the 'save' method of the SimpleJpaRepository class, and delegates the calls to the CustomRestTemplate and CustomJpaRepository classes respectively, which send static responses.
     *
     * @param args Command line arguments. This parameter is optional and currently not used in the method.
     * @throws Exception If an error occurs during the execution of the method.
     */

    public static void premain(String agentArgs, java.lang.instrument.Instrumentation inst) {
        if (System.getenv("HT_MODE").equalsIgnoreCase("RECORD")){
            System.out.println("Record mode");
            recordModeJpaRepository();
            recordModeRestTemplate();
        }
        else if (System.getenv("HT_MODE").equalsIgnoreCase("REPLAY")){
            System.out.println("Replay mode");
            replayJpaRepository();
            replayModeRestTemplate();
        }
    }
}