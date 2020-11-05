package Chapter3.controller;

import Chapter3.model.FileFreq;
import Chapter3.model.PDFdocument;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainViewController {
    //Remove Region and Progress Bar , Add ListView and Button
    private LinkedHashMap<String, ArrayList<FileFreq>> uniqueSets;
    private ArrayList<String> fakeInputListViewItems = new ArrayList<>();
    @FXML
    //private Region dropRegion;
    private ListView<String> inputListView;
    @FXML
    //private ProgressBar progressBar;
    private Button startButton;
    @FXML
    private ListView listView;
    @FXML
    private MenuItem menuClose;
    @FXML
    public void initialize(){
        //dropRegion.setOnDragOver(event -> {
        inputListView.setOnDragOver(event -> {
            Dragboard db = event.getDragboard();
            final boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".pdf");
            if (db.hasFiles() && isAccepted) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            else {
                event.acceptTransferModes(TransferMode.COPY);
            }
        });
        //dropRegion.setOnDragDropped(event -> {

        inputListView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                success = true;
                String filePath;
                int total_files = db.getFiles().size();
                //3.16
                //WordMapPageTask[] wordMapTaskList = new WordMapPageTask[total_files];
                //Map<String, FileFreq>[] wordMap = new Map[total_files];
                for (int i = 0; i < total_files; i++){
                    String[] a = String.valueOf(db.getFiles()).split("/");
                    String b = a[a.length-1]; // sfjklsdj.pdf]
                    b = b.replaceAll("]","");
                    /*
                    File file = db.getFiles().get(i);
                    filePath = file.getAbsolutePath();
                    */
                    //try{
                    File file = db.getFiles().get(i);
                    filePath = file.getAbsolutePath();
                    fakeInputListViewItems.add(filePath);
                    inputListView.getItems().add(b);
                        /*PDFdocument p = new PDFdocument(filePath);
                        wordMapTaskList[i] = new WordMapPageTask(p);
                        wordMap[i] = wordMapTaskList[i].getWordCount();
                        progressBar.setProgress(i / wordMapTaskList.length * 100);
                    } catch (IIOException e){
                        e.printStackTrace();
                    }
                    */
                }
                /*
                WordMapMergeTask merger = new WordMapMergeTask(wordMap);
                LinkedHashMap<String,
                        ArrayList<FileFreq>> uniqueSets = merger.getUniqueSets();
                listView.getItems().addAll(uniqueSets.entrySet());
                progressBar.setProgress(100);
                */
            }
            event.setDropCompleted(success);
            event.consume();
        });

        startButton.setOnAction(event -> {
            // 3.5.6 3.5.5
                    Parent bgRoot = Launcher.primaryStage.getScene().getRoot();
                    Task<Void> processTask = new Task<Void>() {
                @Override
                public Void call() throws IOException {
                    ProgressIndicator pi = new ProgressIndicator();
                    VBox box = new VBox(pi);
                    box.setAlignment(Pos.CENTER);
                    Launcher.primaryStage.getScene().setRoot(box);
                    ExecutorService executor = Executors.newFixedThreadPool(4);
                    final ExecutorCompletionService<Map<String, FileFreq>> completionService = new
                            ExecutorCompletionService<>(executor);
                    List<String> inputListViewItems = fakeInputListViewItems; //wow
                    int total_files = inputListViewItems.size();
                    //WordMapPageTask[] wordMapTaskList = new WordMapPageTask[total_files];
                    Map<String, FileFreq>[] wordMap = new Map[total_files];
                    for (int i = 0; i < total_files; i++) {
                        try {
                            String filePath = inputListViewItems.get(i);
                            PDFdocument p = new PDFdocument(filePath);
                            //wordMapTaskList[i] = new WordMapPageTask(p);
                            //wordMap[i] = wordMapTaskList[i].getWordCount();
                            completionService.submit(new WordMapPageTask(p));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //listView.getItems().addAll(uniqueSets.keySet());
                    }
                    for (int i = 0; i < total_files; i++) {
                        try {
                            Future<Map<String, FileFreq>> future = completionService.take();
                            wordMap[i] = future.get();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        WordMapMergeTask merger = new WordMapMergeTask(wordMap);
                        Future<LinkedHashMap<String, ArrayList<FileFreq>>> future = executor.submit(merger);
                        //uniqueSets = merger.getUniqueSets();
                        uniqueSets = future.get();

                        Set<Map.Entry<String, ArrayList<FileFreq>>> map = uniqueSets.entrySet();
                        List<Map.Entry<String, ArrayList<FileFreq>>> list = new ArrayList<>(map);
                        Collections.sort(list, new Comparator<Map.Entry<String, ArrayList<FileFreq>>>() {
                            @Override
                            public int compare(Map.Entry<String, ArrayList<FileFreq>> o1, Map.Entry<String, ArrayList<FileFreq>> o2) {
                                return o2.getValue().get(0).getFreq().compareTo(o1.getValue().get(0).getFreq());
                            }
                        });
                        uniqueSets.clear();
                        for (Map.Entry<String, ArrayList<FileFreq>> entry : list){
                            uniqueSets.put(entry.getKey(), entry.getValue());
                        }

                        listView.getItems().addAll(uniqueSets.keySet());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        executor.shutdown();
                    }
                    return null;
                }
            };
            processTask.setOnSucceeded( e -> {
                Launcher.primaryStage.getScene().setRoot(bgRoot); });
            Thread thread = new Thread(processTask); thread.setDaemon(true);
            thread.start();
        });

        listView.setOnMouseClicked(event -> {
            ArrayList<FileFreq> listOfLinks = uniqueSets.get(listView.getSelectionModel().getSelectedItem());
            ListView<FileFreq> popupListView = new ListView<>();
            LinkedHashMap<FileFreq,String> lookupTable = new LinkedHashMap<>();
            for (int i=0 ; i<listOfLinks.size() ; i++){
                lookupTable.put(listOfLinks.get(i),listOfLinks.get(i).getPath());
                popupListView.getItems().add(listOfLinks.get(i));
            }
            popupListView.setPrefHeight(popupListView.getItems().size() * 28);
            popupListView.setOnMouseClicked(innerEvent -> {
                Launcher.hs.showDocument("file://"+lookupTable.get(popupListView
                        .getSelectionModel().getSelectedItem()));
                popupListView.getScene().getWindow().hide();
            });
            Popup popup = new Popup();
            popup.getContent().add(popupListView);
            popup.show(Launcher.primaryStage);

            popupListView.setOnKeyPressed(innerEvent -> {
                if (innerEvent.getCode() == KeyCode.ESCAPE) {
                    popupListView.getScene().getWindow().hide();
                }
            });

        });

        menuClose.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
