package br.com.dvsn.controllers;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.dvsn.helpers.StringHelper;

import java.io.File;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class DownloadController extends BaseController {

    private static final Logger logger = LogManager.getLogger(DownloadController.class);

    @GetMapping("/image/**")
    public ResponseEntity<?> downloadFileFromLocal(HttpServletRequest request) {

        var fileName = request.getRequestURI().replace(request.getContextPath() + "/image/", "");

        logger.info("Filename: " + fileName);

        try {
            var path = StringHelper.createUploadFilePath(fileName);

            File file = new File(path);
            if (!file.exists())
                return notFound("Arquivo " + fileName + " não encontrado.");

            final byte[] fileBytes = Files.readAllBytes(Paths.get(path));

            var contentType = URLConnection.guessContentTypeFromName(path);

            final HttpHeaders headers = new HttpHeaders();

            if (contentType != null)
                headers.setContentType(MediaType.parseMediaType(contentType));

            return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
        } catch (Exception ex) {
            return internalServerError(ex);
        }
    }
}
