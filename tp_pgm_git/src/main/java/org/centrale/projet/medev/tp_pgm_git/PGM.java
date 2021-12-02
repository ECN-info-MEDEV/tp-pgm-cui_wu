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
    
    String filename;
    int width, height;
    int grayMax;
    ArrayList<Integer> bucket;
    
    
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
    
    public void write() {
        
    }
    
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
    
    public void thresholding(int threshold) {
        String threshfile = filename.replace(".pgm", "_threashold.pgm");
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
    
    public void difference() {
        
    }
    
    public PGM(String filename) {
        this.filename = filename;
        bucket = new ArrayList<Integer>();
    }
}
