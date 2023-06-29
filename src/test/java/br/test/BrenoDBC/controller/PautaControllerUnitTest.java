package br.test.BrenoDBC.controller;

import br.test.BrenoDBC.service.PautaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


@ActiveProfiles("test")
@WebMvcTest(controllers = PautaController.class)
public class PautaControllerUnitTest {

    private static final String novaPautaPath = "v1/pauta/nova";

    private static final String novaSecaoPautaPath = "v1/pauta/secao";

    private static final String novoVotoPautaPath = "v1/pauta/voto";

    private static final String contabilizaPautaPath = "v1/pauta/contabiliza";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PautaService pautaService;

    @Test
    void whenPostNovaPautaThenGo() throws Exception{
       assert true;
    }
}
