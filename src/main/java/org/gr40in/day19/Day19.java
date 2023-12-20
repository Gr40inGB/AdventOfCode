package org.gr40in.day19;

import org.gr40in.ReadFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day19 {
    static List<String> workFlowsInput;
    static List<String> detailsInput;

    public static void main(String[] args) throws IOException {
        List<String> list = ReadFile.getStringList("src/main/resources/day19");

        workFlowsInput = new ArrayList<>();
        detailsInput = new ArrayList<>();
        for (String s : list) {
            if (s.isEmpty()) continue;
            if (s.matches("^\\w+.+")) workFlowsInput.add(s);
            else detailsInput.add(s);
        }
        System.out.println(workFlowsInput);
        System.out.println(detailsInput);
        System.out.println(flowCommandsParser(workFlowsInput.get(0)));
    }

    public static class WorkFlow {
        String name;
        List<FlowCommand> flow;

        public WorkFlow(String name, List<FlowCommand> flow) {
            this.name = name;
            this.flow = flow;
        }
    }

    public static List<FlowCommand> flowCommandsParser(String data) {
        List<FlowCommand> tempLink = new ArrayList<>();
        String[] arr = (data.substring(data.indexOf('{') + 1, data.indexOf('}'))).split(",+");
        for (String s : arr)
            if (!s.isEmpty()) {
                if (s.contains(":")) {
                    char[] tempArr = s.toCharArray();
                    for ()
                }
            }
        return tempLink;
    }

    public static class FlowCommand {
        String feature;
        boolean bigger;
        int value;
        WorkFlow redirect;

        public FlowCommand(WorkFlow redirect) {
            this.redirect = redirect;
        }

        public FlowCommand(String feature, boolean bigger, int value, WorkFlow redirect) {
            this.feature = feature;
            this.bigger = bigger;
            this.value = value;
            this.redirect = redirect;
        }
    }

}
