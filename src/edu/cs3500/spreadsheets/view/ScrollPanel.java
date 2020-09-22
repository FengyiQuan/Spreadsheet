package edu.cs3500.spreadsheets.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.JScrollBar;


/**
 * A Scroll Panel which contains {@link ContentPanel} and two {@link JScrollBar} to represents the
 * scrolling functionality. Adding the AdjustmentListener to ContentPanel to implement this
 * functionality. The scrolling is move one row or column based on the direction.
 */
public class ScrollPanel extends JPanel {
  private final JScrollBar verticalBar;
  private final JScrollBar horizontalBar;
  private final ContentPanel contentPanel;

  /**
   * Constructor for the ScrollPanel which adds two JScrollBar (vertical and horizontal) to it.
   * Setting the AdjustmentListener to the contentPanel. Setting the position of the three component
   * to the certain position on this panel using GridBagLayout.
   *
   * @param contentPanel contentPanel to draw and scroll
   */
  public ScrollPanel(ContentPanel contentPanel) {
    Dimension d = new Dimension(630, 630);
    this.verticalBar = new JScrollBar(JScrollBar.VERTICAL, 0, 0, 0, 50);
    this.horizontalBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 50);
    this.contentPanel = contentPanel;

    AdjustmentListener v = e -> {
      contentPanel.updateVerticalSlide(this.verticalBar.getValue());
      if (this.verticalBar.getValue() == this.verticalBar.getMaximum()) {
        this.verticalBar.setMaximum((this.verticalBar.getMaximum() + 1));
      }
      this.paintGraph();
    };

    AdjustmentListener h = e -> {
      contentPanel.updateHorizontalSlide(this.horizontalBar.getValue());
      if (this.horizontalBar.getValue() == this.horizontalBar.getMaximum()) {
        this.horizontalBar.setMaximum(this.horizontalBar.getMaximum() + 1);
      }
      this.paintGraph();
    };

    MouseWheelListener wheel = e -> {
      int notches = e.getWheelRotation();
      // Mouse wheel moved UP
      if (notches < 0) {
        if (ScrollPanel.this.contentPanel.getVerticalSlide() > 0) {
          contentPanel.updateVerticalSlide(contentPanel.getVerticalSlide() - 1);
          verticalBar.setValue(verticalBar.getValue() - 1);
        }
      }
      // Mouse wheel moved DOWN
      else if (notches > 0) {
        contentPanel.updateVerticalSlide(contentPanel.getVerticalSlide() + 1);
        verticalBar.setValue(verticalBar.getValue() + 1);
      }
      paintGraph();
    };


    this.addMouseWheelListener(wheel);
    this.verticalBar.addAdjustmentListener(v);
    this.horizontalBar.addAdjustmentListener(h);

    GridBagLayout gridBagLayout = new GridBagLayout();
    GridBagConstraints constraints = new GridBagConstraints();

    setLayout(gridBagLayout);
    contentPanel.setPreferredSize(d);
    this.verticalBar.setPreferredSize(new Dimension(10, 630));
    this.horizontalBar.setPreferredSize(new Dimension(630, 10));

    constraints.gridwidth = GridBagConstraints.REMAINDER;
    gridBagLayout.setConstraints(this.verticalBar, constraints);
    gridBagLayout.setConstraints(this.horizontalBar, constraints);

    this.add(contentPanel);
    this.add(this.verticalBar);
    this.add(this.horizontalBar);
  }


  private void paintGraph() {
    contentPanel.revalidate();
    contentPanel.repaint();
  }
}