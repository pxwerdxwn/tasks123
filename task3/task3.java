package task3;

import java.io.*;
import java.util.*;

public class task3 {
    public static void main(String[] args) throws IOException {
        String values = readFile(args[0]);
        String tests = readFile(args[1]);
        Map<Integer, String> valueMap = parseValues(values);
        String report = fillTestValues(tests, valueMap);
        writeFile(args[2], report);
    }
    
    static String readFile(String path) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        reader.close();
        return content.toString();
    }
    
    static void writeFile(String path, String content) throws IOException {
        FileWriter writer = new FileWriter(path);
        writer.write(content);
        writer.close();
    }
    
    static Map<Integer, String> parseValues(String json) {
        Map<Integer, String> map = new HashMap<>();
        String pattern = "\"id\":\\s*(\\d+),\\s*\"value\":\\s*\"([^\"]*)\"";
        java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = r.matcher(json);
        while (m.find()) {
            int id = Integer.parseInt(m.group(1));
            String value = m.group(2);
            map.put(id, value);
        }
        return map;
    }
    
    static String fillTestValues(String testsJson, Map<Integer, String> valueMap) {
        StringBuilder result = new StringBuilder();
        String[] lines = testsJson.split("\n");
        
        for (String line : lines) {
            if (line.contains("\"id\"")) {
                int start = line.indexOf("\"id\"") + 5;
                int end = line.indexOf(",", start);
                String idStr = line.substring(start, end).trim();
                int id = Integer.parseInt(idStr.replaceAll("[^0-9]", ""));
                
                if (valueMap.containsKey(id)) {
                    int valuePos = line.indexOf("\"value\"");
                    if (valuePos == -1) {
                        int bracePos = line.lastIndexOf("}");
                        line = line.substring(0, bracePos) + 
                               ", \"value\": \"" + valueMap.get(id) + "\"" + 
                               line.substring(bracePos);
                    }
                }
            }
            result.append(line).append("\n");
        }
        
        return result.toString();
    }
}