/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;
import View.Node;
import View.MainWindow;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.World;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Supplier;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane; 
/**
 *
 * @author Mahinour
 */

public final class controller {
public int flag=0;
    
    private final Supplier<World> gameSupplier;
    private JFrame gameFrame;
    private GameEngine.GameController gameController;
    
    public controller(Supplier<World> gameSupplier) {
        this.gameSupplier = gameSupplier;
        //this.gameFrame= gameFrame;
    }
    
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem pauseMenuItem = new JMenuItem("Pause");
        JMenuItem resumeMenuItem = new JMenuItem("Resume");
        menu.add(newMenuItem);
        menu.addSeparator();
        menu.add(pauseMenuItem);
        menu.add(resumeMenuItem);
        menuBar.add(menu);
        
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                gameController.changeWorld(new StarWar(800, 600));
                  gameFrame.dispose();
                  start();
            }
        });
        pauseMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.pause();
            }
        });
        resumeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.resume();
            }
        });
//        JFrame j =getGameFrame() ;
//        j.setVisible(false);
        
        return menuBar;
    }
    public void start() {
        
        JMenuBar menuBar = createMenuBar();
        World game = gameSupplier.get();
        this.gameController = GameEngine.start(" Game", game, menuBar, Color.WHITE);
        this.gameFrame = (JFrame) menuBar.getParent().getParent().getParent();
         gameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.gameFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(gameFrame, "Are you sure you want to close this game?",
                        "End Game?", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    
                    gameFrame.dispose();
                    MainWindow l=new MainWindow();
                    l.setVisible(true);
                 
                    //some code to return to game main window.
                }
            }
        });
    }
//    public Node getParentNode() {
//         return ;
//       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }


    public JFrame getGameFrame() {
        return gameFrame;
    }

    public GameEngine.GameController getGameController() {
        return gameController;
    }
//    public JFrame getparent() {
//        return this;
//    }
    

}
