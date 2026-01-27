package juegoParejas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJuegoParejas {
    private JuegoParejas juego;

    @BeforeEach
    void setUp() {
        juego = DatosJuego.crearJuego();
    }

    @Test
    void testNuevaPreguntaTieneTodasLasOpciones() {
        String resultado = juego.nuevaPregunta();

        assertTrue(resultado.startsWith("PREGUNTA:"), "Las preguntas deben empezar por PREGUNTA:");
        assertTrue(resultado.contains("OPCIONES:"), "Las preguntas deben contener OPCIONES:");
        assertTrue(resultado.contains("||"), "Las preguntas deben contener '||'");
        assertEquals(7, resultado.split(" ").length, "Debe haber 3 opciones");
    }

    @Test
    void testNuevaPreguntaPrimeraPregunta() {
        String resultado = juego.nuevaPregunta();

        assertEquals(0, juego.getPreguntaActual(), "La primera pregunta debe ser la del índice 0");
        assertEquals(List.of("SOL", "DIA"), juego.getPreguntasRespuestas().get(0),
                "La primera pregunta debe ser la del índice 0");
        assertTrue(resultado.startsWith("PREGUNTA: SOL || OPCIONES: "),
                "La primera pregunta debe empezar por 'PREGUNTA: SOL || OPCIONES: '");
        assertTrue(juego.getOpciones().contains(juego.getPreguntasRespuestas().get(0).get(1)),
                "La primera pregunta debe contener 'DIA' entre las opciones");
    }

    @Test
    void testNuevaPreguntaSegundaPregunta() {
        juego.setPreguntaActual(0);
        String resultado = juego.nuevaPregunta();

        assertEquals(1, juego.getPreguntaActual(), "La segunda pregunta debe ser la del índice 1");
        assertEquals(List.of("MADERA", "MESA"), juego.getPreguntasRespuestas().get(1),
                "La segunda pregunta debe ser la del índice 1");
        assertTrue(resultado.startsWith("PREGUNTA: MADERA || OPCIONES: "),
                "La segunda pregunta debe empezar por 'PREGUNTA: MADERA || OPCIONES: '");
        assertTrue(juego.getOpciones().contains(juego.getPreguntasRespuestas().get(1).get(1)),
                "La segunda pregunta debe contener 'MESA' entre las opciones");
    }

    @Test
    void testNuevaPreguntaUltimaPregunta() {
        juego.setPreguntaActual(8);
        String resultado = juego.nuevaPregunta();

        assertEquals(9, juego.getPreguntaActual(), "La última pregunta debe ser la del índice 9");
        assertEquals(List.of("PISTOLA", "BALA"), juego.getPreguntasRespuestas().get(9),
                "La última pregunta debe ser la del índice 9");
        assertTrue(resultado.startsWith("PREGUNTA: PISTOLA || OPCIONES: "),
                "La última pregunta debe empezar por 'PREGUNTA: PISTOLA || OPCIONES: '");
        assertTrue(juego.getOpciones().contains(juego.getPreguntasRespuestas().get(9).get(1)),
                "La última pregunta debe contener 'BALA' entre las opciones");
    }

    @Test
    void testNuevaPreguntaFinJuego() {
        juego.setPreguntaActual(9);
        String resultado = juego.nuevaPregunta();

        assertEquals("FIN",  resultado);
    }

    @Test
    void testPedirPistaPrimeraPregunta() {
        juego.setPreguntaActual(0);
        String resultado = juego.pedirPista();

        assertEquals("PISTA: " + juego.getPistas().get(0), resultado,
                "La pista debe ser la de la primera pregunta");
    }

    @Test
    void testPedirPistaSegundaPregunta() {
        juego.setPreguntaActual(1);
        String resultado = juego.pedirPista();

        assertEquals("PISTA: " + juego.getPistas().get(1), resultado,
                "La pista debe ser la de la segunda pregunta");
    }

    @Test
    void testResponderCorrectamente() {
        juego.setPreguntaActual(0);
        assertEquals("OK", juego.responder("RESPUESTA DIA"),
                "Problema con la respuesta correcta");
        assertEquals("OK", juego.responder("RESPUESTA dia"),
                "Problema con las minúsculas");
        assertEquals("OK", juego.responder("RESPUESTA DÍA"),
                "Problema con las tildes");
    }

    @Test
    void testResponderIncorrectamente() {
        juego.setPreguntaActual(0);
        assertEquals("ERROR", juego.responder("RESPUESTA SARTEN"),
                "Debe dar error con respuesta incorrecta");
        assertEquals("ERROR", juego.responder("RESPUESTA MESA"),
                "Problema con respuesta correcta de OTRA pregunta (debería ser error)");
    }
}
