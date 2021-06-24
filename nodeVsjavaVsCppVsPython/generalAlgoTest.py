import time

def linearLoop(x):
    for i in range(x):
        pass




start_time = time.time();
linearLoop(10000000000);
end_time = time.time();
print("10^9 :"+str(end_time-start_time)+"seconds");
