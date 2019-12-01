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
    int m_transforma[][];

    // construtor realiza a leitura da imagem
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

        // cria instancia da matriz da imagem
        m = new int[h][w];
        s.nextLine();

        // itera lendo os valores da imagem e salvando em m
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                m[i][j] = s.nextInt();
            }
        }
    }

    public void passa_baixa() {
        this.m_transforma = new int[h][w];

        int[][] mascara;
        mascara = new int[][]{{1, 1, 1},
        {1, 1, 1},
        {1, 1, 1}};

        for (int i = 1; i < this.h - 1; i++) {
            for (int j = 1; j < this.w - 1; j++) {
                int valor_novo = (m[i - 1][j - 1] * mascara[0][0]
                        + m[i - 1][j] * mascara[0][1]
                        + m[i - 1][j + 1] * mascara[0][2]
                        + m[i][j - 1] * mascara[1][0]
                        + m[i][j] * mascara[1][1]
                        + m[i][j + 1] * mascara[1][2]
                        + m[i + 1][j - 1] * mascara[2][0]
                        + m[i + 1][j] * mascara[2][1]
                        + m[i + 1][j + 1] * mascara[2][2]) * 1 / 9;

                this.m_transforma[i][j] = valor_novo;
            }
        }

        this.escreve_pgm("passa_baixa");
    }

    public void passa_alta() {
        this.m_transforma = new int[h][w];

        int[][] mascara;
        mascara = new int[][]{{-1, -1, -1},
        {-1, 8, -1},
        {-1, -1, -1}};

        for (int i = 1; i < this.h - 1; i++) {
            for (int j = 1; j < this.w - 1; j++) {
                int valor_novo = (m[i - 1][j - 1] * mascara[0][0]
                        + m[i - 1][j] * mascara[0][1]
                        + m[i - 1][j + 1] * mascara[0][2]
                        + m[i][j - 1] * mascara[1][0]
                        + m[i][j] * mascara[1][1]
                        + m[i][j + 1] * mascara[1][2]
                        + m[i + 1][j - 1] * mascara[2][0]
                        + m[i + 1][j] * mascara[2][1]
                        + m[i + 1][j + 1] * mascara[2][2]);

                this.m_transforma[i][j] = Math.abs(valor_novo);
            }
        }

        this.escreve_pgm("passa_alta");
    }

    public void sobel() {
        this.m_transforma = new int[h][w];

        int[][] mascara_gradiente_x;
        mascara_gradiente_x = new int[][]{{-1, -2, -1},
        {0, 0, 0},
        {1, 2, 1}};

        int[][] mascara_gradiente_y;
        mascara_gradiente_y = new int[][]{{-1, 0, 1},
        {-2, 0, 2},
        {-1, 0, 1}};

        for (int i = 1; i < this.h - 1; i++) {
            for (int j = 1; j < this.w - 1; j++) {
                int gradiente_x = (m[i - 1][j - 1] * mascara_gradiente_x[0][0]
                        + m[i - 1][j] * mascara_gradiente_x[0][1]
                        + m[i - 1][j + 1] * mascara_gradiente_x[0][2]
                        + m[i][j - 1] * mascara_gradiente_x[1][0]
                        + m[i][j] * mascara_gradiente_x[1][1]
                        + m[i][j + 1] * mascara_gradiente_x[1][2]
                        + m[i + 1][j - 1] * mascara_gradiente_x[2][0]
                        + m[i + 1][j] * mascara_gradiente_x[2][1]
                        + m[i + 1][j + 1] * mascara_gradiente_x[2][2]);

                int gradiente_y = (m[i - 1][j - 1] * mascara_gradiente_y[0][0]
                        + m[i - 1][j] * mascara_gradiente_y[0][1]
                        + m[i - 1][j + 1] * mascara_gradiente_y[0][2]
                        + m[i][j - 1] * mascara_gradiente_y[1][0]
                        + m[i][j] * mascara_gradiente_y[1][1]
                        + m[i][j + 1] * mascara_gradiente_y[1][2]
                        + m[i + 1][j - 1] * mascara_gradiente_y[2][0]
                        + m[i + 1][j] * mascara_gradiente_y[2][1]
                        + m[i + 1][j + 1] * mascara_gradiente_y[2][2]);

                int valor_novo = (int) (Math.sqrt((Math.pow(gradiente_x, 2.0) + Math.pow(gradiente_y, 2.0))));

                this.m_transforma[i][j] = valor_novo;
            }
        }

        this.escreve_pgm("sobel");
    }

    public void prewitt() {
        this.m_transforma = new int[h][w];

        int[][] mascara_gradiente_x;
        mascara_gradiente_x = new int[][]{{-1, -1, -1},
        {0, 0, 0},
        {1, 1, 1}};

        int[][] mascara_gradiente_y;
        mascara_gradiente_y = new int[][]{{-1, 0, 1},
        {-1, 0, 1},
        {-1, 0, 1}};

        for (int i = 1; i < this.h - 1; i++) {
            for (int j = 1; j < this.w - 1; j++) {
                int gradiente_x = (m[i - 1][j - 1] * mascara_gradiente_x[0][0]
                        + m[i - 1][j] * mascara_gradiente_x[0][1]
                        + m[i - 1][j + 1] * mascara_gradiente_x[0][2]
                        + m[i][j - 1] * mascara_gradiente_x[1][0]
                        + m[i][j] * mascara_gradiente_x[1][1]
                        + m[i][j + 1] * mascara_gradiente_x[1][2]
                        + m[i + 1][j - 1] * mascara_gradiente_x[2][0]
                        + m[i + 1][j] * mascara_gradiente_x[2][1]
                        + m[i + 1][j + 1] * mascara_gradiente_x[2][2]);

                int gradiente_y = (m[i - 1][j - 1] * mascara_gradiente_y[0][0]
                        + m[i - 1][j] * mascara_gradiente_y[0][1]
                        + m[i - 1][j + 1] * mascara_gradiente_y[0][2]
                        + m[i][j - 1] * mascara_gradiente_y[1][0]
                        + m[i][j] * mascara_gradiente_y[1][1]
                        + m[i][j + 1] * mascara_gradiente_y[1][2]
                        + m[i + 1][j - 1] * mascara_gradiente_y[2][0]
                        + m[i + 1][j] * mascara_gradiente_y[2][1]
                        + m[i + 1][j + 1] * mascara_gradiente_y[2][2]);

                int valor_novo = (int) (Math.sqrt((Math.pow(gradiente_x, 2.0) + Math.pow(gradiente_y, 2.0))));

                this.m_transforma[i][j] = valor_novo;
            }
        }

        this.escreve_pgm("prewitt");
    }

    public void roberts() {
        this.m_transforma = new int[h][w];

        int[][] mascara_gradiente_x;
        mascara_gradiente_x = new int[][]{{-1, 0},
        {0, 1}};

        int[][] mascara_gradiente_y;
        mascara_gradiente_y = new int[][]{{0, -1},
        {1, 0}};

        for (int i = 1; i < this.h - 1; i++) {
            for (int j = 1; j < this.w - 1; j++) {
                int valor_antigo = m[i][j];

                int gradiente_x = (m[i][j] * mascara_gradiente_x[0][0]
                        + m[i][j + 1] * mascara_gradiente_x[0][1]
                        + m[i + 1][j] * mascara_gradiente_x[1][0]
                        + m[i + 1][j + 1] * mascara_gradiente_x[1][1]);

                int gradiente_y = (m[i][j] * mascara_gradiente_y[0][0]
                        + m[i][j + 1] * mascara_gradiente_y[0][1]
                        + m[i + 1][j] * mascara_gradiente_y[1][0]
                        + m[i + 1][j + 1] * mascara_gradiente_y[1][1]);

                int valor_novo = (int) (Math.sqrt((Math.pow(gradiente_x, 2.0) + Math.pow(gradiente_y, 2.0))));

                this.m_transforma[i][j] = valor_novo;
            }
        }
        this.escreve_pgm("roberts");
    }

    public void isotropico() {
        this.m_transforma = new int[h][w];
        double[][] mascara_gradiente_x;
        mascara_gradiente_x = new double[][]{{-1, -Math.sqrt(2), 1},
        {0, 0, 0},
        {1, Math.sqrt(2), 1}};

        double[][] mascara_gradiente_y;
        mascara_gradiente_y = new double[][]{{-1, 0, 1},
        {-Math.sqrt(2), 0, Math.sqrt(2)},
        {-1, 0, 1}};

        for (int i = 1; i < this.h - 1; i++) {
            for (int j = 1; j < this.w - 1; j++) {
                double gradiente_x = (m[i - 1][j - 1] * mascara_gradiente_x[0][0]
                        + m[i - 1][j] * mascara_gradiente_x[0][1]
                        + m[i - 1][j + 1] * mascara_gradiente_x[0][2]
                        + m[i][j - 1] * mascara_gradiente_x[1][0]
                        + m[i][j] * mascara_gradiente_x[1][1]
                        + m[i][j + 1] * mascara_gradiente_x[1][2]
                        + m[i + 1][j - 1] * mascara_gradiente_x[2][0]
                        + m[i + 1][j] * mascara_gradiente_x[2][1]
                        + m[i + 1][j + 1] * mascara_gradiente_x[2][2]);

                double gradiente_y = (m[i - 1][j - 1] * mascara_gradiente_y[0][0]
                        + m[i - 1][j] * mascara_gradiente_y[0][1]
                        + m[i - 1][j + 1] * mascara_gradiente_y[0][2]
                        + m[i][j - 1] * mascara_gradiente_y[1][0]
                        + m[i][j] * mascara_gradiente_y[1][1]
                        + m[i][j + 1] * mascara_gradiente_y[1][2]
                        + m[i + 1][j - 1] * mascara_gradiente_y[2][0]
                        + m[i + 1][j] * mascara_gradiente_y[2][1]
                        + m[i + 1][j + 1] * mascara_gradiente_y[2][2]);

                int valor_novo = (int) (Math.sqrt((Math.pow(gradiente_x, 2.0) + Math.pow(gradiente_y, 2.0))));

                this.m_transforma[i][j] = valor_novo;
            }
        }

        this.escreve_pgm("isotropico");
    }

    public void mostra_matriz() {
        for (int i = 0; i < this.h; i++) {
            for (int j = 0; j < this.w; j++) {
                System.out.print(this.m[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /* Realiza a escrita da imagem
     * uso interno nas mascaras
     */
    private void escreve_pgm(String file_name) {
        File file = new File("src/processamento_de_imagens/filtered_images/" + file_name + ".pgm");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write("P2\n");
            fr.write("# Imagem manipulada por João Pedro Giandoso" + "\n");
            fr.write(String.valueOf(this.w) + " " + String.valueOf(this.h) + "\n");
            fr.write(String.valueOf(this.tons) + "\n");
            for (int i = 0; i < this.h; i++) {
                for (int j = 0; j < this.w; j++) {
                    fr.write(String.valueOf(this.m_transforma[i][j]) + " ");
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
