package be.kdg.groepi.utils;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 28-2-13
 * Time: 15:15
 * To change this template use File | Settings | File Templates.
 */
public class FileUtil {

    public static boolean  savePicture(MultipartFile file, long id) throws IOException {
        String destination = "/images/profilepictures";
        File savedFile = new File("/images");
        savedFile.createNewFile();
        file.transferTo(savedFile);
        return false;
    }

}
