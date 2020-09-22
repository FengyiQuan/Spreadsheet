package edu.cs3500.spreadsheets.controler;

/**
 * Controller to interact with the user. The entry point to this program. A controller can mutate
 * the model based on the user input and visualize the model via View interface.
 */
public interface WorksheetController {

  /**
   * Entry point which makes the view visible and sets functionality of some features.
   *
   * <p>For the GUIWorksheetController, we have delete key to delete a selected cell, save
   * and load button to save and load file, respectively.</p>
   *
   * <p>To start the program, should call this methods.</p>
   */
  void execute();

}
