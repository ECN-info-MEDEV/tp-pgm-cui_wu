

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tigris
 */
public class HelloWorld {
    public static void main(String[] args) {
        PGM testPGM = new PGM("/Users/tigris/EI3/MEDEV/TP3/ImagesTestPGM/baboon.pgm");
        testPGM.read();
        testPGM.thresholding(150);
    }
}
