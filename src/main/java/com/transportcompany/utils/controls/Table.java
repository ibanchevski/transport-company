package com.transportcompany.utils.controls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Table {
    private String heading;
    private ArrayList<ArrayList<String>> rows;
    private Map<Integer, Integer> columnCellWidth;

    public Table(String heading) {
        this.heading = heading;
        this.rows = new ArrayList<>();
        this.columnCellWidth = new HashMap<>();
    }

    public void addRow(ArrayList<String> cells) {
        this.rows.add(cells);
    }

    public void display() {
        System.out.println(this.heading);
        this._calculateColumnsWidth();
        for (ArrayList<String> row : rows) {
            String tableRow = this._generateRow(row);
            // Print separator before headings
            if (rows.indexOf(row) == 0) {
                String rowSep = this._generateDelimiter("-", ((tableRow.length() - this.heading.length()) / 2) - 2, "-");
                System.out.println("," + rowSep + " " + this.heading + " " + rowSep + ".");
            }

            // Print the current row
            System.out.println("| " + tableRow);

            // Generate separator for the headings
            if (rows.indexOf(row) == 0) {
                String rowSep = "|" + this._generateDelimiter("-", tableRow.length() - 1, "-") + "|";
                System.out.println(rowSep);
            }

            // Generate separator for the end of the table
            if (rows.indexOf(row) == rows.size() - 1) {
                String rowSep = "'" + this._generateDelimiter("-", tableRow.length() - 1, "-") + "'";
                System.out.println(rowSep);
            }
        }
    }

    private String _generateRow(ArrayList<String> cells) {
        String row = "";
        for (int j = 0; j < cells.size(); j++) {
            String cell = cells.get(j);
            String del = this._generateDelimiter(cell, this.columnCellWidth.get(j), " ");
            row += cell + del + "| ";
        }
        return row;
    }
    private void _calculateColumnsWidth() {
        for (int i = 0; i < this.rows.get(0).size(); i++) {
            // For each column
            int widestLen = this._calculateWidestColumn(i);
            this.columnCellWidth.put(i, widestLen);
        }
    }

    private int _calculateWidestColumn(int nthColumn) {
        int widest = 0;
        for (ArrayList<String> row : this.rows) {
            if (row.get(nthColumn).length() > widest) {
                widest = row.get(nthColumn).length();
            }
        }
        return widest;
    }

    private String _generateDelimiter(String cell, int maxWidth, String delimiter) {
        String del = delimiter;
        int width = maxWidth - cell.length();
        for (int i = 0; i < width; i++) {
            del += delimiter;
        }
        return del;
    }
}
