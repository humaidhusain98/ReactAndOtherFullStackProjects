import {useSelector,useDispatch} from 'react-redux';
import {increment,decrement, incrementByAmount} from './Redux/count';


const Counter = () => {

    const { count } =useSelector((state)=>state.count);
    const dispatch = useDispatch();

    return (
    <div className = "App">
        <h1>The count is : {count}</h1>
        <button onClick={()=> dispatch(increment())}>Increment</button>
        <button onClick={()=>dispatch(decrement())}>Decrement</button>
        <button onClick={()=>dispatch(incrementByAmount(33))}>Increment  by 33</button>
    </div> );
}
 
export default Counter;