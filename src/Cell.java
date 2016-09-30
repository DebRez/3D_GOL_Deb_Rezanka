/**
 * Created by Deb Rezanka on 9/30/2016.
 */
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Cell
{
    //physical position in 3D grid
    private double xPos;
    private double yPos;
    private double zPos;
    //position in cellArray
    private int x;
    private int y;
    private int z;
    private int aliveForXSeconds;
    private int alive;
    private boolean isAlive;
    private boolean justDied;
    private PhongMaterial redMaterial = new PhongMaterial();
    private Box cell;

    public Cell(double x, double y, double z)
    {
      this.setxPos(x);
      this.setyPos(y);
      this.setzPos(z);
      this.x = (int) x;
      this.y = (int) y;
      this.z = (int) z;
      this.alive = 0;
      this.aliveForXSeconds = 0;
      this.isAlive = false;
      this.justDied = false;
      this.cell = new Box(100, 100, 100);
      this.cell.setTranslateX(x);
      this.cell.setTranslateY(y);
      this.cell.setTranslateZ(z);

      if(!isAlive())
      {
        this.cell.setMaterial(redMaterial);
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);
      }
    }

    public int getAlive()
    {
      return this.alive;
    }
    public void setAlive(int val)
    {
      this.alive = val;
    }
    public void resetAliveForXSeconds()
    {
      this.aliveForXSeconds = 0;
    }
    public void addAliveForXSeconds()
    {
      this.aliveForXSeconds += 1;
    }

    public int getAliveForXSeconds()
    {
      return this.aliveForXSeconds;
    }

    public void setJustDied(boolean value)
    {
      this.justDied = value;
    }
    public boolean getJustDied()
    {
      return this.justDied;
    }
    public double getxPos()
    {
      return xPos;
    }

    public void setxPos(double xPos)
    {
      this.xPos = xPos;
    }

    public double getyPos()
    {
      return yPos;
    }

    public void setyPos(double yPos)
    {
      this.yPos = yPos;
    }

    public double getzPos()
    {
      return zPos;
    }

    public void setzPos(double zPos)
    {
      this.zPos = zPos;
    }


    public boolean isAlive()
    {
      return isAlive;
    }

    public void setAlive(boolean value)
    {
      isAlive = value;
    }


    //position in array, not in 3D grid
    public void setCell(Box cell)
    {
      this.cell = cell;
    }
    public Box getCell()
    {
      return cell;
    }
    public int getX()
    {
      return this.x;
    }
    public int getY()
    {
      return this.y;
    }
    public int getZ()
    {
      return this.z;
    }
  }


