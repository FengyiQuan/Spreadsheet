package edu.cs3500.spreadsheets.model.formula;


import java.util.List;
import java.util.Stack;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetModel;
import edu.cs3500.spreadsheets.model.formula.value.Value;

/**
 * The {@code Formula} represents the content of the single cell might contains.
 * <p>Formula is one of:</p>
 * <li>{@link Value}: represents the direct type of content to show on the cell</li>
 * <li>Function: represents the function of one or more cells</li>
 * <li>Reference: represents the one or more reference of content to show on the cell</li>
 *
 * <p>No formula is permitted to refer to itself, though, either directly or indirectly, since that
 * would lead to an infinite regress.</p>
 */
public interface Formula {
  /**
   * Evaluate the target Formula to a Value.
   *
   * <p>Different formula have different behavior to evaluate the cell.</p>
   * <ls>
   * <li>Function: calculate the actual value that the function represents.</li>
   * <li>Value: return the value that the target cell represents.</li>
   * </ls>
   *
   * @param worksheet the whole map to represent the spreadsheet
   * @return {@link Value} which is the value of this cell represents
   */
  Value evaluate(WorksheetModel worksheet);


  /**
   * Flatten this formula to a list of single cell.
   * <p>Different formula have different behavior to evaluate the cell.</p>
   * <ls>
   * <li>Function and Value: return a list that only contains itself
   * <li>Reference: return a list of reference that contains all its referred cells. A list of
   * single reference.</li>
   * </ls>
   *
   * @return a list of formula that this formula contain
   */
  List<Formula> flatten();

  /**
   * Helper function to indicate whether this formula have circular reference.
   *
   * @param seen      a stack to represents all visited Cell in worksheet
   * @param worksheet a worksheet model which contains all map to represents coordinates and cell
   * @return true if circular reference
   */
  boolean deepHelp(Stack<Coord> seen, WorksheetModel worksheet);



}
