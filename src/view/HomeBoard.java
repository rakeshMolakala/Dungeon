package view;

import controller.GuiFeatures;
import model.WrappingStyle;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Dungeon Home board that takes the input values of the dungeon from the user.
 */
class HomeBoard extends JPanel {

  private final JTextField rowsField;
  private final JTextField columnsField;
  private final JTextField interconnectivityField;
  private final JTextField difficultyField;
  private final JTextField itemsPercentageField;
  private final JRadioButton Wrap;
  private final JButton submit;
  private final JButton exit;

  /**
   * HomeBoard Constructor that makes the GUI prepared.
   */
  HomeBoard() {
    this.setAlignmentX(Component.CENTER_ALIGNMENT);
    setLayout(null);
    JLabel title = new JLabel("Construct the Dungeon");
    title.setFont(new Font("Arial", Font.PLAIN, 25));
    title.setSize(400, 70);
    title.setLocation(300, 30);
    add(title);

    JLabel rows = new JLabel("Enter the number of rows");
    rows.setFont(new Font("Arial", Font.PLAIN, 20));
    rows.setSize(300, 30);
    rows.setLocation(100, 90);
    add(rows);

    rowsField = new JTextField();
    rowsField.setText("6");
    rowsField.setSize(150, 30);
    rowsField.setLocation(500, 90);
    add(rowsField);

    JLabel columns = new JLabel("Enter the number of columns");
    columns.setFont(new Font("Arial", Font.PLAIN, 20));
    columns.setSize(300, 30);
    columns.setLocation(100, 150);
    add(columns);

    columnsField = new JTextField();
    columnsField.setText("7");
    columnsField.setSize(150, 30);
    columnsField.setLocation(500, 150);
    add(columnsField);

    JLabel interconnectivity = new JLabel("Enter the degree of interconnectivity");
    interconnectivity.setFont(new Font("Arial", Font.PLAIN, 20));
    interconnectivity.setSize(350, 30);
    interconnectivity.setLocation(100, 210);
    add(interconnectivity);

    interconnectivityField = new JTextField();
    interconnectivityField.setText("6");
    interconnectivityField.setSize(150, 30);
    interconnectivityField.setLocation(500, 210);
    add(interconnectivityField);

    JLabel wrapping = new JLabel("Wrapping Style");
    wrapping.setFont(new Font("Arial", Font.PLAIN, 20));
    wrapping.setSize(300, 30);
    wrapping.setLocation(100, 260);
    add(wrapping);

    Wrap = new JRadioButton("Wrapping");
    Wrap.setFont(new Font("Arial", Font.PLAIN, 15));
    Wrap.setSelected(false);
    Wrap.setSize(150, 20);
    Wrap.setLocation(500, 260);
    add(Wrap);

    JRadioButton nonWrap = new JRadioButton("Non Wrapping");
    nonWrap.setFont(new Font("Arial", Font.PLAIN, 15));
    nonWrap.setSelected(true);
    nonWrap.setSize(150, 20);
    nonWrap.setLocation(650, 260);
    add(nonWrap);

    ButtonGroup w = new ButtonGroup();
    w.add(Wrap);
    w.add(nonWrap);

    JLabel itemsPercentage = new JLabel("Enter the items percentage");
    itemsPercentage.setFont(new Font("Arial", Font.PLAIN, 20));
    itemsPercentage.setSize(300, 30);
    itemsPercentage.setLocation(100, 320);
    add(itemsPercentage);

    itemsPercentageField = new JTextField();
    itemsPercentageField.setText("70");
    itemsPercentageField.setSize(150, 30);
    itemsPercentageField.setLocation(500, 320);
    add(itemsPercentageField);

    JLabel difficulty = new JLabel("Enter the Difficulty number");
    difficulty.setFont(new Font("Arial", Font.PLAIN, 20));
    difficulty.setSize(300, 30);
    difficulty.setLocation(100, 380);
    add(difficulty);

    difficultyField = new JTextField();
    difficultyField.setText("3");
    difficultyField.setSize(150, 30);
    difficultyField.setLocation(500, 380);
    add(difficultyField);

    submit = new JButton("Submit");
    submit.setFont(new Font("Arial", Font.PLAIN, 20));
    submit.setSize(150, 40);
    submit.setLocation(200, 450);
    add(submit);

    exit = new JButton("Quit");
    exit.setFont(new Font("Arial", Font.PLAIN, 20));
    exit.setSize(150, 40);
    exit.setLocation(400, 450);
    add(exit);
  }

  void setFeaturesInHome(GuiFeatures f) {
    exit.addActionListener(l -> f.exitProgram());
    submit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int rowsValue = 0;
        int columnsValue = 0;
        int interconValue = 0;
        int difficultyValue = 0;
        double itemsValue = 0;
        WrappingStyle w;
        try {
          rowsValue = Integer.parseInt(rowsField.getText());
        } catch (NumberFormatException a1) {
          JOptionPane.showMessageDialog((Component) e.getSource(), "Enter only "
                  + "integer value for Rows");
          return;
        }
        try {
          columnsValue = Integer.parseInt(columnsField.getText());
        } catch (NumberFormatException a2) {
          JOptionPane.showMessageDialog((Component) e.getSource(), "Enter only integer "
                  + "value for Columns");
          return;
        }
        try {
          interconValue = Integer.parseInt(interconnectivityField.getText());
        } catch (NumberFormatException a3) {
          JOptionPane.showMessageDialog((Component) e.getSource(), "Enter only integer "
                  + "value for Interconnectivity degree");
          return;
        }
        try {
          difficultyValue = Integer.parseInt(difficultyField.getText());
        } catch (NumberFormatException a4) {
          JOptionPane.showMessageDialog((Component) e.getSource(), "Enter only integer "
                  + "values for Difficulty");
          return;
        }
        try {
          itemsValue = Double.parseDouble(itemsPercentageField.getText());
        } catch (NumberFormatException a5) {
          JOptionPane.showMessageDialog((Component) e.getSource(), "Enter valid "
                  + "percentage value for Items");
          return;
        }
        if (Wrap.isSelected()) {
          w = WrappingStyle.WRAPPING;
        } else {
          w = WrappingStyle.NONWRAPPING;
        }
        f.createDungeon(w, interconValue, rowsValue, columnsValue, itemsValue, difficultyValue);
      }
    });
  }
}
