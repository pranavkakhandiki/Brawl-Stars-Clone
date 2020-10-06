package finalProject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import apcs.Window;
import java.util.Arrays;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.math.*;


/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author pkakhandiki277
 * @version May 7, 2019
 */

public class Main
{
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    public static int[] blocksX = new int[60];

    public static int[] blocksY = new int[60];


    public static int size = 60;
    public static int boxSize = 7;

    static ArrayList<Integer> elixirX = new ArrayList<Integer>();

    static ArrayList<Integer> elixirY = new ArrayList<Integer>();

    static ArrayList<ElixirBox> elixirBoxes = new ArrayList<ElixirBox>();

    /**
     *
     * executes all of the code in the main
     * @param args
     * @throws IOException
     */
    public static void main( String[] args ) throws IOException
    {
        for ( int i = 0; i < 60; i++ )
        {
            Block b = new Block();
            blocksX[i] = b.x;
            blocksY[i] = b.y;
        }

        boolean coltPressed = false;
        boolean coltPressed2 = false;
        boolean pamPressed = false;
        boolean pamPressed2 = false;
        boolean cSpawned = true;
        boolean pSpawned = true;
        boolean cSupered = false;
        boolean cSuperedPressed = false;

        Arrays.sort( blocksY );
        for ( int i = 0; i < boxSize; i++ )
        {
            ElixirBox e = new ElixirBox(blocksX, blocksY);
            elixirBoxes.add( e );
            elixirX.add(e.x);
            elixirY.add(e.y);
            System.out.println( e.x + " " + e.y );
        }
        System.out.println(  );
        for (int i=0; i<boxSize; i++)
        {
            System.out.println( elixirBoxes.get( i ).x + " " + elixirBoxes.get( i ).y );
        }
        setup();
        Pam pam = new Pam();
        ArrayList<Gem> gems = new ArrayList<Gem>();
        ArrayList<PamBullet> pShots = new ArrayList<PamBullet>();
        ArrayList<Triple<Integer, Integer, Double>> pShotsInitial = new ArrayList<Triple<Integer, Integer, Double>>();
        ArrayList<ColtBullet> cShots = new ArrayList<ColtBullet>();
        ArrayList<ColtSuper> cSupers = new ArrayList<ColtSuper>();
        ArrayList<Triple<Integer, Integer, Double>> cShotsInitial = new ArrayList<Triple<Integer, Integer, Double>>();
        ArrayList<Triple<Integer, Integer, Double>> cSuperInitial = new ArrayList<Triple<Integer, Integer, Double>>();
        Colt colt = new Colt();

        int pdir = 0;
        int cdir = 0;
        double coltangle = 0.0;
        double pamangle = 0.0;
        int coltammo = 3;
        int pamammo = 3;
        int pSuper = 0;
        int cSuper = 0;
        int coltcounter = 180;
        int pamcounter = 180;
        int cHeal = 0;
        int pHeal = 0;
        int distX = 0;
        int distY = 0;
        int poisonTick = 21;
        int initialDelay = 200;
        int coltpCounter = 0;
        int pampCounter = 0;
        int poisonMovement = 60;
        int time = 0;
        int poison = 0;
        boolean sOnMap = false;
        boolean deployedSuper = false;
        int pamSupX  = 0;
        int pamSupY = 0;
        int pSuperCoolDown = 0;
        int superHealth = 0;
        int coltShotCounter = 0;
        boolean coltShot = false;
        boolean coltSuper = false;
        int coltSuperCounter = 0;
        int coltInitialX = 0;
        int coltInitialY = 0;
        double coltInitialAngle = 0.0;
        int coltSuperInitialX = 0;
        int coltSuperInitialY = 0;
        double coltSuperInitialAngle = 0.0;
        boolean pamShot = false;
        int pamShotCounter = 0;
        int pamInitialX = 0;
        int pamInitialY = 0;
        double pamInitialAngle = 0.0;


        while ( true )
        {
            drawBackground();
            ArrayList<Poison> map = new ArrayList<Poison>();
            for (int ev = 0; ev<2; ev++)
            {
                for (int i=0; i<15; i++)
                {
                    for (int j=0; j<16; j++)
                    {
                        if ((((i*100 + (ev % 2) * 50)) < (distX/2)) || (((i*100 + (ev % 2) * 50) + 50) > (1450 - (distX/2))) || ((j * 60) < distY/2) || ((j * 60) > (840 - distY/2)))
                        {
                            if ((ev % 2 == 0) && (j % 2 == 0))
                            {
                                map.add( new Poison((i*100 + (ev % 2) * 50), j * 60, poison));
                            }
                            if ((ev % 2 == 1) && (j % 2 == 1))
                            {
                                map.add( new Poison((i*100 + (ev % 2) * 50), j * 60, (poison + 21)));
                            }
                        }
                    }
                }
            }
            for (int i=0; i<map.size(); i++)
            {
                map.get( i ).draw();
            }
            poison++;
            boolean coltInPoison = false;
            boolean pamInPoison = false;
            if (time > initialDelay)
            {
                if ((time-initialDelay) % 100 == 0)
                {
                    distX += 1.6 * poisonMovement;
                    distY += poisonMovement;
                }
            }
            time++;
            if (colt.poisonCollision( distX/2, distY/2 ))
            {
                coltInPoison = true;
                if (coltpCounter % poisonTick == 0)
                {
                    colt.decreaseHealth( 500 );
                    cHeal = 0;
                }
                coltpCounter++;
            }
            else
            {
                coltInPoison = false;
                coltpCounter = 0;
            }
            if (pam.poisonCollision( distX/2, distY/2 ))
            {
                pamInPoison = true;
                if (pampCounter % poisonTick == 0)
                {
                    pam.decreaseHealth( 500 );
                    pHeal = 0;
                }
                pampCounter++;
            }
            else
            {
                pampCounter = 0;
            }
            ArrayList<String> pamdir = new ArrayList<String>();
            ArrayList<String> coltdir = new ArrayList<String>();

            coltdir = colt.move( blocksX, blocksY);
            if ( cSpawned )
            {
                colt.spawn( blocksX, blocksY);
                cSpawned = false;
            }
            else
            {
                colt.draw( cdir, coltcounter, coltangle, cSuper );
            }
            if ( coltdir.size() != 0 )
            {
                for ( String i : coltdir )
                {
                    if ( i == "right" )
                    {
                        cdir = 1;
                    }
                    else if ( i == "left" )
                        cdir = 0;
                    colt.draw( cdir, coltcounter, coltangle, cSuper );
                }
            }
            else
            {
                cdir = 2;
                colt.draw( cdir, coltcounter, coltangle, cSuper );
            }
            pamdir = pam.move( blocksX, blocksY);
            if ( pSpawned )
            {
                pam.spawn( blocksX, blocksY, colt);
                pSpawned = false;
            }
            else
            {
                pam.draw( pdir, pamcounter, pamangle, pSuper );
            }

            if ( pamdir.size() != 0 )
            {
                for ( String i : pamdir )
                {
                    if ( i == "d" )
                    {
                        pdir = 1;
                    }
                    else if ( i == "a" )
                        pdir = 0;
                    pam.draw( pdir, pamcounter, pamangle, pSuper );
                }
            }
            else
            {
                pdir = 2;
                pam.draw( pdir, pamcounter, pamangle, pSuper );
            }

            if ( Window.key.pressed( "." ) )
            {
                coltangle += 5;
                coltPressed = true;
            }
            if ( Window.key.pressed( "," ) )
            {
                coltangle -= 5;
                coltPressed2 = true;
            }
            if ( Window.key.pressed( "b" ) )
            {
                pamangle += 5;
                pamPressed = true;
            }
            if ( Window.key.pressed( "n" ) )
            {
                pamangle -= 5;
                pamPressed2 = true;
            }
            for ( int i = 0; i < 60; i++ )
            {
                pam.playerCollision( blocksX[i], blocksY[i] );
                colt.playerCollision( blocksX[i], blocksY[i] );
            }

            if ( (( pamammo > 0 ) && pam.shoot() || pamShot))
            {
                pamShot = true;
                if (pamShotCounter == 0)
                {
                    pamInitialAngle = pamangle;
                    pamInitialX = pam.x;
                    pamInitialY = pam.y;

                    pShots.add( new PamBullet( pam.x, pam.y, pamangle ) );
                    pShotsInitial.add( new Triple( pam.x, pam.y, pamangle ) );


                    pShots.add( new PamBullet( pam.x, pam.y, pamangle + 5 - (int)(1 - (pamangle%360) / 180) ) );
                    pShotsInitial.add( new Triple( pam.x, pam.y, pamangle + 5 - (int)(1 - (pamangle%360) / 180) ) );

                    pShots.add( new PamBullet( pam.x, pam.y, pamangle - 5 + (int)((pamangle%360) / 180)) );
                    pShotsInitial.add( new Triple( pam.x, pam.y, pamangle - 5 + (int)((pamangle%360) / 180)) );
                    pamShotCounter++;
                }
                else if (pamShotCounter == 3)
                {
                    pShots.add( new PamBullet( pam.x, pam.y, pamangle ) );
                    pShotsInitial.add( new Triple( pam.x, pam.y, pamangle ) );


                    pShots.add( new PamBullet( pam.x, pam.y, pamangle + 5 - (1 - (int)(pamangle%360) / 180) ) );
                    pShotsInitial.add( new Triple( pam.x, pam.y, pamangle + 5 - (1 - (int)(pamangle%360) / 180) ) );

                    pShots.add( new PamBullet( pam.x, pam.y, pamangle - 5 + (int)(pamangle%360) / 180));
                    pShotsInitial.add( new Triple( pam.x, pam.y, pamangle - 5 + (int)(pamangle%360) / 180 ));

                    pamShotCounter++;
                }
                else if (pamShotCounter == 6)
                {
                    pShots.add( new PamBullet( pam.x, pam.y, pamangle ) );
                    pShotsInitial.add( new Triple( pam.x, pam.y, pamangle ) );


                    pShots.add( new PamBullet( pam.x, pam.y, pamangle + 5 - (1 - (int)(pamangle%360) / 180) ) );
                    pShotsInitial.add( new Triple( pam.x, pam.y, pamangle + 5 - (1 - (int)(pamangle%360) / 180) ) );

                    pShots.add( new PamBullet( pam.x, pam.y, pamangle - 5 + (int)(pamangle%360) / 180) );
                    pShotsInitial.add( new Triple( pam.x, pam.y, pamangle - 5 + (int)(pamangle%360) / 180) );

                    pamammo--;
                    pamcounter = pamcounter - 60;
                    pHeal = 0;
                    pamShot = false;
                    pamShotCounter = 0;
                }

                else
                    pamShotCounter++;


            }
            else
                pHeal++;

            if ( ( (coltammo > 0 ) && colt.shoot()) || coltShot )
            {
                coltShot = true;
                if (coltShotCounter == 0)
                {
                    coltInitialAngle = coltangle;
                    coltInitialX = colt.x;
                    coltInitialY = colt.y;

                    cShots.add( new ColtBullet( coltInitialX, coltInitialY, coltInitialAngle ) );
                    cShotsInitial.add( new Triple( coltInitialX, coltInitialY, coltInitialAngle ) );
                    coltShotCounter++;

                }
                else if (coltShotCounter == 2)
                {
                    cShots
                        .add(
                            new ColtBullet(
                                coltInitialX
                                    + (int)( 20 * Math.sin( 2 * Math.PI * ( (coltInitialAngle) / 360.0 ) ) )
                                    + (int)( 20
                                        * Math.cos( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0 ) ) ),
                                    coltInitialY
                                    + (int)( 20 * Math.cos( 2 * Math.PI * ( (coltInitialAngle) / 360.0 ) ) )
                                    - (int)( 20
                                        * Math.sin( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0 ) ) ),
                                    coltInitialAngle ) );
                    cShotsInitial
                        .add(
                            new Triple(
                                coltInitialX
                                    + (int)( 20 * Math.sin( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0  ) ) )
                                    + (int)( 20
                                        * Math.cos( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0 ) ) ),
                                    coltInitialY
                                    + (int)( 20 * Math.cos( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0  ) ) )
                                    - (int)( 20
                                        * Math.sin( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0 ) ) ),
                                    coltInitialAngle ) );

                    cShots.add( new ColtBullet( coltInitialX, coltInitialY, coltInitialAngle ) );
                    cShotsInitial.add( new Triple( coltInitialX, coltInitialY, coltInitialAngle ) );
                    coltShotCounter++;

                }
                else if (coltShotCounter == 4)
                {
                    cShots
                    .add(
                        new ColtBullet(
                            coltInitialX
                                + (int)( 20 * Math.sin( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0  ) ) )
                                + (int)( 20
                                    * Math.cos( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0 ) ) ),
                                coltInitialY
                                + (int)( 20 * Math.cos( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0  ) ) )
                                - (int)( 20
                                    * Math.sin( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0 ) ) ),
                                coltInitialAngle ) );
                cShotsInitial
                    .add(
                        new Triple(
                            coltInitialX
                                + (int)( 20 * Math.sin( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0  ) ) )
                                + (int)( 20
                                    * Math.cos( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0 ) ) ),
                                coltInitialY
                                + (int)( 20 * Math.cos( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0  ) ) )
                                - (int)( 20
                                    * Math.sin( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0 ) ) ),
                                coltInitialAngle ) );
                    cShots.add( new ColtBullet( coltInitialX, coltInitialY, coltInitialAngle ) );
                    cShotsInitial.add( new Triple( coltInitialX, coltInitialY, coltInitialAngle ) );
                    coltShotCounter++;
                }
                else if (coltShotCounter == 6)
                {
                    cShots
                    .add(
                        new ColtBullet(
                            coltInitialX
                                + (int)( 20 * Math.sin( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0  ) ) )
                                + (int)( 20
                                    * Math.cos( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0 ) ) ),
                                coltInitialY
                                + (int)( 20 * Math.cos( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0  ) ) )
                                - (int)( 20
                                    * Math.sin( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0 ) ) ),
                                coltInitialAngle ) );
                    cShotsInitial
                        .add(
                            new Triple(
                                coltInitialX
                                    + (int)( 20 * Math.sin( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0  ) ) )
                                    + (int)( 20
                                        * Math.cos( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0 ) ) ),
                                    coltInitialY
                                    + (int)( 20 * Math.cos( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0  ) ) )
                                    - (int)( 20
                                        * Math.sin( 2 * Math.PI * ( ( coltInitialAngle ) / 360.0 ) ) ),
                                    coltInitialAngle ) );
                    coltammo--;
                    coltcounter = coltcounter - 60;
                    cHeal = 0;
                    coltShot = false;
                    coltShotCounter = 0;
                }
                else
                    coltShotCounter++;





            }
            else
                cHeal++;

            if ( (colt.coltSuper() && cSuper >= 100) || coltSuper)
            {


                coltSuper = true;
                if (coltSuperCounter == 0)
                {
                    coltSuperInitialAngle = coltangle;
                    coltSuperInitialX = colt.x;
                    coltSuperInitialY = colt.y;

                    cSupers.add( new ColtSuper( coltSuperInitialX, coltSuperInitialY, coltSuperInitialAngle ) );
                    cSuperInitial.add( new Triple( coltSuperInitialX, coltSuperInitialY, coltSuperInitialAngle ) );
                    coltSuperCounter++;
                }
                else if (coltSuperCounter == 2)
                {
                    cSupers
                        .add(
                            new ColtSuper(
                                coltSuperInitialX
                                    + (int)( 20 * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                    + (int)( 20
                                        * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                    coltSuperInitialY
                                    + (int)( 20 * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                    - (int)( 20
                                        * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                    coltSuperInitialAngle ) );
                    cSuperInitial
                        .add(
                            new Triple(
                                coltSuperInitialX
                                    + (int)( 20 * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                    + (int)( 20
                                        * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                    coltSuperInitialY
                                    + (int)( 20 * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                    - (int)( 20
                                        * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                    coltSuperInitialAngle ) );

                    cSupers.add( new ColtSuper( coltSuperInitialX, coltSuperInitialY, coltSuperInitialAngle ) );
                    cSuperInitial.add( new Triple( coltSuperInitialX, coltSuperInitialY, coltSuperInitialAngle ) );
                    coltSuperCounter++;

                }
                else if (coltSuperCounter == 4)
                {
                    cSupers
                    .add(
                        new ColtSuper(
                            coltSuperInitialX
                                + (int)( 20 * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                + (int)( 20
                                    * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                coltSuperInitialY
                                + (int)( 20 * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                - (int)( 20
                                    * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                coltSuperInitialAngle ) );
                cSuperInitial
                    .add(
                        new Triple(
                            coltSuperInitialX
                                + (int)( 20 * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                + (int)( 20
                                    * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                coltSuperInitialY
                                + (int)( 20 * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                - (int)( 20
                                    * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                coltSuperInitialAngle ) );
                    cSupers.add( new ColtSuper( coltSuperInitialX, coltSuperInitialY, coltSuperInitialAngle ) );
                    cSuperInitial.add( new Triple( coltSuperInitialX, coltSuperInitialY, coltSuperInitialAngle ) );
                    coltSuperCounter++;

                }
                else if (coltSuperCounter == 6)
                {
                    cSupers
                    .add(
                        new ColtSuper(
                            coltSuperInitialX
                                + (int)( 20 * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                + (int)( 20
                                    * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                coltSuperInitialY
                                + (int)( 20 * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                - (int)( 20
                                    * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                coltSuperInitialAngle ) );
                cSuperInitial
                    .add(
                        new Triple(
                            coltSuperInitialX
                                + (int)( 20 * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                + (int)( 20
                                    * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                coltSuperInitialY
                                + (int)( 20 * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                - (int)( 20
                                    * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                coltSuperInitialAngle ) );
                    cSupers.add( new ColtSuper( coltSuperInitialX, coltSuperInitialY, coltSuperInitialAngle ) );
                    cSuperInitial.add( new Triple( coltSuperInitialX, coltSuperInitialY, coltSuperInitialAngle ) );
                    coltSuperCounter++;
                }
                else if (coltSuperCounter == 8)
                {
                    cSupers
                    .add(
                        new ColtSuper(
                            coltSuperInitialX
                                + (int)( 20 * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                + (int)( 20
                                    * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                coltSuperInitialY
                                + (int)( 20 * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                - (int)( 20
                                    * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                coltSuperInitialAngle ) );
                cSuperInitial
                    .add(
                        new Triple(
                            coltSuperInitialX
                                + (int)( 20 * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                + (int)( 20
                                    * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                coltSuperInitialY
                                + (int)( 20 * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                - (int)( 20
                                    * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                coltSuperInitialAngle ) );
                    cSupers.add( new ColtSuper( coltSuperInitialX, coltSuperInitialY, coltSuperInitialAngle ) );
                    cSuperInitial.add( new Triple( coltSuperInitialX, coltSuperInitialY, coltSuperInitialAngle ) );
                    coltSuperCounter++;
                }
                else if (coltSuperCounter == 10)
                {
                    cSupers
                    .add(
                        new ColtSuper(
                            coltSuperInitialX
                                + (int)( 20 * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                + (int)( 20
                                    * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                coltSuperInitialY
                                + (int)( 20 * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                - (int)( 20
                                    * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                coltSuperInitialAngle ) );
                    cSuperInitial
                        .add(
                            new Triple(
                                coltSuperInitialX
                                    + (int)( 20 * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                    + (int)( 20
                                        * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                    coltSuperInitialY
                                    + (int)( 20 * Math.cos( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) )
                                    - (int)( 20
                                        * Math.sin( 2 * Math.PI * ( ( coltSuperInitialAngle ) / 360.0 ) ) ),
                                    coltSuperInitialAngle ) );
                    cHeal = 0;
                    coltSuper = false;
                    coltSuperCounter = 0;
                    cSuper = 0;

                }
                else
                    coltSuperCounter++;




            }
            else
                cHeal++;


            for ( int i = 0; i < pShots.size(); i++ )
            {
                for (int j=0; j<elixirBoxes.size(); j++)
                {
                    if ((i < pShots.size()) && pShots.get( i ).checkCollisionBox( elixirX.get(j), elixirY.get(j) )
                                && ( Math.sqrt( ( ( pShots.get( i ).x - pShotsInitial.get( i ).getX() )
                                    * ( pShots.get( i ).x - pShotsInitial.get( i ).getX() ) )
                                    + ( ( pShots.get( i ).y - pShotsInitial.get( i ).getY() )
                                        * ( pShots.get( i ).y - pShotsInitial.get( i ).getY() ) ) ) < 500 ) )
                    {
                        pShotsInitial.remove( i );
                        pShots.remove( i );

                        elixirBoxes.get( j ).decreaseHealth( pam.damage );
                        if (elixirBoxes.get( j ).health <= 0)
                        {
                            Gem g = new Gem(elixirBoxes.get( j ).x, elixirBoxes.get( j ).y);
                            gems.add(g);
                            elixirBoxes.remove(j);
                            elixirX.remove(j);
                            elixirY.remove(j);
                            System.out.println( elixirBoxes.size() );

                        }

                    }
                }
            }
            for (int i=0; i<pShots.size(); i++)
            {
                if (( !pShots.get( i ).checkCollisionColt( colt ) ) && ( pShots.get( i )
                                .checkMove( blocksX, blocksY)
                                && ( Math.sqrt( ( ( pShots.get( i ).x - pShotsInitial.get( i ).getX() )
                                    * ( pShots.get( i ).x - pShotsInitial.get( i ).getX() ) )
                                    + ( ( pShots.get( i ).y - pShotsInitial.get( i ).getY() )
                                        * ( pShots.get( i ).y - pShotsInitial.get( i ).getY() ) ) ) < 500 ) ) )
                {
                    pShots.get( i ).move( blocksX, blocksY, pShotsInitial.get( i ).getZ() );
                    pShots.get( i ).draw( pShotsInitial.get( i ).getZ() );
                }
                else if ( pShots.get( i ).checkCollisionColt( colt ) )
                {
                    pSuper += 5;
                    colt.decreaseHealth(pam.damage);
                    pShotsInitial.remove( i );
                    pShots.remove( i );

                }

            }
            for ( int i = 0; i < cShots.size(); i++ )
            {
                for (int j=0; j<elixirBoxes.size(); j++)
                {
                    if ((i < cShots.size()) && cShots.get( i ).checkCollisionBox( elixirX.get(j), elixirY.get(j) )
                                && ( Math.sqrt( ( ( cShots.get( i ).x - cShotsInitial.get( i ).getX() )
                                    * ( cShots.get( i ).x - cShotsInitial.get( i ).getX() ) )
                                    + ( ( cShots.get( i ).y - cShotsInitial.get( i ).getY() )
                                        * ( cShots.get( i ).y - cShotsInitial.get( i ).getY() ) ) ) < 500 ) )
                    {
                        cShotsInitial.remove( i );
                        cShots.remove( i );

                        elixirBoxes.get( j ).decreaseHealth( colt.damage );
                        if (elixirBoxes.get( j ).health <= 0)
                        {
                            Gem g = new Gem(elixirBoxes.get( j ).x, elixirBoxes.get( j ).y);
                            gems.add(g);
                            elixirBoxes.remove(j);
                            elixirX.remove(j);
                            elixirY.remove(j);
                            System.out.println( elixirBoxes.size() );

                        }

                    }
                }
            }
            for ( int i = 0; i < cShots.size(); i++ )
            {
                if ( ( !cShots.get( i ).checkCollisionPam( pam ) ) && ( cShots.get( i )
                    .checkMove( blocksX, blocksY )
                    && ( Math.sqrt( ( ( cShots.get( i ).x - cShotsInitial.get( i ).getX() )
                        * ( cShots.get( i ).x - cShotsInitial.get( i ).getX() ) )
                        + ( ( cShots.get( i ).y - cShotsInitial.get( i ).getY() )
                            * ( cShots.get( i ).y - cShotsInitial.get( i ).getY() ) ) ) < 500 ) ) )
                {
                    cShots.get( i ).move( blocksX, blocksY, cShotsInitial.get( i ).getZ() );
                    cShots.get( i ).draw( cShotsInitial.get( i ).getZ() );
                }
                else if ( cShots.get( i ).checkCollisionPam( pam ) )
                {
                    cSuper += 7;
                    pam.decreaseHealth(colt.damage);
                    cShotsInitial.remove( i );
                    cShots.remove( i );

                }
            }
            for ( int i = 0; i < cSupers.size(); i++ )
            {
                for (int j=0; j<elixirBoxes.size(); j++)
                {
                    if ((i < cSupers.size()) && cSupers.get( i ).checkCollisionBox( elixirX.get(j), elixirY.get(j) )
                                && ( Math.sqrt( ( ( cSupers.get( i ).x - cSuperInitial.get( i ).getX() )
                                    * ( cSupers.get( i ).x - cSuperInitial.get( i ).getX() ) )
                                    + ( ( cSupers.get( i ).y - cSuperInitial.get( i ).getY() )
                                        * ( cSupers.get( i ).y - cSuperInitial.get( i ).getY() ) ) ) < 500 ) )
                    {

                        elixirBoxes.get( j ).decreaseHealth( 400 );
                        if (elixirBoxes.get( j ).health <= 0)
                        {
                            Gem g = new Gem(elixirBoxes.get( j ).x, elixirBoxes.get( j ).y);
                            gems.add(g);
                            elixirBoxes.remove(j);
                            elixirX.remove(j);
                            elixirY.remove(j);
                            System.out.println( elixirBoxes.size() );

                        }

                    }
                }
            }
            for ( int i = 0; i < cSupers.size(); i++ )
            {

                if ( ( !cSupers.get( i ).checkCollisionPam( pam ) ) && ( cSupers.get( i )
                    .checkMove( blocksX, blocksY, size )
                    && ( Math.sqrt( ( ( cSupers.get( i ).x - cSuperInitial.get( i ).getX() )
                        * ( cSupers.get( i ).x - cSuperInitial.get( i ).getX() ) )
                        + ( ( cSupers.get( i ).y - cSuperInitial.get( i ).getY() )
                            * ( cSupers.get( i ).y - cSuperInitial.get( i ).getY() ) ) ) < 630 ) ) )
                {
                    cSupers.get( i ).move( blocksX, blocksY, cSuperInitial.get( i ).getZ() );
                    cSupers.get( i ).draw( cSuperInitial.get( i ).getZ() );
                }
                else if ( cSupers.get( i ).checkCollisionPam( pam ) )
                {

                    pam.decreaseHealth( colt.superdamage );
                    cSuperInitial.remove( i );
                    cSupers.remove( i );

                }

                else if ( cSupers.get( i ).checkMove2( blocksX, blocksY, size ) >= 0 )
                {
                    int removed = cSupers.get( i ).checkMove2( blocksX, blocksY, size );
                    for ( int j = removed; j < size - 1; j++ )
                    {

                        blocksX[j] = blocksX[j + 1];
                        blocksY[j] = blocksY[j + 1];

                    }
                    size--;

                }

            }
            int gemscount = 0;
            while (gemscount < gems.size())
            {
                if (gems.get( gemscount ).checkCollision( colt ))
                {
                    colt.gems++;
                    colt.damage = (int)(colt.damage * 1.1);
                    colt.superdamage = (int)(colt.superdamage * 1.1);
                    gems.remove(gemscount);
                }
                else if (gems.get( gemscount ).checkCollision( pam ))
                {
                    pam.gems++;
                    pam.damage = (int)(pam.damage * 1.1);
                    gems.remove(gemscount);
                }
                else
                    gemscount++;
            }
            if ( pam.getHealth() <= 0 )
            {
                Window.out.color( "black" );
                Window.out.font( "Arial", 150 );
                Window.out.print( "Colt Wins!!!", Window.width() / 4, Window.height() / 2 );
                pam.setHealth( 0 );
                pam.draw( pdir, pamcounter, pamangle, pSuper );
                Window.frame( 4000 );
                break;
            }

            if ( colt.getHealth() <= 0 )
            {
                Window.out.color( "black" );
                Window.out.font( "Arial", 150 );
                Window.out.print( "Pam Wins!!!", Window.width() / 4, Window.height() / 2 );
                colt.setHealth( 0 );
                colt.draw( cdir, coltcounter, coltangle, cSuper );
                Window.frame( 4000 );
                break;
            }

            if ( coltcounter < 180 )
            {
                coltcounter++;
            }
            if ( coltcounter == 60 )
            {
                coltammo = 1;
            }
            else if ( coltcounter == 120 )
            {
                coltammo = 2;
            }
            else if ( coltcounter == 180 )
            {
                coltammo = 3;
            }

            if ( pamcounter < 180 )
            {
                pamcounter++;
            }
            if ( pamcounter == 60 )
            {
                pamammo = 1;
            }
            else if ( pamcounter == 120 )
            {
                pamammo = 2;
            }
            else if ( pamcounter == 180 )
            {
                pamammo = 3;
            }
            if ( pHeal > 100 && !pamInPoison)
            {
                pam.increaseHealth();
                pHeal = 0;
            }
            if ( cHeal > 100 && !coltInPoison )
            {
                colt.increaseHealth();
                cHeal = 0;
            }
            int pamX = pam.x;
            int pamY = pam.y;
            if (Window.key.pressed("q") && sOnMap == false && pSuper>10)
            {
                superHealth = 3000;
                pSuper = 0;
                PamSuper sup = new PamSuper(pamX, pamY);

                sup.draw(superHealth);

                pamSupX = pamX;
                pamSupY = pamY;

                sOnMap = true;
                deployedSuper = true;


                if (pSuperCoolDown%20 == 0 && superHealth > 0)
                {
                    pam.healFromStation(sup);
                }
                for(int i=0;i<cShots.size();i++)
                {
                    if (sup.checkCollision(cShots.get(i))&& superHealth > 0)
                    {
                        superHealth -= 300;
                        cShots.remove(i);
                    }
                }



            }

            if (deployedSuper == true)
            {

                PamSuper sup = new PamSuper(pamSupX, pamSupY);
                if (superHealth>0 )
                {
                    sup.draw(superHealth);
                }
                else
                {
                    sOnMap = false;
                    superHealth = 3000;
                    deployedSuper = false;


                }
                if (pSuperCoolDown%20 == 0 && superHealth > 0)
                {
                    pam.healFromStation(sup);
                }
                for(int i=0;i<cShots.size();i++)
                {
                    {
                        if (sup.checkCollision(cShots.get(i)) && superHealth > 0)
                        {
                            superHealth -= 300;
                            cShots.remove(i);

                        }
                    }
                }
                for(int i=0;i<cSupers.size();i++)
                {
                    if (sup.checkCollision(cSupers.get(i))&& superHealth > 0)
                    {
                        superHealth -= 100;
                    }
                }




            }
            for (int i=0; i<gems.size(); i++)
            {
                gems.get( i ).draw();
            }
            pSuperCoolDown++;
            Window.frame();

        }

    }

    /**
     *
     * sets window size
     */
    public static void setup()
    {
        Window.size( 1450, 900 );

    }

    /**
     *
     * draws the background including map and poison
     */
    public static void drawBackground()
    {
        Window.out.background( "tan" );
        // make the brown border
        Window.out.color( 136, 64, 26 );
        Window.out.rectangle( 0, Window.height() / 2, 55, Window.height() );
        Window.out.rectangle( Window.width(), Window.height() / 2, 55, Window.height() );
        Window.out.rectangle( Window.width() / 2, Window.height(), Window.width(), 55 );
        Window.out.rectangle( Window.width() / 2, 0, Window.width(), 55 );

        // make the black border
        Window.out.color( "maroon" );
        Window.out.rectangle( 0, Window.height() / 2, 15, Window.height() );
        Window.out.rectangle( Window.width(), Window.height() / 2, 15, Window.height() );
        Window.out.rectangle( Window.width() / 2, Window.height(), Window.width(), 15 );
        Window.out.rectangle( Window.width() / 2, 0, Window.width(), 15 );



        for ( int i = 0; i < 60; i++ )
        {
            Block b = new Block( blocksX[i], blocksY[i] );
        }

        for ( int i = 0; i < elixirBoxes.size(); i++ )
        {
            elixirBoxes.get( i ).draw();
        }


    }

}
