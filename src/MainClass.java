/**
 * Created by Deb Rezanka on 9/30/2016.
 * Based on the MoleculeSampleApp by Oracle
 * Copyright (c) 2013, 2014 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 */
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class MainClass extends Application
{
  private Group root = new Group();
  private Group subRoot = new Group();
  private Scene scene;
  private SubScene subScene;

  private Controls controls = new Controls();
  private GridPane grid;
  private Cell[][][] cellArray = new Cell[SIZE][SIZE][SIZE];
  private final Xform axisGroup = new Xform();
  private final Xform organism = new Xform();
  private final Xform world = new Xform();
  private final PerspectiveCamera camera = new PerspectiveCamera(true);
  private final Xform cameraXform = new Xform();
  private final Xform cameraXform2 = new Xform();
  private final Xform cameraXform3 = new Xform();
  private static final double CAMERA_INITIAL_DISTANCE = -450;
  private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
  private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
  private static final double CAMERA_NEAR_CLIP = 0.1;
  private static final double CAMERA_FAR_CLIP = 10000.0;
  private static final double AXIS_LENGTH = 250.0;
  private static final double CONTROL_MULTIPLIER = 0.1;
  private static final double SHIFT_MULTIPLIER = 10.0;
  private static final double MOUSE_SPEED = 0.1;
  private static final double ROTATION_SPEED = 2.0;
  private static final double TRACK_SPEED = 0.3;
  private static final int SIZE = 10;

  private double mousePosX;
  private double mousePosY;
  private double mouseOldX;
  private double mouseOldY;
  private double mouseDeltaX;
  private double mouseDeltaY;

  @Override
  public void start(Stage primaryStage)
  {
    System.out.println("start()");
    scene = new Scene(root, 1000, 1000, true);
    subScene = new SubScene(subRoot, 800, 800, true, SceneAntialiasing.BALANCED);
    buildCamera();
    subScene.setCamera(camera);
    root.getChildren().add(controls.getGrid());
    root.getChildren().add(subScene);
    subRoot.setDepthTest(DepthTest.ENABLE);
    subRoot.getChildren().add(world);

    buildAxes();
    buildOrganism();

    handleKeyboard(scene, subRoot);
    handleMouse(scene, subRoot);
    primaryStage.setTitle("Game of Life By Deb Rezanka");
    primaryStage.setScene(scene);
    primaryStage.show();
  }


  private void buildCamera()
  {
    System.out.println("buildCamera()");
    subRoot.getChildren().add(cameraXform);
    cameraXform.getChildren().add(cameraXform2);
    cameraXform2.getChildren().add(cameraXform3);
    cameraXform3.getChildren().add(camera);
    cameraXform3.setRotateZ(180.0);


    camera.setNearClip(CAMERA_NEAR_CLIP);
    camera.setFarClip(CAMERA_FAR_CLIP);
    camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
    cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
    cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);

  }

  private void buildAxes() {
    System.out.println("buildAxes()");
    final PhongMaterial redMaterial = new PhongMaterial();
    redMaterial.setDiffuseColor(Color.DARKRED);
    redMaterial.setSpecularColor(Color.RED);

    final PhongMaterial greenMaterial = new PhongMaterial();
    greenMaterial.setDiffuseColor(Color.DARKGREEN);
    greenMaterial.setSpecularColor(Color.GREEN);

    final PhongMaterial blueMaterial = new PhongMaterial();
    blueMaterial.setDiffuseColor(Color.DARKBLUE);
    blueMaterial.setSpecularColor(Color.BLUE);

    final Box xAxis = new Box(AXIS_LENGTH, 1, 1);
    final Box yAxis = new Box(1, AXIS_LENGTH, 1);
    final Box zAxis = new Box(1, 1, AXIS_LENGTH);

    xAxis.setMaterial(redMaterial);
    yAxis.setMaterial(greenMaterial);
    zAxis.setMaterial(blueMaterial);

    axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
    axisGroup.setVisible(true);
    axisGroup.setTranslate(400, 400, 0.0);
    world.getChildren().addAll(axisGroup);
  }

  private void buildOrganism()
  {
    for(int x = 0; x < SIZE; x++)
    {
      for (int y = 0; y < SIZE; y++)
      {
        for (int z = 0; z < SIZE; z++)
        {
          cellArray[x][y][z] = new Cell((double) x, (double) y, (double) z);
          organism.getChildren().add(cellArray[x][y][z].getCell());
        }
      }
    }
    organism.setVisible(true);
    organism.setTranslate(400, 400, 0.0);
    world.getChildren().add(organism);
  }
  private void handleMouse(Scene scene, final Node root) {
    scene.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent me) {
        mousePosX = me.getSceneX();
        mousePosY = me.getSceneY();
        mouseOldX = me.getSceneX();
        mouseOldY = me.getSceneY();
      }
    });
    world.setOnMouseDragged(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent me) {
        mouseOldX = mousePosX;
        mouseOldY = mousePosY;
        mousePosX = me.getSceneX();
        mousePosY = me.getSceneY();
        mouseDeltaX = (mousePosX - mouseOldX);
        mouseDeltaY = (mousePosY - mouseOldY);

        double modifier = 1.0;

        if (me.isControlDown()) {
          modifier = CONTROL_MULTIPLIER;
        }
        if (me.isShiftDown()) {
          modifier = SHIFT_MULTIPLIER;
        }
        if (me.isPrimaryButtonDown()) {
          cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX*MOUSE_SPEED*modifier*ROTATION_SPEED);
          cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY*MOUSE_SPEED*modifier*ROTATION_SPEED);
        }
        else if (me.isSecondaryButtonDown()) {
          double z = camera.getTranslateZ();
          double newZ = z + mouseDeltaX*MOUSE_SPEED*modifier;
          camera.setTranslateZ(newZ);
        }
        else if (me.isMiddleButtonDown()) {
          cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX*MOUSE_SPEED*modifier*TRACK_SPEED);
          cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY*MOUSE_SPEED*modifier*TRACK_SPEED);
        }
      }
    });
  }

  private void handleKeyboard(Scene scene, final Node root) {
    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case Z:
            cameraXform2.t.setX(0.0);
            cameraXform2.t.setY(0.0);
            camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
            cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
            cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
            break;
          case X:
            axisGroup.setVisible(!axisGroup.isVisible());
            break;
          case V:
            organism.setVisible(!organism.isVisible());
            break;
        }
      }
    });
  }
  /**
   * The main() method is ignored in correctly deployed JavaFX application.
   * main() serves only as fallback in case the application can not be
   * launched through deployment artifacts, e.g., in IDEs with limited FX
   * support. NetBeans ignores main().
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}
