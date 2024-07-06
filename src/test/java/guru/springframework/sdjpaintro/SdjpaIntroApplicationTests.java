package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SdjpaIntroApplicationTests {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testBookRepository() {
        long count = bookRepository.count();
        Assertions.assertThat(count).isGreaterThan(0);
    }

    @Test
    void contextLoads() {
    }

}
