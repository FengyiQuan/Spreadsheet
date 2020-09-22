package edu.cs3500.spreadsheets.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.cs3500.spreadsheets.controler.listener.ButtonListener;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.WorksheetModel;

/**
 * Editable View class which supports selecting cell by click and arrow key, infinite scrolling,
 * etc. It use {@code GridBagLayout} to set the position of the the JComponent.
 *
 * <p>The view should looks like there are four buttons: save, load, √, X from left to right. And
 * there is a text field after them to show the information fo the selected cell. "save" button is
 * to save the current model to the target input path when click on it. It will shows a Input Dialog
 * when click it to allow user to enter a path. "load" button is to load a specific file to the
 * model when click on it. It will shows a Input Dialog when click it to allow user to enter a path
 * as well. "√" button is to confirm to change the formula for the selected cell. "X" button is to
 * reject to change whatever the textfield is back to the original string format of the
 * formula.</p>
 *
 * <p>It can indicate which cell selected; displaying the full formula for that cell somewhere
 * editable; scrolling infinitely right or down. Show the GUI view</p>
 *
 * <p>Command line argument indicator: -edit</p>
 */
public class EditView extends JFrame implements WorksheetView {
  private static final int BACKGROUND_WIDTH = 1000;
  private static final int BACKGROUND_HEIGHT = 1000;
  private static final int CELL_WIDTH = 70;
  private static final int CELL_HEIGHT = 30;

  private final GridBagLayout gridBagLayout = new GridBagLayout();
  private final GridBagConstraints constraints = new GridBagConstraints();

  private final ContentPanel contentPanel;
  private final ScrollPanel scrollPanel;

  private final JTextField editable;
  private final JButton save;
  private final JButton load;
  private final JButton clear;
  private final JButton confirm;


  /**
   * Constructor for ContentPanel which contains the information to draw.
   *
   * @param contentPanel ContentPanel
   */
  public EditView(ContentPanel contentPanel) {
    this.contentPanel = contentPanel;
    this.scrollPanel = new ScrollPanel(contentPanel);
    this.editable = new JTextField();
    this.save = new JButton("Save");
    this.load = new JButton("Load");
    this.clear = new JButton("X");
    this.confirm = new JButton("√");

    render();
  }


  /**
   * Constructor for EditView which takes in a ViewModel for simply use.
   *
   * @param viewModel viewModel contains information to draw
   */
  public EditView(WorksheetModel viewModel) {
    this(new ContentPanel(viewModel));
  }


  @Override
  public void render() {
    //add a listener to listen which to click
    this.contentPanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e) {
        // calculate the coord
        final int rowIndex = e.getY() / CELL_HEIGHT + contentPanel.getVerticalSlide();
        final int colIndex = e.getX() / CELL_WIDTH + contentPanel.getHorizontalSlide();
        // update the coord and the textField
        if (rowIndex > 0 && colIndex > 0) {
          contentPanel.updateSelected(new Coord(colIndex, rowIndex));
          EditView.this.rejectChange();
          requestFocus();
        }
      }
    });


    setFocusable(true);

    Dimension buttonDimension = new Dimension(50, 50);

    setTitle("Edit Worksheet View");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(BACKGROUND_WIDTH, BACKGROUND_HEIGHT));

    //set preferred size for each component
    scrollPanel.setPreferredSize(new Dimension(640, 640));
    editable.setPreferredSize(new Dimension(440, 50));
    save.setPreferredSize(buttonDimension);
    load.setPreferredSize(buttonDimension);
    clear.setPreferredSize(buttonDimension);
    confirm.setPreferredSize(buttonDimension);

    //set layout
    setLayout(gridBagLayout);

    constraints.gridwidth = GridBagConstraints.REMAINDER;
    gridBagLayout.setConstraints(this.editable, constraints);
    gridBagLayout.setConstraints(this.scrollPanel, constraints);

    // Displaying the full formula
    this.add(load);
    this.add(save);
    this.add(confirm);
    this.add(clear);
    this.add(editable);
    this.add(scrollPanel);

    //add action listener to each button
    load.setActionCommand("Load");
    save.setActionCommand("Save");
    confirm.setActionCommand("Confirm");
    clear.setActionCommand("Reject");

    pack();

  }

  @Override
  public Coord getSelect() {
    return contentPanel.getSelected();
  }

  @Override
  public String getInputString() {
    return editable.getText();
  }


  @Override
  public void setListeners(ActionListener clicks) {
    load.addActionListener(clicks);
    save.addActionListener(clicks);
    confirm.addActionListener(clicks);
    clear.addActionListener(clicks);
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void refresh() {
    this.revalidate();
    this.repaint();
  }

  @Override
  public void rejectChange() {
    String formula;
    try {
      formula = contentPanel.getContentAtSelect();
    } catch (IllegalArgumentException e) {
      return;
    }
    editable.setText(formula);
    refresh();
  }


  @Override
  public String loadFile() {
    return JOptionPane.showInputDialog(null, "Enter the file below: ");
  }

  @Override
  public String saveFile() {
    return JOptionPane.showInputDialog(null, "Save as ...");
  }


  public void moveRight() {
    Coord original = contentPanel.getSelected();
    contentPanel.updateSelected(new Coord(original.col + 1, original.row));
  }

  @Override
  public void moveLeft() {
    Coord original = contentPanel.getSelected();
    if (original.col - 1 > 0) {
      contentPanel.updateSelected(new Coord(original.col - 1, original.row));
    }
  }

  @Override
  public void moveUp() {
    Coord original = contentPanel.getSelected();
    if (original.row - 1 > 0) {
      contentPanel.updateSelected(new Coord(original.col, original.row - 1));
    }
  }

  @Override
  public void moveDown() {
    Coord original = contentPanel.getSelected();
    contentPanel.updateSelected(new Coord(original.col, original.row + 1));

  }

  @Override
  public void addActionListener(ButtonListener buttonListener) {
    load.addActionListener(buttonListener);
    clear.addActionListener(buttonListener);
    confirm.addActionListener(buttonListener);
    save.addActionListener(buttonListener);

  }
}
