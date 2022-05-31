import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import paranavai.calendario.Calendario;

import static org.junit.Assert.*;

public class TestaCelendario {
    private final ByteArrayOutputStream saidaConsole = new ByteArrayOutputStream();
    private final PrintStream saidaOriginal = System.out;

    @Before
    public void executaAntes() {
        System.setOut(new PrintStream(saidaConsole));
    }

    @After
    public void executaDepois() {
        System.setOut(new PrintStream(saidaOriginal));
    }

    @Test
    public void testarimpressaoDoAnoRecebidoPorParametro() throws IOException {
        Path arquivo = Paths.get("src\\test\\resources", "2022.txt");
        String ano2022 = Files.readString(arquivo); //saida esperada
        Calendario.mostrarCalendario("2022");
        String obtido = saidaConsole.toString();
        assertEquals(ano2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
    }

    @Test
    public void testarQuantidadeDeParametrosMaiorQueDois() throws IOException {
        Path arquivo = Paths.get("src\\test\\resources", "janeiro2022.txt");
        String janeiro2022 = Files.readString(arquivo); //saida esperada
        Calendario.mostrarCalendario("1", "2022", "12");
        String obtido = saidaConsole.toString();
        assertEquals(janeiro2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
    }

//    @Test
//    public void testarAnoNaoInteiro() throws IOException {
//        Throwable excecao = assertThrows(NumberFormatException.class,
//                () -> Calendario.mostrarCalendario("@"));
//
//        assertEquals("mostrarCalendario: @: ano inválido.", excecao.getMessage());
//    }


    ///TESTES PEDIDOS PELO PROFESSOR

    //Teste 01 - Mês não-inteiro, ano não-inteiro
    @Test
    public void testarMesNaoInteiroAnoNaoInteiro() throws IOException {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("1,5", "2,2"));

        assertEquals("mostrarCalendario: 1,5: mês inválido.", excecao.getMessage());
    }

    //Teste 02 - Mês não-inteiro, ano < 1
    @Test
    public void testarMesNaoInteiroAnoMenorQueUm() throws IOException {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("3,5", "0"));

        assertEquals("mostrarCalendario: 3,5: mês inválido.", excecao.getMessage());
    }

    //Teste 03 - Mês não-inteiro, ano > 9999
    @Test
    public void testarMesNaoInteiroAnoMaiorQue9999() throws IOException {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("3,5", "10000"));

        assertEquals("mostrarCalendario: 3,5: mês inválido.", excecao.getMessage());
    }

    //Teste 04 - Mês não-inteiro, 1 <= ano <= 9999
    @Test
    public void testarMesNaoInteiroAnoMenorIgualUmMaiorQueAte9999() throws IOException {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("3,5", "2022"));

        assertEquals("mostrarCalendario: 3,5: mês inválido.", excecao.getMessage());
    }

    //Teste 05 - Mês menor que um, Mês não-inteiro
    @Test
    public void testarMesMenorQueUmAnoNaoInteiro() throws IOException {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("0", "3,5"));

        assertEquals("mostrarCalendario: 0: mês inválido.", excecao.getMessage());
    }

    //Teste 06 - Mês menor que um, Ano < 1
    @Test
    public void testarMesMenorQueUmAnoMenorQueUm() throws IOException {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("0", "-1"));

        assertEquals("mostrarCalendario: 0: mês inválido.", excecao.getMessage());
    }

    //Teste 07 - Mês menor que um, Ano > 9999
    @Test
    public void testarMesMenorQueUmAnoMaiorQue9999() throws IOException {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("0", "10000"));

        assertEquals("mostrarCalendario: 0: mês inválido.", excecao.getMessage());
    }

    //Teste 08 - Mês menor que um, 1 <= ano <= 9999
    @Test
    public void testarMesMenorQueUmAnoMenorIgualUmMenorIgual9999() throws IOException {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("0", "2000"));

        assertEquals("mostrarCalendario: 0: mês inválido.", excecao.getMessage());
    }

    //Teste 09 - Mês > 12, Ano não-inteiro
    @Test
    public void testarMesMaiorQueDozeAnoNaoInteiro() throws IOException {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("17", "3,5"));

        assertEquals("mostrarCalendario: 17: mês inválido.", excecao.getMessage());
    }

    //Teste 10 - Mês > 12, Ano < 1
    @Test
    public void testarMesMaiorQueDozeAnoMenorQueUm() throws IOException {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("17", "0"));

        assertEquals("mostrarCalendario: 17: mês inválido.", excecao.getMessage());
    }

    //Teste 11 - Mês > 12, Ano > 9999
    @Test
    public void testarMesMaiorQueDozeAnoMaiorQue9999() throws IOException {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("17", "10000"));

        assertEquals("mostrarCalendario: 17: mês inválido.", excecao.getMessage());
    }

    //Teste 12 - Mês > 12, 1 <= ano <= 9999
    @Test
    public void testarMesMaiorQueDozeAnoMenorIgualUmMenorIgual9999() throws IOException {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("17", "2000"));

        assertEquals("mostrarCalendario: 17: mês inválido.", excecao.getMessage());
    }

    //Teste 13 - Mês <= 1 <= 12, Ano não inteiro
    @Test
    public void testarMesMenorIgualUmMenorIgualDozeAnoNaoInteiro() throws IOException {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("10", "3,5"));

        assertEquals("mostrarCalendario: 3,5: ano inválido.", excecao.getMessage());
    }

    //Teste 14 - Mês <= 1 <= 12, Ano < 1
    @Test
    public void testarMesMenorIgualUmMenorIgualDozeAnoMenorQueUm() throws IOException {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("10", "-1"));

        assertEquals("mostrarCalendario: -1: ano inválido.", excecao.getMessage());
    }

    //Teste 15 - Mês <= 1 <= 12, Ano > 9999
    @Test
    public void testarMesMenorIgualUmMenorIgualDozeAnoMaiorQue9999() throws IOException {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("10", "10000"));

        assertEquals("mostrarCalendario: 10000: ano inválido.", excecao.getMessage());
    }

    //Teste 16 - Mês <= 1 <= 12, 1 <= ano <= 9999
    @Test
    public void testarMesMenorIgualUmMenorIgualDozeAnoMenorIgualUmMenorIgual9999() throws IOException {
        Path arquivo = Paths.get("src\\test\\resources", "outubro2000.txt");
        String ano2022 = Files.readString(arquivo); //saida esperada
        Calendario.mostrarCalendario("10", "2000");
        String obtido = saidaConsole.toString();
        assertEquals(ano2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
    }


}
