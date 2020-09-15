package br.com.ecommerce.api.resources;

import br.com.ecommerce.api.EcommerceApplication;
import br.com.ecommerce.api.domain.Categoria;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = EcommerceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoriaResourceTest {

    @Autowired
    private CategoriaResource controller;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCategoriaForbiden() {
        Categoria categoria = new Categoria(1,"Informatica");
        ResponseEntity<Categoria> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/categorias", categoria, Categoria.class);
        assertEquals(403, responseEntity.getStatusCodeValue());
    }
    @Test
    void find() {
        assertThat(controller).isNotNull();
    }

    @Test
    void findAll() {
        assertThat(controller).isNotNull();
    }

    @Test
    void findPage() {
        assertThat(controller).isNotNull();
    }

    @Test
    void insert() {
        assertThat(controller).isNotNull();
    }

    @Test
    void update() {
        assertThat(controller).isNotNull();
    }

    @Test
    void delete() {
        assertThat(controller).isNotNull();
    }
}