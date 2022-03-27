package controllers;

public class graphController implements javafx.fxml.Initializable {
    @javafx.fxml.FXML
    private javafx.scene.layout.Pane pane_graph;
    @javafx.fxml.FXML
    javafx.scene.chart.BarChart<String, Double> mothlyRecordsBar;

    @javafx.fxml.FXML
    private javafx.scene.chart.LineChart yearlyLinegraph;

    javafx.scene.chart.XYChart.Series yearlySeries = new javafx.scene.chart.XYChart.Series<>();

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        new animatefx.animation.FadeInUp(pane_graph).play();

        try {
            loadBarchart();
            displayLineGraph();
        } catch (java.sql.SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void loadBarchart() throws java.sql.SQLException {
        try {
            javafx.collections.ObservableList<javafx.scene.chart.XYChart.Data<String, Double>> data = DAO.Credited_AccountsDAO.loadBarchat();
            javafx.scene.chart.XYChart.Series<String, Double> series = new javafx.scene.chart.XYChart.Series<>(data);
            series.setName("MONTH RECORDS");
            mothlyRecordsBar.getData().setAll(series);
        } catch (java.sql.SQLException throwables) {
            throwables.printStackTrace();
        }
    }


//    public void populateLineGraph() throws java.sql.SQLException {
//        DBclass.DataBase_connection db = new DBclass.DataBase_connection();
//        db.open();
//        String sql = "select monthname(date), sum(amount) from pacesetterfx.credited_accounts " +
//                "where year(date) = " + java.time.LocalDate.now().getYear() +
//                " group by month(date);";
//        java.sql.PreparedStatement statement = db.conn.prepareCall(sql);
////        statement.setInt(1, 34327010);
////        statement.setInt(1, java.time.LocalDate.now().getYear());
//
//        java.sql.ResultSet resultSet = statement.executeQuery();
//
//        while (resultSet.next()){
//            yearlySeries.getData().add(new javafx.scene.chart.XYChart.Data(resultSet.getString(1), resultSet.getDouble(2)));
//        }
//        yearlyLinegraph.getData().addAll(yearlySeries);
//        yearlySeries.setName(Integer.toString(java.time.LocalDate.now().getYear()));
//    }

    private void displayLineGraph() throws java.sql.SQLException {
        try {
            javafx.collections.ObservableList<javafx.scene.chart.XYChart.Data> data = DAO.Credited_AccountsDAO.loadLineChart();
            javafx.scene.chart.XYChart.Series series = new javafx.scene.chart.XYChart.Series(data);
            series.setName("YEARLY RECORDS");
            yearlyLinegraph.getData().setAll(series);
        } catch (java.sql.SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
