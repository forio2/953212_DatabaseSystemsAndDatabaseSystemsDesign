package Chapter3.controller;

import Chapter3.model.FileFreq;
import Chapter3.model.PDFdocument;
import com.sun.javafx.collections.MappingChange;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

//public class WordMapPageTask {
public class WordMapPageTask implements Callable<Map<String,FileFreq>> {
    //private Map<String, FileFreq> wordCount;
    private PDFdocument doc;
    public WordMapPageTask(PDFdocument doc) throws IOException {
        this.doc = doc;
    }
    @Override
    public Map<String, FileFreq> call() throws Exception {
        Map<String, FileFreq> wordCount;
        PDFTextStripper reader = new PDFTextStripper();
        Pattern pattern = Pattern.compile(" ");
        String s = reader.getText(doc.getDocument());
        //this.wordCount = pattern.splitAsStream(s)
        wordCount = pattern.splitAsStream(s)
                .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase().trim())
                .filter(word -> word.length() > 3)
                .map(word -> new AbstractMap.SimpleEntry<>(word, 1))
                .collect(toMap(e -> e.getKey(), e -> e.getValue(), (v1, v2) -> v1 + v2))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(e -> e.getKey(),
                        e-> new FileFreq(doc.getName(),doc.getFilePath(),e.getValue())));
        return wordCount;
    }
}
