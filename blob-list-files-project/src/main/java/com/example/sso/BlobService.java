package com.example.sso;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

@Service
public class BlobService {
    @Autowired
    private AzureBlobConfig config;

    public List<String> listBlobs() throws Exception {
        String sasToken = config.getSasToken();
        // Remove the leading '?' if present in the stored token
        if (sasToken.startsWith("?")) {
            sasToken = sasToken.substring(1);
        }
        
        String listUrl = String.format(
            "https://%s.blob.core.windows.net/%s?restype=container&comp=list&%s",
            config.getStorageAccount(),
            config.getContainerName(),
            sasToken
        );

        URL url = new URL(listUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        List<String> blobNames = new ArrayList<>();
        
        try {
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(connection.getInputStream());
                
                NodeList blobs = doc.getElementsByTagName("Blob");
                for (int i = 0; i < blobs.getLength(); i++) {
                    Node blob = blobs.item(i);
                    NodeList children = blob.getChildNodes();
                    for (int j = 0; j < children.getLength(); j++) {
                        Node child = children.item(j);
                        if ("Name".equals(child.getNodeName())) {
                            blobNames.add(child.getTextContent());
                            break;
                        }
                    }
                }
            } else {
                throw new RuntimeException("Failed to list blobs: " + connection.getResponseCode());
            }
        } finally {
            connection.disconnect();
        }
        
        return blobNames;
    }
    public Map<String, String> listBlobsWithContent() throws Exception {
        Map<String, String> contentsMap = new HashMap<>();
        List<String> blobNames = listBlobs();
        
        for (String blobName : blobNames) {
            String content = readBlobContent(blobName);
            contentsMap.put(blobName, content);
        }
        
        return contentsMap;
    }

    public String readBlobContent(String blobName) throws Exception {
        String sasToken = config.getSasToken();
        if (!sasToken.startsWith("?")) {
            sasToken = "?" + sasToken;
        }
        
        String blobUrl = String.format(
            "https://%s.blob.core.windows.net/%s/%s%s",
            config.getStorageAccount(),
            config.getContainerName(),
            blobName,
            sasToken
        );

        URL url = new URL(blobUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        StringBuilder content = new StringBuilder();
        
        try {
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine).append("\n");
                    }
                }
            } else {
                throw new RuntimeException("Failed to read blob: " + connection.getResponseCode());
            }
        } finally {
            connection.disconnect();
        }
        
        return content.toString();
    }

}