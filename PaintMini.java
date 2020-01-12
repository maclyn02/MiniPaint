/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.Graphics.*;
import java.awt.event.*;
import javax.swing.*;

public class PaintMini extends JApplet implements ActionListener, MouseListener, MouseMotionListener {

    public int mx=0,my=0,mx1,my1,cx,cy,x,y,px,py,h,w,sx,sy,t_size;
    public JButton line,rect,oval,circle,erase,fh,brush,text;
    public JTextField strval;
    public JLabel bg,pt,jl,szl;
    public JSlider jsz;
    public int flag=0,click=0,clk=0,clik=0;
    public JButton colour[];
    public Color col_str[]={ Color.red, Color.blue, Color.green, Color.yellow, Color.orange, Color.cyan, Color.pink, Color.magenta, Color.black, Color.white, Color.lightGray, Color.gray};
    public String str="";
    @Override
    public void init() 
    {       
        Toolkit t=Toolkit.getDefaultToolkit();
        Dimension d=t.getScreenSize();
        h=d.height;     w=d.width;        setSize(w,h);        
        bg=new JLabel();
        bg.setLayout(new FlowLayout()); 
        pt=new JLabel();
        pt.setLayout(new FlowLayout());
        erase=new JButton("Eraser");
        erase.addActionListener(this);
        erase.setActionCommand("E");
        erase.setCursor(Cursor.getPredefinedCursor(0)); 
        fh=new JButton("FreeHand");
        fh.addActionListener(this);
        fh.setActionCommand("F");
        fh.setCursor(Cursor.getPredefinedCursor(0)); 
        line=new JButton("Line");
        line.addActionListener(this);
        line.setActionCommand("L");
        line.setCursor(Cursor.getPredefinedCursor(0));
        rect=new JButton("Rectangle");
        rect.addActionListener(this);
        rect.setActionCommand("R");
        rect.setCursor(Cursor.getPredefinedCursor(0));
        circle=new JButton("Circle");
        circle.addActionListener(this);
        circle.setActionCommand("C");
        circle.setCursor(Cursor.getPredefinedCursor(0));
        oval=new JButton("Oval");
        oval.addActionListener(this);
        oval.setActionCommand("O");
        oval.setCursor(Cursor.getPredefinedCursor(0));
        brush=new JButton("Brush");
        brush.addActionListener(this);
        brush.setActionCommand("B");
        brush.setCursor(Cursor.getPredefinedCursor(0));
        text=new JButton("Text");
        text.addActionListener(this);
        text.setActionCommand("T");
        text.setCursor(Cursor.getPredefinedCursor(0));
        strval=new JTextField(15);
        strval.setText("Hello");
        szl=new JLabel("Text Size");
        jsz=new JSlider(15,100,15);
        jsz.setBackground(Color.lightGray);
        jsz.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        t_size=jsz.getValue();
        bg.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        bg.setBackground(Color.lightGray);
        bg.setSize(w, h/8);
        bg.setOpaque(true);
        pt.setBackground(Color.white);
        pt.setOpaque(true);
        jl=new JLabel();
        jl.setLayout(new FlowLayout(1, 0, 0));
        jl.setBackground(Color.lightGray);
        jl.setOpaque(true);
        jl.setPreferredSize(new Dimension(120, 40));
        colour=new JButton[12];
        colour[0]=new JButton();
        colour[1]=new JButton();
        colour[2]=new JButton();
        colour[3]=new JButton();
        colour[4]=new JButton();
        colour[5]=new JButton();
        colour[6]=new JButton();
        colour[7]=new JButton();
        colour[8]=new JButton();
        colour[9]=new JButton();
        colour[10]=new JButton();
        colour[11]=new JButton();
        for(int i=0;i<=11;i++)
        {
            colour[i].setBackground(col_str[i]);
            colour[i].setPreferredSize(new Dimension(20,20));
            colour[i].addActionListener(this);
            colour[i].setActionCommand(i+"");
            colour[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            jl.add(colour[i]);
        }            

        bg.add(erase);  
        bg.add(line);       
        bg.add(rect);
        bg.add(oval);
         bg.add(circle);   
        bg.add(fh);
        bg.add(brush);
        bg.add(text);
        bg.add(strval);
        bg.add(szl);
        bg.add(jsz);
        bg.add(jl);
        add(bg);
        add(pt);
        strval.setEnabled(!strval.isEnabled());
        pt.addMouseListener(this);
        pt.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g)
    {               
        if(my<(h/8)+20)
           my=(h/8)+40; 
        if(my1<(h/8))
        	my1=(h/8)+5; 
        if(sy<(h/8)+20)
              sy=(h/8)+t_size; 
        
        if(flag==0 && click!=0)
        { 
            setCursor(Cursor.getPredefinedCursor(1));
            g.drawLine(mx, my, mx1, my1);
            click=0;
        }
        else if(flag==1 && click!=0)
        {
            setCursor(Cursor.getPredefinedCursor(1));
            g.drawRect(mx, my, mx1-mx, my1-my); 
            click=0;
        }
        else if(flag==2 && click!=0)
        {
            setCursor(Cursor.getPredefinedCursor(1));
            g.drawOval(mx, my, mx1-mx, mx1-mx);
            click=0;
        }
        else if(flag==3 && click!=0)
        {
            setCursor(Cursor.getPredefinedCursor(1));
            g.drawOval(mx, my, mx1-mx, my1-my);
            click=0;
        }        
        else if(flag==4 && clik!=0)
        {     
            g.setColor(getBackground());
            g.fillOval(mx-20, my-20, 40, 40);
        } 
        else if(flag==5 && click!=0)
        {     
            setCursor(Cursor.getPredefinedCursor(1));
            click=0;
        } 
        else if(flag==6 && clik!=0)
        {            
            setCursor(Cursor.getPredefinedCursor(1));
            g.fillOval(mx-20, my-20, 40, 40);
        } 
        else if(flag==7 && clk!=0)
        {  
            str=strval.getText();   
            t_size=jsz.getValue();
            Font f=new Font("Arial",Font.BOLD,t_size);
            g.setFont(f);
            g.drawString(str, sx, sy);
            clk=0;
        } 
    }

    @Override
    public void mouseClicked(MouseEvent me) 
    {
        if(flag==7)
        {
            clk=1;
            sx=me.getX();
            sy=me.getY();
            repaint();
        }        
    }

    @Override
    public void mousePressed(MouseEvent me) 
    {
        click=1;clik=1;
        mx=me.getX();
        my=me.getY();
        px=mx;
        py=my;
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        mx1=me.getX();
        my1=me.getY();
        clik=0;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if(flag==4 || flag==6)
        {
            mx=me.getX();
            my=me.getY();            
            repaint();
        }
        else if(flag==5)
        {
            mx=me.getX();
            my=me.getY(); 
            Graphics g=getGraphics();
            if(py>((h/8)+5) && my>((h/8)+5))
                g.drawLine(px, py , mx, my);
            px=mx;
            py=my;
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) 
    {						 }
    @Override
    public void actionPerformed(ActionEvent ae) {
        Graphics g=getGraphics();
        if(ae.getActionCommand().equals("L"))
            flag=0;
        else if(ae.getActionCommand().equals("R"))
            flag=1;
        else if(ae.getActionCommand().equals("C"))
            flag=2;
        else if(ae.getActionCommand().equals("O"))
            flag=3;        
        else if(ae.getActionCommand().equals("E"))
            flag=4;
        else if(ae.getActionCommand().equals("F"))
            flag=5; 
        else if(ae.getActionCommand().equals("B"))
            flag=6; 
        else if(ae.getActionCommand().equals("T"))
        {
            strval.setEnabled(true);
            flag=7;            
        } 
        else if(ae.getActionCommand().equals("0"))
            setForeground(Color.red);
        else if(ae.getActionCommand().equals("1"))
            setForeground(Color.blue);
        else if(ae.getActionCommand().equals("2"))
            setForeground(Color.green);
        else if(ae.getActionCommand().equals("3"))
            setForeground(Color.yellow);
        else if(ae.getActionCommand().equals("4"))
            setForeground(Color.orange);
        else if(ae.getActionCommand().equals("5"))
            setForeground(Color.cyan);
        else if(ae.getActionCommand().equals("6"))
            setForeground(Color.pink);
        else if(ae.getActionCommand().equals("7"))
            setForeground(Color.magenta);
        else if(ae.getActionCommand().equals("8"))
            setForeground(Color.black);
        else if(ae.getActionCommand().equals("9"))
            setForeground(Color.white);
        else if(ae.getActionCommand().equals("10"))
            setForeground(Color.lightGray);
        else if(ae.getActionCommand().equals("11"))
            setForeground(Color.gray);         
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent me) 
    {						   }
    @Override
    public void mouseExited(MouseEvent me) 
   {						  }
}

