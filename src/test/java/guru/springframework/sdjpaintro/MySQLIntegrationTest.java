package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.AuthorUuid;
import guru.springframework.sdjpaintro.domain.BookNatural;
import guru.springframework.sdjpaintro.domain.BookUuid;
import guru.springframework.sdjpaintro.domain.composite.AuthorComposite;
import guru.springframework.sdjpaintro.domain.composite.AuthorEmbedded;
import guru.springframework.sdjpaintro.domain.composite.NameId;
import guru.springframework.sdjpaintro.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MySQLIntegrationTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookUuidRepository bookUuidRepository;

    @Autowired
    AuthorUuidRepository authorUuidRepository;

    @Autowired
    BookNaturalRepository bookNaturalRepository;

    @Autowired
    AuthorCompositeRepository authorCompositeRepository;

    @Autowired
    AuthorEmbeddedRepository authorEmbeddedRepository;

    @Test
    void testMySQL() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);
    }

    @Test
    void testBookUUID() {
        BookUuid bookUuid = bookUuidRepository.save(new BookUuid());
        assertThat(bookUuid).isNotNull();
        assertThat(bookUuid.getId()).isNotNull();

        BookUuid fetched = bookUuidRepository.getById(bookUuid.getId());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testAuthorEmbedded() {
        NameId nameId = new NameId("Eben", "Java8");
        AuthorEmbedded authorEmbedded = new AuthorEmbedded();
        authorEmbedded.setCountry("US");
        authorEmbedded.setNameId(nameId);
        AuthorEmbedded saved = authorEmbeddedRepository.save(authorEmbedded);
        assertThat(saved).isNotNull();

        AuthorEmbedded fetched = authorEmbeddedRepository.getById(authorEmbedded.getNameId());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testAuthorComposite() {
        NameId nameId = new NameId("Ebe", "Java");
        AuthorComposite authorComposite = new AuthorComposite();
        authorComposite.setCountry("US");
        authorComposite.setFirstName(nameId.getFirstName());
        authorComposite.setLastName(nameId.getLastName());
        AuthorComposite saved = authorCompositeRepository.save(authorComposite);
        assertThat(saved).isNotNull();

        AuthorComposite fetched = authorCompositeRepository.getById(nameId);
        assertThat(fetched).isNotNull();
    }

    @Test
    void testAuthorUUID() {
        AuthorUuid authorUuid = authorUuidRepository.save(new AuthorUuid());
        assertThat(authorUuid).isNotNull();
        assertThat(authorUuid.getId()).isNotNull();

        AuthorUuid fetched = authorUuidRepository.getById(authorUuid.getId());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testBookNatural() {
        BookNatural bookNatural = new BookNatural();
        bookNatural.setTitle("My Book");
        BookNatural savedBookNatural = bookNaturalRepository.save(bookNatural);
        assertThat(savedBookNatural).isNotNull();

        BookNatural fetched = bookNaturalRepository.getById(savedBookNatural.getTitle());
        assertThat(fetched).isNotNull();
    }
}


