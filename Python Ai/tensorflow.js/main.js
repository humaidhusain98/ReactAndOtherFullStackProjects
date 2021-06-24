const tf = require('@tensorflow/tfjs-node');

const values= [];
for(let i=0;i<30;i++)
{
    values[i]=Math.floor(Math.random()*100);
}

const shape = [2,5,3];//[size,width,height]

const data = tf.tensor3d(values,shape,'int32');

const vtense = tf.variable(data);

vtense.print();
 
