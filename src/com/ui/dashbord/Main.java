
package com.ui.dashbord;
//
//import BAO.EquipementManage;
//import BAO.logementManage;
//import DAO.Equipement;
//import DAO.Logement;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import javafx.collections.FXCollections;
//import javafx.collections.ListChangeListener;
//import javafx.collections.ObservableList;
////import javafx.scene.chart.PieChart;
//
///**
// *
// * @author Yousra
// */
//public class Main {
//
//    public static void main(String[] args) {
////        equipement();
//        logement();
//        List<String> list = new ArrayList<>();
//        ObservableList<String> observableList = FXCollections.observableList(list);
//        observableList.addListener(new ListChangeListener() {
//            @Override
//            public void onChanged(ListChangeListener.Change change) {
//                System.out.println("change!");
//            }
//
//        });
//        observableList.add("item one");
//        list.add("item two");
//        list.add("item three");
//        list.add("item 4");
//        System.out.println("Size: " + observableList.size());
//    }
//
//    private static void equipement() {
//        EquipementManage m = EquipementManage.getInstance();
//        
//        Equipement e = new Equipement();
//        e.setName("Test");
//        m.create(e);
//        m.update(e);
//        m.delete(e);
//        
//        ArrayList<Equipement> all = m.getAll();
//        
//        for (Equipement equipement : all) {
//            System.out.println(equipement.getId());
//            System.out.println(equipement.getName());
//        }
//    }
//
//    private static void logement() {
//        logementManage m = logementManage.getInstance();
//        Logement e = new Logement();
//        
//        
//        e.setAddresse("addresse");
//        e.setCreated_at(LocalDateTime.now());
//        e.setUpdated_at(LocalDateTime.now());
//        e.setDescription("description");
//        e.setFilename("filename");
//        e.setHotelID(1);
//        e.setActive(true);
//        e.setQr_file_name("qr_file_name");
//        e.setTitre("titre");
//        m.create(e);
//        e.setUpdated_at(LocalDateTime.now().plusDays(1));
//        
//        
//        m.update(e);
//        m.delete(e);
//        
//        ArrayList<Logement> all = m.getAll();
//        for (Logement logement : all) {
//            System.out.println(logement.getHotelID());
//            System.out.println(logement.getTitre());
//            System.out.println(logement.getDescription());
//            System.out.println(logement.getAddresse());
//            System.out.println(logement.getFilename());
//            System.out.println(logement.getCreated_at().toString());
//            if (logement.getUpdated_at() != null) {
//                System.out.println(logement.getUpdated_at().toString());
//            }
//            System.out.println(logement.getQr_file_name());
//            System.out.println(logement.isActive());
//        }
//        
//    }
//}
