package Chapter3.controller;

import Chapter3.model.FileFreq;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//public class WordMapMergeTask {
public class WordMapMergeTask implements Callable<LinkedHashMap<String, ArrayList<
        FileFreq>>> {
    //private LinkedHashMap<String, ArrayList<FileFreq>> uniqueSets;
    private Map<String, FileFreq>[] wordMap;
    public WordMapMergeTask(Map<String, FileFreq>[] wordMap) {
        this.wordMap = wordMap;
    }
    @Override
    //public LinkedHashMap<String, ArrayList<FileFreq>> getUniqueSets() {
    public LinkedHashMap<String, ArrayList<FileFreq>> call() throws Exception {
        LinkedHashMap<String, ArrayList<FileFreq>> uniqueSets;
        List<Map<String, FileFreq>> wordMapList = new ArrayList<>(Arrays.asList(wordMap));
        //this.uniqueSets = wordMapList.stream()
        uniqueSets = wordMapList.stream()
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.groupingBy(
                        e->e.getKey(), Collector.of(
                                () -> new ArrayList<FileFreq>(),
                                (list, item) -> list.add(item.getValue()),
                                (current_list, new_items) -> {
                                    current_list.addAll(new_items);
                                    return current_list;
                                })
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue(),
                        (v1,v2)->v1, () -> new LinkedHashMap<>()));
        return uniqueSets;
    }
}

