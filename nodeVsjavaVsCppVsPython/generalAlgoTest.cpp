

#include <bits/stdc++.h> 
using namespace std; 
  
// A sample function whose time taken to 
// be measured 
void linearLoop(long x) 
{ 
    for (int i=1; i<=x; i++) 
    { 
    } 
} 


 void doubleLoop(long x)
    {
        for(long i=1;i<=x;i++)
        {
            for(long j=1;j<=x;j++)
              {
              }
        }
    }

  void printTest(long x) 
{ 
    for (int i=1; i<=x; i++) 
    { 
        cout<<"*\n";
    } 
} 
    
  
int main() 
{ 
    /* int clock_gettime( clockid_t clock_id, struct  
     timespec *tp ); The clock_gettime() function gets 
     the current time of the clock specified by clock_id,  
     and puts it into the buffer  pointed to by tp.tp  
     parameter points to a structure containing  
     atleast the following members:     
     struct timespec { 
               time_t   tv_sec;        // seconds  
               long     tv_nsec;       // nanoseconds 
           }; 
    clock id = CLOCK_REALTIME, CLOCK_PROCESS_CPUTIME_ID,  
               CLOCK_MONOTONIC ...etc 
    CLOCK_REALTIME : clock  that  measures real (i.e., wall-clock) time. 
    CLOCK_PROCESS_CPUTIME_ID : High-resolution per-process timer  
                               from the CPU. 
    CLOCK_MONOTONIC : High resolution timer that is unaffected 
                      by system date changes (e.g. NTP daemons).  */
    struct timespec start, end; 
  
    // start timer. 
    // clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &start); 
    // clock_gettime(CLOCK_REALTIME, &start); 
    clock_gettime(CLOCK_MONOTONIC, &start); 
  
    // unsync the I/O of C and C++. 
    ios_base::sync_with_stdio(false); 
  
    // linearLoop(1000000); 
    // doubleLoop(10);
    printTest(100000);
  
    // stop timer. 
    // clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &end); 
    // clock_gettime(CLOCK_REALTIME, &end); 
    clock_gettime(CLOCK_MONOTONIC, &end); 
  
    // Calculating total time taken by the program. 
    double time_taken; 
    time_taken = (end.tv_sec - start.tv_sec) * 1e9; 
    time_taken = (time_taken + (end.tv_nsec - start.tv_nsec)) * 1e-9; 
  
    cout << "10^5: " << fixed 
         << time_taken << setprecision(9); 
    cout << " sec" << endl; 
    return 0; 
} 
