import React from 'react'
import ReactDOM from 'react-dom'
import { BrowserRouter } from 'react-router-dom'
import './css/index.css'
import registerServiceWorker from './registerServiceWorker'
import App from './App'

ReactDOM.render(

  <BrowserRouter>
    <App/>
  </BrowserRouter>,
  document.getElementById('root')
)
registerServiceWorker()