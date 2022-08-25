package com.epam.mjc.io;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        StringBuilder profileInfo = new StringBuilder();
        try (java.io.FileReader inputStream = new java.io.FileReader(file)) {
            int ch;
            while ((ch = inputStream.read()) != -1) {
                profileInfo.append((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String cleanString = profileInfo.toString().replaceAll("[\\n\\r]", ":").replace(" ", "").trim().replace("::", ":");
        String[] strArr = cleanString.substring(0, cleanString.length() - 1).split(":");
        Map<String, String> profileFields = new HashMap<>();
        for (int i = 0; i < strArr.length; i += 2) {
            profileFields.put(strArr[i], strArr[i + 1]);
        }
        for (Map.Entry<String, String> set :
                profileFields.entrySet()) {
            switch (set.getKey()) {
                case "Email":
                    profile.setEmail(set.getValue());
                    break;
                case "Name":
                    profile.setName(set.getValue());
                    break;
                case "Age":
                    profile.setAge(Integer.valueOf(set.getValue()));
                    break;
                case "Phone":
                    profile.setPhone(Long.valueOf(set.getValue()));
                    break;
                default:
            }
        }
        return profile;
    }
}