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
        Imagem img = new Imagem("src/lena.pgm");
        img.passa_baixa();
        img.passa_alta();
        img.sobel_3x3();
        img.prewitt();
        img.sobel_2x2();
        img.isotropico();
    }
    
}
