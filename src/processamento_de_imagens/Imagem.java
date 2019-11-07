/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processamento_de_imagens;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author giandoso
 */
public class Imagem {

    int h, w, tons;

    int m[][];

    public Imagem(String path) throws FileNotFoundException {
        File f = new File(path);
        Scanner s = new Scanner(f);

        // pula cabeçalho
        s.nextLine();
        s.nextLine();

        // captura dimensão da imagem
        this.w = s.nextInt();
        this.h = s.nextInt();
        this.tons = s.nextInt();

        // instancia matriz da imagem
        m = new int[h][w];
        s.nextLine();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                m[i][j] = s.nextInt();
            }
        }
    }

    public void mostra_matriz() {
        for (int i = 0; i < this.h; i++) {
            for (int j = 0; j < this.w; j++) {
                System.out.print(this.m[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public void escreve_pgm(String file_name) {
        File file = new File("src/processamento_de_imagens/" + file_name + ".pgm");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write("P2\n");
            fr.write("# Imagem manipulada por João Pedro Giandoso" + "\n");
            fr.write(String.valueOf(this.w) + " " + String.valueOf(this.h) + "\n");
            fr.write(String.valueOf(this.tons)+ "\n");
            for (int i = 0; i < this.h; i++) {
                for (int j = 0; j < this.w; j++) {
                    fr.write(String.valueOf(this.m[i][j]) + " ");
                }
                fr.write("\n");
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
