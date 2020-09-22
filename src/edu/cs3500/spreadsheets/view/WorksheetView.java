package edu.cs3500.spreadsheets.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import edu.cs3500.spreadsheets.controler.listener.ButtonListener;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetModel;

/**
 * Renders a {@link WorksheetModel} in some manner.
 */
public interface WorksheetView {

  /**
   * Renders a model in some manner (e.g. as text, or as graphics, etc) based on the abstract class
   * type.
   * <ls>
   * <li>{@link WorksheetTextualView}: takes a model and an Appendable, and renders the model into
   * the Appendable in the same format as you read files.</li>
   * <li>{@link VisualWorksheetView}: In this view, it will render the spreadsheet inside a window,
   * drawing a grid of cells and showing their values.</li>
   * <li>{@link EditView}: it can indicate which cell selected; displaying the full formula for
   * that cell somewhere editable; scrolling infinitely right or down. Show the GUI view
   * anywhere.</li>
   * </ls>
   */
  void render();

  /**
   * Getter to get the current position of selected cell.
   *
   * @return the current coord of selected position
   */
  Coord getSelect();


  /**
   * Getter to get the input string from the text field.
   *
   * @return input string of text field
   */
  String getInputString();


  /**
   * Add some action listener to this view's component.
   *
   * @param clicks action lister to add to
   */
  void setListeners(ActionListener clicks);


  /**
   * Make the view visible. This is usually called after the view is constructed.
   */
  void makeVisible();

  /**
   * Causes the JFrame window to be destroyed and cleaned up by the operating system.
   */
  void dispose();


  /**
   * Transmit an error message to the view, in case the input could not be processed correctly.
   *
   * @param error indicate what causes the error
   */
  void showErrorMessage(String error);

  /**
   * Signal to the view to re-draw itself.
   */
  void refresh();

  /**
   * Setting the text field to the certain text (showing the text indicating Formula of the current
   * selected cell in a specific format).
   *
   * <p>When click a cell: the text should be set to the Formula string representing the
   * information of the selected cell.</p>
   *
   * <p>When click "X" button: the text should back to the original string representing the
   * information of the selected cell, omitting whatever the user typed in the text field so
   * far.</p>
   */
  void rejectChange();


  /**
   * Showing a JOptionPane input dialog to let the user type the target loading file path, then
   * passing the path to the control to actually load the file.
   *
   * @return a loading path String
   */
  String loadFile();

  /**
   * Showing a JOptionPane input dialog to let the user type the target saving path, then passing
   * the path to the control to actually save the file.
   *
   * @return a saving path String
   */
  String saveFile();

  /**
   * Add the key listener to the view.
   *
   * @param kbd key listener to be added
   */
  void addKeyListener(KeyListener kbd);

  /**
   * Move the current selected coordinate to the right cell.
   */
  void moveRight();

  /**
   * Move the current selected coordinate to the left cell.
   */
  void moveLeft();

  /**
   * Move the current selected coordinate to the upper cell.
   */
  void moveUp();

  /**
   * Move the current selected coordinate to the bottom cell.
   */
  void moveDown();

  /**
   * Add the button listener to the view.
   *
   * @param buttonListener button listener
   */
  void addActionListener(ButtonListener buttonListener);

}
