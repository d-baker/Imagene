package imagene.gui;

import java.io.InputStream;

/**
 * Created by avishkar on 10/30/2016.
 */
final class ResourceLoader {

    public static InputStream load(String path)
    {


        InputStream input= ResourceLoader.class.getResourceAsStream(path);
        //fileReader=new InputStreamReader(input);
        if(input==null)
        {
            input= ResourceLoader.class.getResourceAsStream("/"+path);
           // fileReader=new InputStreamReader(input);
        }
         return input;
    }
}
