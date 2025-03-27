package com.example.demo;

import com.example.demo.avro.User;
import io.confluent.kafka.schemaregistry.avro.AvroSchema;
import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ApplicationListener<ApplicationStartedEvent> registerSchemas() {
        return event -> {
            try {
                String userSchema = User.getClassSchema().toString();
                System.out.println("Registering schema: " + userSchema);
                schemaRegistryClient().register("user-topic-value",
                        new AvroSchema(userSchema));
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    @Bean
    public SchemaRegistryClient schemaRegistryClient() {
        return new CachedSchemaRegistryClient("http://localhost:8081", 20);
    }

    @Configuration
    public static class FunctionConfig {
        @Bean
        public Consumer<User> readUser() {
            return user -> {
                // Process the user
                System.out.println("Received user: " + user);
            };
        }

        // Convert name to uppercase
        @Bean
        public Function<User, User> processUser() {
            return user -> {
                System.out.println("Received: " + user);
                if (user.getAge() < 18) {
                    return null;
                }
                return User.newBuilder()
                        .setName(user.getName().toUpperCase())
                        .setAge(user.getAge())
                        .build();
            };
        }
    }
}