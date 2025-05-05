package com.trysol.utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.awt.image.BufferedImage;

public class OCRUtil {
    private final ITesseract tesseract;

    public OCRUtil() {
        this.tesseract = new Tesseract();
        tesseract.setDatapath("./src/main/resources/tessdata");
        tesseract.setTessVariable("user_defined_dpi", "300");
    }

    public String extractTextFromImage(BufferedImage image) throws TesseractException {
        try {
            return tesseract.doOCR(image)
                    .replaceAll("[^0-9=+]", "")
                    .trim();
        } catch (Exception e) {
            throw new TesseractException("OCR failed: " + e.getMessage());
        }
    }
}