package pro.paullezin.menuvoting;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pro.paullezin.menuvoting.repository.UserRepository;

@SpringBootApplication
@AllArgsConstructor
public class MenuVotingApplication implements ApplicationRunner {
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(MenuVotingApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println(userRepository.findByLastNameContainingIgnoreCase("last"));
    }

}
