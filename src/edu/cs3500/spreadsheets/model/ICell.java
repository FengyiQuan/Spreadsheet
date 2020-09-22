package edu.cs3500.spreadsheets.model;

import edu.cs3500.spreadsheets.model.formula.Formula;
import edu.cs3500.spreadsheets.model.formula.value.Value;

/**
 * A single cell that used on the map to represents content and coordinate for this worksheet. Cell
 * is a fundamental unit for worksheet.
 *
 * <p> A WorksheetModel should contains a map of {@link Coord} and {@link Cell} to represents
 * information stored in this worksheet. </p>
 *
 * <p>A cell contains a field named {@code recent} which sets {@code null} initially. It changes
 * when it called {@code getActualValue} to evaluate the value of the given {@link Formula}. This
 * {@code recent} stores the Value of this Cell to increase efficiency to avoid calculating the same
 * {@code Formula} multiple times. </p>
 */
public interface ICell {

  Formula getContent();

  /**
   * Evaluate {@code this} Cell to get a Value. (see evaluate in {@link Formula})
   *
   * @param worksheet a worksheet that should evaluate from
   * @return a {@code Value} that represents the Value of this Cell
   */
  Value getActualValue(WorksheetModel worksheet);

  /**
   * determine if this cell has some circular referring to avoid infinite loop on WorksheetModel.
   *
   * @param worksheet a worksheet that represents whole worksheet
   * @return true if there is circular reference, {@code false} if there is no circular reference
   */
  boolean deepOnCircle(WorksheetModel worksheet);

  /**
   * Set the recent value to null.
   */
  void setRecentNull();

}
