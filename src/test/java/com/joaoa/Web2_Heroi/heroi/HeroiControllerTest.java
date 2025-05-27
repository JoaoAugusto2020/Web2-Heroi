package com.joaoa.Web2_Heroi.heroi;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.joaoa.Web2_Heroi.config.TestConfig;
import com.joaoa.Web2_Heroi.controller.HeroiController;
import com.joaoa.Web2_Heroi.model.Heroi;
import com.joaoa.Web2_Heroi.service.IHeroiService;


@WebMvcTest(HeroiController.class)
@Import(TestConfig.class)
public class HeroiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IHeroiService heroiService;

    @AfterEach
    void resetMocks() {
        reset(heroiService);
    }

    private List<Heroi> testCreateHeroiList(){
        Heroi heroiB = new Heroi();
        heroiB.setIdHeroi(1L);
        heroiB.setNome("Heroi B");
        heroiB.setVida(999);
        heroiB.setDefesa(999);
        heroiB.setAtaque(999);
        heroiB.setUniverso("Teste");
        heroiB.setAnoOrigem(999);

        return List.of(heroiB);
    }

    @Test
    @DisplayName("GET /heroi - Listar heróis sem usuário autenticado")
    void testIndexNotAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/heroi"))
                .andExpect(status().isUnauthorized()); // Correção aqui
    }

    @Test
    @WithMockUser
    @DisplayName("GET /heroi/list - Listar heróis com usuário logado")
    void testIndexAuthenticatedUser() throws Exception {
        when(heroiService.getAllHerois()).thenReturn(testCreateHeroiList());

        mockMvc.perform(get("/heroi/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("heroi/list"))
                .andExpect(model().attributeExists("heroisList"))
                .andExpect(content().string(containsString("Listagem de Herói")))
                .andExpect(content().string(containsString("Heroi B")));
    }

    @Test
    @WithMockUser(username = "admin@gmail.com", authorities = { "Admin" })
    @DisplayName("GET /heroi/create - Exibe formulário de criação")
    void testCreateFormAuthorizedUser() throws Exception {
        mockMvc.perform(get("/heroi/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("heroi/create"))
                .andExpect(model().attributeExists("heroi"))
                .andExpect(content().string(containsString("Cadastro de Heroi")));
    }

    @Test
    @WithMockUser(username = "aluno2@gmail.com", authorities = { "Manager" })
    @DisplayName("GET /heroi - Verificar o link de cadastrar para um usuario não admin logado")
    void testCreateFormNotAuthorizedUser() throws Exception {
        when(heroiService.getAllHerois()).thenReturn(testCreateHeroiList());
        // Obter o HTML da página renderizada pelo controlador
        mockMvc.perform(get("/heroi/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("heroi/create"))
                .andExpect(model().attributeExists("heroi"));
    }

    @Test
    @WithMockUser
    @DisplayName("POST /heroi/save - Falha na validação e retorna para o formulário")
    void testSaveHeroiValidationError() throws Exception {
        Heroi heroi = new Heroi(); // Heroi sem nome, o que causará erro de validação

        mockMvc.perform(post("/heroi/save")
                        .with(csrf())
                        .flashAttr("heroi", heroi))
                .andExpect(status().isOk())
                .andExpect(view().name("heroi/create"))
                .andExpect(model().attributeHasErrors("heroi"));

        verify(heroiService, never()).saveHeroi(any(Heroi.class));
    }

    @Test
    @WithMockUser(username = "aluno@iftm.edu.br", authorities = { "Admin" })
    @DisplayName("POST /heroi/save - Herói válido é salvo com sucesso")
    void testSaveValidHeroi() throws Exception {
        Heroi heroi = new Heroi();
        heroi.setIdHeroi(2L);
        heroi.setNome("Heroi");
        heroi.setVida(999);
        heroi.setDefesa(999);
        heroi.setAtaque(999);
        heroi.setUniverso("Teste");
        heroi.setAnoOrigem(999);

        mockMvc.perform(post("/heroi/save")
                        .with(csrf())
                        .flashAttr("heroi", heroi))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));

        verify(heroiService).saveHeroi(any(Heroi.class));
    }

}