package edu.cs3500.spreadsheets.model;

import java.util.Map;

import edu.cs3500.spreadsheets.model.formula.value.Value;

/**
 * A Model interface to represents Worksheet collect the all concrete class together to easily
 * managed. It will be built as the application progressively using the classic
 * Model-View-Controller architecture.
 */
public interface WorksheetModel {
  /**
   * Evaluate the target cell to a Value.
   *
   * <p>Different formula have different behavior to evaluate the cell.</p>
   * <ls>
   * <li>Function: calculate the actual value that the function represents.</li>
   * <li>Value: return the value that the target cell represents.</li>
   * </ls>
   *
   * <p>Cells that contain values just evaluate to themselves. a formula can take as inputs
   * references to other cells (or groups of cells). Evaluating a formula therefore may require
   * evaluating other formulas as well.</p>
   *
   * @param coord the coordinate of the given cell
   * @return {@link Value} which is the value of this cell represents
   * @throws IllegalArgumentException if coord is null
   * @throws IllegalArgumentException if some reference is mal-formed
   */
  Value evaluate(Coord coord);


  /**
   * Get the content that the {@code coord} represents in this {@code WorksheetModel}.
   *
   * @param coord the coordinate of the given cell
   * @return T which is the type of the single cell can represent
   * @throws IllegalArgumentException if coord is null
   */
  ICell getContentAt(Coord coord);

  /**
   * Update a given formula to a given coord. If the coordinate doesn't exist on the map, then
   * create a new entry to represent this update.
   *
   * <p> If the Cell is mal-formed, it will be updated as "#ERROR!" {@code ValueString}</p>
   *
   * @param formula the cell that will be updated
   * @param coord   the target coordinate that will be updated to
   * @throws CircularReferenceException if after this update, there will form a circular reference
   *                                    somewhere
   */
  void update(ICell formula, Coord coord) throws CircularReferenceException;
  //this style error is a false positive, please give the points back

  /**
   * Update a given String to a given coord. If the coordinate doesn't exist on the map, then create
   * a new entry to represent this update. If the String cannot be parsed as a {@code Formula}, it
   * will represented as "#ERROR!".
   *
   * <p> If the Cell is mal-formed, it will be updated as "#ERROR!" {@code ValueString}</p>
   *
   * @param input the input String which should be parsed first
   * @param coord the target coordinate that will be updated to
   * @throws CircularReferenceException if after this update, there will form a circular reference
   *                                    somewhere
   */
  void update(String input, Coord coord) throws CircularReferenceException;

  /**
   * Delete the formula on the given coordinate. If there is no Formula at the given coord, nothing
   * happened. This is to delete the whole entry instead of setting the value of the given key
   * null.
   *
   * @param coord the coord that will be delete.
   * @throws IllegalArgumentException if coord is null
   */
  void deleteContent(Coord coord);

  /**
   * return the whole map including Cood and Cell to represents spreadsheet.
   *
   * @return a copy of the map to represents the whole worksheet
   */
  Map<Coord, ICell> getWorksheet();

  /**
   * Get the row number of the worksheet. How many rows are in the worksheet.
   *
   * @return row number of the worksheet (1-index)
   */
  int getRowNum();

  /**
   * Get the column number of the worksheet. How many columns are in the worksheet.
   *
   * @return column number of the worksheet (1-index)
   */
  int getColNum();

}
