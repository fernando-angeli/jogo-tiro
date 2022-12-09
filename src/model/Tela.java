package model;

import java.util.*;

public class Tela {

    private static final int TAMANHO_CAMPO = 10;
    private static final int QTDE_ALVOS = 5;
    private static final int TOTAL_TIROS = 25;
    private static Random random = new Random();
    private String[][] telaJogo = new String[TAMANHO_CAMPO][TAMANHO_CAMPO];
    private int alvosAtivos = QTDE_ALVOS;
    private int tirosRestantes = TOTAL_TIROS;

    private double distanciaAlvoMaisProximo = 0.0;
    public Set<String> posicaoAlvos = new HashSet<>();

    public Tela(){
        iniciarTela();
    }

    public int getTirosRestantes() {
        return tirosRestantes;
    }

    public int getAlvosAtivos() {
        return posicaoAlvos.size();
    }

    public void setDistanciaAlvoMaisProximo(double distanciaAlvoMaisProximo) {
        this.distanciaAlvoMaisProximo = distanciaAlvoMaisProximo;
    }

    public void iniciarTela(){
        sortearAlvos();
        for(int i = 0; i < TAMANHO_CAMPO; i++)
            for (int j = 0; j < TAMANHO_CAMPO; j++)
                telaJogo[i][j] = "-";
    }

    public void sortearAlvos(){
        while(posicaoAlvos.size() < 5){
            posicaoAlvos.add(String.valueOf(random.nextInt(10))+","+String.valueOf(random.nextInt(10)));
        }
    }

    public void printarTela(){
        System.out.println("==================================================");
        System.out.println("Distância alvo mais próximo: " + distanciaAlvoMaisProximo);
        System.out.println("==================================================");
        for(int i = 0; i < TAMANHO_CAMPO; i++) {
            for (int j = 0; j < TAMANHO_CAMPO; j++) {
                System.out.print(telaJogo[i][j]);
                if(j < TAMANHO_CAMPO-1)
                    System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("Inimigos restantes: " + alvosAtivos);
        System.out.println("Tiros restantes: " + tirosRestantes);
    }

    public void verificarMovimento(int x, int y){
        if(!testeAtaque(x, y)) {
            telaJogo[x][y] = "X";
            tirosRestantes--;
            setDistanciaAlvoMaisProximo(verificarDistanciaAlvoMaisProximo(x, y));
        }
    }

    private double verificarDistanciaAlvoMaisProximo(int x, int y) {
        double menorDistancia = 0;
        for(String pos : posicaoAlvos){
            String[] coord = pos.split(",");
            int xAlvo = Integer.parseInt(coord[0]);
            int yAlvo = Integer.parseInt(coord[1]);
            double distancia = Math.sqrt(((Math.pow((x - xAlvo), 2))+(Math.pow((y - yAlvo), 2))));
            if(menorDistancia == 0) menorDistancia = distancia;
            else if(distancia < menorDistancia) menorDistancia = distancia;
        }
        return menorDistancia;
    }

    public boolean testeAtaque(int x, int y){
        String ataque = String.valueOf(x)+","+String.valueOf(y);
        for(String alvo : posicaoAlvos){
            if(alvo != null && alvo.equals(ataque)){
                alvosAtivos--;
                posicaoAlvos.remove(alvo);
                telaJogo[x][y] = "*";
                tirosRestantes--;
                System.out.println("Acertou miserávi!");
                return true;
            }
        }
        return false;
    }

    public void mostrarAlvosNaoAcertados(){
        for(String alvo : posicaoAlvos)
            System.out.print("["+alvo+"]");
    }

    //Método para tentar limpeza do screen
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
