package org.centrale.projet.medev.tp_pgm_git;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.*;
 /**
 *
 * @author tigris
 */
public class PGM {
    
    /**
     * This class is for the processing of a PGM image file.
     * filename : path to the image file
     * width, height : well obviously
     * grayMax : the maximum value of the grayscale of the image
     * bucket : counts of grayscale values
     */
    private String filename;
    private int width, height;
    private int grayMax;
    private ArrayList<Integer> bucket;
    
    /**
     * Read and process the image file.
     * Fill the bucket for the generation of the histogram
     */
    public void read() {
        try {
            FileInputStream fin = new FileInputStream(filename);
            System.setIn(fin);
            Scanner sc = new Scanner(System.in);
            
            sc.nextLine(); sc.nextLine();
            width = sc.nextInt(); height = sc.nextInt();
            grayMax = sc.nextInt();
            
            for (int i = 0; i <= grayMax; i++) {
                bucket.add(0);
            }
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int temp = sc.nextInt();
                    bucket.set(temp, bucket.get(temp)+1);
                }
            }
            System.out.println(bucket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
//    public void write() {
//        
//    }
    
    /**
     * Histogram generation.
     * Width : the number of grayscale of the image
     * Height : the count of the grayscale which the image has the most
     */
    public void generateHistogram() {
        int bucketMax = Collections.max(bucket);
        String histogramfile = filename.replace(".pgm", "_histogram.pgm");
        try {
            FileOutputStream fout = new FileOutputStream(histogramfile);
            PrintStream pstr = new PrintStream(fout);
            System.setOut(pstr);

            System.out.println("P2");
            System.out.println("#");
            System.out.println("" + (grayMax+1) + " " + bucketMax);
            System.out.println("255");

            for (int i = bucketMax; i > 0; i--) {
                int count = 0;
                for (int j = 0; j <= grayMax; j++) {
                    if (bucket.get(j) >= i) {
                        System.out.print("0  ");
                    } else {
                        System.out.print("255  ");
                    }

                    count++;
                    if (count >= 12) {
                        System.out.println("");
                        count = 0;
                    }
                }
                System.out.println("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Thresholding the image. 
     * @param threshold the grayscale threshold
     * This method generates a new .pgm image that after thresholding
     */
    public void thresholding(int threshold) {
        String threshfile = filename.replace(".pgm", "_threashold.pgm");
        if (threshold > 255 || threshold < 0) {
            System.out.println("Illegal threshold value. ");
            return;
        }
        try {
            FileInputStream fin = new FileInputStream(filename);
            System.setIn(fin);
            Scanner sc = new Scanner(System.in);
            
            FileOutputStream fout = new FileOutputStream(threshfile);
            PrintStream pstr = new PrintStream(fout);
            System.setOut(pstr);
            
            System.out.println(sc.nextLine());
            System.out.println(sc.nextLine());
            width = sc.nextInt(); height = sc.nextInt();
            grayMax = sc.nextInt();
            System.out.println("" + width + " " + height);
            System.out.println(grayMax);
            
            for (int i = 0; i < height; i++) {
                int count = 0;
                for (int j = 0; j < width; j++) {
                    int temp = sc.nextInt();
                    if (temp > threshold) {
                        temp = 255;
                    }
                    System.out.print(temp + "  ");
                    count++;
                    if (count >= 12) {
                        System.out.println("");
                        count = 0;
                    }
                }
                System.out.println("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
//    public void difference() {
//        
//    }
    
    public PGM(String filename) {
        this.filename = filename;
        bucket = new ArrayList<Integer>();
    }
}
