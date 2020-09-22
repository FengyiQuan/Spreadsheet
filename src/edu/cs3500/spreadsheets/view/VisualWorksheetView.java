package edu.cs3500.spreadsheets.view;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.cs3500.spreadsheets.controler.listener.ButtonListener;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * A Visual Worksheet View to represents a View of Worksheet to visualize using swing.
 *
 * <p>The first row of the worksheet is the column index which represents as the following
 * pattern:</p>
 *
 * <ls>
 * <li>Columns 1 through 26 get names A through Z</li>
 * <li>Columns 27 through 52 get names AA through AZ</li>
 * <li>Columns 53 through 78 get names BA through BZ ...</li>
 * <li>Column 703 gets name AAA ...</li>
 * </ls>
 *
 * <p>The first column of the worksheet is the row index which represents as an integer.</p>
 * <p>The most upper left cell on the worksheet is always empty.</p>
 * <p>This view would draw the cell up to the most greatest column number and row number in the
 * worksheet.</p>
 */
public class VisualWorksheetView extends JFrame implements WorksheetView {
  private static final int BACKGROUND_WIDTH = 1000;
  private static final int BACKGROUND_HEIGHT = 1000;

  private final ViewModel model;
  private final JPanel panel;
  private final GridBagLayout gridBagLayout = new GridBagLayout();

  private Dimension dimension;

  /**
   * Construct a VisualWorksheetView class to visualize the worksheet.
   *
   * @param model a read-only model which represent the information that should be drawn
   */
  public VisualWorksheetView(ViewModel model) {
    this.model = model;
    this.dimension = new Dimension(800, 800);

    setTitle("Visual Worksheet View");
    setPreferredSize(new Dimension(BACKGROUND_WIDTH, BACKGROUND_HEIGHT));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // a panel to hold all components representing single cell
    panel = (new ScrollPanel(new ContentPanel(model)));
    this.add(panel);
    // a scroll panel to hold the panel and this scroll panel is directly on the JFrame only.
    panel.setPreferredSize(dimension);
    //JScrollPane scrollPane = new JScrollPane(panel);
    //scrollPane.setPreferredSize(new Dimension(300, 300));
    //getContentPane().add(scrollPane);
    pack();
    setVisible(true);
  }

  @Override
  public void render() {
    this.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        return;

      }

      //Handle mouse events.
      public void mouseReleased(MouseEvent e) {
        //Update client's preferred size because
        //the area taken up by the graphics has
        //gotten larger or smaller (if cleared).
        int height = dimension.height;
        int width = dimension.width;
        if (e.getX() > 0 && e.getY() > 0) {
          dimension = new Dimension(width + 700, height + 700);
          panel.setPreferredSize(dimension);

          //Let the scroll pane know to update itself
          //and its scrollbars.
          panel.revalidate();
          panel.repaint();
        }
      }

      @Override
      public void mousePressed(MouseEvent e) {
        return;
      }


      @Override
      public void mouseEntered(MouseEvent e) {
        return;
      }

      @Override
      public void mouseExited(MouseEvent e) {
        return;
      }
    });


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
  public String saveFile() {
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
