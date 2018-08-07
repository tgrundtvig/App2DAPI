/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dapi.graphics;

/**
 *
 * @author tog
 */
public interface ColorFactory
{
    public Color newColor(float r, float g, float b, float a);
    public Color newColor(float r, float g, float b);
    public Color newColorInt(int r, int g, int b, int a);
    public Color newColorInt(int r, int g, int b);
    
    public Color getWhite();
    public Color getBlack();
    public Color getRed();
    public Color getGreen();
    public Color getBlue();
    public Color getYellow();
}
