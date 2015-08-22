package edu.kzk.java_samle.basic.io.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadResourceMain {

    public static void main(String[] args) {

        // ClassLoader経由で "src/main/resources"からファイルを読み込む．
        // subresをつける
        InputStream is = ClassLoader
                .getSystemResourceAsStream("subres/resource_file.prop");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String l = null;
        try {
            while ((l = br.readLine()) != null) {
                // System.out.println(l);
                String[] sl = l.split("=", -1);
                System.out.println("k=" + sl[0] + ", v=" + sl[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
