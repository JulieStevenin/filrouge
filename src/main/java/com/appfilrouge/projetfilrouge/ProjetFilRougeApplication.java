package com.appfilrouge.projetfilrouge;


import com.appfilrouge.projetfilrouge.entities.Role;
import com.appfilrouge.projetfilrouge.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetFilRougeApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(ProjetFilRougeApplication.class, args);
    }

@Autowired
private RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {

        roleRepository.save(new Role("ROLE_USER"));
    }
}