import React from 'react'
import ReactDOM from 'react-dom'
import { BrowserRouter } from 'react-router-dom'
import './css/index.css'
import registerServiceWorker from './registerServiceWorker'
import App from './App'
import store from './store/index'
import {Provider} from 'react-redux'

ReactDOM.render(
  <Provider store={store}>
    <BrowserRouter>
      <App/>
    </BrowserRouter>
  </Provider>,
  document.getElementById('root')
)
registerServiceWorker()