package de.codencetric.blog.nlp.textprocessor.processor;

import com.optimaize.langdetect.i18n.LdLocale;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TextProcessorController.class)
public class TextProcessorControllerIT {

    @Autowired
    MockMvc mvc;

    @MockBean
    SimpleProcessor simpleProcessor;

    @MockBean
    AdvancedProcessor advancedProcessor;

    @MockBean
    LanguageDetectionService languageDetectionService;

    @MockBean
    Tokenizer tokenizer;

    @Before
    public void setUp() throws Exception {
        given(languageDetectionService.detect(any())).willReturn(LdLocale.fromString("en"));
    }

    @Test
    public void should_return_tokenized_processed_text() throws Exception {
        final String request = "{text: Please process my too long text}";
        mvc.perform(get("/processTextBasic")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_simple_processed_text() throws Exception {
        final String request = "{text: Please process my too long text}";
        mvc.perform(get("/processTextSimple")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_advanced_processed_text() throws Exception {
        final String request = "{text: Please process my too long text}";
        mvc.perform(get("/processTextAdvanced")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());
    }
}