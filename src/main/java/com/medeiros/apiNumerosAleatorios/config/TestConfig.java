package com.medeiros.apiNumerosAleatorios.config;


import com.medeiros.apiNumerosAleatorios.repositories.ApostadorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {


    private final ApostadorRepository repository;

    public TestConfig(ApostadorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        Long numero1 = (long) 123123;
        Long numero2 = (long) 132131;

//	Loteria lot1 = new Loteria(null, "mateus@medeiros", numero1);
//	Loteria lot2 = new Loteria(null, "medeiros@medeiros", numero2);
//	
//	repository.saveAll(Arrays.asList(lot1, lot2));


    }
}
