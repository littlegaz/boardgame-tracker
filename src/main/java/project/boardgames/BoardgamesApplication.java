package project.boardgames;/* This specifies that the class BoardgamesApplication belongs to the project.boardgames package*/

import org.springframework.boot.SpringApplication;/* this bootstraps a Spring Application */
import org.springframework.boot.autoconfigure.SpringBootApplication;/* this combines @Configuration, @EnableAutoConfiguration and @ComponentScan */

@SpringBootApplication/* this indicates that ths class provides Spring Configuration, enables springs auto configuration mechanism and tells spring to scan for componants (controllers, services, repositories) */
public class BoardgamesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardgamesApplication.class, args);
    }/* this starts the springboot application */
}