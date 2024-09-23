package com.example.dnd.model;
import java.util.ArrayList;
import java.util.List;

public class BattleLog {
    private List<String> logs = new ArrayList<>();
    private final int MAX_LOG_SIZE = 1000;



    private String result;
    private String summary;

    public void addLog(String logEntry) {
        // Limit the log size to avoid memory overflow
        if (logs.size() > MAX_LOG_SIZE) {
            logs.add("...log truncated due to size limits...");
            return;
        }
        logs.add(logEntry);
    }

    public List<String> getLogs() {
        return logs;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


}
