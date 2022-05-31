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
        Path arquivo = Paths.get("src\\test\\resources", "janeiro2022.txt");
        String janeiro2022 = Files.readString(arquivo); //saida esperada
        Calendario.mostrarCalendario("1,5", "2,2");
        String obtido = saidaConsole.toString();
        assertEquals(janeiro2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
    }

    //Teste 02 - Mês não-inteiro, ano < 1
    @Test
    public void testarMesNaoInteiroAnoMenorQueUm() throws IOException {
        Path arquivo = Paths.get("src\\test\\resources", "janeiro2022.txt");
        String janeiro2022 = Files.readString(arquivo); //saida esperada
        Calendario.mostrarCalendario("3,5", "0");
        String obtido = saidaConsole.toString();
        assertEquals(janeiro2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
    }

    //Teste 03 - Mês não-inteiro, ano > 9999
    @Test
    public void testarMesNaoInteiroAnoMaiorQue9999() throws IOException {
        Path arquivo = Paths.get("src\\test\\resources", "janeiro2022.txt");
        String janeiro2022 = Files.readString(arquivo); //saida esperada
        Calendario.mostrarCalendario("3,5", "10000");
        String obtido = saidaConsole.toString();
        assertEquals(janeiro2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
    }

    //Teste 04 - Mês não-inteiro, 1 <= ano <= 9999
    @Test
    public void testarMesNaoInteiroAnoMenorIgualUmMaiorQueAte9999() throws IOException {
        Path arquivo = Paths.get("src\\test\\resources", "janeiro2022.txt");
        String janeiro2022 = Files.readString(arquivo); //saida esperada
        Calendario.mostrarCalendario("3,5", "2022");
        String obtido = saidaConsole.toString();
        assertEquals(janeiro2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
    }

    //Teste 05 - Mês menor que um, Mês não-inteiro
    @Test
    public void testarMesMenorQueUmAnoNaoInteiro() throws IOException {
        Path arquivo = Paths.get("src\\test\\resources", "janeiro2022.txt");
        String janeiro2022 = Files.readString(arquivo); //saida esperada
        Calendario.mostrarCalendario("0", "3,5");
        String obtido = saidaConsole.toString();
        assertEquals(janeiro2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
    }
}
