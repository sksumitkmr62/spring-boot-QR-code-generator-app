package com.pixeltrice.springbootQRcodegeneratorapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QRCodeController {
	
	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";

	
    @GetMapping(value = "/genrateAndDownloadQRCode/{codeText}/{width}/{height}")
		public void download(
				@PathVariable("codeText") String codeText,
				@PathVariable("width") Integer width,
				@PathVariable("height") Integer height)
			    throws Exception {
			        QRCodeGenerator.generateQRCodeImage(codeText, width, height, QR_CODE_IMAGE_PATH);
			    }

    @GetMapping(value = "/genrateQRCode/{codeText}/{width}/{height}")
   	public ResponseEntity<byte[]> generateQRCode(
   			@PathVariable("codeText") String codeText,
   			@PathVariable("width") Integer width,
   			@PathVariable("height") Integer height)
   		    throws Exception {
   		        return ResponseEntity.status(HttpStatus.OK).body(QRCodeGenerator.getQRCodeImage(codeText, width, height));
   		    }
   	

    @GetMapping(value = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCode(@RequestParam String text) {
        try {
            byte[] qrImage = QRCodeGenerator.getQRCodeImage(text, 300, 300);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrImage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
