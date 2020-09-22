In our BasicWorkSheet, we use Map to represent our worksheet which stores all the Cell and its corresponding coord. 

Each cell will has a formula which is its content, and coord which represents its coordinate, and worksheet which represents the worksheet the cell is mapped in.

The data structure of our Formula is can be represent as follows:


                Formula(interface)
      
     Implements.        Extends.          Implements
  Reference(class).  Value(interface). Function(class) 

                       Implements 
                     ValueBoolean(class)
                     ValueDouble(class)
                     ValueString(class)


To evaluate Cell, we used visitor pattern.

To check validity, we used the concept of graph and implemented DFS to check cyclic. 

We create a new read-only Model passing into the View to render the graphic view.

We have a textual view which write back information to a file and a GUI view to render a GUI view.

Foe GUI view, we use scroll panel to hold a Jpanel. And Jpanel contains several small label each represents the information about a given cell.

We move the cell class outside of the Basicworksheet. And remove the generic type of the WorksheetModel interface. there is no need to do so because every worksheet should contains cell and formula as their contents. We delete some code from ReadFile class to make it more concentrate on reading things and make a new class named CHooseCommand to deal with the user inputs command. 


We use the MVC pattern to get this program. 

Controller to interact with the user. The entry point to this program. A controller can mutate the model based on the user input and visualize the model via View interface. We have a start entry method execute which is the entry point which makes the view visible and sets functionality of some features. Controller takes in a model and a view and adding a key listener to listen for the delete key to delete the selected cell. And have some callback when view passing some action event to the controller, controller will actually response to the model, such that load file, save file, confirm to update cell and reject the change of the cell. When load a file, controller should dispose the previous visible view first, then generate the new view to be visible.

We add a new methods to model interface which is a new overload update methods, taking in an input String and a target Coord. 
Also adding a new mock model to help testing the user input and controller interaction.

View:
We have a new view class which implements all possible functionality to edit the view. (EditView) which includes:

The base panel to show the content is ContentPanel which visualize all contents/information of a the model. It represents a rectangle area of the content (from evaluate) based on the horizontalSlide, verticalSlide which represents the upper-left coordinate to represent. This panel can be scrolling down and right infinitely to show the region of that area. This panel is extending JPanel to be used as the content represent for the ScrollPanel. 

Two scroll bar: horizontal and vertical

A ScrollPanel which extends JPanel has three components, including ContentPanel and two scroll bar. We add the AdjustmentListener and MouseWheelListener on this panel to listen and interact when the scroll bar changed. MouseWheelListener only works on the vertical scroll bar. The content panel only draws the area that shows on the screen, instead of drawing all the possible cells to increase the performance of the program. The drawing area is indicated by the x and y value stored in the content panel. For the ScrollPanel, we use GridBagLayout to set the position of these three component. Content panel and vertical bar is on the same row; and the horizontal bar occupy the bottom row. 

For the Edit View, we have several button to indicate the different functionality. There is a text field after these buttons to indicate the information of the cell formula. 


We create a new button listener and key board listener to listen for the click and key board event based not the user input. These class contains a map which key represents the command String and the value represents the runnable which is the code to execute when the certain event has been called. It is the same as the adding the listener directly on the view instead helping us to test the functionality of the certain behavior when certain event happened.