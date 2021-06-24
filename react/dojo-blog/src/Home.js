import BlogList from './BlogList';
import useFetch from './useFetch';



const Home = () => {
    const {data,isPending,error} = useFetch('http://localhost:8000/blogs');

    return (  
        <div className="home">
            { error && <div>{error}</div>}
            {isPending && <h2>Loading...</h2>}
            {data && <BlogList blogs={data} title="All Benchmarks"  />}
            
        </div>
    );
}
 
export default Home;