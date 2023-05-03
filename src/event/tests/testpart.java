///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package event.tests;
//
//import event.entites.Participants;
//import event.tools.MyConnection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author Dell
// */
//public class testpart {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        
//        List<Participants> participants = displayParticipants();
//        for (Participants p : participants) {
//        System.out.println(p);
//}
//
//        // TODO code application logic here
//    }
//
//    private static List<Participants> displayParticipants() {
//        List<Participants> participants = new ArrayList<>();
//    try {
//        String requete = "SELECT * FROM participants";
//        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
//        ResultSet rs = pst.executeQuery();
//        while (rs.next()) {
//            Participants p = new Participants();
//            p.setId(rs.getInt("id"));
//            p.setName(rs.getString("nom"));
//            p.setPrenom(rs.getString("prenom"));
//            p.setTelephone(rs.getString("telephone"));     
//            p.setMail(rs.getString("email"));
//            participants.add(p);
//        }
//    } catch (SQLException ex) {
//        System.out.println(ex.getMessage());
//    }
//    return participants;
//        }
//    
//}
