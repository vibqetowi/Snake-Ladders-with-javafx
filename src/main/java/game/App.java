package game;
import java.util.Arrays;
import java.util.Scanner;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.*;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * This is a simple Snake and Ladders game with a graphical interface.
 * I spent some time researching the graphical part of the game and found
 * the javafx library. Because I haven't truly learnt javafx, a lot
 * of parts are inelegant and do not take advantage of OOP. 
 * From what I can observe, javafx has extensive dependencies I can't yet tamper with.
 * 
 * This was originally a school assignment hence a few strange choices
 * 
 * Needless to say all the javafx stuff is heavily sampled from online tutorials
 */
public class App extends Application {

    /**
     *This label will describe what is happening in the game, in addition
     * to the graphical animations
     */
public Label notification;
public int numofplayers;
public int roll;
public int turn;

//players will be displayed as circles
public Circle pl_a;
public Circle pl_b;
public Circle pl_c;
public Circle pl_d;

/**
 * the following variables will be useful for animating the
 * circles graphically
 */
public static int x;
public static int y;

public static int pla_index;
public static int plb_index;
public static int plc_index;
public static int pld_index;

/**
 * the following boolean variables are used to determine player's turns
 * and also game start
 */
public boolean pla_turn;
public boolean plb_turn;
public boolean plc_turn;
public boolean pld_turn;

public boolean gameStart = false;

    /**
     *most of the interactions done in game will be through
     * buttons placed on the board
     */
public Button Startbutton;

public Button button1 = new Button();
public Button button2 = new Button();
public Button button3 = new Button();
public Button button4 = new Button();
    
// --------------------------------------------------

/**
 * I only  know that this is very important in generating my stage
 */
private final Group tileGroup = new Group();

/**
 * The play method is of course where the main content is, if we scroll down to the
 * start method, we can see it first determines the number of players then runs the
 * play method
 * 
 */
private Parent play() {
    
// display the board

    Pane root = new Pane();
    root.setPrefSize((550)+144,(550)+99); //(x,y)
    root.getChildren().addAll (tileGroup) ;

  
// creating the Player icons    
pl_a = new Circle(22); 
pl_a.setFill(Color.RED);

pl_b = new Circle(22); 
pl_b.setFill(Color.BLUE);

pl_c = new Circle(22); 
pl_c.setFill(Color.GREEN);

pl_d = new Circle(22); 
pl_d.setFill(Color.BLACK);

/**
*A requirement of the assignment was making playing turns random, here is the logic
 * 1) set each player to a numerical value: A=1, B=2...
 * 2) shuffle randomly the values
 * 3) then we iterate through the array to determine whose turn it is
 * 
 */
 int[] order=new int[numofplayers];
   
 if (numofplayers>=2){
    order[0]=1;
    order[1]=2;
 }
if (numofplayers>=3)
     order[2]=3;
if(numofplayers>=4)
    order[3]=4;
   

// Interaction buttons

/**
 * All the buttons 1 - 4 have the same code so I'll go through just one

 * I tried to create a single method and to call it upon click without much sucess
 * hence why the 3 other buttons are copy-paste of this button
 * 
 * here's the logic line by line
 * 
 * 1) it checks whether the game has started and if it's the player's turn
 * 2) it rolls the dice and updates the index of A (position in game)
 * 3)checks and corrects index if it went over 100 (100-what went over)
 * 4) checks whether the current index is equal to a snake or ladder and corrects 
 * the index. for this one i should have used a 2d array instead of a wall of if 
 * statements, when i realized though i had already coded the thing so oh well
 * 5) graphically moves the players
 * 6) calls the next player's turns (again governed by booleans)
 */
button1.setTranslateX(570);
button1.setTranslateY(20+55*6);
button1.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (gameStart&&pla_turn) {
                flipDice();
                notification.setText("Player A rolled a "+roll);
                pla_index = pla_index + roll;
                
                if (pla_index >100) // avoids going over 100
                    pla_index = 100-(pla_index-100);
                

                if (pla_index==1){
                    pla_index=38;
                    notification.setText("Player A climbs to "+ pla_index);}
                
                if(pla_index==4){
                    pla_index=14;
                    notification.setText("Player A climbs to "+ pla_index);}
                
                if(pla_index==9){
                    pla_index=31;
                    notification.setText("Player A climbs to "+ pla_index);}
                
                if(pla_index==16){
                    pla_index=6;
                    notification.setText("Player A slides to "+ pla_index);}
                
                if (pla_index==28){
                    pla_index=84;
                    notification.setText("Player A climbs to "+ pla_index);}
                
                if (pla_index==21){
                    pla_index=42;
                    notification.setText("Player A climbs to "+ pla_index);}
                
                if (pla_index==36){
                    pla_index=44;
                    notification.setText("Player A climbs to "+ pla_index);}
                
                if (pla_index==48){
                    pla_index=30;
                    notification.setText("Player A slides to "+ pla_index);}
                
                if (pla_index==51){
                    pla_index=67;
                    notification.setText("Player A climbs to "+ pla_index);}
                
                if (pla_index==64){
                    pla_index=60;
                    notification.setText("Player A slides to "+ pla_index);}
                
                if (pla_index==79){
                    pla_index=19;
                    notification.setText("Player A slides to "+ pla_index);}
                
                if (pla_index==71){
                    pla_index=91;
                    notification.setText("Player A climbs to "+ pla_index);}
                
                if (pla_index==80){
                    pla_index=100;}
                
                if (pla_index==93){
                    pla_index=68;
                    notification.setText("Player A slides to "+ pla_index);}
                
                if (pla_index==95){
                    pla_index=24;
                    notification.setText("Player A slides to "+ pla_index);}
                
                if (pla_index==97){
                    pla_index=76;
                    notification.setText("Player A slides to "+ pla_index);}
                
                if (pla_index==98){
                    pla_index=78;
                    notification.setText("Player A slides to "+ pla_index);}
                
                move(pla_index);
                moveCircle(x,y,pl_a);
                
                if (pla_index==100){
                    notification.setText("Player A wins!");
                    Startbutton.setText("Click to Play again");
                    gameStart = false;}
                
                turn++;
                if (turn==numofplayers)
                    turn=0;
                
                if (turn==4)
                    turn=0;
                    
                pla_turn=false;    
 
                if (order[turn]==1)
                    pla_turn=true;
                if (order[turn]==2)
                    plb_turn=true;
                if (order[turn]==3)
                    plc_turn=true;
                if (order[turn]==4)
                    pld_turn=true;
                
                changebuttontext();
            }   }
    });


button2.setTranslateX(570);
button2.setTranslateY(20+55*7);
button2.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (gameStart&&plb_turn) {
                flipDice();
                notification.setText("Player B rolled a "+roll);
                plb_index = plb_index + roll;
                
                if (plb_index >100)
                    plb_index = 100-(plb_index-100);
                
                
                if (plb_index==1){
                    plb_index=38;
                    notification.setText("Player B climbs to "+ plb_index);}
                if(plb_index==4){
                    plb_index=14;
                    notification.setText("Player B climbs to "+ plb_index);}
                if(plb_index==9){
                    plb_index=31;
                    notification.setText("Player B climbs to "+ plb_index);}
                if(plb_index==16){
                    plb_index=6;
                    notification.setText("Player B slides to "+ plb_index);}
                if (plb_index==28){
                    plb_index=84;
                    notification.setText("Player B climbs to "+ plb_index);}
                if (plb_index==21){
                    plb_index=42;
                    notification.setText("Player B climbs to "+ plb_index);}
                if (plb_index==36){
                    plb_index=44;
                    notification.setText("Player B climbs to "+ plb_index);}
                if (plb_index==48){
                    plb_index=30;
                    notification.setText("Player B slides to "+ plb_index);}
                if (plb_index==51){
                    plb_index=67;
                    notification.setText("Player B climbs to "+ plb_index);}
                if (plb_index==64){
                    plb_index=60;
                    notification.setText("Player B slides to "+ plb_index);}
                if (plb_index==79){
                    plb_index=19;
                    notification.setText("Player B slides to "+ plb_index);}
                if (plb_index==71){
                    plb_index=91;
                    notification.setText("Player B climbs to "+ plb_index);}
                if (plb_index==80){
                    plb_index=100;}
                if (plb_index==93){
                    plb_index=68;
                    notification.setText("Player B slides to "+ plb_index);}
                if (plb_index==95){
                    plb_index=24;
                    notification.setText("Player B slides to "+ plb_index);}
                if (plb_index==97){
                    plb_index=76;
                    notification.setText("Player B slides to "+ plb_index);}
                if (plb_index==98){
                    plb_index=78;
                    notification.setText("Player B slides to "+ plb_index);}
                
                move(plb_index);
                moveCircle(x,y,pl_b);
                
                if (plb_index==100){
                    notification.setText("Player B wins!");
                    Startbutton.setText("Click to Play again");
                    gameStart = false;}
                
                turn++;
                if (turn==numofplayers)
                    turn=0;
                
                plb_turn=false;    
 
                if (order[turn]==1)
                    pla_turn=true;
                if (order[turn]==2)
                    plb_turn=true;
                if (order[turn]==3)
                    plc_turn=true;
                if (order[turn]==4)
                    pld_turn=true;
                
                changebuttontext();
            }
        }
    });

button3.setTranslateX(570);
button3.setTranslateY(20+55*8);
button3.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (gameStart&&plc_turn) {
                flipDice();
                notification.setText("Player C rolled a "+roll);
                plc_index=plc_index+roll;
                
                if (plc_index >100)
                    plc_index = 100-(plc_index-100);
                
                
                if (plc_index==1){
                    plc_index=38;
                    notification.setText("Player C climbs to "+ plc_index);}
                if(plc_index==4){
                    plc_index=14;
                    notification.setText("Player C climbs to "+ plc_index);}
                if(plc_index==9){
                    plc_index=31;
                    notification.setText("Player C climbs to "+ plc_index);}
                if(plc_index==16){
                    plc_index=6;
                    notification.setText("Player C slides to "+ plc_index);}
                if (plc_index==28){
                    plc_index=84;
                    notification.setText("Player C climbs to "+ plc_index);}
                if (plc_index==21){
                    plc_index=42;
                    notification.setText("Player C climbs to "+ plc_index);}
                if (plc_index==36){
                    plc_index=44;
                    notification.setText("Player C climbs to "+ plc_index);}
                if (plc_index==48){
                    plc_index=30;
                    notification.setText("Player C slides to "+ plc_index);}
                if (plc_index==51){
                    plc_index=67;
                    notification.setText("Player C climbs to "+ plc_index);}
                if (plc_index==64){
                    plc_index=60;
                    notification.setText("Player C slides to "+ plc_index);}
                if (plc_index==79){
                    plc_index=19;
                    notification.setText("Player C slides to "+ plc_index);}
                if (plc_index==71){
                    plc_index=91;
                    notification.setText("Player C climbs to "+ plc_index);}
                if (plc_index==80){
                    plc_index=100;}
                if (plc_index==93){
                    plc_index=68;
                    notification.setText("Player C slides to "+ plc_index);}
                if (plc_index==95){
                    plc_index=24;
                    notification.setText("Player C slides to "+ plc_index);}
                if (plc_index==97){
                    plc_index=76;
                    notification.setText("Player C slides to "+ plc_index);}
                if (plc_index==98){
                    plc_index=78;
                    notification.setText("Player C slides to "+ plc_index);}
                
                move(plc_index);
                moveCircle(x,y,pl_c);
                
                if (plc_index==100){
                    notification.setText("Player C wins!");
                    Startbutton.setText("Click to Play again");
                    gameStart = false;}
                
                turn++;
                if (turn==numofplayers)
                    turn=0;
                
                plc_turn=false;    
 
                if (order[turn]==1)
                    pla_turn=true;
                if (order[turn]==2)
                    plb_turn=true;
                if (order[turn]==3)
                    plc_turn=true;
                if (order[turn]==4)
                    pld_turn=true;
                                
                changebuttontext();
            }
        }
    });

button4.setTranslateX(570);
button4.setTranslateY(20+55*9);
button4.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (gameStart&&pld_turn) {
                flipDice();
                notification.setText("Player D rolled a "+roll);
                
                pld_index=pld_index+roll;
                
                if (pld_index >100)
                    pld_index = 100-(pld_index-100);
                
                
                if (pld_index==1){
                    pld_index=38;
                    notification.setText("Player D climbs to "+ pld_index);}
                if(pld_index==4){
                    pld_index=14;
                    notification.setText("Player D climbs to "+ pld_index);}
                if(pld_index==9){
                    pld_index=31;
                    notification.setText("Player D climbs to "+ pld_index);}
                if(pld_index==16){
                    pld_index=6;
                    notification.setText("Player D slides to "+ pld_index);}
                if (pld_index==28){
                    pld_index=84;
                    notification.setText("Player D climbs to "+ pld_index);}
                if (pld_index==21){
                    pld_index=42;
                    notification.setText("Player D climbs to "+ pld_index);}
                if (pld_index==36){
                    pld_index=44;
                    notification.setText("Player D climbs to "+ pld_index);}
                if (pld_index==48){
                    pld_index=30;
                    notification.setText("Player D slides to "+ pld_index);}
                if (pld_index==51){
                    pld_index=67;
                    notification.setText("Player D climbs to "+ pld_index);}
                if (pld_index==64){
                    pld_index=60;
                    notification.setText("Player D slides to "+ pld_index);}
                if (pld_index==79){
                    pld_index=19;
                    notification.setText("Player D slides to "+ pld_index);}
                if (pld_index==71){
                    pld_index=91;
                    notification.setText("Player D climbs to "+ pld_index);}
                if (pld_index==80){
                    pld_index=100;}
                if (pld_index==93){
                    pld_index=68;
                    notification.setText("Player D slides to "+ pld_index);}
                if (pld_index==95){
                    pld_index=24;
                    notification.setText("Player D slides to "+ pld_index);}
                if (pld_index==97){
                    pld_index=76;
                    notification.setText("Player D slides to "+ pld_index);}
                if (pld_index==98){
                    pld_index=78;
                    notification.setText("Player D slides to "+ pld_index);}
                
                move(pld_index);
                moveCircle(x,y,pl_d);
                
                if (pld_index==100){
                    notification.setText("Player D wins!");
                    Startbutton.setText("Click to Play again");
                    gameStart = false;}
                
                turn++;
                if (turn==numofplayers)
                    turn=0;
                
                 pld_turn=false;    
 
                if (order[turn]==1)
                    pla_turn=true;
                if (order[turn]==2)
                    plb_turn=true;
                if (order[turn]==3)
                    plc_turn=true;
                if (order[turn]==4)
                    pld_turn=true;
                               
                
                changebuttontext();
            }
        }
    });

/**
 * This button will start the game and reset all game values to an initial
 * position. clicking it again mid game will also reset everything, though
 * this is more of a bug than a feature.
 */
Startbutton = new Button("Start Game");
Startbutton.setTranslateX(570);
Startbutton.setTranslateY(20+55*5);
Startbutton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Startbutton.setText("Game Started");
            gameStart = true;
    
    /**
     * This block below is used to shuffle the play order array giving us a random
     * play order every game. 
     */    
    for(int i = order.length - 1; i > 0; i--){
        int j = java.util.concurrent.ThreadLocalRandom.current().nextInt(i + 1);
        int temp = order[i];
        order[i] = order[j];
        order[j] = temp;}
            
            pl_a.setTranslateX(15);
            pl_a.setTranslateY(570);
            
            
            pl_b.setTranslateX(35);
            pl_b.setTranslateY(570);
            
            
            pl_c.setTranslateX(15);
            pl_c.setTranslateY(590);
            
            pl_d.setTranslateX(35);
            pl_d.setTranslateY(590);
            
            pla_index=0;
            plb_index=0;
            plc_index=0;
            pld_index=0;
            
            pla_turn=false;
            plb_turn=false;
            plc_turn=false;
            pld_turn=false;
            
            turn=0;
            
            System.out.println("-> this is for the TA's, the current play order is:");
            System.out.println(Arrays.toString(order)+" 1 corresponds to player A, 2 to B and so on");
            
                if (order[turn]==1)
                    pla_turn=true;
                if (order[turn]==2)
                    plb_turn=true;
                if (order[turn]==3)
                    plc_turn=true;
                if (order[turn]==4)
                    pld_turn=true;
                
            changebuttontext();
        }
        
    });

notification = new Label("Waiting for game start");
notification.setTranslateX(266);
notification.setTranslateY(580);

/**
 * Here I uploaded the image in the assignment to some cloud and this code
 * fetches the image and puts in on the stage as a background. The alternative
 * wouldve been to somehow draw the tiles, snakes and ladders. 
 */
Image bg = new Image("https://i.imgur.com/qqtyoYt.jpeg");
ImageView bgView = new ImageView();
bgView.setImage(bg);
bgView.setFitHeight(550);
bgView.setFitWidth(550);


// add appropriate elements according to num players

if (numofplayers>=2)
    tileGroup.getChildren().addAll(Startbutton, bgView,notification, pl_a,button1, pl_b,button2);
if (numofplayers>=3)
    tileGroup.getChildren().addAll(pl_c,button3);
if (numofplayers>=4)
    tileGroup.getChildren().addAll(pl_d,button4);
 

return root;
}

    /**
     *This function changes the text on the player buttons so we know
     * whose turn it is
     */
public void changebuttontext(){
if(pla_turn==true)
    button1.setText("A's turn (click here)");
if (pla_turn == false)
    button1.setText("Player A idle at "+pla_index);

if(plb_turn==true)
    button2.setText("B's turn (click here)");
if (plb_turn == false)
    button2.setText("Player B idle at "+plb_index);

if(plc_turn==true)
    button3.setText("C's turn (click here)");
if (plc_turn == false)
    button3.setText("Player C idle at "+plc_index);

if(pld_turn==true)
    button4.setText("D's turn (click here)");
if (pld_turn == false)
    button4.setText("Player D idle at "+pld_index);}

    /**
     *asks how many players to create, fairly straightforward
     * I used a while loop to check for invalid entries
     * 
     * When this was written we barely started exception handling
     * so users are assumed perfect
     */
public void setNumplayers(){
Scanner kb1 = new Scanner(System.in);
System.out.println("\n-> First, enter number of players:");

int attempts = 1;
numofplayers = kb1.nextInt();

while (numofplayers<2 || numofplayers>4 ){
        
if (attempts>4){
        System.out.println("-> Sorry too many attempts, the game will now end");
        System.exit(0);}

    System.out.println("-> Please enter a value between 2 and 4: (attempt "+attempts+"/4)");
    numofplayers = kb1.nextInt();

attempts++;}
System.out.println("-> "+numofplayers+" players will be created shortly");
System.out.println("The playing turn will be randomized on game start");
}

//flipDice method
private void flipDice(){
roll = (int)(Math.random()*6+1);
}

/**
 * This method uses the index to dertermine the pixel coordinates to set the
 * graphical circles to. There's probably a way to logically determine some index 
 * goes to which coordinates. I just didnt do it in this instance
 * 
 * @param index 
 * this parameter is of course the in game position of the player
 * think of it as "square 60" for instance
 */
private void move(int index){
    
    switch (index) {
        case 1:case 20:case 21:case 40:case 41:case 60:case 61:case 80:case 81:case 100:
            x=25;
            break;
        case 2:case 19:case 22:case 39:case 42: case 59:case 62:case 79: case 82:case 99:
            x= 80;
            break;
        case 3: case 18:case 23:case 38:case 43:case 58:case 63:case 78:case 83:case 98:
            x=135;
            break;
        case 4: case 17:case 24:case 37:case 44:case 57:case 64:case 77: case 84:case 97:
            x=190;
            break;
        case 5:case 16:case 25:case 36:case 45: case 56:case 65:case 76:case 85:case 96:
            x=245;
            break;
        case 6:case 15:case 26:case 35:case 46:case 55:case 66:case 75:case 86:case 95:
            x=300;
            break;
        case 7:case 14:case 27:case 34:case 47:case 54:case 67:case 74:case 87:case 94:
            x=355;
            break;
        case 8:case 13:case 28:case 33:case 48:case 53:case 68:case 73:case 88:case 93:
            x=410;
            break;
        case 9:case 12:case 29:case 32:case 49:case 52:case 69:case 72:case 89:case 92:
            x=465;
            break;
        case 10:case 11:case 30:case 31:case 50:case 51:case 70:case 71:case 90:case 91:
            x=520;
            break;
  
    }
    
    //----------------    

    //{25,80,135,190,245,300,355,410,465,520};
   if(index>=1&&index<=10)
    y = 520;
   else if(index>=11&&index<=20)
    y = 465; 
   else if(index>=21&&index<=30)
    y = 410; 
   else if(index>=31&&index<=40)
    y = 355; 
   else if(index>=41&&index<=50)
    y = 300; 
   else if(index>=51&&index<=60)
    y = 245;
   else if(index>=61&&index<=70)
    y = 190; 
   else if(index>=71&&index<=80)
    y = 135; 
   else if(index>=81&&index<=90)
    y = 80; 
   else if(index>=91&&index<=100)
    y = 25; 
}

/** 
 * This animates the specified circle to the specified coordinates
 * this moving the players on the board
 * 
 * @param x
 * @param y
 * @param circle 
 */
private void moveCircle(int x, int y, Circle circle){
    TranslateTransition animate = new TranslateTransition(Duration.millis(666), circle);
    animate.setToX(x);
    animate.setToY(y);
    animate.setAutoReverse(false);
    animate.play();
}


    /**
     *I coded the game to start in console, honestly it would have been possible to make 
     * everything run in the game board but it would have been adding even more complication
     * to a program I don't fully understand
     * 
     * Basically it will first ask how many players to create, once that's done, the game 
     * board will appear and we can start playing.
     * 
     * @param Board
     * @throws Exception
     */
    public void start(Stage Board) throws Exception{
    System.out.println("\n=================================================");
    System.out.println("\t    Welcome to Snake and Ladder!");
    System.out.println("=================================================");
    setNumplayers();
    
    Scene scene = new Scene (play());
    Board.setTitle("Snake and Ladder");
    Board.setScene (scene) ;
    Board.show();
    scene.getRoot().setStyle("-fx-font-family: 'serif'");
    }

/**
 * it seems that for javafx you cant really touch the main method
 * I didnt need to as this was pre generated by my IDE
 * @param args 
 */
public static void main(String[] args) {
        launch();
        
    }

}

