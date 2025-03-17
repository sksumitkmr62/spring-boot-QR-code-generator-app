# QR Code Generator in Spring Boot

## Overview

This project is a simple **QR Code Generator** built using **Spring Boot** and **ZXing (Zebra Crossing)** library. It allows generating QR codes from text, URLs, or any data and provides both file-based and API-based QR code generation.

## Features

- **Generate QR Code as PNG Image** and save it to a file.
- **Generate QR Code as Byte Array** for API responses.
- **REST API** to generate QR codes dynamically.
- **Uses ZXing Library** for QR code generation.

## Technologies Used

- **Spring Boot** (2.x/3.x)
- **ZXing Library** (Google's QR Code Generator)
- **REST API (Spring Web)**

## Installation & Setup

### 1. Clone the Repository

```sh
git clone https://github.com/yourusername/spring-boot-qr-code-generator.git
cd spring-boot-qr-code-generator
```

### 2. Add Dependencies

Add the following dependency in `pom.xml`:

```xml
<dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>core</artifactId>
    <version>3.4.1</version>
</dependency>
<dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>javase</artifactId>
    <version>3.4.1</version>
</dependency>
```

### 3. Build & Run the Application

```sh
mvn spring-boot:run
```

The application will be available at:

```
http://localhost:8080
```

## Project Structure

```
├── src/main/java/com/example/qrcode
│   ├── QRCodeGenerator.java
│   ├── QRCodeController.java
├── src/main/resources
│   ├── application.properties
├── pom.xml
├── README.md
```

## Usage

### Generate QR Code and Save as Image

You can generate a QR code and save it as an image file:

```java
public static void main(String[] args) {
    try {
        QRCodeGenerator.generateQRCodeImage("https://example.com", 300, 300, "qrcode.png");
        System.out.println("QR Code generated successfully!");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

This will generate a QR code and save it as **qrcode.png**.

### Generate QR Code via REST API

The application provides a REST API endpoint:

```
GET /generate?text=HelloWorld
```

#### Example Controller

```java
@RestController
public class QRCodeController {
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
```

Now, opening the URL:

```
http://localhost:8080/generate?text=HelloWorld
```

will return a **QR code image**.


## Contact

For questions or suggestions, reach out to [**sksumitkmr62@gmail.com**](sksumitkmr62@gmail.com) or create an issue in the repository.

Happy Coding! 🚀
