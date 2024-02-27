package org.example.models;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;

public class ContratCell extends ListCell<Contrat> {
    private final GridPane gridPane = new GridPane();
    private final Label idLabel = new Label();
    private final Label idEmployeLabel = new Label();
    private final Label dateDebutLabel = new Label();
    private final Label dateFinLabel = new Label();
    private final Label salaireLabel = new Label();

    public ContratCell() {
        super();

        // Define column width constraints
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(20);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(20);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(20);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(20);
        ColumnConstraints col5 = new ColumnConstraints();
        col5.setPercentWidth(20);

        // Add column constraints to the GridPane
        gridPane.getColumnConstraints().addAll(col1, col2, col3, col4, col5);
        gridPane.setAlignment(Pos.BASELINE_CENTER);

        // Add labels to the GridPane
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idEmployeLabel, 1, 0);
        gridPane.add(dateDebutLabel, 2, 0);
        gridPane.add(dateFinLabel, 3, 0);
        gridPane.add(salaireLabel, 4, 0);

        // Set horizontal gap between columns
        gridPane.setHgap(5);
    }

    @Override
    protected void updateItem(Contrat contrat, boolean empty) {
        super.updateItem(contrat, empty);

        if (empty || contrat == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Update labels with contrat attributes
            idLabel.setText(String.valueOf(contrat.getId()));
            idEmployeLabel.setText(String.valueOf(contrat.getId_Employe()));
            dateDebutLabel.setText(contrat.getDate_debut().toString());
            dateFinLabel.setText(contrat.getDate_fin().toString());
            salaireLabel.setText(String.valueOf(contrat.getSalaire()));

            // Set the GridPane as the graphic of the cell
            setGraphic(gridPane);
        }
    }
}