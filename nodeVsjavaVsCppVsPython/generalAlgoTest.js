
function linearLoop(l){
    for(i=1;i<=l;i++)
    {
    }
}

function doubleLoop(l){
    for(i=1;i<=l;i++)
    {
        for(j=1;j<=l;j++)
         {
         }
    }
}


function printTest(l){
    for(i=1;i<=l;i++)
    {
        // process.stdout.write("*");
        console.log("*");
    }
}



// console.time('10^3');
//  linearLoop(1000);
// console.timeEnd('10^3');

// console.time('10^6');
//  linearLoop(1000000);
// console.timeEnd('10^6');

// console.time('10^9');
// linearLoop(1000000000);
// console.timeEnd('10^9');

console.time('10^9');
linearLoop(1000000000);
// doubleLoop(100000);
// printTest(100000);
console.timeEnd('10^9');

//time limit exceeded takes a lot of time
// console.time('10^12');
// linearLoop(1000000000000);
// console.timeEnd('10^12');