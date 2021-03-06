import Head from 'next/head'

export default function Home({articles}) {
  console.log(articles);
  return (
    <div >
      <Head>
        <title>WebDev News</title>
        <meta name="keywords" content="web development, programming"/>
      </Head>


     <h1>Welcome to next</h1>
      {
      articles.map(article=>(
        <h3>{article.title}</h3>
      ))
      }
    </div>
  )
}

export const getStaticProps = async () =>{
  try
  {
    const res = await fetch(`https://jsonplaceholder.typicode.com/posts?_limit=6`);
    const articles = await res.json();
  
    return {
      props:{
        articles
      }
    }
  }
  catch(e)
  {
    
  }
 

}