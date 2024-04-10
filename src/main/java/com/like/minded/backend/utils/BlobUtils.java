/* LikeMinded (C)2024 */
package com.like.minded.backend.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class BlobUtils {

    public static String blobToBase64(Blob blob) throws SQLException, IOException {
        try {
            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] bytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
