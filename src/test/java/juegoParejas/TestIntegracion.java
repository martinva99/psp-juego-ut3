package juegoParejas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestIntegracion {

    private JuegoParejas juego;

    @BeforeEach
    void setUp() {
        juego = DatosJuego.crearJuego();
    }
}
