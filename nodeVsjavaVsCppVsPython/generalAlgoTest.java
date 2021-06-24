public class generalAlgoTest
{
    static void linearLoopfunc(long x)
    {
        for(long i=1;i<=x;i++)
        {
        }
    }

    static void doubleLoop(long x)
    {
        for(long i=1;i<=x;i++)
        {
            for(long j=1;j<=x;j++)
              {
              }
        }
    }

    static void printTest(long x)
    {
        for(long i=1;i<=x;i++)
        {
            System.out.println("*");
        }
    }

    

    public static void main(String args[])
    {
        long start,finish,timeElapsed;

        start = System.currentTimeMillis();
        linearLoopfunc(1000000000);
        // doubleLoop(100000);
        // printTest(100000);
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;
        System.out.println("10^9 : "+timeElapsed+"ms");

        // start = System.currentTimeMillis();
        // linearLoopfunc(1000000);
        // finish = System.currentTimeMillis();
        // timeElapsed = finish - start;
        // System.out.println("10^6 : "+timeElapsed+"ms");

        // start = System.currentTimeMillis();
        // linearLoopfunc(1000000000);
        // finish = System.currentTimeMillis();
        // timeElapsed = finish - start;
        // System.out.println("10^9 : "+timeElapsed+"ms");

        // start = System.currentTimeMillis();
        // linearLoopfunc(10000000000l);
        // finish = System.currentTimeMillis();
        // timeElapsed = finish - start;
        // System.out.println("10^10 : "+timeElapsed+"ms");

        //10^12 time limit exceeded


    }

}