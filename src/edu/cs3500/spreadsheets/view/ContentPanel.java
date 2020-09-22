package edu.cs3500.spreadsheets.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetModel;

/**
 * ContentPanel which visualize all contents/information of a the model. It represents a rectangle
 * area of the content (from evaluate) based on the {@code horizontalSlide, verticalSlide} which
 * represents the upper-left coordinate to represent. This panel can be scrolling down and right
 * infinitely to show the region of that area. This panel is extending {@code JPanel} to be used as
 * the content represent for the {@link ScrollPanel}.
 *
 * <p>The index of the column and row are represents as grey cell; The content are represents as
 * the white cell; and the selected cell is represented as the red rectangle around that cell
 * boarder.</p>
 *
 * <p>Rewrite the {@code paintComponent} methods from super class to easily generate the view of
 * the model and call repaint every time when the model has been changed.</p>
 */
public class ContentPanel extends JPanel {
  private static final int CELL_WIDTH = 70;
  private static final int CELL_HEIGHT = 30;
  private static final int STRING_LENGTH = 9;
  private static final int LENGTH = 20;
  private static final int WIDTH = 8;

  private final WorksheetModel model;
  // represents the upper-left x coordinate
  private int horizontalSlide = 0;
  // represents the upper-left y coordinate
  private int verticalSlide = 0;
  private Coord selected = null;


  /**
   * Constructor for the ContentPanel.
   *
   * @param model use the VieModel as a reference to draw the information.
   */
  public ContentPanel(WorksheetModel model) {
    this.model = model;
    this.revalidate();
    this.repaint();
  }


  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D graph2D = (Graphics2D) g;

    for (int r = 0; r <= LENGTH; r++) {
      for (int c = 0; c <= WIDTH; c++) {

        // draw all border of the each cell
        graph2D.drawRect(CELL_WIDTH * c, CELL_HEIGHT * r, CELL_WIDTH, CELL_HEIGHT);
        // draw the most upper-left corner, empty cell
        if (c == 0 && r == 0) {
          graph2D.setColor(Color.GRAY);
          graph2D.fillRect(CELL_WIDTH * c, CELL_HEIGHT * r, CELL_WIDTH, CELL_HEIGHT);
          graph2D.setColor(Color.black);
        }
        // draw row index
        else if (c == 0 && (r + verticalSlide) > 0) {
          graph2D.setColor(Color.GRAY);
          graph2D.fillRect(CELL_WIDTH * c, CELL_HEIGHT * r, CELL_WIDTH, CELL_HEIGHT);
          graph2D.setColor(Color.black);
          graph2D.drawString(Integer.toString(r + verticalSlide),
                  CELL_WIDTH * c + 5, CELL_HEIGHT * r + 28);
        }
        // draw col index
        else if ((c + horizontalSlide) > 0 && r == 0) {
          graph2D.setColor(Color.GRAY);
          graph2D.fillRect(CELL_WIDTH * c, CELL_HEIGHT * r, CELL_WIDTH, CELL_HEIGHT);
          graph2D.setColor(Color.black);
          graph2D.drawString(Coord.colIndexToName(c + horizontalSlide),
                  CELL_WIDTH * c + 5, CELL_HEIGHT * r + 28);
        }
        // draw content
        else {
          if ((c + horizontalSlide) > 0 && (r + verticalSlide) > 0
              && model.getContentAt(new Coord((c + horizontalSlide), (r + verticalSlide)))
                 != null) {
            String s = (model.getContentAt(new Coord((c + horizontalSlide), (r + verticalSlide))))
                    .getActualValue(model).toString();
            graph2D.drawString(cutString(s), CELL_WIDTH * c, CELL_HEIGHT * (r + 1) - 5);
          }

          // draw the highlighted border
          if (selected != null) {
            int selectCol = selected.col - horizontalSlide;
            int selectRow = selected.row - verticalSlide;
            if (selectCol != 0 && selectRow != 0) {
              graph2D.setColor(Color.RED);
              graph2D.drawRect(
                      CELL_WIDTH * selectCol, CELL_HEIGHT * selectRow, CELL_WIDTH, CELL_HEIGHT);
              graph2D.setColor(Color.black);
            }
          }
        }
      }
    }
  }

  /**
   * Updates the field horizontalSlide. if the horizontalSlide is 0, then we draw from Col index 1.
   * if the horizontalSlide is x, then we draw from Col index X + 1.
   *
   * @param horizontalSlide indicates the first Col we see in the contentPanel.
   */
  public void updateHorizontalSlide(int horizontalSlide) {
    this.horizontalSlide = horizontalSlide;
  }

  /**
   * updates the field verticalSlide . if the verticalSlide is 0, then we draw from row index 1. if
   * the verticalSlide is x, then we draw from row index X + 1.
   *
   * @param verticalSlide indicates the first row we see in the contentPanel.
   */
  public void updateVerticalSlide(int verticalSlide) {
    this.verticalSlide = verticalSlide;
  }


  /**
   * if the input string length is greater than STRING_LENGTH then we cut from the back. so that it
   * has length less or equal than STRING_LENGTH.
   *
   * @param s the input string that will be cut if needed
   * @return a string with length less or equal than STRING_LENGTH
   */
  private static String cutString(String s) {
    if (s.length() > STRING_LENGTH) {
      return s.substring(0, STRING_LENGTH);
    } else {
      return s;
    }
  }

  /**
   * Replace the current selected coord stored in the field with the input coord.
   *
   * @param coord a new selected coord
   */
  public void updateSelected(Coord coord) {
    selected = coord;
    revalidate();
    repaint();
  }

  /**
   * If the there is no selected coord return null, otherwise return a copy of the coord.
   *
   * @return the current select coord
   */
  public Coord getSelected() {
    if (selected == null) {
      return null;
    } else {
      return new Coord(selected.col, selected.row);
    }
  }

  /**
   * Getter methods to get the {@code horizontalSlide}.
   *
   * @return horizontalSlide
   */
  public int getHorizontalSlide() {
    return horizontalSlide;
  }

  /**
   * Getter methods to get the {@code verticalSlide}.
   *
   * @return verticalSlide
   */
  public int getVerticalSlide() {
    return verticalSlide;
  }

  /**
   * get the content at the selected coordinate.
   *
   * @return string format of formula of the selected coordinate
   */
  public String getContentAtSelect() {
    if (selected != null && model.getContentAt(selected) != null) {
      String coordWithFormula = model.getContentAt(selected).toString();
      int coord = selected.toString().length();
      return coordWithFormula.substring(coord + 1);
    } else {
      return "";
    }
  }
}