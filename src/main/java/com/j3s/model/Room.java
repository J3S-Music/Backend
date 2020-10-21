package com.j3s.model;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.awt.image.BufferedImage;
import java.util.UUID;

public class Room {
    private final UUID id;
    private BufferedImage qr;
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public BufferedImage getQr() {
        return qr;
    }

    public void setQr(BufferedImage qr) {
        this.qr = qr;
    }

    public UUID getId() {
        return id;
    }

    public Room(){
        id = UUID.randomUUID();
        qr = null;
        link=generateRoomLink();
    }

    private String generateRoomLink(){
        String URL = null;
        URL="http://localhost:8080/rooms/"+getId();
        return URL;
    }

    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    @Autowired
    private Environment environment;

    public String getServerAddress() {
        return environment.getProperty("server.address");
    }

    public String getServerPort() {
        return environment.getProperty("server.port");
    }


}


