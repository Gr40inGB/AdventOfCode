package org.gr40in.day19;

import org.gr40in.ReadFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day19 {
    static List<String> workFlowsInput;
    static List<String> detailsInput;
    static HashMap<String, List<FlowCommand>> allWorkFlows;
    static List<HashMap<String, Integer>> details;
    static List<HashMap<String, Integer>> accepted;

    public static void main(String[] args) throws IOException {
        List<String> list = ReadFile.getStringList("src/main/resources/day19");

        workFlowsInput = new ArrayList<>();
        detailsInput = new ArrayList<>();
        for (String s : list) {
            if (s.isEmpty()) continue;
            if (s.matches("^\\w+.+")) workFlowsInput.add(s);
            else detailsInput.add(s);
        }
//        System.out.println(workFlowsInput);
//        System.out.println(detailsInput);
        details = new ArrayList<>();
        for (var data : detailsInput) {
            String[] arr = (data.substring(data.indexOf('{') + 1, data.indexOf('}'))).split(",");
            HashMap<String, Integer> detailIndicators = new HashMap<>();
            for (String s : arr) {
                if (!s.isEmpty()) {
                    detailIndicators.put(s.substring(0, 1), Integer.parseInt(s.substring(2)));
                }
            }
            details.add(detailIndicators);
        }

        allWorkFlows = new HashMap<>();
        for (String s : workFlowsInput) {
            allWorkFlows.put(s.substring(0, s.indexOf('{')), flowCommandsParser(s));
        }

        accepted = new ArrayList<>();

        for (var detail : details) {
            System.out.println(detail);
        }

    }

    public static boolean ItIsOK(HashMap<String, Integer> detail) {
        String nextWorkFlow = "in";
        while (!nextWorkFlow.equals("A") || !nextWorkFlow.equals("R")) {
            List<FlowCommand> currentList = allWorkFlows.get(nextWorkFlow);
            for (FlowCommand command : currentList) {
                if ( detail.get( command.feature) )
            }
        }
    }

    public static class WorkFlow {
        String name;
        List<FlowCommand> flow;

        public WorkFlow(String data) {
            name = data.substring(0, data.indexOf('{'));
            flow = flowCommandsParser(data);
        }

        public WorkFlow(String name, List<FlowCommand> flow) {
            this.name = name;
            this.flow = flow;
        }

        @Override
        public String toString() {
            return "WorkFlow{" +
                    "name='" + name + '\'' +
                    ", flow=" + flow +
                    '}';
        }

        public String showWhoIAm() {
            StringBuilder result = new StringBuilder("name " + name + "\n");
            for (FlowCommand command : flow) {
                result.append("\t").append(command).append("\n");
            }
            return result.toString();
        }
    }

    public static List<FlowCommand> flowCommandsParser(String data) {
        List<FlowCommand> tempLink = new ArrayList<>();
        String[] arr = (data.substring(data.indexOf('{') + 1, data.indexOf('}'))).split(",");
        for (String s : arr)
            if (!s.isEmpty()) {
                if (s.contains(":")) {
                    char[] tempArr = s.toCharArray();
                    String feature = Character.toString(tempArr[0]);
                    boolean bigger = tempArr[1] == '>';
                    int value = 0;
                    String redirectWorkFlowName = "";
                    StringBuilder sb = new StringBuilder();
                    for (int i = 2; i < tempArr.length; i++) {
                        if (tempArr[i] == ':') {
                            value = Integer.parseInt(sb.toString());
                            redirectWorkFlowName = s.substring(i + 1, tempArr.length);
                        } else sb.append(tempArr[i]);
                    }
                    tempLink.add(new FlowCommand(feature, bigger, value, redirectWorkFlowName));
                } else tempLink.add(new FlowCommand(s));
            }
        return tempLink;
    }

    public static class FlowCommand {
        String feature;
        boolean bigger;
        int value;
        String redirectWorkFlowName;

        public FlowCommand(String redirect) {
            this.redirectWorkFlowName = redirect;
        }

        public FlowCommand(String feature, boolean bigger, int value, String redirect) {
            this.feature = feature;
            this.bigger = bigger;
            this.value = value;
            this.redirectWorkFlowName = redirect;
        }

        @Override
        public String toString() {
            return "FlowCommand{" +
                    "feature='" + feature + '\'' +
                    ", bigger=" + bigger +
                    ", value=" + value +
                    ", redirectWorkFlowName='" + redirectWorkFlowName + '\'' +
                    '}';
        }
    }

}
