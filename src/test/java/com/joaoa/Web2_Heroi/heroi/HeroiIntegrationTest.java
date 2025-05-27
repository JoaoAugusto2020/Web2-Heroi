package com.joaoa.Web2_Heroi.heroi;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.joaoa.Web2_Heroi.model.Heroi;
import com.joaoa.Web2_Heroi.repository.HeroiRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Usa application-test.properties
@Transactional // Limpa o banco após cada teste
public class HeroiIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HeroiRepository heroiRepository;

    @Test
    @WithMockUser(authorities = { "Admin" })
    void testSaveHeroiIntegration() throws Exception {

        Heroi heroiA = new Heroi();
        heroiA.setNome("Heroi A");
        heroiA.setVida(999);
        heroiA.setDefesa(999);
        heroiA.setAtaque(999);
        heroiA.setUniverso("Teste");
        heroiA.setAnoOrigem(2000);


        mockMvc.perform(post("/heroi/save")
                        .with(csrf())
                        .flashAttr("heroi", heroiA))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        // Verifica no banco se foi salvo
        assertTrue(heroiRepository.findAll()
                .stream()
                .anyMatch(h -> "Heroi A".equals(h.getNome())));

    }
}