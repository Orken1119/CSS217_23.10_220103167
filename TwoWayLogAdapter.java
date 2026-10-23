interface ModernConsole {
    void logMessage(String tag, String body);
}

interface LegacyPrinter {
    void printLine(String combinedText);
}

public class TwoWayLogAdapter implements ModernConsole, LegacyPrinter {
    private ModernConsole modernConsole;
    private LegacyPrinter legacyPrinter;

    public TwoWayLogAdapter() {}

    public TwoWayLogAdapter(ModernConsole modernConsole) {
        this.modernConsole = modernConsole;
    }

    public TwoWayLogAdapter(LegacyPrinter legacyPrinter) {
        this.legacyPrinter = legacyPrinter;
    }

    public TwoWayLogAdapter(ModernConsole modernConsole, LegacyPrinter legacyPrinter) {
        this.modernConsole = modernConsole;
        this.legacyPrinter = legacyPrinter;
    }

    @Override
    public void logMessage(String tag, String body) {
        if (modernConsole != null) {
            modernConsole.logMessage(tag, body);
        } else {
            System.out.println("[" + tag + "] " + body);
        }
    }

    @Override
    public void printLine(String combinedText) {
        if (combinedText != null && combinedText.startsWith("[") && combinedText.indexOf(']') > 1) {
            int closeBracket = combinedText.indexOf(']');
            String tag = combinedText.substring(1, closeBracket).trim();
            String msg = combinedText.substring(closeBracket + 1).trim();
            logMessage(tag, msg);
        } else {
            if (legacyPrinter != null) {
                legacyPrinter.printLine(combinedText);
            } else {
                System.out.println(combinedText);
            }
        }
    }

    public static void main(String[] args) {
        String studentId = "220103167";
        System.out.println("Student ID: " + studentId);

        TwoWayLogAdapter adapter = new TwoWayLogAdapter();

        ModernConsole modernConsole = adapter;
        modernConsole.logMessage(studentId, "Log message via ModernConsole interface");

        LegacyPrinter legacyPrinter = adapter;
        legacyPrinter.printLine("[" + studentId + "] Print line via LegacyPrinter interface");
    }
}
