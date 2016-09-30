import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by Deb Rezanka on 9/30/2016.
 */
public class Controls
{
  private GridPane grid = new GridPane();
  // values chosen by ChoiceBoxes
  // Choice Box for Starting pattern and r1,r2, r3, r4

  public Number pattern;
  public Number AIfGT;
  public Number AIfLT;
  public Number DIfGT;
  public Number DIfLT;


  public Controls()
  {
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(0, 10, 0, 10));
    ChoiceBox patternCB = new ChoiceBox(observableArrayList("Random", "Pattern One",
            "Pattern Two", "Pattern Three", "Pattern Four", "Pattern Five"));
    patternCB.setValue("Pattern One");
    Text patternT = new Text("Starting Pattern");
    grid.add(patternT, 0, 0);
    grid.add(patternCB, 0, 1);
    patternCB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
    {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
      {
        pattern = observable.getValue();
        System.out.println(pattern.toString());
      }
    });

    ChoiceBox cellAliveIfGT = new ChoiceBox(observableArrayList("1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
            "19", "20", "21", "22", "23", "24", "25"));
    cellAliveIfGT.setValue("3");
    Text cagt = new Text("Cell lives if >=");
    grid.add(cagt, 1, 0);
    grid.add(cellAliveIfGT, 1, 1);
    cellAliveIfGT.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
    {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
      {
        AIfGT = observable.getValue();
      }
    });

    ChoiceBox cellAliveIfLT = new ChoiceBox(observableArrayList("1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
            "19", "20", "21", "22", "23", "24", "25"));
    cellAliveIfLT.setValue("5");
    Text calt = new Text("Cell lives if <=");
    grid.add(calt, 2, 0);
    grid.add(cellAliveIfLT, 2, 1);
    cellAliveIfLT.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
    {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
      {
        AIfLT = observable.getValue();
      }
    });

    ChoiceBox cellDiesIfGT = new ChoiceBox(observableArrayList("1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
            "19", "20", "21", "22", "23", "24", "25"));
    cellDiesIfGT.setValue("10");
    Text cdgt = new Text("Cell dies if >");
    grid.add(cdgt, 3, 0);
    grid.add(cellDiesIfGT, 3, 1);
    cellDiesIfGT.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
    {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
      {
        DIfGT = observable.getValue();
      }
    });

    ChoiceBox cellDiesIfLT = new ChoiceBox(observableArrayList("1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
            "19", "20", "21", "22", "23", "24", "25"));
    cellDiesIfLT.setValue("2");
    Text cdlt = new Text("Cell dies if <");
    grid.add(cdlt, 4, 0);
    grid.add(cellDiesIfLT, 4, 1);
    cellDiesIfLT.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
    {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
      {
        DIfLT = observable.getValue();
      }
    });

  }

  public GridPane getGrid()
  {
    return grid;
  }
  public Number getChosenPattern()
  {
    return pattern;
  }

  public void setChosenPattern(Number chosenPattern)
  {
    this.pattern = chosenPattern;
  }

  public Number getAIfGT()
  {
    return AIfGT;
  }

  public void setAIfGT(Number cellAIfGT)
  {
    this.AIfGT = cellAIfGT;
  }

  public Number getAIfLT()
  {
    return AIfLT;
  }

  public void setAIfLT(Number cellAIfLT)
  {
    this.AIfLT = cellAIfLT;
  }

  public Number getDIfGT()
  {
    return DIfGT;
  }

  public void setDIfGT(Number cellDIfGT)
  {
    this.DIfGT = cellDIfGT;
  }

  public Number getDIfLT()
  {
    return DIfLT;
  }

  public void setDIfLT(Number cellDIfLT)
  {
    this.DIfLT = cellDIfLT;
  }
}


