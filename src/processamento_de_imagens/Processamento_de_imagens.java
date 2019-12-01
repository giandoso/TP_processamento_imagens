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
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {


        /*
         * Escolha a imagem de entrada(as duas do moodle estão abaixo)
         * A classe imagem realiza a leitura do arquivo
         *  além disso possui a implementação de cada mascara.
         * 
         */
        Imagem img = new Imagem("src/mona_lisa.pgm");
//        Imagem img = new Imagem("src/lena.pgm");


        /*
         * Cada método é responsavel pela operação aritmetica
         * internamente cada metodo chama o metodo escreve.pgm
         * as imagens são salvas em src/processamento_de_imagens/filtered_images
         *
         * ps: a execução com uma segunda imagem de entrada 
         *     irá sobrescrever as imagens antigas
         */
        img.passa_baixa();
        img.passa_alta();
        img.sobel();
        img.prewitt();
        img.roberts();
        img.isotropico();
    }

}
