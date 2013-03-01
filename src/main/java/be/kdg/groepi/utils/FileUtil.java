package be.kdg.groepi.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 28-2-13
 * Time: 15:15
 * To change this template use File | Settings | File Templates.
 */
public class FileUtil {
    private static String destination = "src/main/webapp/images/profilepictures/";
    //String srcPath = config.getServletContext().getRealPath("/");


    public static String savePicture(MultipartFile file, long id) throws IOException {
        String sRootPath = new File("").getAbsolutePath();
        File savedFile = new File(destination + id + ".jpg");
        //File savedFile = new File(id + ".jpg");
        //file.transferTo(savedFile);
        FileUtils.writeByteArrayToFile(savedFile, file.getBytes());
        /*java.io.File filelocationtest = new java.io.File(".");  */
        String pathdinges = savedFile.getAbsolutePath();
        String tomcatbase = System.getProperty("catalina.base");
        File[] testFileSave = findFile(id);


        if (testFileSave.length == 1) {  // return waarde van profilePicture-attribuut van User
            //UserService.updateUser(user); --> Gebeurt in RestUserController: editUser
            return destination + id + ".jpg";
            //return "images/profilepictures/ " + id + ".jpg";
            //return id + ".jpg";
        } else {
            return null;
        }

    }

    public static File[] findFile(final long id) {
        File dir = new File(destination);

        return dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename) {
                return filename.startsWith(String.valueOf(id)) && filename.endsWith(".jpg");
            }
        });

    }

}
