import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import {Button,Alert,Breadcrumb,Card,Form,Container,Row,Col} from 'react-bootstrap';
import Register from './components/register';
import OtpLogin from './components/otplogin';
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom';
import NavbarComp from './components/NavbarComp';
import GenerateOtp from './components/generateComp';
import Dashboard from './components/dashboardComp';


function App() {
  return (
    <Router>
    <div className="App">
      <NavbarComp/>
      <header className="App-header">
        <Switch>
          <Route exact path="/register">
              <Register/>  
          </Route>
          <Route exact path="/login">
            <GenerateOtp/>
          </Route>
          <Route exact path="/verify">
            <OtpLogin mobile="+91"></OtpLogin>
          </Route>
          <Route exact path="/">
            <Dashboard/>
          </Route>
        </Switch>
      </header>
    </div>
    </Router>
  );
}

export default App;
