/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processamento_de_imagens;

import java.io.FileNotFoundException;

/**
 *
 * @author giandoso
 */
public class Processamento_de_imagens {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Imagem img = new Imagem("src/mona_lisa.pgm");
//        img.mostra_matriz();
        img.escreve_pgm("teste");
    }
    
}
