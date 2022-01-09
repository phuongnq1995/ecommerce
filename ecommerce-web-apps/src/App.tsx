import React from 'react';
import { BrowserRouter } from 'react-router-dom'
import Routes from './route';
import Header from './components/Header';

function App() {
  return (
    <BrowserRouter>
      <Header />
      <Routes />
    </BrowserRouter>
  );
}

export default App;
