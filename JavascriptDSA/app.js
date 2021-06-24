// const names=["Max",24,88,'Max'];


//--------------list
// console.log(names[1]);
// for(const el of names)
// {
//     console.log(el);
// }

// console.log(names.length);
// names.push('Julie');

// const julieIndex = names.findIndex(el=>el==='Julie');
// names.splice(1,2);
// console.log(names);

//-------------------sets
// const ids =new Set(names);
// ids.add('abc');
// for(const i of ids)
// {
//     console.log(i);
// }

// console.log(ids.has('abc'));
// ids.delete('abcd');
// console.log(ids);

//-----------------objects
// const person={ firstName:"Max",age:31,hobbies:['Sports','Coooking'],greet(){
//     console.log("I am a function");
// }};

// person.lastName="Husain";

// delete person.age;
// person.greet();

// --------------maps
// const resultData = new Map();

// resultData.set('average',1.53);
// resultData.set('lastResult',null);

// const germany= {name:"Germany",population:82};
// resultData.set(germany,"Hello");
// resultData.set(germany,"Bye");
// resultData.delete(germany);
// console.log(resultData);

//----------Linked list-----
class LinkedList 
{
    constructor()
    {
        this.head=null;//first element of the list
        this.tail=null;//last element of the list 
    }
    append(value)
    {
        const newNode= {value: value,next: null};
        
        if(this.tail)
        {
            this.tail.next= newNode;
        }
        this.tail=newNode;
        if(!this.head)
        {
            this.head=newNode;
        }
    }

    prepend(value)
    {
        const newNode= {value: value,next: null};
        newNode.next=this.head;
        this.head=newNode;
        if(!this.tail)
        {
            this.tail=newNode;
        }
        
    }

    toArray()
    {
        const elements=[];
        let currNode=this.head;
        while(currNode)
        {
            elements.push(currNode);
            currNode = currNode.next;
        }
        return elements;


    }
}

const linkedlist1=new LinkedList();
linkedlist1.append(1);
linkedlist1.append('Hi');
linkedlist1.append(1);
linkedlist1.append('Hi');
linkedlist1.append(1);
linkedlist1.append('Hi');
linkedlist1.append(1);
linkedlist1.append('Hi');
linkedlist1.prepend('First Value');


console.log(linkedlist1.toArray());