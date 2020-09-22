package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


import edu.cs3500.spreadsheets.controler.listener.ButtonListener;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * A WorksheetTextualView class which write given model information into the file when calling
 * render. The file produced does not need to be identical to the input file (ie cells can be
 * assigned in different orders, etc), but it is able to create a model identical to the one it was
 * produced from.
 *
 * <p>The output does not  reproduce any comments or specific formatting that was in the original
 * file, and does not  print the cells back in the same order that they were in the original
 * file.</p>
 */
public class WorksheetTextualView implements WorksheetView {
  private final ViewModel model;
  private PrintWriter out;

  /**
   * Default constructor for WorksheetTextualView.
   *
   * @param model a read-only model represents the information should be drawn
   * @param out   PrintWriter where to write the file
   */
  public WorksheetTextualView(ViewModel model, PrintWriter out) {
    this.model = model;
    this.out = out;
  }

  /**
   * Convenient constructor: takes in a string to indicate the path, not the whole PrintWriter.
   *
   * @param model a read-only model represents the information should be drawn
   * @param path  string where the target file is to write on
   */
  public WorksheetTextualView(ViewModel model, String path) {
    this.model = model;
    try {
      this.out = new PrintWriter(path);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found. ");
    }
  }

  /**
   * Convenient constructor: only takes in a model, default PrintWriter path is "output.txt".
   *
   * @param model a read-only model represents the information should be drawn
   */
  public WorksheetTextualView(ViewModel model) {
    this.model = model;
    try {
      this.out = new PrintWriter("output.txt");
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found. ");
    }
  }


  @Override
  public void render() {
    out.write(toString());
    out.close();
  }

  @Override
  public Coord getSelect() {
    throw new UnsupportedOperationException("Not apply for this class. ");
  }

  @Override
  public String getInputString() {
    throw new UnsupportedOperationException("Not apply for this class. ");
  }

  @Override
  public void setListeners(ActionListener clicks) {
    throw new UnsupportedOperationException("Not apply for this class. ");
  }

  @Override
  public void makeVisible() {
    throw new UnsupportedOperationException("Not apply for this class. ");
  }

  @Override
  public void dispose() {
    throw new UnsupportedOperationException("Not apply for this class. ");
  }

  @Override
  public void showErrorMessage(String error) {
    throw new UnsupportedOperationException("Not apply for this class. ");
  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException("Not apply for this class. ");
  }

  @Override
  public void rejectChange() {
    throw new UnsupportedOperationException("Not apply for this class. ");
  }

  @Override
  public String loadFile() {
    throw new UnsupportedOperationException("Not apply for this class. ");
  }


  @Override
  public String toString() {
    StringBuilder res = new StringBuilder();

    for (Object cell : model.getWorksheet().values()) {
      res.append(cell.toString()).append("\n");
    }
    return res.toString();
  }

  @Override
  public String saveFile() {
    throw new UnsupportedOperationException("Not apply for this class. ");
  }

  @Override
  public void addKeyListener(KeyListener kbd) {
    throw new UnsupportedOperationException("Not apply for this class. ");

  }


  @Override
  public void moveRight() {
    throw new UnsupportedOperationException("Not apply for this class. ");
  }

  @Override
  public void moveLeft() {
    throw new UnsupportedOperationException("Not apply for this class. ");
  }

  @Override
  public void moveUp() {
    throw new UnsupportedOperationException("Not apply for this class. ");
  }

  @Override
  public void moveDown() {
    throw new UnsupportedOperationException("Not apply for this class. ");
  }

  @Override
  public void addActionListener(ButtonListener buttonListener) {
    throw new UnsupportedOperationException("Not apply for this class. ");
  }



}
