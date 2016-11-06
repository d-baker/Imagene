package imagene.view;

import java.io.InputStream;

/*****************************************
 * Written by Avishkar Giri (s3346203)   *
 * for                                   *
 * Programming Project 1                 *
 * SP3 2016                              *
 ****************************************/

final class ResourceLoader {

    public static InputStream load(String path)
    {


        InputStream input= ResourceLoader.class.getResourceAsStream(path);

        if(input==null)
        {
            input= ResourceLoader.class.getResourceAsStream("/"+path);

        }
         return input;
    }
}
