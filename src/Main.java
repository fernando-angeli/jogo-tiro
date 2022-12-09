import model.Tela;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean continuarPartida = true;
        Tela tela = new Tela();

        String leitura = null;
        while(continuarPartida){
            tela.printarTela();
            System.out.print("Entre com as posições X e Y no formato (x,y) ou digite FIM para encerrrar: ");
            leitura = input.nextLine();
            if(leitura.equalsIgnoreCase("FIM")){
                System.out.println("Jogo encerrado.");
                break;
            }
            String[] coordenadas = leitura.split(",");
            int x = Integer.parseInt(coordenadas[0])-1;
            int y = Integer.parseInt(coordenadas[1])-1;
            tela.verificarMovimento(x, y);
            if(tela.getTirosRestantes() == 0){
                System.out.println("YOU LOSER!");
                tela.mostrarAlvosNaoAcertados();
                break;
            }
            if(tela.getAlvosAtivos() == 0){
                System.out.println("YOU WIN!");
                break;
            }
        }
    }
}