package org.centrale.projet.medev.tp_pgm_git;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.io.*;
/**
 *
 * @author tigris
 */
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Please enter the path to the image (.pgm)");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        
        PGM testPGM = new PGM(input);
        testPGM.read();
        System.out.println("Please enter the threshold for thresholding (0 - 255)");
        int threshold = Integer.parseInt(sc.nextLine());
        testPGM.thresholding(threshold);
        testPGM.generateHistogram();
    }
}
