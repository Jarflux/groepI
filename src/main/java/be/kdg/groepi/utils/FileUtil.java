package be.kdg.groepi.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
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
    private static String destination = File.separator + "images" + File.separator + "profilepictures";

    public static String savePicture(HttpSession session, MultipartFile file, long id) throws IOException {
        String path = session.getServletContext().getRealPath(destination);
        File savedFile = new File(path + File.separator + id + ".jpg");
        FileUtils.writeByteArrayToFile(savedFile, file.getBytes());
        File[] testFileSave = findFile(session, id);

        if (testFileSave.length == 1) {  // return waarde van profilePicture-attribuut van User
            return destination + File.separator + id + ".jpg";
        } else {
            return null;
        }

    }

    public static File[] findFile(HttpSession session, final long id) {
        File dir = new File(session.getServletContext().getRealPath(destination));

        return dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename) {
                return filename.startsWith(String.valueOf(id)) && filename.endsWith(".jpg");
            }
        });

    }

}
