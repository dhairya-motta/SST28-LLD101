package com.example.reports;

/**
 * RealReport - The real subject that performs expensive file loading.
 */
public class RealReport implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private String content; // Lazily loaded content

    public RealReport(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        // Load content only when actually needed
        if (content == null) {
            content = loadFromDisk();
        }
        System.out.println("REPORT -> id=" + reportId
                + " title=" + title
                + " classification=" + classification
                + " openedBy=" + user.getName());
        System.out.println("CONTENT: " + content);
    }

    private String loadFromDisk() {
        System.out.println("[disk] loading report " + reportId + " ...");
        try { Thread.sleep(120); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return "Internal report body for " + title;
    }

    public String getClassification() {
        return classification;
    }
}
