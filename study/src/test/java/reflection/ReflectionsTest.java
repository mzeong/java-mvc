package reflection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Set;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reflection.annotation.Controller;
import reflection.annotation.Repository;
import reflection.annotation.Service;
import reflection.examples.JdbcQuestionRepository;
import reflection.examples.JdbcUserRepository;
import reflection.examples.MyQnaService;
import reflection.examples.QnaController;

class ReflectionsTest {

    private static final Logger log = LoggerFactory.getLogger(ReflectionsTest.class);

    @Test
    void showAnnotationClass() throws Exception {
        Reflections reflections = new Reflections("reflection.examples");

        Set<Class<?>> controllerClasses = reflections.getTypesAnnotatedWith(Controller.class);
        Set<Class<?>> serviceClasses = reflections.getTypesAnnotatedWith(Service.class);
        Set<Class<?>> repositoryClasses = reflections.getTypesAnnotatedWith(Repository.class);

        log.info("controller: {}", controllerClasses);
        log.info("service: {}", serviceClasses);
        log.info("repository: {}", repositoryClasses);

        assertAll(
                () -> assertThat(controllerClasses).containsOnly(QnaController.class),
                () -> assertThat(serviceClasses).containsOnly(MyQnaService.class),
                () -> assertThat(repositoryClasses).containsOnly(JdbcQuestionRepository.class, JdbcUserRepository.class)
        );
    }
}
