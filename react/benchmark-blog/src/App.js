import './App.css';
import BlogRender from './BlogRender';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import NavbarComp from './NavbarComp';
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom';
import FooterComp from './FooterComp';
import AboutComp from './AboutComp';
import HomepageComp from './HomepageComp';
import NotFound from './NotFound';
import AddNewBlog from './data/AddNewBlog';
// import {Button,Alert,Breadcrumb,Card,Form,Container,Row,Col} from 'react-bootstrap';




function App() {

 

  return (
    <Router>
    <div className="App">
      <NavbarComp/>
      <header className="App-header">
        <Switch>
          <Route exact path="/">
            <HomepageComp/>
          </Route>
          <Route exact path="/about">
            <AboutComp/>
          </Route>
          <Route exact path="/benchmarks/:id">
            <BlogRender/>
          </Route>
          <Route exact path="/add">
            <AddNewBlog/>
          </Route>
          <Route path="*">
            <NotFound/>
          </Route>
        </Switch>
      </header>
    </div>
    </Router>
  );
}

export default App;
