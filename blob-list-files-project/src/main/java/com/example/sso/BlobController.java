package com.example.sso;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/blob")
public class BlobController {
    @Autowired
    private BlobService blobService;

    @GetMapping("/read/{blobName}")
    public String readBlob(@PathVariable String blobName) {
        try {
            return blobService.readBlobContent(blobName);
        } catch (Exception e) {
            return "Error reading blob: " + e.getMessage();
        }
    }
    @GetMapping("/list")
    public List<String> listBlobs() {
        try {
            return blobService.listBlobs();
        } catch (Exception e) {
            throw new RuntimeException("Error listing blobs: " + e.getMessage());
        }
    }
    @GetMapping("/list-contents")
    public ResponseEntity<Map<String, String>> listBlobsWithContent() {
        try {
            Map<String, String> contents = blobService.listBlobsWithContent();
            return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(contents);
        } catch (Exception e) {
            throw new RuntimeException("Error listing blob contents: " + e.getMessage());
        }
    }
}