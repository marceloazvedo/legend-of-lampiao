package br.com.lol.gerenciadores;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.event.MouseInputListener;

public class InputManager implements KeyListener, MouseInputListener
{
    static protected int KEY_RELEASED = 0;
    static protected int KEY_JUST_PRESSED = 1;
    static protected int KEY_PRESSED = 2;
    static protected int KEY_TYPED = 3;
    static private InputManager instance;
    private HashMap<Integer, Integer> keyCache;
    private ArrayList<Integer> pressedKeys;
    private ArrayList<Integer> releasedKeys;
    private ArrayList<Integer> typedKeys;
    
    private boolean mouseButton1;
    private boolean mouseButton2;
    private Point mousePos;

    private InputManager()
    {
        keyCache = new HashMap<Integer, Integer>();
        pressedKeys = new ArrayList<Integer>();
        releasedKeys = new ArrayList<Integer>();
        typedKeys = new ArrayList<Integer>();
        mousePos = new Point();
    }

    static public InputManager getInstance()
    {
        if (instance == null)
        {
            instance = new InputManager();
        }
        return instance;
    }

    public boolean isPressed(int keyId)
    {
        return keyCache.containsKey(keyId)
                && !keyCache.get(keyId).equals(KEY_RELEASED);
    }
    
    public boolean isTyped(int keyId){
    	return keyCache.containsKey(keyId) 
    			&& !keyCache.get(keyId).equals(KEY_RELEASED);
    }

    public boolean isJustPressed(int keyId)
    {
        return keyCache.containsKey(keyId)
                && keyCache.get(keyId).equals(KEY_JUST_PRESSED);
    }

    public boolean isReleased(int keyId)
    {
        return !keyCache.containsKey(keyId)
                || keyCache.get(keyId).equals(KEY_RELEASED);
    }

    public void update()
    {
        for (Integer keyCode : keyCache.keySet())
        {
            if (isJustPressed(keyCode))
            {
                keyCache.put(keyCode, KEY_PRESSED);
            }
        }
        for (Integer keyCode : releasedKeys)
        {
            keyCache.put(keyCode, KEY_RELEASED);
        }
        for (Integer keyCode : pressedKeys)
        {
            if (isReleased(keyCode))
            {
                keyCache.put(keyCode, KEY_JUST_PRESSED);
            } else
            {
                keyCache.put(keyCode, KEY_PRESSED);
            }
        }
        for(Integer keyCode: typedKeys){
        	if(isReleased(keyCode)){
        		keyCache.put(keyCode, KEY_RELEASED);
        	}
        	if(isTyped(keyCode)){
        		keyCache.put(keyCode, KEY_TYPED);
        	}
        }
        pressedKeys.clear();
        releasedKeys.clear();
        typedKeys.clear();
    }

    public void keyTyped(KeyEvent e)
    {
    	typedKeys.add(e.getKeyCode());
    }

    public void keyPressed(KeyEvent e)
    {
        pressedKeys.add(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e)
    {
        releasedKeys.add(e.getKeyCode());
    }

    public boolean isMousePressed(int button1){
    	if(button1 == MouseEvent.BUTTON1){
    		return mouseButton1;
    	}
    	if(button1 == MouseEvent.BUTTON2){
    		return mouseButton2;
    	}
    	return false;
    }
    
    public int getMouseX(){
    	return (int)this.mousePos.getX();
    }
    
    public int getMouseY(){
    	return (int)this.mousePos.getY();	
    }
    
    public Point getMousePos(){
    	return this.mousePos;
    }
    
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			this.mouseButton1 = true;
		}
		if(e.getButton() == MouseEvent.BUTTON2){
			this.mouseButton2 = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			this.mouseButton1 = false;
		}
		if(e.getButton() == MouseEvent.BUTTON2){
			this.mouseButton2 = false;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.mousePos.setLocation(e.getPoint());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.mousePos.setLocation(e.getPoint());
	}
}