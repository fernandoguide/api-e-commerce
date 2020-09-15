package br.com.ecommerce.api.repositories;

import br.com.ecommerce.api.domain.Categoria;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
class CategoriaRepositoryTest {


    @Autowired
    private CategoriaRepository repository;

    @Test
    public void save() {
        this.repository.save(new Categoria(1, "Games"));
        Optional<Categoria> categoria = this.repository.findById(1);
        assertThat(categoria.get().getId()).isEqualTo(1);
        assertThat(categoria.get().getNome()).isEqualTo("Games");
    }
}