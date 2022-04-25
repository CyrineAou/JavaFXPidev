package com.ui.logements;

import com.database.DatabaseHandler;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

public class ChartFxmlController implements Initializable {

    @FXML
    private PieChart pieChart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            int active, disactive;
            Connection con = DatabaseHandler.getInstance().getConnection();
            Statement st = con.createStatement();
            Statement st2 = con.createStatement();
            ResultSet rs = st.executeQuery("select COUNT(*) FROM logement where is_active = '1'");
            rs.next();
            active = rs.getInt("COUNT(*)");
            ResultSet rs2 = st2.executeQuery("select COUNT(*) FROM logement where is_active ='0'");
            rs2.next();
            disactive = rs2.getInt("COUNT(*)");
            PieChart.Data slice1 = new PieChart.Data("Active", active);
            PieChart.Data slice2 = new PieChart.Data("UN-Active", disactive);
            pieChart.getData().add(slice1);
            pieChart.getData().add(slice2);

            st.close();
            st2.close();
            rs.close();
            rs2.close();
        } catch (SQLException ex) {
        }
    }

}
