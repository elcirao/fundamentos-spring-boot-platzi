package com.fundamentos.platzi.fundamentos;

import com.fundamentos.platzi.fundamentos.bean.MyBean;
import com.fundamentos.platzi.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.platzi.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.platzi.fundamentos.component.ComponentDependency;
import com.fundamentos.platzi.fundamentos.entity.User;
import com.fundamentos.platzi.fundamentos.pojo.UserPojo;
import com.fundamentos.platzi.fundamentos.repository.UserRepository;
import com.fundamentos.platzi.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

    private final Log Logger = LogFactory.getLog(FundamentosApplication.class);

    private ComponentDependency componentDependency;
    private MyBean myBean;
    private MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;
    private UserRepository userRepository;
    private UserService userService;

    public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
        this.userRepository = userRepository;
        this.userService = userService;
    }

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        //clasesAnteriores();
        saveUsersInDataBase();
        getInfoJpqlFromUser();
        saveWithErrorTransactional();
    }

    private void getInfoJpqlFromUser() {
        /*Logger.info("Usuario con el metodo findByUserEmail" +
                userRepository.findByUserEmail("john@domain.com")
                        .orElseThrow(() -> new RuntimeException("No se encontro el usuario")));

        userRepository.findAndSort("user", Sort.by("id").descending())
                .stream()
                .forEach(Logger::info);

        userRepository.findByName("Jhon")
                .stream()
                .forEach(user -> Logger.info("usuario con query method " + user));

        Logger.info("Usuario con query method con email and name: " + userRepository.findByEmailAndName("daniela@domain.com", "Daniela")
                .orElseThrow(() -> new RuntimeException("No encontrado")));

        userRepository.findByNameLike("%user%")
                .stream()
                .forEach(user -> Logger.info("Usuario find by name like: "+ user));

        userRepository.findByNameOrEmail(null, "user10@domain.com")
                .stream()
                .forEach(user -> Logger.info("Usuario find by name or email: "+ user));
        userRepository.findByBirthDateBetween(LocalDate.of(1990, 3, 1), LocalDate.of(2021, 4, 2))
                .stream()
                .forEach(user -> Logger.info("usuario con fecha: " + user));

        userRepository.findByNameLikeOrderByIdDesc("%user%")
                .stream()
                .forEach(user -> Logger.info("usuario con nombre y ordenado: " + user));*/

        Logger.info("Usuario a partir del named parameter: " + userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021, 7, 15), "daniela@domain.com")
                .orElseThrow(() -> new RuntimeException("No encontrado")));
    }

    private void saveWithErrorTransactional() {
        User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
        User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
        User test3 = new User("TestTransactional3", "TestTransactional1@domain.com", LocalDate.now());
        User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

        List<User> users = Arrays.asList(test1, test2, test3, test4);
        try {
            userService.saveTransactional(users);
        }catch (Exception e) {
            Logger.error("Esta es una excepcion dentro del metodo saveWithErrorTransactional" + e.getMessage());
        }
        userService.getAllUsers()
                .stream()
                .forEach(user -> Logger.info("Usuario transactional: " + user));
    }

    private void saveUsersInDataBase() {
        User user1 = new User("Jhon", "john@domain.com", LocalDate.of(2021, 1, 3));
        User user2 = new User("Julie", "julie@domain.com", LocalDate.of(2021, 5, 7));
        User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 7, 15));
        User user4 = new User("Alberto", "alberto@domain.com", LocalDate.of(1990, 5, 12));
        User user5 = new User("user5", "user5@domain.com", LocalDate.of(2021, 12, 21));
        User user6 = new User("user6", "user6@domain.com", LocalDate.of(2021, 9, 1));
        User user7 = new User("user7", "user7@domain.com", LocalDate.of(2021, 1, 30));
        User user8 = new User("user8", "user8@domain.com", LocalDate.of(2021, 12, 25));
        User user9 = new User("user9", "user9@domain.com", LocalDate.of(2021, 12, 24));
        User user10 = new User("user10", "user10@domain.com", LocalDate.of(2021, 10, 14));
        List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);
        list.stream().forEach(userRepository::save);
    }

    private void clasesAnteriores() {
        myBeanWithDependency.printWithDependency();
        componentDependency.saludar();
        myBean.print();
        System.out.println(myBeanWithProperties.fullname());
        System.out.println(userPojo.getEmail() +"-"+userPojo.getPassword()+"-"+userPojo.getAge());
        Logger.error("Error del aplicativo");
    }
}
